package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import game.abstraction.Bricks;
import game.ball.*;
import game.bricks.BrickLoader;
import game.objects.Paddle;
import game.particle.ParticleManager;
import game.powerup.AvailableItems;
import game.powerup.PowerupManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import game.powerup.FallingItem;
///  Manager

public class GameManager {
    private final int widthScreen, heightScreen;

    /// Ball, Paddle, Brick,...
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;
    private PowerupManager powerupManager;
    private List<ItemsForBall> availableItems;
    private List<FallingItem> fallingItems;
    private List<Ball> pendingBallsToAdd;

    /// Game statistics
    private final App app;
    private boolean gameOver;
    private boolean gamePaused = false;

    private boolean isAiming = false;

    public GameManager(int widthScreen, int heightScreen, App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.app = app;
        this.availableItems = AvailableItems.getAvailableItems();
        this.powerupManager = new PowerupManager();
        this.fallingItems = new ArrayList<>();
        this.pendingBallsToAdd = new ArrayList<>();
    }
    private static  double lastUpdateTime = 0;
    public static double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
        }
        double dt = (currentTime - lastUpdateTime) / 1_000_000_000.0;
        lastUpdateTime = currentTime;
        if (dt < 0.001 || dt > 0.05) {
            dt = 0.016; // khoảng 60 FPS
        }
        return dt;
    }

    // Check collisions
    private void checkCollision() {
        for (Iterator<Ball> BALL = balls.iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();
            ball.collides(ball);
            if (ball.getRect().intersects(paddle.getBounds())) {
                ball.collides(paddle);
                powerupManager.handlePaddleCollision(ball, this.balls, this.bricks, this.pendingBallsToAdd, this.paddle);
            }
            for (Iterator<Bricks> BRICK = bricks.iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();
                double dame = ball.getDamege();
                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    brick.hit(dame);
                    ball.setMaxcollision(ball.getMaxcollision()-1);
                    ball.collides(brick);
                    powerupManager.handleBrickCollision(ball, this.balls, bricks, pendingBallsToAdd);
                    AssetManager.playSound("brick_break");
                    if (brick.isBroken()) {
                        System.out.println("break brick");
                        AssetManager.playSound("ball_collide");
                        BRICK.remove();
                        GameContext.getInstance().addScore(brick.getPoint());
                        double brickCenterX = brick.getX() + brick.getWidth() / 2;
                        double brickCenterY = brick.getY() + brick.getHeight() / 2;
                        FallingItem newItem = FallingItem.createRandomItem( brickCenterX, brickCenterY);
                        if (newItem != null) {
                            this.fallingItems.add(newItem);
                        }
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6,
                                brick.getColor());
                    }
                if(ball.getMaxcollision() <= 0) {
                    BALL.remove();
                }
                    break;
                }
            }
            /// Game over
            if (ball.getY() > heightScreen) {
                BALL.remove();
            }
        }
        Iterator<FallingItem> itemIt = fallingItems.iterator();
        while (itemIt.hasNext()) {
            FallingItem item = itemIt.next();
            if (paddle.getBounds().intersects(item.getBounds())) {
                powerupManager.addPowerup(item.getItemType(), this.balls, this.bricks, this.pendingBallsToAdd);
                ;
                itemIt.remove();
                AssetManager.playSound("powerup_pickup");
            } else if (item.getY() > this.heightScreen) {
                itemIt.remove();
            }
        }
        if (bricks.stream().allMatch(brick -> !brick.isDestroyable())) {
            GameContext.getInstance().nextLevel();
            reset();
        }
        if (balls.isEmpty()) {
            GameContext.getInstance().resetLevel();
            gameOver = true;
            app.switchToGameOverScene(GameContext.getInstance().getCurrentScore());
        }
        balls.addAll(pendingBallsToAdd);
        pendingBallsToAdd.clear();
    }
    public void reset() {
        this.fallingItems = new ArrayList<>();
        paddle = new Paddle();
        balls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            balls.add(new NormalBall(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
        }
        bricks = BrickLoader.loadBricks();
        ParticleManager.getInstance().clear();
        this.powerupManager = new PowerupManager();
        this.pendingBallsToAdd = new ArrayList<>();
        gameOver = false;
        gamePaused = false;
        ParticleManager.setLastUpdateTime();
    }

    private void shootBall() {
        if (balls.isEmpty())
            return;
            for (Ball ball : balls) {
                if (!ball.isRunning) {
                    ball.launchBall();
                    ball.setRunning(true);
                    break;
                }
            }
    }
    public void update() {
        for(Ball ball : balls){
            if(ball.getRadius() >= 25){
                ball.setRadius(25);
            }
            if(ball.getSpeedball() >= 15){
                ball.setSpeedball(15);
            }
        }
        if (gamePaused == true) {
            return;
        }
        // check game over
        if (gameOver == true) {
            return;
        }
        paddle.update();
        for (Ball ball : balls) {
            ball.update(paddle);
            ball.setPlayerAiming(isAiming);
        }
        checkCollision();
        Iterator<Bricks> cleanupIt = bricks.iterator();
        while (cleanupIt.hasNext()) {
            Bricks brick = cleanupIt.next();
            if (brick.isBroken()) {
                cleanupIt.remove();
            }
        }
        if (bricks.isEmpty() == true) {
            gameOver = true;
            return;
        }
        double deltaTime = calculateDeltaTime();
        for (FallingItem item : fallingItems) {
            item.update(deltaTime);
        }
        ParticleManager.getInstance().update(deltaTime);
        // update particles
        powerupManager.update(deltaTime, this.balls);
    }
    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, widthScreen, heightScreen); 
        paddle.render(gc);

        for (Ball ball : balls) {
            ball.render(gc);
        }

        for (Bricks brick : bricks) {
            brick.render(gc);
        }
        for (FallingItem item : fallingItems) {
            item.render(gc);
        }
        ParticleManager.getInstance().render(gc);
    }


    /// HANDLE KEY EVENT
    public void handleKeyPress(KeyEvent key) {
        paddle.handleKeyPressed(key);

        if (key.getCode() == KeyCode.SPACE && !isAiming) {
            this.isAiming = true;
        }
    }

    public void handleKeyRelease(KeyEvent key) {
        paddle.handleKeyReleased(key);
        if (key.getCode() == KeyCode.SPACE && isAiming) {
            this.isAiming = false;
            shootBall();
        }
    }
    public int getScore() {
        return GameContext.getInstance().getCurrentScore();
    }
    public static String getSkin() {
        String skin = "";
        return skin;
    }
    public static void setSkin(String skin) {
        skin = skin;
    }
}

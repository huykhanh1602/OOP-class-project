package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.abstraction.Bricks;
import game.ball.Ball;
import game.bricks.BrickLoader;
import game.objects.Paddle;
import game.particle.ParticleManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

///  Manager

public class GameManager {
    private int widthScreen, heightScreen;

    /// Ball, Paddle, Brick,...
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;

    /// Game statistics
    private final App app;
    private boolean gameOver;
    private boolean gamePaused = false;
    private int currentLevel;

    private boolean isAiming = false;

    private int level = 1;

    /// Time tracking for particle updates
    private long lastUpdateTime = 0;

    public GameManager(int widthScreen, int heightScreen, App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.app = app;
    }

    // Check collisions
    private void checkCollision() {
        for (Iterator<Ball> BALL = balls.iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();

            ball.collides(ball);
            ball.collides(paddle);

            for (Iterator<Bricks> BRICK = bricks.iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();

                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    ball.collides(brick);
                    brick.hit(10);
                    if (brick.isBroken()) {
                        double brickCenterX = brick.getX() + brick.getWidth() / 2;
                        double brickCenterY = brick.getY() + brick.getHeight() / 2;
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6, brick.getColor());
                        BRICK.remove();
                        scorePlayer.addScore(brick.getPoint());
                    }
                        break;
                }
            }

            /// Game over
            if (ball.getY() > heightScreen) {
                BALL.remove();
                if (balls.size() == 0) {
                    level = 1;
                    app.switchToGameOverScene(scorePlayer.getScore());
                }
            }

            if (bricks.stream().allMatch(brick -> !brick.isDestroyable())) {
                level++;
                reset("level"+level);
            }
        }
    }


    public void reset(String path) {
        paddle = new Paddle();
        balls = new ArrayList<Ball>();
        for (int i = 0; i < 2; i++) {
            balls.add(new Ball(paddle.getX() + paddle.getWidth() / 2.0f, paddle.getY() - paddle.getHeight()));
        }
        bricks = BrickLoader.loadBricks(AssetManager.getLevel(path));
        ParticleManager.getInstance().clear();// clear particles when reset game
        gameOver = false;
        // reset particle time for particle updates
        ParticleManager.setLastUpdateTime();
    }

    private void shootBall() {
        if (balls.isEmpty())
            return;

        if (!balls.isEmpty()) {
            for(Iterator<Ball> BALL = balls.iterator(); BALL.hasNext();) {
                Ball ball = BALL.next();
                if(!ball.isRunning) {
                    ball.launchBall();
                    ball.setRunning(true);
                    break;
                }
            }
        }
    }

    public void update() {

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
        ParticleManager.getInstance().update();
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
    }


    /// HANDLE KEY EVENT
    public void handleKeyPress(KeyEvent key) {
        paddle.handleKeyPressed(key);

        if (key.getCode() == KeyCode.SPACE && !isAiming) {
            this.isAiming = true;
        }

        if (key.getCode() == KeyCode.R) {
            level = 1;
            reset("test");
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
}

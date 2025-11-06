package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.abstraction.Bricks;
import game.ball.Ball;
import game.ball.NormalBall;
import game.bricks.BrickLoader;
import game.objects.Paddle;
import game.particle.ParticleManager;
import javafx.scene.canvas.GraphicsContext;
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

    /// Aiming Arc
    private boolean isAiming = false;
    private double aimAngle = 30;
    private boolean aimIncrease = true;
    private final double aimSpeed = 0.3;
    private final double ainMin = 30;
    private final double ainMax = 150;

    /// Time tracking for particle updates
    private long lastUpdateTime = 0;

    public GameManager(int widthScreen, int heightScreen, App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        // bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        this.app = app;
    }

    // Calculate delta time for particle update
    private double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
        }
        double dt = (currentTime - lastUpdateTime) / 1_000_000_000.0;
        lastUpdateTime = currentTime;

        //
        if (dt < 0.001 || dt > 0.05) {
            dt = 0.016;
        }
        return dt;
    }

    // Check collisions
    private void checkCollision() {
        for (Iterator<Ball> BALL = balls.iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();

            ball.collides(ball);
            ball.collides(paddle);

            for (Iterator<Bricks> BRICK = bricks.iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();
                double dame = ball.getDamege();
                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    brick.hit(dame);
                    ball.setMaxcollision(ball.getMaxcollision()-1);
                    ball.collides(brick);
                    if (brick.isBroken()) {
                        System.out.println("break brick");
                        AssetManager.playSound("brick_break");
                        BRICK.remove();
                        GameContext.getInstance().addScore(brick.getPoint());

                        // Break particle
                        double brickCenterX = brick.getX() + brick.getWidth() / 2;
                        double brickCenterY = brick.getY() + brick.getHeight() / 2;

                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6,
                                brick.getColor());
                    }
                if(ball.getMaxcollision() <= 0) {
                    ball.isRunning = false;
                }
                    break; // tránh va chạm nhiều brick 1 frame
                }
            }

            /// Game over
            if (ball.getY() > heightScreen) {
                BALL.remove();

            }
        }
    }

    // Khởi tạo lại game
    public void reset() {
        this.currentLevel = GameContext.getInstance().getCurrentLevel();

        paddle = new Paddle(widthScreen / 4, heightScreen * 7 / 8 - 30);
        balls = new ArrayList<Ball>();
        for (int i = 0; i < 3; i++) {
            balls.add(new NormalBall(paddle.getX() + paddle.getWidthPaddle() / 2, paddle.getY() - paddle.getHeightPaddle()));
        }

        bricks = BrickLoader.loadBricks();

        // clear particles when reset game
        ParticleManager.getInstance().clear();

        gameOver = false;
        gamePaused = false;
        lastUpdateTime = 0;// reset particle time for particle updates
    }

    private void shootBall() {
        if (isAiming || balls.isEmpty())
            return;
        double rad = Math.toRadians(aimAngle);
        double dx = Math.cos(rad);
        double dy = -Math.sin(rad);

        if (!balls.isEmpty()) {
            Ball ball = balls.get(0);
            ball.launchBall(dx, dy);
            ball.setRunning(true);
            aimAngle = ainMin;
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

        // check level complete
        if (bricks.isEmpty() == true) {
            gameOver = true;
            app.levelWon();
            return;
        }

        if (balls.isEmpty() == true) {
            app.switchToGameOverScene(GameContext.getInstance().getCurrentScore());
        }

        // update game objects
        paddle.update();
        for (Ball ball : balls) {
            ball.update(paddle);
        }
        if (isAiming) {
            if (aimIncrease) {
                aimAngle += aimSpeed;
            } else {
                aimAngle -= aimSpeed;
            }

            if (aimAngle > ainMax) {
                aimAngle = ainMax;
                aimIncrease = false;
            } else if (aimAngle < ainMin) {
                aimAngle = ainMin;
                aimIncrease = true;
            }
        }
        checkCollision();

        double deltaTime = calculateDeltaTime();
        ParticleManager.getInstance().update(deltaTime);
        // update particles
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, widthScreen, heightScreen);

        paddle.render(gc);

        if (balls.stream().noneMatch(Ball::isRunning) && isAiming) {
            double startX = paddle.getX() + paddle.getWidthPaddle() / 2;
            double startY = paddle.getY() - 7.5;
            double length = 100;

            double endX = startX + Math.cos(Math.toRadians(aimAngle)) * length;
            double endY = startY - Math.sin(Math.toRadians(aimAngle)) * length;

            gc.setStroke(Color.YELLOW);
            gc.setLineWidth(2);
            gc.strokeLine(startX, startY, endX, endY);
        }
        for (Ball ball : balls) {
            ball.render(gc);
        }
        if (bricks == null) {
            System.out.println("bricks is null");
        }
        for (Bricks brick : bricks) {
            brick.render(gc);
        }
        ParticleManager.getInstance().render(gc);

    }

    /// HANDLE KEY EVENT
    public void handleKeyPress(KeyEvent key) {
        paddle.handleKeyPressed(key);

        if (key.getCode() == KeyCode.SPACE && !isAiming) {
            isAiming = true;
        }
    }

    public void handleKeyRelease(KeyEvent key) {
        paddle.handleKeyReleased(key);
        if (key.getCode() == KeyCode.SPACE && isAiming) {
            isAiming = false;
            shootBall();
        }
    }

    public int getScore() {
        return GameContext.getInstance().getCurrentScore();
    }
}

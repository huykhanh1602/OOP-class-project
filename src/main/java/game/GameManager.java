package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.ball.Ball;
import game.bricks.BrickLoader;
import game.bricks.Bricks;
import game.objects.Paddle;
import game.particle.ParticleManager;
import game.score.ScoreManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

///  Manager

public class GameManager {
    private int widthScreen, heightScreen; // 600 * 600

    /// Ball, Paddle, Brick,...
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;

    /// Game statistics
    private ScoreManager scorePlayer = new ScoreManager();
    private boolean gameOver;
    private final App app;

    /// Aiming Arc
    private boolean isAiming = false;
    private double aimAngle = 30;
    private boolean aimIncrease = true;
    private final double aimSpeed = 1;
    private final double ainMin = 30;
    private final double ainMax = 150;

<<<<<<< Updated upstream
=======
    /// Time tracking for particle updates
    private long lastUpdateTime = 0;

    private int level = 1;

>>>>>>> Stashed changes
    public GameManager(int widthScreen, int heightScreen, App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        this.app = app;
        reset(AssetManager.getLevel("level"+level)); // Initialize game state
    }

    private void checkCollision() { // Check collisions
        for (Iterator<Ball> BALL = balls.iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();

            ball.collides(ball);
            ball.collides(paddle);

            for (Iterator<Bricks> BRICK = bricks.iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();

                if (ball.intersects(brick.getRectBrick())) {
                    ball.collides(brick);
                    brick.hit();
                    if (brick.isBroken()) {
<<<<<<< Updated upstream
                        double brickCenterX = brick.getX() + brick.getWidth() / 2;
                        double brickCenterY = brick.getY() + brick.getHeight() / 2;
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6);

                        System.out.println("break brick");
=======
>>>>>>> Stashed changes
                        BRICK.remove();
                        scorePlayer.addScore(brick.getPoint());

                        // Break particle
<<<<<<< Updated upstream

                    }
=======
                        double brickCenterX = brick.getX() + brick.getWidth() / 2;
                        double brickCenterY = brick.getY() + brick.getHeight() / 2;
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6);
>>>>>>> Stashed changes

                        break;
                    }
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
                reset(AssetManager.getLevel("level"+level));
            }
        }
    }

<<<<<<< Updated upstream
    public void reset() { // Khởi tạo lại game
=======
    // Khởi tạo lại game
    public void reset(String path) {
>>>>>>> Stashed changes
        paddle = new Paddle(widthScreen / 4, heightScreen * 7 / 8 - 30);
        balls = new ArrayList<Ball>();
        for (int i = 0; i < 1; i++) {
            balls.add(new Ball(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
        }
<<<<<<< Updated upstream
        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
=======
        bricks = BrickLoader.loadBricks(path);
        ParticleManager.getInstance().clear();// clear particles when reset game
>>>>>>> Stashed changes
        gameOver = false;

    }

    private void shootBall() {
        if (isAiming || balls.isEmpty())
            return;
        double rad = Math.toRadians(aimAngle);
        double vx = Math.cos(rad);
        double vy = -Math.sin(rad);

        if (!balls.isEmpty()) {
            Ball ball = balls.get(0);
            ball.launchBall(vx, vy);
            ball.setRunning(true);
            aimAngle = ainMin;
        }
    }

    public void update() {
        if (gameOver) {
            return;
        }

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
        // update particles
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, widthScreen, heightScreen);

        paddle.render(gc);

        if (balls.stream().noneMatch(Ball::isRunning) && isAiming) {
            double startX = paddle.getX() + paddle.getWidth() / 2;
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

        // gc.setFill(Color.LIGHTGRAY); // Che phần bóng rơi
        // gc.fillRect(0, heightScreen * 9 / 10 + 20, widthScreen * 3 / 4, heightScreen - heightScreen * 9 / 10 - 20);

        // gc.setFill(Color.DARKGRAY);
        // gc.fillText("Score: " + scorePlayer.getScore(), widthScreen * 3 / 4 + 60, heightScreen * 1 / 8);// DRAW SCORE

    }

    /// HANDLE KEY EVENT
    public void handleKeyPress(KeyEvent key) {
        paddle.handleKeyPressed(key);

        if (key.getCode() == KeyCode.SPACE && !isAiming) {
            isAiming = true;
        }

        if (key.getCode() == KeyCode.R) {
            level = 1;
            reset(AssetManager.getLevel("level"+level));
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
        return scorePlayer.getScore();
    }
}

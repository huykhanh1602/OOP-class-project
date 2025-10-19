package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import vnu.edu.vn.App;
import vnu.edu.vn.game.ball.Ball;
import vnu.edu.vn.game.bricks.BrickLoader;
import vnu.edu.vn.game.bricks.Bricks;
import vnu.edu.vn.game.objects.Paddle;
import vnu.edu.vn.game.particle.ParticleManager;
import vnu.edu.vn.game.score.ScoreManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

///  Manager

public class GameManager {
    private int widthScreen, heightScreen; // 600 * 600

    /// Ball, Paddle, Brick,...
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;

    /// Thông số game
    private ScoreManager scorePlayer = new ScoreManager();
    private boolean gameOver;
    private final App app;

    /// Aiming Arc
    private boolean isAiming = false;
    private double aimAngle = 30;
    private boolean aimIncrease = true;
    private final double aimSpeed = 0.3;
    private final double ainMin = 30;
    private final double ainMax = 150;

    public GameManager(int widthScreen, int heightScreen, App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
        // bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        this.app = app;
        reset(); // Khởi tạo game
    }

    private void checkCollision() { // Kiểm tra va chạm
        for (Iterator<Ball> BALL = balls.iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();

            ball.collides(ball);
            ball.collides(paddle);

            for (Iterator<Bricks> BRICK = bricks.iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();

                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    brick.hit();
                    ball.collides(brick);
                    if (brick.isBroken()) {
                        double brickCenterX = brick.getX() + brick.getWidth() / 2;
                        double brickCenterY = brick.getY() + brick.getHeight() / 2;
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6);

                        System.out.println("break brick");
                        BRICK.remove();
                        scorePlayer.addScore(brick.getPoint());

                        // Break particle

                    }

                    break; // tránh va chạm nhiều brick 1 frame
                }
            }

            /// Game over
            if (ball.getY() > heightScreen) {
                BALL.remove();
                if (balls.size() == 0) {
                    app.switchToGameOverScene(scorePlayer.getScore());
                }
            }
        }
    }

    public void reset() { // Khởi tạo lại game
        paddle = new Paddle(widthScreen / 4, heightScreen * 7 / 8 - 30);
        balls = new ArrayList<Ball>();
        for (int i = 0; i < 10; i++) {
            balls.add(new Ball(paddle.getX() + paddle.getWidthPaddle() / 2, paddle.getY() - paddle.getHeightPaddle()));
        }
        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        gameOver = false;

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

        gc.setFill(Color.LIGHTGRAY); // Che phần bóng rơi
        gc.fillRect(0, heightScreen * 9 / 10 + 20, widthScreen * 3 / 4, heightScreen - heightScreen * 9 / 10 - 20);

        gc.setFill(Color.DARKGRAY);
        gc.fillText("Score: " + scorePlayer.getScore(), widthScreen * 3 / 4 + 60, heightScreen * 1 / 8);// DRAW SCORE

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
        return scorePlayer.getScore();
    }
}

package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    // x:10 y:20 width:600*3/4 height:600*9/10 khu vực va chạm bóng

    /// Ball, Paddle, Brick,...
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;

    /// Thông số game
    private ScoreManager scorePlayer = new ScoreManager();
    private boolean gameOver;
    private final App app;

    // Hiệu ứng va chạm
    private long lastUpdateTime = 0;

    private double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
        }
        double dt = (currentTime - lastUpdateTime) / 1_000_000_000.0;
        lastUpdateTime = currentTime;
        return dt;
    }

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
                        BRICK.remove();
                        scorePlayer.addScore(brick.getPoint());

                        // Break particle
                        double brickCenterX = brick.getX() + brick.getWidthBrick() / 2;
                        double brickCenterY = brick.getY() + brick.getHeightBrick() / 2;
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 6);
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
            balls.add(new Ball(paddle.getX() + paddle.getWidthPaddle() / 2,
                    paddle.getY() - paddle.getHeightPaddle() - 3));
        }
        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");

        ParticleManager.getInstance().clear();// clear particles when reset game

        gameOver = false;

        lastUpdateTime = 0; // reset particale time
    }

    public void update() {
        if (gameOver) {
            return;
        }

        paddle.update();

        for (Ball ball : balls) {
            ball.update(paddle);
        }

        checkCollision();

        double deltaTime = calculateDeltaTime();
        ParticleManager.getInstance().update(deltaTime);
        // update particles
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, widthScreen, heightScreen);

        paddle.render(gc);
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
        if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
            paddle.moveLeft();
        } else if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
            paddle.moveRight();
        } else if (key.getCode() == KeyCode.SPACE) {
            for (Ball ball : balls) {
                if (!ball.isRunning()) {
                    ball.launchBall();
                    break;
                }
            }
        }
    }

    public void handleKeyRelease(KeyEvent key) {
        if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A) {
            paddle.moveLeft();
        } else if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D) {
            paddle.moveRight();
        }
    }

    public int getScore() {
        return scorePlayer.getScore();
    }
}

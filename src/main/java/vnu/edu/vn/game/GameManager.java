package vnu.edu.vn.game;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import vnu.edu.vn.App;
import vnu.edu.vn.game.ball.Ball;
import vnu.edu.vn.game.Paddle;
import vnu.edu.vn.game.bricks.BrickLoader;
import vnu.edu.vn.game.bricks.Bricks;
import vnu.edu.vn.game.score.Score;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


///  Manager

public class GameManager {
    private int widthScreen, heightScreen;

    /// Ball, Paddle, Brick,...
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;
    private Background background;

    ///Thông số game
    private Score scorePlayer = new Score();
    private boolean gameOver;
    private final App app;


    public GameManager(int widthScreen, int heightScreen,  App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
//        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        this.app = app;
        reset();                    //Khởi tạo game
    }

    private void checkCollision() {                                         //Kiểm tra va chạm
        for(Iterator<Ball> BALL = balls.iterator(); BALL.hasNext(); ) {
            Ball ball = BALL.next();

            ball.collides(ball);
            ball.collides(paddle);

            for (Iterator<Bricks> BRICK = bricks.iterator(); BRICK.hasNext(); ) {
                Bricks brick = BRICK.next();

                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    brick.hit();
                    ball.collides(brick);
                    if (brick.isBroken()) {
                        BRICK.remove();
                        scorePlayer.addScore(brick.getAmount());
                    }

                    break; // tránh va chạm nhiều brick 1 frame
                }
            }

            /// Game over
            if (ball.getY() > heightScreen*5/6+40) {
                BALL.remove();
                if(balls.size() == 0) {
                    app.switchToGameOver(scorePlayer.getScore());
                }
            }
        }
    }


    public void reset() {                                                       //Khởi tạo lại game
        paddle = new Paddle(widthScreen/4, heightScreen*7/8-30);
        balls = new ArrayList<Ball>();
        for(int i = 0; i < 5; i++) {
            balls.add(new Ball(paddle.getX() + paddle.getWidthPaddle() / 2, paddle.getY() - paddle.getHeightPaddle()-1));
        }
        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        background = new Background(600 * 3 / 4, heightScreen*8/9);

        gameOver = false;
    }


    public void update() {
        if (gameOver) return;

        paddle.update();

        for (Ball ball : balls) {
            ball.update();
        }

        checkCollision();
    }

    public void render(GraphicsContext gc) {
        background.render(gc);                          //Vị trí chơi chính

        paddle.render(gc);
        for(Ball ball : balls) {
            ball.render(gc);
        }
        if(bricks == null) {
            System.out.println("bricks is null");
        }
        for (Bricks brick : bricks) {
            brick.render(gc);
        }

        gc.setFill(Color.DARKGRAY);
        gc.fillText("Score: " + scorePlayer.getScore(), widthScreen*3/4+60, heightScreen*1/8);              //DRAW SCORE
    }

    /// HANDLE KEY EVENT
    public void handleKeyPress(KeyEvent key) {
        paddle.handleKeyPressed(key);
        for(Ball ball : balls) {
            ball.handleKeyPressed(key);
        }
    }

    public void handleKeyRelease(KeyEvent key) {
        paddle.handleKeyReleased(key);
    }
}

package vnu.edu.vn.game;


import javafx.animation.AnimationTimer;
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

    /// Ball, Paddle, Brick
    private Paddle paddle;
    private Ball ball;
    private List<Bricks> bricks;
    private Score scorePlayer = new Score();
    private boolean gameOver;
    private final App app;


    public GameManager(int widthScreen, int heightScreen,  App app) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
//        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        this.app = app;
        reset();
    }

    private void checkCollision() {
        if (ball.getX() <= 10 || ball.getX() + ball.getRadius() * 2 >= widthScreen*3/4) ball.bounceX();
        if (ball.getY() <= 20) ball.bounceY();


        for (Iterator<Bricks> iterator = bricks.iterator(); iterator.hasNext();) {
            Bricks brick = iterator.next();

            if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                brick.hit();
                ball.bounceY();
                if (brick.isBroken()) {
                    iterator.remove();
                    scorePlayer.addScore(brick.getAmount());
                }
                break; // tránh va chạm nhiều brick 1 frame
            }
        }

        // Game over
        if (ball.getY() > heightScreen*5/6+40) {
            app.switchToGameOver(scorePlayer.getScore());
        }
    }





    public void reset() {
        paddle = new Paddle(widthScreen/2, heightScreen*7/8-30);
        ball = new Ball(paddle.getX() + paddle.getWidthPaddle()/2, paddle.getY()-paddle.getHeightPaddle());
        bricks = BrickLoader.loadBricks("/vnu/edu/vn/game/bricks/level1.txt");
        gameOver = false;
    }


    public void update() {
        if (gameOver) return;

        if (ball.collides(paddle)) {
            ball.bounce();
        }


        paddle.update();
        ball.update();
        checkCollision();

//        if (ball.isOutOfBounds()) {
//            gameOver = true;
//            app.switchToGameOver(scorePlayer.getScore());
//        }
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(10, 20, widthScreen*3/4-10, heightScreen*8/9);

        paddle.render(gc);
        ball.render(gc);
        if(bricks == null) {
            System.out.println("bricks is null");
        }
        for (Bricks brick : bricks) {
            brick.render(gc);
        }

        gc.setFill(Color.DARKGRAY);
        gc.fillText("Score: " + scorePlayer.getScore(), widthScreen*3/4+60, heightScreen*1/8);
    }

    /// HANDLE KEY EVENT
    public void handleKeyPress(KeyEvent e) {
        paddle.handleKeyPressed(e);
    }

    public void handleKeyRelease(KeyEvent e) {
        paddle.handleKeyReleased(e);
    }
}

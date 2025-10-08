package vnu.edu.vn.game;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;


///  Manager

public class GameManager {
    private int widthScreen, heightScreen;

    private Paddle paddle;
    private Ball ball;
    private List<Bricks> bricks = new ArrayList<>();
    private Background background;

    public GameManager(int widthScreen, int heightScreen) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;

        paddle = new Paddle(widthScreen / 4, heightScreen*4/5 - 20, widthScreen*3/4);
        ball = new Ball(widthScreen / 4, heightScreen / 2 - 40, widthScreen*3/4, heightScreen*5/6);
        background = new Background(widthScreen*3/4, heightScreen-70);


        // Create some bricks
        int i = 0;
        while (i * 30 + 10 < widthScreen/2 - 40) {
            i++;
            bricks.add(new Bricks(30 * i, 50));
        }
    }

    public void update() {
        paddle.update();
        ball.update();

        // Collision with paddle
        if (ball.Collusion(paddle)) {
            ball.bounceY();
        }

        // Collision with bricks
        for (Bricks brick : bricks) {
            if (!brick.isDestroyed() && ball.Collusion(brick)) {
                brick.destroy();
                ball.bounceY();
            }
        }
    }

    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, widthScreen, heightScreen);
        background.render(gc);
        paddle.render(gc);
        ball.render(gc);
        bricks.forEach(brick -> brick.render(gc));
    }

    public void handleInput(Scene scene) {
        scene.setOnKeyPressed(e -> paddle.onKeyPressed(e.getCode()));
        scene.setOnKeyReleased(e -> paddle.onKeyReleased(e.getCode()));
    }
}

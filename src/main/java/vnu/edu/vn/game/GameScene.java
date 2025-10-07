package vnu.edu.vn.game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.animation.AnimationTimer;


/// Setup game loop

public class GameScene {

    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager gameManager;
    private AnimationTimer timer;

    public GameScene(int widthScreen, int heightScreen) {

        canvas = new Canvas(widthScreen, heightScreen);
        gc = canvas.getGraphicsContext2D();

        gameManager = new GameManager(widthScreen, heightScreen);

        scene = new Scene(new StackPane(canvas));

        gameManager.handleInput(scene);

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameManager.update();
                gameManager.render(gc);
            }
        };
    }


    public Scene getScene() {
        return scene;
    }

    public void start() {
        timer.start();
    }
}

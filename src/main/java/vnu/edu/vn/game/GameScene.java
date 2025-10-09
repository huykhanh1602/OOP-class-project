package vnu.edu.vn.game;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;
import vnu.edu.vn.App;


/// Setup game loop

public class GameScene extends StackPane {

    private Canvas canvas;
    private GraphicsContext gc;
    private GameManager gameManager;

    public GameScene(int widthScreen, int heightScreen, App app) {
        this.canvas = new Canvas(widthScreen, heightScreen);
        this.getChildren().add(canvas);

        this.gc = canvas.getGraphicsContext2D();
        this.gameManager = new GameManager(widthScreen, heightScreen, app);

        setupInput();
        startGameLoop();
    }

    private void setupInput() {
        canvas.setFocusTraversable(true);

        canvas.setOnKeyPressed(e -> gameManager.handleKeyPress(e));
        canvas.setOnKeyReleased(e -> gameManager.handleKeyRelease(e));
    }


    public void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameManager.update();
                render();
            }
        }.start();
    }

    private void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gameManager.render(gc);
    }

    public void resetGame() {
        gameManager.reset();
    }

}

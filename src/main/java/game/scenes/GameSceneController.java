package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.Constant;
import game.GameManager;
import game.abstraction.GameScene;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

public class GameSceneController extends GameScene {

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    private GraphicsContext gc;
    private GameManager gameManager;

    private AnimationTimer gameLoop;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DoubleBinding widthScale = rootContainer.widthProperty().divide(Constant.WIDTH_SCREEN);
        DoubleBinding heightScale = rootContainer.heightProperty().divide(Constant.HEIGHT_SCREEN);

        // Take the smaller scale factor (to maintain the 16:9 aspect ratio without
        // cropping)
        Binding<Number> scale = Bindings.min(widthScale, heightScale);

        // Apply the scale to the gamePane
        gamePane.scaleXProperty().bind(scale);
        gamePane.scaleYProperty().bind(scale);

        // Get the GraphicsContext from the Canvas
        gc = gameCanvas.getGraphicsContext2D();
        setupInputHandlers();

        createGameLoop();
    }

    @Override
    public void setup(App app) {
        this.app = app;
        this.gameManager = new GameManager((int) gameCanvas.getWidth(), (int) gameCanvas.getHeight(), app);

        resetGame();
    }

    private void setupInputHandlers() {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(e -> gameManager.handleKeyPress(e));
        gameCanvas.setOnKeyReleased(e -> gameManager.handleKeyRelease(e));
    }

    private void createGameLoop() {
        this.gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (gameManager != null) {
                    gameManager.update();
                    scoreLabel.setText("Score:\n" + gameManager.getScore());
                    gameManager.render(gc);
                } else {
                    System.out.println("GameManager is not initialized!");
                }
            }
        };
    }

    private void startGameLoop() {
        if (gameLoop != null) {
            gameLoop.start();
        }
    }

    public void stopGameLoop() {
        if (gameLoop != null) {
            gameLoop.stop();
            gameLoop = null;
        }
    }

    @FXML
    public void resetGame() {
        if (gameManager != null) {
            gameManager.reset("level1");
        }

        if (gameLoop != null && !gameLoop.toString().contains("RUNNING")) {
            startGameLoop();
        } else if (gameLoop == null) {
            createGameLoop();
            startGameLoop();
        }
    }
}
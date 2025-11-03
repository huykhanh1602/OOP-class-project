package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.AssetManager;
import game.Constant;
import game.GameManager;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GameSceneController implements Initializable {

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    @FXML
    private StackPane rootContainer;

    @FXML
    private AnchorPane gamePane;

    private GraphicsContext gc;
    private GameManager gameManager;
    private App app;

    // This method is automatically called after initializing the FXML file
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DoubleBinding widthScale = rootContainer.widthProperty().divide(Constant.WIDTH_SCREEN);
        DoubleBinding heightScale = rootContainer.heightProperty().divide(Constant.HEIGHT_SCREEN);

        // Take the smaller scale factor (to maintain the 16:9 aspect ratio without cropping)
        Binding<Number> scale = Bindings.min(widthScale, heightScale);

        // Apply the scale to the gamePane
        gamePane.scaleXProperty().bind(scale);
        gamePane.scaleYProperty().bind(scale);

        // Get the GraphicsContext from the Canvas
        gc = gameCanvas.getGraphicsContext2D();
        setupInputHandlers();// Move input handling logic here
        startGameLoop();// Start the game loop
    }

    public void setup(App app) {
        this.app = app;
        // Initialize GameManager after obtaining App reference
        this.gameManager = new GameManager((int) gameCanvas.getWidth(), (int) gameCanvas.getHeight(), app);
    }

    private void setupInputHandlers() {
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(e -> gameManager.handleKeyPress(e));
        gameCanvas.setOnKeyReleased(e -> gameManager.handleKeyRelease(e));
    }

    private void startGameLoop() {
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                gameManager.update();

                // Update score label
                scoreLabel.setText("Score:\n" + gameManager.getScore());

                // Render game on canvas
                gameManager.render(gc);
            }
        }.start();
    }

    public void resetGame() {
        if (gameManager != null) {
            gameManager.reset(AssetManager.getLevel("level1"));
        }
    }
}
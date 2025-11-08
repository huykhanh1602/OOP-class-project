package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.Constant;
import game.GameContext;
import game.GameManager;
import game.abstraction.GameScene;
import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class GameSceneController extends GameScene {
    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    private GraphicsContext gc;
    private GameManager gameManager;

    private AnimationTimer gameLoop;

    @FXML
    private ImageView portalLeftImageView;

    @FXML
    private ImageView portalRightImageView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        
        // Get the GraphicsContext from the Canvas
        gc = gameCanvas.getGraphicsContext2D();
        setupInputHandlers();
        createGameLoop();
    }

    @Override
    public void setup(App app) {
        this.app = app;
        this.gameManager = new GameManager((int) gameCanvas.getWidth(), (int) gameCanvas.getHeight(), app);

        gameManager.createPortals(
        portalLeftImageView.getLayoutX(),
        portalLeftImageView.getLayoutY(),
        portalLeftImageView.getFitWidth(),
        portalLeftImageView.getFitHeight(),

        portalRightImageView.getLayoutX(),
        portalRightImageView.getLayoutY(),
        portalRightImageView.getFitWidth(),
        portalRightImageView.getFitHeight()
    );
        resetGame();
    }

    private void bindLabel() {
        var scoreProperty = GameContext.getInstance().getCurrentScore();
        scoreLabel.textProperty().bind(Bindings.format("Score:\n%d", scoreProperty));
    }

    // Set up input handlers for key presses and releases
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
            gameManager.reset();
        }

        if (gameLoop != null && !gameLoop.toString().contains("RUNNING")) {
            startGameLoop();
        } else if (gameLoop == null) {
            createGameLoop();
            startGameLoop();
        }

        if (gameLoop != null && !gameLoop.toString().contains("RUNNING")) {
            startGameLoop();
        } else if (gameLoop == null) {
            createGameLoop();
            startGameLoop();
        }
    }
}
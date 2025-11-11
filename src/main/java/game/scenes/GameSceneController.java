package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.abstraction.GameScene;
import game.manager.CoinManager;
import game.manager.GameManager;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;

public class GameSceneController extends GameScene {
    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;
    @FXML
    private Label coinLabel;
    @FXML
    private Label numBall;

    private GraphicsContext gc;

    private GameManager gameManager;

    private AnimationTimer gameLoop;

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

        resetGame();
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
                    double deltatime = calculateDeltaTime();
                    gameManager.update(deltatime);
                    scoreLabel.setText("Score: " + gameManager.getScore());
                    coinLabel.setText("Coin: " + CoinManager.getInstance().getCoin());
                    numBall.setText("x" + gameManager.getBalls());
                    gameManager.render(gc);
                } else {
                    System.out.println("GameManager is not initialized!");
                }
            }
        };
    }

    private double lastUpdateTime = 0;

    private double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
        }
        double dt = (currentTime - lastUpdateTime) / 1_000_000_000.0;
        lastUpdateTime = currentTime;

        // Clamp giá trị dt để tránh outlier
        if (dt < 0.001 || dt > 0.05) {
            dt = 0.016; // khoảng 60 FPS
        }
        return dt;
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
            gameManager = new GameManager((int) gameCanvas.getWidth(), (int) gameCanvas.getHeight(), app);
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
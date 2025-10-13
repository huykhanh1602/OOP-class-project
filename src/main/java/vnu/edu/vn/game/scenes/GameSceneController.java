package vnu.edu.vn.game.scenes;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import vnu.edu.vn.App;
import vnu.edu.vn.game.GameManager;

public class GameSceneController {

    @FXML
    private Canvas gameCanvas;

    @FXML
    private Label scoreLabel;

    private GraphicsContext gc;
    private GameManager gameManager;
    private App app;

    // Phương thức này sẽ được gọi tự động sau khi tệp FXML được tải
    @FXML
    public void initialize() {
        // Lấy GraphicsContext từ Canvas
        gc = gameCanvas.getGraphicsContext2D();

        // Di chuyển logic xử lý input vào đây
        setupInputHandlers();

        // Bắt đầu vòng lặp game
        startGameLoop();
    }

    // Phương thức này được dùng để App truyền tham chiếu vào
    public void setup(App app) {
        this.app = app;
        // Khởi tạo GameManager sau khi đã có tham chiếu App
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

                // Cập nhật điểm số trên Label
                scoreLabel.setText("Score: " + gameManager.getScore());

                // Vẽ game lên canvas
                gameManager.render(gc);
            }
        }.start();
    }

    public void resetGame() {
        if (gameManager != null) {
            gameManager.reset();
        }
    }
}
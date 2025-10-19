package game.scenes;

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

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.Constant;

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

    // Phương thức này sẽ được gọi tự động sau khi tệp FXML được tải
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DoubleBinding widthScale = rootContainer.widthProperty().divide(Constant.WIDTH_SCREEN);
        DoubleBinding heightScale = rootContainer.heightProperty().divide(Constant.HEIGHT_SCREEN);

        // Lấy tỷ lệ nào nhỏ hơn (để giữ tỷ lệ 16:9 và không bị cắt)
        Binding<Number> scale = Bindings.min(widthScale, heightScale);

        // Áp dụng tỷ lệ đó cho gamePane
        gamePane.scaleXProperty().bind(scale);
        gamePane.scaleYProperty().bind(scale);

        // Lấy GraphicsContext từ Canvas
        gc = gameCanvas.getGraphicsContext2D();
        setupInputHandlers();// Di chuyển logic xử lý input vào đây
        startGameLoop();// Bắt đầu vòng lặp game
    }

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
                scoreLabel.setText("Score:\n" + gameManager.getScore());

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
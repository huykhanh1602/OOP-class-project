package vnu.edu.vn.game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import vnu.edu.vn.App;
import vnu.edu.vn.game.Constant;

public class GameOverController implements Initializable {
    private App app;

    @FXML
    private StackPane rootContainer;

    @FXML
    private AnchorPane gamePane; // Hoặc Pane

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Tạo binding tính tỷ lệ theo chiều rộng
        DoubleBinding widthScale = rootContainer.widthProperty().divide(Constant.WIDTH_SCREEN);
        DoubleBinding heightScale = rootContainer.heightProperty().divide(Constant.HEIGHT_SCREEN);

        // Lấy tỷ lệ nào nhỏ hơn (để giữ tỷ lệ 16:9 và không bị cắt)
        Binding<Number> scale = Bindings.min(widthScale, heightScale);

        // Áp dụng tỷ lệ đó cho gamePane
        gamePane.scaleXProperty().bind(scale);
        gamePane.scaleYProperty().bind(scale);
    }

    public void setup(App app) {
        this.app = app;
    }

    @FXML
    private Label scoreLabel;

    public void setScore(int finalScore) {
        scoreLabel.setText("Score: " + finalScore);
    }

    @FXML
    protected void setOnRestart(ActionEvent e) {
        System.out.println("restart button pressed");
        if (app != null) {
            app.switchToGameScene();
        } else {
            System.out.println("Lỗi: Tham chiếu App chưa được thiết lập!");
        }
    }

    @FXML
    protected void setToHome(ActionEvent e) {
        System.out.println("home button pressed");
        if (app != null) {
            app.switchToHomeScene();
        } else {
            System.out.println("Lỗi: Tham chiếu App chưa được thiết lập!");
        }
    }

    @FXML
    protected void Quit(ActionEvent e) {
        System.out.println("quit button pressed");
        if (app != null) {
            Platform.exit();
        } else {
            System.out.println("Lỗi: Tham chiếu App chưa được thiết lập!");
        }
    }

}

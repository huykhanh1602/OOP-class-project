package vnu.edu.vn.game.scenes;

import javafx.fxml.FXML;
import javafx.application.Platform;
import javafx.event.ActionEvent; // Thêm thư viện này để xử lý sự kiện
import vnu.edu.vn.App;

public class HomeSceneController {
    private App app;

    public void setup(App app) {
        this.app = app;
    }

    @FXML
    protected void handleStartButtonAction(ActionEvent e) {
        System.out.println("meo");
        if (app != null) {
            app.switchToGameScene();
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
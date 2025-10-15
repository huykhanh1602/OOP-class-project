package vnu.edu.vn.game.scenes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import vnu.edu.vn.App;

public class GameOverController {
    private App app;

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
            app.switchtoHomeScene();
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

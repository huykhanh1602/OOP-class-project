package game.scenes;

import game.abstraction.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends GameScene {
    @FXML
    private Label scoreLabel;

    public void setScore(int finalScore) {
        scoreLabel.setText("Score: " + finalScore);
    }

    @FXML
    protected void setToHome(ActionEvent e) {
        System.out.println("home button pressed");
        if (app != null) {
            app.switchToHomeScene();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

}

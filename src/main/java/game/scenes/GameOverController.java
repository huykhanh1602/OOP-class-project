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

}

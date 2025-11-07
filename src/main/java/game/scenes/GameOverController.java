package game.scenes;

import java.awt.Button;

import game.abstraction.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameOverController extends GameScene {
    @FXML
    private Label scoreLabel;

    public void setScore(int finalScore) {
        scoreLabel.setText("Score: " + finalScore);
    }

    @FXML
    private void ADS() {
        app.switchToADSScene();
    }
}

package vnu.edu.vn.game.gameOver;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameOverController {

    @FXML
    private Label scoreLabel;

    @FXML
    private Button restartButton;

    private Runnable onRestart;

    public void setScore(int score) {
        if (scoreLabel != null) {
            scoreLabel.setText("Score: " + score);
        }
    }

    public void setOnRestart(Runnable onRestart) {
        this.onRestart = onRestart;
        if (restartButton != null) {
            restartButton.setOnAction(e -> {
                if (this.onRestart != null) {
                    this.onRestart.run();
                }
            });
        }
    }

    @FXML
    private void initialize() {
        restartButton.setOnAction(e -> {
            if (onRestart != null) {
                onRestart.run();
            }
        });
    }
}

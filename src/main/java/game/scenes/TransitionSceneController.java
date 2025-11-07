package game.scenes;


import java.net.URL;
import java.util.ResourceBundle;

import game.abstraction.GameScene;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class TransitionSceneController extends GameScene{
    private int per = 0;
    @FXML
    private Label load;
    private Timeline timeStart;    

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        startLoadPer();
    }

    @FXML
    private void loadPer() {
        per += Math.random() * 5;
        load.setText("Load: " + String.valueOf(per) + "%");
        if (per >= 95) {
            timeStart.stop();
            app.switchToGameScene();
        }
    }

    @FXML
    private void startLoadPer() {
    timeStart = new Timeline(
        new KeyFrame(Duration.millis(100), e -> loadPer())
    );
    timeStart.setCycleCount(Animation.INDEFINITE);
    timeStart.play();    }
    
}

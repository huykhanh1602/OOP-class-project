package vnu.edu.vn.game.home;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class HomeController {
    @FXML
    private Label pressLabel;

    public void initialize() {
        Timeline blink = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> pressLabel.setVisible(false)),
                new KeyFrame(Duration.seconds(1.0), e -> pressLabel.setVisible(true))
        );
        blink.setCycleCount(Animation.INDEFINITE);
        blink.play();
    }

    @FXML
    private AnchorPane rootPane;

    public AnchorPane getRootPane() {
        return rootPane;
    }
}

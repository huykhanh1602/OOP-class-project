package game.abstraction;

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.Constant;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent; // Import to handle events
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public abstract class GameScene implements Initializable {

    protected App app;

    @FXML
    protected StackPane rootContainer;

    @FXML
    protected AnchorPane gamePane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // Create binding for width scale and height scale
        DoubleBinding widthScale = rootContainer.widthProperty().divide(Constant.WIDTH_SCREEN);
        DoubleBinding heightScale = rootContainer.heightProperty().divide(Constant.HEIGHT_SCREEN);

        // Take the smaller scale factor (to maintain the 16:9 aspect ratio without
        // cropping)
        Binding<Number> scale = Bindings.min(widthScale, heightScale);

        // Apply the scale to the gamePane
        gamePane.scaleXProperty().bind(scale);
        gamePane.scaleYProperty().bind(scale);
    }

    public void setup(App app) {
        this.app = app;
    }

    // event handlers
    @FXML
    protected void handleStartButtonAction(ActionEvent e) {
        System.out.println("Start button pressed");
        if (app != null) {
            app.startNewGame();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

    @FXML
    protected void Quit(ActionEvent e) {
        System.out.println("quit button pressed");
        if (app != null) {
            Platform.exit();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

    @FXML
    protected void handleInstructionButton(ActionEvent e) {
        System.out.println("Instruction button pressed");
        if (app != null) {
            app.switchToInstructionScene();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

    @FXML
    protected void returnToHome(ActionEvent e) {
        System.out.println("Return to home button pressed");
        if (app != null) {
            app.switchToHomeScene();
        } else {
            System.out.println("Error: App reference is null");
        }
    }
}

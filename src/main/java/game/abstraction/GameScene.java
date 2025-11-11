package game.abstraction;

import java.net.URL;
import java.util.ResourceBundle;

import game.App;
import game.AssetManager;
import game.Constant;
import game.GameContext;
import game.manager.ScoreManager;
import javafx.application.Platform;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public abstract class GameScene implements Initializable {

    protected App app;

    @FXML
    protected StackPane rootContainer;

    @FXML
    protected AnchorPane gamePane;

    private Binding<Number> scale;

    protected int currentIndex = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DoubleBinding widthScale = rootContainer.widthProperty().divide(Constant.WIDTH_SCREEN);
        DoubleBinding heightScale = rootContainer.heightProperty().divide(Constant.HEIGHT_SCREEN);

        // Take the smaller scale factor (to maintain the 16:9 aspect ratio without
        // cropping)
        Binding<Number> scale = Bindings.min(widthScale, heightScale);

        // Apply the scale to the gamePane
        gamePane.scaleXProperty().bind(scale);
        gamePane.scaleYProperty().bind(scale);
    }

    public Binding<Number> getScale() {
        return scale;
    }

    public void setup(App app) {
        this.app = app;
    }

    @FXML
    protected void handleStartButtonAction(ActionEvent e) {
        GameContext.getInstance().resetScore();
        AssetManager.playSound("click");
        System.out.println("Start button pressed");
        if (app != null) {
            app.startNewGame();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

    @FXML
    protected void Setting(ActionEvent e) {
        AssetManager.playSound("click");
        if (app != null) {
            app.switchToSettingScene();
        } else {
            System.out.println("error setting scene");
        }
    }

    @FXML
    protected void Quit(ActionEvent e) {
        AssetManager.playSound("click");
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
            app.switchToGameScene();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

    @FXML
    protected void returnToHome(ActionEvent e) {
        AssetManager.playSound("click");
        if (app != null) {
            GameContext.getInstance().resetLevel();
            app.switchToHomeScene();
        }
    }
}

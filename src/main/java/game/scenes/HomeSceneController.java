package game.scenes;

import game.abstraction.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeSceneController extends GameScene {

    @FXML
    protected void handleInstructionButton(ActionEvent e) {
        System.out.println("Instruction button pressed");
        if (app != null) {
            app.switchToInstructionScene();
        } else {
            System.out.println("Error: App reference is null");
        }
    }
}
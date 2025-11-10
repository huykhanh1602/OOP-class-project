package game.scenes;

import game.AssetManager;
import game.GameContext;
import game.abstraction.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MerchantController extends GameScene {

    @FXML
    protected void nextLevel(ActionEvent e) {
        AssetManager.playSound("click");
        if (app != null) {
            app.nextLevel();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

}

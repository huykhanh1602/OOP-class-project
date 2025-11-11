package game.scenes;

import game.AssetManager;
import game.abstraction.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class MerchantController extends GameScene {
    @FXML
    private ImageView itemImage1;

    @FXML
    private ImageView itemImage2;

    @FXML
    private ImageView itemImage3;

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

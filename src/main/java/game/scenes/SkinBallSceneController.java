package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.Constant;
import game.GameContext;
import game.GameManager;
import game.abstraction.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkinBallSceneController extends GameScene{
    @FXML
    private ImageView ballView;

    @FXML
    private Button buttonPrev;

    @FXML
    private Button buttonNext;

    @FXML
    private Label name;
    private String nameBall;

    private final String[] skins = {
        Constant.SLIME_BALL,
        Constant.EYEOFDRAGON_BALL
    };

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        updateSkinUI();
    }

    public void next() {
        currentIndex++;
        if (currentIndex >= skins.length) currentIndex = 0;
        GameContext.getInstance().setNameBall(skins[currentIndex]);
        updateSkinUI();
    }

    public void prev() {
        currentIndex--;
        if (currentIndex < 0) currentIndex = skins.length - 1;
        GameContext.getInstance().setNameBall(skins[currentIndex]);
        updateSkinUI();
    }

    private void updateSkinUI() {
        ballView.setImage(
            new Image(getClass().getResource(skins[currentIndex]).toExternalForm())
        );
        switch (currentIndex) {
            case 0:
                nameBall = "SLIME BALL";
                break;
            case 1:
                nameBall = "EYE OF DRAGON BALL";
                break;
            default:
                throw new AssertionError();
        }
        name.setText(nameBall);
    }
}

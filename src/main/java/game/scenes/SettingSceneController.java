package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.GameContext;
import game.abstraction.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class SettingSceneController extends GameScene {

    @FXML
    public Slider soundVolume;
    public Slider musicVolume;
    public Slider masterVolume;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);
        soundVolume.setValue(GameContext.getInstance().getSoundVolume());
        musicVolume.setValue(GameContext.getInstance().getBackgroundMusic());
        masterVolume.setValue(GameContext.getInstance().getMasterVolume());

        soundVolume.valueProperty().addListener((obs, lasVolume, curVolume) -> {
            GameContext.getInstance().setSoundVolume((double)curVolume);
        });
        musicVolume.valueProperty().addListener((obs, lasVolume, curVolume) -> {
            GameContext.getInstance().setBackgroundMusic((double)curVolume);
        });
        masterVolume.valueProperty().addListener((obs, lasVolume, curVolume) -> {
            GameContext.getInstance().setMasterVolume((double)curVolume);
        });
    }

}
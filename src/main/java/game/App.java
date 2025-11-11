package game;

import java.io.IOException;
import java.util.function.Consumer;

import game.scenes.ADSSceneController;
import game.scenes.GameOverController;
import game.scenes.GameSceneController;
import game.scenes.HomeSceneController;
import game.scenes.SettingSceneController;
import game.scenes.SkinBallSceneController;
import game.scenes.TransitionSceneController;

import game.manager.CoinManager;
import game.manager.ScoreManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;
    private MediaPlayer backgroundMusicPlayer;
    private GameSceneController currentGameController;

    @Override
    public void start(Stage stage) throws IOException {
        AssetManager.loadAssets();
        stage.getIcons().add(AssetManager.getImage("icon"));

        this.primaryStage = stage;

        switchToHomeScene();

        stage.setTitle(Constant.GAME_NAME);
        stage.setMaximized(true);
        stage.show();
        AssetManager.playSound("run_game");
    }

    public void startNewGame() {
        GameContext.getInstance().resetLevel();
        ScoreManager.getInstance().resetScore();
        CoinManager.getInstance().resetCoin();
        switchToTransitionScene();
        playBackgroundMusic("Game_Background");
    }

    public void nextLevel() {
        GameContext.getInstance().nextLevel();
        switchToGameScene();
    }

    public void gameOver(int finalScore) {
        switchToGameOverScene(finalScore);
    }

    /**
     * Changes the current scene to the one specified by fxmlPath.
     * 
     * @param fxmlPath   fxmxlPath
     * @param setupLogic setup logic for controller
     * @param <T>        Controller type
     */
    private <T> void switchScene(String fxmlPath, Consumer<T> setupLogic) {
        if (this.currentGameController != null) {
            this.currentGameController.stopGameLoop();
            this.currentGameController = null;
        }
        try {
            // load FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // get controller
            T controller = loader.getController();
            if (setupLogic != null) {
                setupLogic.accept(controller);
            }

            // set and load scene
            Scene scene;
            if (primaryStage.getScene() != null) {
                scene = new Scene(root, primaryStage.getScene().getWidth(), primaryStage.getScene().getHeight());
            } else {
                scene = new Scene(root);
            }

            primaryStage.setScene(scene);
            backgroundMusicPlayer.setVolume(GameContext.getInstance().getBackgroundMusic());
        } catch (IOException e) {
            throw new RuntimeException("Cant load: " + fxmlPath, e);
        }
    }

    // Switch to Home Scene
    public void switchToHomeScene() {
        switchScene(Constant.HOME_SCENE_PATH, (HomeSceneController controller) -> {
            controller.setup(this);
            playBackgroundMusic("Home_Background");
        });
    }

    // Switch to Game Scene
    public void switchToGameScene() {
        switchScene(Constant.GAME_SCENE_PATH, (GameSceneController controller) -> {
            this.currentGameController = controller;
            controller.setup(this);
        });
    }

    public void switchToSettingScene() {
        switchScene(Constant.SETTING_SCENE_PATH, (SettingSceneController controller) -> {
            controller.setup(this);
        });
    }

    public void switchToADSScene() {
        switchScene(Constant.ADS_SCENE, (ADSSceneController controller) -> {
            controller.setup(this);
        });
    }

    // Switch to Game Over Scene
    public void switchToGameOverScene(int finalScore) {
        switchScene(Constant.GAME_OVER_SCENE_PATH, (GameOverController controller) -> {
            controller.setup(this);
            controller.setScore(finalScore); // Logic riêng của GameOverScene
            // playBackgroundMusic("game_over_music");
        });
    }

    public void switchToSkinScene() {
        switchScene(Constant.SKIN_BALL_SCENE, (SkinBallSceneController controller) -> {
            controller.setup(this);
        });
    }

    public void switchToTransitionScene() {
        switchScene(Constant.TRANSITION_SCENE, (TransitionSceneController controller) -> {
            controller.setup(this);
        });
    }

    public void switchToMerchantScene() {
        switchScene(Constant.MERCHANT_SCENE, (game.scenes.MerchantController controller) -> {
            controller.setup(this);
        });
    }

    /**
     * play the background music.
     * 
     * @param musicKey the key of the music to play
     */
    public void playBackgroundMusic(String musicKey) {
        // Dừng nhạc hiện tại trước
        stopBackgroundMusic();

        if (musicKey == null || musicKey.isEmpty()) {
            return; // Không phát gì nếu key là null hoặc rỗng
        }

        // THAY ĐỔI: Giả định AssetManager.getMusic(key) là đủ
        Media backgroundMusic = AssetManager.getMusic(musicKey);

        if (backgroundMusic != null) {
            backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusicPlayer.setVolume(GameContext.getInstance().getBackgroundMusic()); // Rõ ràng hơn khi dùng 1.0
            backgroundMusicPlayer.play();
        } else {
            System.err.println("Cant find: " + musicKey);
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
            backgroundMusicPlayer.dispose();
            backgroundMusicPlayer = null;
        }
    }

    // LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}
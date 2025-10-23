package game;

import java.io.IOException;

import game.scenes.GameOverController;
import game.scenes.GameSceneController;
import game.scenes.HomeSceneController;
import game.AssetManager;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    private MediaPlayer backgroundMusicPlayer;

    @Override
    public void start(Stage stage) throws IOException {

        AssetManager.loadAssets();
        stage.getIcons().add(AssetManager.getImage("icon"));

        this.primaryStage = stage;
        // switchToHomeScene();
        switchToGameOverScene(0); // testing
        stage.setTitle(Constant.GAME_NAME);

        stage.setMaximized(true);
        stage.show();
    }

    // Switch to Home Scene
    public void switchToHomeScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.HOME_SCENE_PATH));
            Parent root = loader.load();

            HomeSceneController controller = loader.getController();

            controller.setup(this);

            Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
            scene.setRoot(root);
            primaryStage.setScene(scene);

            startBackgroundMusic("home_background_music");

        } catch (IOException e) {
            throw new RuntimeException("Cant load FXML: " + Constant.HOME_SCENE_PATH, e);
        }
    }

    // Switch to Game Scene
    public void switchToGameScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GAME_SCENE_PATH));
            Parent root = loader.load();

            // Get the controller and pass the 'App' reference to it
            GameSceneController controller = loader.getController();
            controller.setup(this);

            Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.setScene(scene);

        } catch (IOException e) {
            throw new RuntimeException("Cant load FXML: " + Constant.HOME_SCENE_PATH, e);
        }
    }

    // Switch to Game Over Scene
    public void switchToGameOverScene(int finalScore) {

        System.out.println("Game Over! Final Score: " + finalScore);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GAME_OVER_SCENE_PATH));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setup(this);
            controller.setScore(finalScore);

            Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException("Cant load FXML: " + Constant.HOME_SCENE_PATH, e);
        }

    }

    // Music control
    public void startBackgroundMusic(String musicKey) {
        Media backgroundMusic = AssetManager.getMusic(musicKey);
        if (backgroundMusic != null) {
            backgroundMusicPlayer = new MediaPlayer(backgroundMusic);
            backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            backgroundMusicPlayer.play();
            backgroundMusicPlayer.setVolume(1); // Set volume (0.0 to 1.0)
        } else {
            System.err.println("Background music not found: " + musicKey);
        }
    }

    public void stopBackgroundMusic() {
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.stop();
        }
    }

    // LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}

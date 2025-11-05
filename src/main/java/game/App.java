package game;

import java.io.IOException;
import java.util.function.Consumer;

import game.scenes.GameOverController;
import game.scenes.GameSceneController;
import game.scenes.HomeSceneController;

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
    }

    // Switch to Home Scene
    public void switchToHomeScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.HOME_SCENE_PATH));
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

        } catch (IOException e) {
            throw new RuntimeException("Cant load: " + fxmlPath, e);
        }
    }

    // Switch to Home Scene
    public void switchToHomeScene() {
        switchScene(Constant.HOME_SCENE_PATH, (HomeSceneController controller) -> {
            controller.setup(this);
            // playBackgroundMusic("home_background_music");
        });
    }

    // Switch to Game Scene
    public void switchToGameScene() {
        switchScene(Constant.GAME_SCENE_PATH, (GameSceneController controller) -> {
            this.currentGameController = controller;
            controller.setup(this);
            // playBackgroundMusic("game_background_music");
        });
    }

    // Switch to Game Over Scene
    public void switchToGameOverScene(int finalScore) {
        switchScene(Constant.GAME_OVER_SCENE_PATH, (GameOverController controller) -> {
            controller.setup(this);
            controller.setScore(finalScore);

            Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException("Cant load FXML: " + Constant.HOME_SCENE_PATH, e);
        }

    }

    // LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}
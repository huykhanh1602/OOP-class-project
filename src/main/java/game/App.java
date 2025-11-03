package game;

import java.io.IOException;

import game.scenes.GameOverController;
import game.scenes.GameSceneController;
import game.scenes.HomeSceneController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        Image icon = new Image(getClass().getResourceAsStream(Constant.ICON_PATH));
        stage.getIcons().add(icon);

        this.primaryStage = stage;
        switchToHomeScene();
        // switchToGameOverScene(0); // testing
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
            primaryStage.setScene(scene);

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

    // LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}

package vnu.edu.vn;

/// Launcher game

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vnu.edu.vn.game.GameScene;
import vnu.edu.vn.game.gameOver.GameOverController;

public class App extends Application {

    private Stage stage;
    private Scene scene;
    private Scene gameOverScene;
    private GameOverController gameOverController;
    private GameScene gameScene;

    //WINDOW SETTING
    public final int widthScreen = 600;             //WIDTH SCREEN
    public final int heightScreen = 600;            //HEIGHT SCREEN
    private final String title = "Arknoid";


    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        gameScene = new GameScene(widthScreen, heightScreen, this);
        scene = new Scene(gameScene, widthScreen, heightScreen);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vnu/edu/vn/game/gameOver/GameOverScene.fxml"));

        gameOverScene = new Scene(loader.load(), widthScreen, heightScreen);
        gameOverController = loader.getController();
        gameOverController.setOnRestart(() -> switchToGame());


        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameOver(int score) {
        gameOverController.setScore(score);
        stage.setScene(gameOverScene);
    }

    public void switchToGame() {
        gameScene.resetGame();
        stage.setScene(scene);
    }


    //LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}

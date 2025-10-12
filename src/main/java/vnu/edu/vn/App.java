package vnu.edu.vn;

/// Launcher game

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import vnu.edu.vn.game.GameScene;
import vnu.edu.vn.game.gameOver.GameOverController;
import vnu.edu.vn.game.home.HomeController;

public class App extends Application {

    private Stage stage;

    private Scene scene;
    private GameScene gameScene;

    private Scene gameOverScene;
    private GameOverController gameOverController;

    private Scene homeScene;
    private HomeController homeController;

    //WINDOW SETTING
    public final int widthScreen = 600;             //WIDTH SCREEN
    public final int heightScreen = 600;            //HEIGHT SCREEN
    private final String title = "Arknoid";


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        //Game Scene
        gameScene = new GameScene(widthScreen, heightScreen, this);
        scene = new Scene(gameScene, widthScreen, heightScreen);

        //GameOver Scene
        FXMLLoader endLoader = new FXMLLoader(getClass().getResource("/vnu/edu/vn/game/gameOver/GameOverScene.fxml"));
        gameOverScene = new Scene(endLoader.load(), widthScreen, heightScreen);
        gameOverController = endLoader.getController();
        gameOverController.setOnRestart(() -> switchToGame());
        gameOverController.setOnHome(() -> switchToHome());

        //Home Scene
        FXMLLoader homeLoader = new FXMLLoader(getClass().getResource("/vnu/edu/vn/game/home/HomeScene.fxml"));
        homeController = homeLoader.getController();
        homeScene = new Scene(homeLoader.load(), widthScreen, heightScreen);
        homeScene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE) {
                switchToGame();
            }
        });


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

    public void switchToHome() {
        stage.setScene(scene);
        homeController.getRootPane().requestFocus();
    }


    //LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}

package vnu.edu.vn;

/// Launcher game

import javafx.application.Application;
import javafx.stage.Stage;
import vnu.edu.vn.game.GameScene;

public class App extends Application {
    //WINDOW SETTING
    public final int widthScreen = 800;             //WIDTH SCREEN
    public final int heightScreen = 600;            //HEIGHT SCREEN
    private final String title = "Arknoid";


    @Override
    public void start(Stage stage) throws Exception {

        GameScene gameScene = new GameScene(widthScreen, heightScreen);
        stage.setTitle(title);
        stage.setScene(gameScene.getScene());
        stage.show();

        gameScene.start();
    }


    //LAUNCH GAME
    public static void main(String[] args) {
        launch(args);
    }
}

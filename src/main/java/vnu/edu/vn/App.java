package vnu.edu.vn;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import vnu.edu.vn.game.scenes.GameSceneController;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;
        switchToGameScene(); // Chuyển đến màn hình game khi bắt đầu
        stage.setTitle("Arkanoid");
        stage.show();
    }

    public void switchToGameScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vnu/edu/vn/game/scenes/GameScene.fxml"));
            Parent root = loader.load();

            // Lấy controller và truyền tham chiếu 'App' vào
            GameSceneController controller = loader.getController();
            controller.setup(this);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToGameOver(int finalScore) {
        // Logic chuyển sang màn hình GameOver (bạn có thể tạo GameOver.fxml tương tự)
        System.out.println("Game Over! Final Score: " + finalScore);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
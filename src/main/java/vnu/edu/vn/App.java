package vnu.edu.vn;

import java.io.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vnu.edu.vn.game.scenes.GameSceneController;
import vnu.edu.vn.game.scenes.HomeSceneController;
import vnu.edu.vn.game.scenes.GameOverController;
import vnu.edu.vn.game.Constant;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {

        Image icon = new Image(getClass().getResourceAsStream(Constant.IconPath));
        stage.getIcons().add(icon);
        if (icon == null)
            System.out.println("cant find icon");

        this.primaryStage = stage;
        switchtoHomeScene(); // Chuyển đến màn hình game khi bắt đầu
        stage.setTitle(Constant.GameName);
        stage.show();

        // Image icon = new
        // Image(getClass().getResourceAsStream("/vnu/edu/vn/game/image/icon.png"));
        // stage.getIcons().add(icon);

    }

    public void switchtoHomeScene() {
        try {
            // Tải file FXML của màn hình chính (Lưu ý: tên file có thể là Home.fxml)
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.HomeScenePath));
            Parent root = loader.load();

            // LẤY RA ĐÚNG CONTROLLER CỦA NÓ: HomeController
            HomeSceneController controller = loader.getController();

            // Truyền tham chiếu App cho HomeController
            controller.setup(this); // Giả sử bạn có phương thức setApp(this) trong HomeController

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToGameScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GameScenePath));
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

    public void switchToGameOverScene(int finalScore) {
        // Logic chuyển sang màn hình GameOver (bạn có thể tạo GameOver.fxml tương tự)
        System.out.println("Game Over! Final Score: " + finalScore);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constant.GameOverScenePath));
            Parent root = loader.load();

            GameOverController controller = loader.getController();
            controller.setup(this);
            controller.setScore(finalScore);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}

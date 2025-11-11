package game.scenes;

import java.net.URL;
import java.util.ResourceBundle;

import game.AssetManager;
import game.abstraction.GameScene;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HomeSceneController extends GameScene {

    @FXML
    private ImageView nameGame;
    private Timeline resizeTimeline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);        
        nameGame.setPreserveRatio(true);
        startResizeAnimation();
    }

    private void createResizeTimeline() {
        var baseWidthProperty = nameGame.scaleXProperty();
        var baseHeightProperty = nameGame.scaleYProperty();
        var baseXProperty = nameGame.xProperty();
        var baseYProperty = nameGame.yProperty();
        // Dừng timeline cũ nếu đang chạy
        if (resizeTimeline != null) {
            resizeTimeline.stop();
        }

        // Tạo Timeline
        resizeTimeline = new Timeline(
            // KeyFrame 1: Giữ nguyên kích thước ban đầu (hoặc bắt đầu từ kích thước đó)
            new KeyFrame(Duration.ZERO, 
                new KeyValue(baseWidthProperty, baseWidthProperty.get()), 
                new KeyValue(baseHeightProperty, baseHeightProperty.get()),
                new KeyValue(baseXProperty, baseXProperty.get()),
                new KeyValue(baseYProperty, baseYProperty.get())
            ),
            
            // KeyFrame 2: Thay đổi đến kích thước mới sau 1 giây
            new KeyFrame(Duration.seconds(1), 
                new KeyValue(baseWidthProperty, baseWidthProperty.get() * 1.2),
                new KeyValue(baseHeightProperty, baseHeightProperty.get() * 1.2),
                new KeyValue(baseXProperty, baseXProperty.get() - baseWidthProperty.get() * 0.1),
                new KeyValue(baseYProperty, baseYProperty.get() - baseHeightProperty.get() * 0.1)
            ),
            
            // KeyFrame 3: Quay trở lại kích thước ban đầu sau 2 giây (tính từ Duration.ZERO)
            new KeyFrame(Duration.seconds(2), 
                new KeyValue(baseWidthProperty, baseWidthProperty.get()),
                new KeyValue(baseHeightProperty, baseHeightProperty.get()),
                new KeyValue(baseXProperty, baseXProperty.get()),
                new KeyValue(baseYProperty, baseYProperty.get())
            )
        );

        // Đặt số lần lặp vô hạn
        resizeTimeline.setCycleCount(Timeline.INDEFINITE);
        resizeTimeline.setAutoReverse(true);
    }

    @FXML
    public void startResizeAnimation() {
        createResizeTimeline(); 
        
        if (resizeTimeline != null) {
            // Đảm bảo animation luôn bắt đầu từ đầu
            resizeTimeline.playFromStart(); 
        }
    }

    @FXML
    public void changeSkin() {
        AssetManager.playSound("click");
        if (app != null) {
            app.switchToSkinScene();
        } else {
            System.out.println("error setting scene");
        }    
    }
}
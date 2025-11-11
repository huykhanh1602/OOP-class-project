package game.scenes;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;

import game.abstraction.GameScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

public class ADSSceneController extends GameScene{
    @FXML
    private MediaView adsvideo;

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);              
        playVideo();
    }

    @FXML
    private void close() {
        app.switchToGameScene();
    }

    @FXML
    private void playVideo() {
        Rectangle clip = new Rectangle(
            adsvideo.getFitWidth(), 
            adsvideo.getFitHeight()
        );

        double radius = 20.0;
        clip.setArcWidth(radius);
        clip.setArcHeight(radius);

        adsvideo.setClip(clip);

        adsvideo.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            clip.setWidth(newBounds.getWidth());
            clip.setHeight(newBounds.getHeight());
        });     

        URL videoUrl = getClass().getResource("/game/images/ads/ads.mp4");
        if (videoUrl != null) {
            try {
                Media media = new Media(videoUrl.toExternalForm());

                MediaPlayer mediaPlayer = new MediaPlayer(media);

                adsvideo.setMediaPlayer(mediaPlayer);

                mediaPlayer.play();
            } catch (Exception e) {
                e.printStackTrace();
            }  
        } else {
            throw new RuntimeException("Video resource not found!");
        }
    }
    
    @FXML
    private Hyperlink link;

    @FXML
    private void linkWeb(ActionEvent event) {
        try {
            openLink("https://youtu.be/dQw4w9WgXcQ?si=alUhrCrivvSaVTQK");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi cuối cùng: Không trình duyệt nào có thể mở.");
        }
    }

    public static void openLink(String url) throws Exception {
        URI uri = new URI(url);

        try {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                System.out.println("Thử 1: Mở bằng Trình duyệt Mặc định...");
                Desktop.getDesktop().browse(uri);
            } else {
                throw new UnsupportedOperationException("Desktop API không được hỗ trợ hoặc không thể browse.");
            }
        } catch (IOException | UnsupportedOperationException e) {
            System.err.println("Lỗi Thử 1: Mở mặc định thất bại. " + e.getMessage());

            try {
                String os = System.getProperty("os.name").toLowerCase();
                String chromePath;

                if (os.contains("win")) {
                    chromePath = "cmd /c start chrome " + url; 
                } else {
                    chromePath = "open -a \"Google Chrome\" " + url;
                }

                Runtime.getRuntime().exec(chromePath);

            } catch (IOException chromeException) {
                try {
                    String os = System.getProperty("os.name").toLowerCase();
                    String edgePath;

                    if (os.contains("win")) {
                        edgePath = "cmd /c start msedge " + url;
                    } else {
                        edgePath = "open -a \"Microsoft Edge\" " + url;
                    }
                    Runtime.getRuntime().exec(edgePath);
                } catch (IOException edgeException) {
                    
                    throw new Exception("Không thể mở trình duyệt: " + url, edgeException);
                }
            }
        }
    }

    public Hyperlink getLink() {
        return link;
    }

    public void setLink(Hyperlink link) {
        this.link = link;
    }
}

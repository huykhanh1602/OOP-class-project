package game;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    // images
    private static Map<String, Image> images = new HashMap<>();

    // sounds
    private static Map<String, AudioClip> sounds = new HashMap<>();

    // music
    private static Map<String, Media> musics = new HashMap<>();

    public static void loadAssets() {
        // load images
        try {
            images.put("icon", new Image(AssetManager.class.getResourceAsStream(Constant.ICON_PATH)));

            images.put("ball", new Image(AssetManager.class.getResourceAsStream(Constant.BALL_PATH)));

            images.put("stone_brick",
                    new Image(AssetManager.class.getResourceAsStream(Constant.STONE_BBRICK_PATH)));

            images.put("destroy_stage_1", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_1)));
            images.put("destroy_stage_2", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_2)));
            images.put("destroy_stage_3", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_3)));
            images.put("destroy_stage_4", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_4)));
            images.put("destroy_stage_5", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_5)));
            images.put("destroy_stage_6", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_6)));
            images.put("destroy_stage_7", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_7)));
            images.put("destroy_stage_8", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_8)));
            images.put("destroy_stage_9", new Image(AssetManager.class.getResourceAsStream(Constant.DESTROY_STAGE_9)));
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }

        // load sounds
        try {
            sounds.put("brick_hit",
                    new AudioClip(AssetManager.class.getResource("/game/sounds/brick_hit.wav").toString()));
            sounds.put("paddle_hit",
                    new AudioClip(AssetManager.class.getResource("/game/sounds/paddle_hit.wav").toString()));
        } catch (Exception e) {
            System.err.println("Error loading sounds: " + e.getMessage());
        }

        // load music
        try {
            musics.put("home_background_music",
                    new Media(
                            AssetManager.class.getResource("/game/sounds/music/Home_background_music.mp3").toString()));
        } catch (Exception e) {
            System.err.println("Error loading music: " + e.getMessage());
        }
    }

    public static Image getImage(String key) {
        return images.get(key);
    }

    public static AudioClip getSound(String key) {
        return sounds.get(key);
    }

    public static Media getMusic(String key) {
        return musics.get(key);
    }
}

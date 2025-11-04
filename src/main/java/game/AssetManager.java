package game;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    // images
    private static Map<String, Image> images = new HashMap<>();
    // sounds
    private static Map<String, AudioClip> sounds = new HashMap<>();
    // music
    private static Map<String, Map<String, Media>> musics = new HashMap<>();

    public static void loadAssets() {
        // load images
        try {
            imageInput("icon", Constant.ICON_PATH);
            imageInput("ball", Constant.BALL_PATH);
            imageInput("paddle", Constant.PADDLE_IMAGE_PATH);

            imageInput("STONE_BRICK", Constant.STONE_BRICK_IMAGE);
            imageInput("IRON_BRICK", Constant.IRON_BRICK_IMAGE);
            imageInput("GOLD_BRICK", Constant.GOLD_BRICK_IMAGE);
            imageInput("DIAMOND_BRICK", Constant.DIAMOND_BRICK_IMAGE);
            imageInput("NETHERITE_BRICK", Constant.NETHERITE_BRICK_IMAGE);
            imageInput("BED_ROCK", Constant.BED_ROCK_IMAGE);

            imageInput("destroy_stage_1", Constant.DESTROY_STAGE_1);
            imageInput("destroy_stage_2", Constant.DESTROY_STAGE_2);
            imageInput("destroy_stage_3", Constant.DESTROY_STAGE_3);
            imageInput("destroy_stage_4", Constant.DESTROY_STAGE_4);
            imageInput("destroy_stage_5", Constant.DESTROY_STAGE_5);
            imageInput("destroy_stage_6", Constant.DESTROY_STAGE_6);
            imageInput("destroy_stage_7", Constant.DESTROY_STAGE_7);
            imageInput("destroy_stage_8", Constant.DESTROY_STAGE_8);
            imageInput("destroy_stage_9", Constant.DESTROY_STAGE_9);
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
        musics.put("Home_Background", new HashMap<>());
        try {
            musics.get("Home_Background").put("home_background_music",
                    new Media(AssetManager.class.getResource(Constant.HOME_BACKGROUND_MUSIC).toString()));
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

    public static Media getMusic(String category, String key) {
        return musics.get(category).get(key);
    }

    public static void imageInput(String key, String path) {
        try (InputStream inputStream = AssetManager.class.getResourceAsStream(path)) {
            if (inputStream == null) {
                System.err.println("Image resource not found: " + path);
                return;
            }
            images.put(key, new Image(inputStream));
        } catch (Exception e) {
            System.err.println("Failed to load image " + path + ": " + e.getMessage());
        }
    }

}

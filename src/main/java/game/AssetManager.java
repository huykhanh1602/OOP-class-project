package game;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class AssetManager {
    // level
    private static Map<String, String> level = new HashMap<>();

    // images
    private static Map<String, Image> images = new HashMap<>();

    // sounds
    private static Map<String, AudioClip> sounds = new HashMap<>();

    // music
    private static Map<String, Media> musics = new HashMap<>();

    public static void loadAssets() {
        try {
            level.put("level1", Constant.LEVEL_1);
            level.put("level2", Constant.LEVEL_2);
            level.put("level3", Constant.LEVEL_3);
            level.put("level4", Constant.LEVEL_4);
            level.put("level5", Constant.LEVEL_5);
            level.put("test", Constant.TEST);
        } catch (Exception e) {
            System.out.println("Error to load level");
        }


        // load images
        try {
            images.put("icon", new Image(AssetManager.class.getResourceAsStream(Constant.ICON_PATH)));
            images.put("ball", new Image(AssetManager.class.getResourceAsStream(Constant.BALL_PATH)));
            images.put("paddle", new Image(AssetManager.class.getResourceAsStream(Constant.PADDLE_IMAGE_PATH)));

            images.put("stone_brick",new Image(AssetManager.class.getResourceAsStream(Constant.STONE_BRICK_IMAGE)));
            images.put("iron_brick",new Image(AssetManager.class.getResourceAsStream(Constant.IRON_BRICK_IMAGE)));
            images.put("gold_brick",new Image(AssetManager.class.getResourceAsStream(Constant.GOLD_BRICK_IMAGE)));
            images.put("diamond_brick",new Image(AssetManager.class.getResourceAsStream(Constant.DIAMOND_BRICK_IMAGE)));
            images.put("netherite_brick",new Image(AssetManager.class.getResourceAsStream(Constant.NETHERITE_BRICK_IMAGE)));
            images.put("bedrock_brick", new Image(AssetManager.class.getResourceAsStream(Constant.BED_ROCK_IMAGE)));

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
            musics.get("Home_Background").put("home_background_music",
                    new Media(AssetManager.class.getResource(Constant.HOME_BACKGROUND_MUSIC).toString()));
        } catch (Exception e) {
            System.err.println("Error loading music: " + e.getMessage());
        }
    }

    // getter methods
    public static String getLevel(String key) {
        return level.get(key);
    }

    public static Image getImage(String key) {
        return images.get(key);
    }

    public static Media getMusic(String category, String key) {
        return musics.get(category).get(key);
    }

    public static void playSound(String key) {
        List<AudioClip> soundList = sounds.get(key);

        // Kiểm tra xem gói âm thanh có tồn tại và có âm thanh không
        if (soundList == null || soundList.isEmpty()) {
            System.err.println("Sound pack not found or empty: " + key);
            return;
        }

        // Chọn ngẫu nhiên một AudioClip từ List
        int index = random.nextInt(soundList.size());
        AudioClip clipToPlay = soundList.get(index);

        // Phát âm thanh
        clipToPlay.play();
    }

    // helper methods to load assets
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

    public static void soundInput(String category, String key, String path) {
        try {
            AudioClip audioClip = new AudioClip(AssetManager.class.getResource(path).toString());
            sounds.get(category).add(audioClip);
        } catch (Exception e) {
            System.err.println("Failed to load sound " + path + ": " + e.getMessage());
        }
    }

}

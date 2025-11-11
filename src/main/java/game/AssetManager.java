package game;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

import java.util.List;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AssetManager {
    // level
    private final static Map<String, String> level = new HashMap<>();

    // images
    private final static Map<String, Image> images = new HashMap<>();

    // sounds
    private final static Map<String, ArrayList<AudioClip>> sounds = new HashMap<>();

    // music
    private final static Map<String, ArrayList<Media>> musics = new HashMap<>();

    private final static Random random = new Random();

    public static void loadAssets() {
        try {
            imageInput("icon", Constant.ICON_PATH);
            imageInput("slime_ball", Constant.SLIME_BALL);
            imageInput("eod_ball", Constant.EYEOFDRAGON_BALL);
            imageInput("fire_ball", Constant.FIRE_BALL);
            imageInput("snow_ball", Constant.SNOW_BALL);
            imageInput("left_paddle", Constant.LEFT_PADDLE_PATH);
            imageInput("right_paddle", Constant.RIGHT_PADDLE_PATH);
            imageInput("mid_paddle", Constant.MID_PADDLE_PATH);
            imageInput("diretion", Constant.DIRETION);

            imageInput("STONE_BRICK", Constant.STONE_BRICK_IMAGE);
            imageInput("IRON_BRICK", Constant.IRON_BRICK_IMAGE);
            imageInput("GOLD_BRICK", Constant.GOLD_BRICK_IMAGE);
            imageInput("DIAMOND_BRICK", Constant.DIAMOND_BRICK_IMAGE);
            imageInput("NETHERITE_BRICK", Constant.NETHERITE_BRICK_IMAGE);
            imageInput("BED_ROCK", Constant.BED_ROCK_IMAGE);
            imageInput("CHEST", Constant.CHEST_IMAGE);
            imageInput("PORTAL", Constant.PORTAL_IMAGE);

            imageInput("diamond", Constant.DIAMOND);

            imageInput("heart", Constant.HEART);

            // load destroy stage images
            for (int i = 1; i <= 9; i++) {
                String key = "destroy_stage_" + i;
                String path = Constant.DESTROY_STAGE + i + ".png";
                imageInput(key, path);
            }

        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
        }

        // load sounds
        sounds.put("ball_collide", new ArrayList<>());
        sounds.put("brick_break", new ArrayList<>());
        sounds.put("click", new ArrayList<>());
        sounds.put("portal", new ArrayList<>());
        try {
            for (int i = 1; i <= 5; i++) {
                String key = "ball_collide_" + i;
                String path = Constant.BALL_COLLIDE + i + ".wav";
                soundInput("ball_collide", key, path);
            }

            for (int i = 1; i <= 4; i++) {
                String key = "brick_break_" + i;
                String path = Constant.BRICK_BREAK + i + ".wav";
                soundInput("brick_break", key, path);
            }

            soundInput("click", "click_sound", Constant.BASE_SOUND + "click.wav");
            soundInput("portal", "portal_sound", Constant.BASE_SOUND + "portal.wav");

        } catch (Exception e) {
            System.err.println("Error loading sounds: " + e.getMessage());
        }

        // load music
        musics.put("Home_Background", new ArrayList<>());
        musics.put("Game_Background", new ArrayList<>());
        try {
            musics.get("Home_Background").add(
                    new Media(AssetManager.class.getResource(Constant.HOME_BACKGROUND_MUSIC).toString()));
            musics.get("Game_Background").add(
                    new Media(AssetManager.class.getResource(Constant.GAME_BACKGROUND_MUSIC + "cat.mp3").toString()));
            musics.get("Game_Background").add(
                    new Media(AssetManager.class.getResource(Constant.GAME_BACKGROUND_MUSIC + "chirp.mp3").toString()));
            musics.get("Game_Background").add(
                    new Media(AssetManager.class.getResource(Constant.GAME_BACKGROUND_MUSIC + "creator.mp3")
                            .toString()));
        } catch (Exception e) {
            System.err.println("Error loading music: " + e.getMessage());
        }
    }

    // Manage playing sound effects
    public static void playSound(String key) {
        List<AudioClip> soundList = sounds.get(key);

        // Check if the sound pack exists and is not empty
        if (soundList == null || soundList.isEmpty()) {
            System.err.println("Sound pack not found or empty: " + key);
            return;
        }

        // Choose a random AudioClip from the List
        int index = random.nextInt(soundList.size());
        AudioClip clipToPlay = soundList.get(index);
        clipToPlay.setVolume(GameContext.getInstance().getSoundVolume());

        // Play the sound
        clipToPlay.play();
    }

    // Helper methods to load assets
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

    // getter methods
    public static Image getImage(String key) {
        return images.get(key);
    }

    public static Media getMusic(String category) {
        int index = random.nextInt(musics.get(category).size());
        return musics.get(category).get(index);
    }

    public static String getLevel(String key) {
        return level.get(key);
    }
}

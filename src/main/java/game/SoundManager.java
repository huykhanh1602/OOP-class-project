package game;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.media.AudioClip;

public class SoundManager {
    // 1. Singleton Pattern
    private static final SoundManager instance = new SoundManager();

    private SoundManager() {
    }

    public static SoundManager getInstance() {
        return instance;
    }

    // 2. A place to store loaded sound files
    private Map<String, AudioClip> soundCache = new HashMap<>();
    private AudioClip backgroundMusic;

    // 3. Method to preload sounds
    public void loadSounds() {
        try {
            // Load Sounds
            loadSound("bounce", "/sounds/bounce.wav"); // Assuming you have this file
            loadSound("brick_break", "/sounds/break.wav");

            // Load Background Music
            backgroundMusic = new AudioClip(getClass().getResource("/sounds/music.mp3").toExternalForm());
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // Loop indefinitely

        } catch (Exception e) {
            System.err.println("Error loading sounds: " + e.getMessage());
        }
    }

    private void loadSound(String name, String resourcePath) {
        AudioClip sound = new AudioClip(getClass().getResource(resourcePath).toExternalForm());
        soundCache.put(name, sound);
    }

    // 4. Methods for other places to call
    public void playSound(String name) {
        AudioClip sound = soundCache.get(name);
        if (sound != null) {
            // Read volume from GameContext to adjust
            sound.setVolume(GameContext.getInstance().getSoundVolume());
            sound.play();
        }
    }

    public void playMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.setVolume(GameContext.getInstance().getSoundVolume()); // Có thể có âm lượng riêng cho nhạc
            backgroundMusic.play();
        }
    }

    public void stopMusic() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }
}
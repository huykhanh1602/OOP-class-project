package vnu.edu.vn.game;

import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    // 1. Singleton Pattern
    private static final SoundManager instance = new SoundManager();

    private SoundManager() {
    }

    public static SoundManager getInstance() {
        return instance;
    }

    // 2. Một nơi để lưu trữ các file âm thanh đã được tải
    private Map<String, AudioClip> soundCache = new HashMap<>();
    private AudioClip backgroundMusic;

    // 3. Phương thức để tải trước các âm thanh
    public void loadSounds() {
        try {
            // Load Sounds
            loadSound("bounce", "/sounds/bounce.wav"); // Giả sử bạn có file này
            loadSound("brick_break", "/sounds/break.wav");

            // Load Background Music
            backgroundMusic = new AudioClip(getClass().getResource("/sounds/music.mp3").toExternalForm());
            backgroundMusic.setCycleCount(AudioClip.INDEFINITE); // Lặp lại vô hạn

        } catch (Exception e) {
            System.err.println("Error loading sounds: " + e.getMessage());
        }
    }

    private void loadSound(String name, String resourcePath) {
        AudioClip sound = new AudioClip(getClass().getResource(resourcePath).toExternalForm());
        soundCache.put(name, sound);
    }

    // 4. Các phương thức để các nơi khác có thể gọi
    public void playSound(String name) {
        AudioClip sound = soundCache.get(name);
        if (sound != null) {
            // Đọc âm lượng từ GameContext để điều chỉnh
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
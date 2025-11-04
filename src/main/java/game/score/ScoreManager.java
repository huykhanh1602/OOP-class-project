package game.score;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScoreManager {
    private int score = 0;
    private int highScore = 0;
    private final String path = "\\vnu\\edu\\vn\\game\\score\\score.txt";

    public ScoreManager() {
        loadHighScore();
    }

    public void addScore(int amount) {
        score += amount;
        if (score > highScore) {
            highScore = score;
            loadHighScore();
        }
    }

    public void resetScore() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    private void loadHighScore() {
        try {
            Path p = Paths.get(path);
            if (Files.exists(p)) {
                String content = Files.readString(p).trim();
                highScore = Integer.parseInt(content);
            } else {
                highScore = 0;
            }
        } catch (Exception e) {
            highScore = 0;
            System.out.println("Error loading high score");
        }
    }
}

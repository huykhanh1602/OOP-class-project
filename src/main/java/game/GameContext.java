package game;

import javafx.beans.property.SimpleIntegerProperty;

public class GameContext {
    private int highScore;
    private int currentScore;

    private static final GameContext instance = new GameContext();

    private GameContext() {
    }

    public static GameContext getInstance() {
        return instance;
    }

    private int currentDifficulty = 1;
    private double soundVolume = 0.8;
    // private double backgroundMusic = 0.8;

    // Getter and Setter
    public int getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(int difficulty) {
        this.currentDifficulty = difficulty;
    }

    public double getSoundVolume() {
        return soundVolume;
    }

    public void setSoundVolume(double volume) {
        this.soundVolume = volume;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int score) {
        if (score > this.highScore) {
            this.highScore = score;
        }
    }

    public int getCurrentScore() {
        return currentScore.get();
    }

    public void setCurrentScore(int score) {
        this.currentScore.set(score);
    }

    public void addScore(int score) {
        this.currentScore.set(this.currentScore.get() + score);

    }
}
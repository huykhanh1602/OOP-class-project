package game;

import javafx.beans.property.SimpleIntegerProperty;

public class GameContext {
    private static final GameContext instance = new GameContext();

    private GameContext() {
    }

    public static GameContext getInstance() {
        return instance;
    }

    private int currentDifficulty = 1;
    private double soundVolume = 0.8;
    private double backgroundMusic = 0.8;
    private double masterVolume = 0.2;
    private String nameBall = "slime_ball";

    private int highScore = 0;
    public SimpleIntegerProperty currentScore = new SimpleIntegerProperty(0);

    // Game level management
    private int currentLevel = 1;
    private final int maxLevel = Constant.MAX_LEVEL;

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    /**
     * start a new game from level 1
     */
    public void resetLevel() {
        this.currentLevel = 1;
    }

    public void resetScore() {
        this.currentScore.set(0);
    }

    /**
     * increase to next level
     * reset to level 1 if exceed max level
     */
    public void nextLevel() {
        this.currentLevel++;
        if (this.currentLevel > maxLevel) {
            this.currentLevel = 1; // Quay vòng
        }
    }

    // Getter and Setter
    public int getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(int difficulty) {
        this.currentDifficulty = difficulty;
    }

    public double getSoundVolume() {
        return soundVolume * masterVolume;
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

    public double getBackgroundMusic() {
        return backgroundMusic * masterVolume;
    }

    public void setBackgroundMusic(double backgroundMusic) {
        this.backgroundMusic = backgroundMusic;
    }

    public double getMasterVolume() {
        return masterVolume;
    }

    public void setMasterVolume(double masterVolume) {
        this.masterVolume = masterVolume;
    }

    public String getNameBall() {
        return nameBall;
    }

    public void setNameBall(String nameBall) {
        this.nameBall = nameBall;
    }
    
    public void resetCurrentScore() {
        currentScore = new SimpleIntegerProperty(0);
    }
}
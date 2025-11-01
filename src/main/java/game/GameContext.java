package game;

public class GameContext {
    private static final GameContext instance = new GameContext();

    private GameContext() {
    }

    public static GameContext getInstance() {
        return instance;
    }

    // Storage of virable
    private int currentDifficulty = 1;
    private int highScore = 0;
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
        this.highScore = score;
    }
}
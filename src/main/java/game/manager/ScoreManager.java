package game.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static game.Constant.SCORE_PATH;

public class ScoreManager {
    private static int score = 0;
    private int highScore = 0;
    private final String path = "/game/score/score.txt";

    private static final ScoreManager instance = new ScoreManager();

    public static ScoreManager getInstance() {
        return instance;
    }

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
        } catch (IOException | NumberFormatException e) {
            highScore = 0;
            System.out.println("Error loading high score");
        }
    }

    private void saveHighScore() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(String.valueOf(highScore));

        } catch (Exception e) {
            System.out.println("Error saving high score");
        }
    }

    public static int updateTopScores(int finalScore) {
        List<Integer> scores = new ArrayList<>();
        try {
            if (Files.exists(SCORE_PATH)) {
                for (String line : Files.readAllLines(SCORE_PATH, StandardCharsets.UTF_8)) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        try {
                            scores.add(Integer.valueOf(line));
                        } catch (NumberFormatException ignore) {
                            // skip invalid line
                        }
                    }
                }
            }

            scores.add(finalScore);
            scores.sort(Collections.reverseOrder());
            if (scores.size() > 3) {
                scores = new ArrayList<>(scores.subList(0, 3));
            }

            List<String> outLines = new ArrayList<>();
            for (Integer s : scores) {
                outLines.add(String.valueOf(s));
            }

            Files.createDirectories(SCORE_PATH.getParent());
            Files.write(SCORE_PATH, outLines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scores.isEmpty() ? finalScore : scores.get(0);
    }

    public static void printHighScores() {
        try {
            if (Files.exists(SCORE_PATH)) {
                System.out.println("High Scores:");
                for (String line : Files.readAllLines(SCORE_PATH, StandardCharsets.UTF_8)) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("No high scores recorded yet.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

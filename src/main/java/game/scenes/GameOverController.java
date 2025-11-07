package game.scenes;

import game.abstraction.GameScene;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static game.Constant.SCORE_PATH;

public class GameOverController extends GameScene {
    @FXML
    private Label scoreLabel;

    @FXML
    private Label highScoreLabel;

    public void setScore(int finalScore) {
        java.nio.file.Path scorePath = SCORE_PATH;
        try {
            java.util.List<Integer> scores = new java.util.ArrayList<>();

            if (Files.exists(scorePath)) {
                for (String line : Files.readAllLines(scorePath, StandardCharsets.UTF_8)) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        try {
                            scores.add(Integer.parseInt(line));
                        } catch (NumberFormatException ignore) {
                            // skip invalid line
                        }
                    }
                }
            }

            scores.add(finalScore);
            scores.sort(java.util.Collections.reverseOrder()); // descending
            if (scores.size() > 3) {
                scores = new java.util.ArrayList<>(scores.subList(0, 3));
            }

            java.util.List<String> outLines = new java.util.ArrayList<>();
            for (Integer s : scores) {
                outLines.add(String.valueOf(s));
            }

            Files.createDirectories(scorePath.getParent());
            Files.write(scorePath, outLines, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING,
                    StandardOpenOption.WRITE);

            if (highScoreLabel != null && !scores.isEmpty()) {
                StringBuilder sb = new StringBuilder("Highest Scores:\n");
                for (int i = 0; i < Math.min(3, scores.size()); i++) {
                    sb.append(i + 1).append(". ").append(scores.get(i)).append("\n");
                }
                highScoreLabel.setText(sb.toString().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scoreLabel.setText("Score: " + finalScore);
    }

    public void printHighScores() {
        java.nio.file.Path scorePath = SCORE_PATH;
        try {
            if (Files.exists(scorePath)) {
                System.out.println("High Scores:");
                for (String line : Files.readAllLines(scorePath, StandardCharsets.UTF_8)) {
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

    @FXML
    private void ADS() {
        app.switchToADSScene();
    }
}

package vnu.edu.vn.game.background;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Background {
    private List<Image> backgrounds = new ArrayList<>();
    private int currentIndex = 0;
    private int widthMap, heightMap;

    public Background(int widthMap, int heightMap) {
        this.widthMap = widthMap;
        this.heightMap = heightMap;

        try {
            backgrounds.add(new Image(getClass().getResourceAsStream("/vnu/edu/vn/game/backgrounds/hill.png")));
        } catch (Exception e) {
            System.out.println("Don't find any images");
        }
    }

    public void render(GraphicsContext gc) {
        if (!backgrounds.isEmpty()) {
            gc.drawImage(backgrounds.get(currentIndex), 10, 20, widthMap, heightMap);
        } else {
            gc.setFill(Color.DARKGRAY);
            gc.fillRect(0, 0, 600, 600);
        }
    }

    public void nextBackground() {
        currentIndex = (currentIndex + 1) % backgrounds.size();
    }

    public void setBackground(int index) {
        if (index >= 0 && index < backgrounds.size()) {
            currentIndex = index;
        }
    }
}

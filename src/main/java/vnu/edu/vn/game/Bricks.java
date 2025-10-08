package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/// Like this name

public class Bricks extends GameObject {

    private static int widthBrick = 30;
    private static int heightBrick = 15;

    private boolean destroyed = false;

    public Bricks(double x, double y) {
        super(x, y, widthBrick, heightBrick);
    }

    @Override
    public void update() {
    // Maybe change later
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!destroyed) {
            gc.setFill(Color.GREEN);
            gc.fillRect(x, y, width, height);
        }
    }


    //ERASE BRICK
    public void destroy() {
        destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}

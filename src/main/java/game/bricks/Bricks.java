package game.bricks;

import game.Constant;
import game.objects.GameObject;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/// Like this name

public class Bricks extends GameObject {
    protected int durability;
    protected int point;
    protected boolean destroyable = true;

    public Bricks(double x, double y, int durability, int amount) {
        super(x, y, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
        this.durability = durability;
        this.point = amount;
        if (durability <= -1) {
            destroyable = false;
        }
    }

    // ERASE BRICK
    public boolean isBroken() { // Check if brick is broken
        return durability <= 0;
    }

    public void hit() { // Decrease durability
        if (destroyable == false) {
            return;
        }
        durability--;
    }

    public Rectangle2D getRectBrick() { // Return the rectangle for collision detection
        return new Rectangle2D(x, y, width, height);
    }

    public void update() {
        // Maybe change later
    }

    public void render(GraphicsContext gc) {
        if (durability == 0) {
            return;
        }
        gc.drawImage(image, width, height);
        switch (durability) {
            case 1:
                Color c1 = Color.RED;
                gc.setFill(c1);
                gc.fillRect(x, y, width, height);
                break;
            case 2:
                Color c2 = Color.YELLOW;
                gc.setFill(c2);
                gc.fillRect(x, y, width, height);
                break;
            case 3:
                Color c3 = Color.GREEN;
                gc.setFill(c3);
                gc.fillRect(x, y, width, height);
                break;
        }
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, width, height);

    }

    // getters and setters
    public int getDurability() {
        return durability;
    }

    public void setWidthBrick(double widthBrick) {
        this.width = widthBrick;
    }

    public void setHeightBrick(double heightBrick) {
        this.height = heightBrick;
    }

    public int getPoint() {
        return point;
    }
}

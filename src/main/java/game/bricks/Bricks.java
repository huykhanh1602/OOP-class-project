package game.bricks;

import game.Constant;
import game.objects.GameObject;
import game.AssetManager;
import game.Constant;
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
        gc.drawImage(AssetManager.getImage("stone_brick"), x, y, Constant.BRICK_HEIGHT, Constant.BRICK_WIDTH);

        switch (durability) {
            case 9:
                gc.drawImage(AssetManager.getImage("destroy_stage_1"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 8:
                gc.drawImage(AssetManager.getImage("destroy_stage_2"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 7:
                gc.drawImage(AssetManager.getImage("destroy_stage_3"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 6:
                gc.drawImage(AssetManager.getImage("destroy_stage_4"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 5:
                gc.drawImage(AssetManager.getImage("destroy_stage_5"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 4:
                gc.drawImage(AssetManager.getImage("destroy_stage_6"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 3:
                gc.drawImage(AssetManager.getImage("destroy_stage_7"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 2:
                gc.drawImage(AssetManager.getImage("destroy_stage_8"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 1:
                gc.drawImage(AssetManager.getImage("destroy_stage_9"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
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

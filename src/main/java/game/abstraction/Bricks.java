package game.abstraction;

import game.Constant;
import game.objects.GameObject;
import game.AssetManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/// Like this name

public abstract class Bricks extends GameObject {
    protected String type;
    protected int originalDurability;
    protected int durability;
    protected int point;
    protected boolean destroyable = true;
    protected Color color;

    protected int stage = 10;

    public Bricks(String type, double x, double y, int originalDurability, int amount, Color color) {
        super(x, y, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
        this.type = type;
        this.originalDurability = originalDurability;
        this.durability = originalDurability;
        this.point = amount;
        this.color = color;
        if (durability <= -1) {
            durability = Integer.MAX_VALUE;
            destroyable = false;
        }
    }

    // ERASE BRICK
    public boolean isBroken() { // Check if brick is broken
        return durability <= 0;
    }

    // Decrease durability
    public void hit(double damage) {
        if (destroyable == false) {
            return;
        }
        durability -= damage;
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
        gc.drawImage(AssetManager.getImage(type), x, y, Constant.BRICK_HEIGHT, Constant.BRICK_WIDTH);
        stage = (int) Math.floor((durability * 10) / originalDurability);
        switch (stage) {
            case 8:
                gc.drawImage(AssetManager.getImage("destroy_stage_1"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 7:
                gc.drawImage(AssetManager.getImage("destroy_stage_2"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 6:
                gc.drawImage(AssetManager.getImage("destroy_stage_3"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 5:
                gc.drawImage(AssetManager.getImage("destroy_stage_4"), x, y, Constant.BRICK_HEIGHT,
                        Constant.BRICK_HEIGHT);
                break;
            case 4:
                gc.drawImage(AssetManager.getImage("destroy_stage_5"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 3:
                gc.drawImage(AssetManager.getImage("destroy_stage_6"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 2:
                gc.drawImage(AssetManager.getImage("destroy_stage_7"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 1:
                gc.drawImage(AssetManager.getImage("destroy_stage_8"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
            case 0:
                gc.drawImage(AssetManager.getImage("destroy_stage_9"), x, y,
                        Constant.BRICK_HEIGHT, Constant.BRICK_HEIGHT);
                break;
        }
        // gc.setStroke(Color.BLACK);
        // gc.strokeRect(x, y, width, height);

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

    public Color getColor() {
        return color;
    }

}

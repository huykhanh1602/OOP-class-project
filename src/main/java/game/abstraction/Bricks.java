package game.abstraction;

import game.AssetManager;
import game.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Bricks extends GameObject {
    protected String type;
    protected int originalDurability;
    protected int durability;
    protected int point;
    protected boolean destroyable = true;
    protected Color color;

    protected int stage = 10;

    private double scale = 1.0;
    private double scaleSpeed = 0;

    public Bricks(String type, double x, double y, int originalDurability, int amount, Color color) {
        super(x + Constant.BRICK_WIDTH / 2.0f, y + Constant.BRICK_HEIGHT / 2.0f, Constant.BRICK_WIDTH,
                Constant.BRICK_HEIGHT);
        this.type = type;
        this.originalDurability = originalDurability;
        this.durability = originalDurability;
        this.point = amount;
        this.color = color;
        if (durability <= -1) {
            destroyable = false;
        }
    }

    public void hitAnimation() {
        scaleSpeed = -0.05; // start shrinking
    }

    // ERASE BRICK
    public boolean isBroken() { // Check if brick is broken
        return durability == 0;
    }

    public void update() {
        if (scaleSpeed != 0) {
            scale += scaleSpeed;

            if (scale <= 0.75) {
                scaleSpeed = Math.abs(scaleSpeed); // change direction → grow back
            }
            if (scale >= 1.0) {
                scale = 1.0;
                scaleSpeed = 0;
            }
        }
    }

    // Decrease durability
    public void hit(double damage) {
        if (destroyable == false) {
            return;
        }
        if (durability > damage) {
            durability -= damage;
        } else {
            durability = 0;
        }
    }

    // Return the rectangle for collision detection
    public Rectangle2D getRectBrick() { 
        return new Rectangle2D(x - Constant.BRICK_WIDTH / 2.0f + 10, y - Constant.BRICK_HEIGHT / 2.0f + 10,
                Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
    }

    public void render(GraphicsContext gc) {
        if (durability == 0) {
            return;
        }
        gc.save();
        gc.translate(x, y);
        gc.scale(scale, scale);
        gc.drawImage(AssetManager.getImage(type), -Constant.BRICK_WIDTH / 2.0f, -Constant.BRICK_HEIGHT / 2.0f,
                Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
        stage = 9 - ((int) Math.floor((durability * 10) / originalDurability));

        gc.drawImage(AssetManager.getImage("destroy_stage_" + stage), -Constant.BRICK_WIDTH / 2.0f,
                -Constant.BRICK_HEIGHT / 2.0f, Constant.BRICK_HEIGHT,
                Constant.BRICK_HEIGHT);

        gc.setFill(Color.RED);
        gc.restore();
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

    public boolean isDestroyable() {
        return destroyable;
    }

    public String getType() {
        return type;
    }
}

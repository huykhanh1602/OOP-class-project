package game.objects;

import java.nio.channels.Channel;

import game.AssetManager;
import game.Constant;
import game.abstraction.GameObject;
import game.powerup.merchant;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Paddle extends GameObject {
    /// ELEMENT PADDLE
    private final double speed = 3; // SPEED PADDLE

    private boolean moveLeft = false;
    private boolean moveRight = false; // MOVEMENT

    public Paddle() {
        this.width = Constant.BRICK_WIDTH * 3 + Constant.BRICK_WIDTH * merchant.paddleSize;
        this.height = Constant.BRICK_HEIGHT;
        this.x = Constant.CANVAS_WIDTH / 2 - width / 2;
        this.y = Constant.CANVAS_HEIGHT - 75 - height;
    }


    /// MOVEMENT
    public void update() {
        if (moveLeft && x > 0)
            x -= speed;
        if (moveRight && x < 1000 - width)
            x += speed;
    }

    /// RENDER
    public void render(GraphicsContext gc) {

        try {
            int i = 0;
            while (i != width / Constant.BRICK_WIDTH) {
                gc.drawImage(AssetManager.getImage("paddle"), x + i * Constant.BRICK_WIDTH, y, Constant.BRICK_WIDTH,
                        Constant.BRICK_HEIGHT);
                i++;
            }
        } catch (Exception e) {
            System.out.println("Cant load paddle image");
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, width, height);
        }
    }

    public void handleKeyPressed(KeyEvent key) {
        if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A)
            moveLeft = true;
        if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D)
            moveRight = true;
    }

    public void handleKeyReleased(KeyEvent key) {
        if (key.getCode() == KeyCode.LEFT || key.getCode() == KeyCode.A)
            moveLeft = false;
        if (key.getCode() == KeyCode.RIGHT || key.getCode() == KeyCode.D)
            moveRight = false;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean getMoveLeft() {
        return moveLeft;
    }

    public boolean getMoveRight() {
        return moveRight;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(this.x, this.y, this.width, this.height);
    }
}

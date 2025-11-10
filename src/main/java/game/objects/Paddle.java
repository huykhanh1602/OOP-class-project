package game.objects;

import static game.Constant.HEIGHT_SCREEN;
import game.AssetManager;
import javafx.geometry.Rectangle2D;
import game.Constant;
import game.abstraction.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/// Just Paddle

public class Paddle extends GameObject {
    /// ELEMENT PADDLE
    private final double speed = 5; // SPEED PADDLE

    private boolean moveLeft = false;
    private boolean moveRight = false; // MOVEMENT

    Image paddleImage;

    public Paddle() {
        this.width = Constant.BRICK_WIDTH * 6;
        this.height = Constant.BRICK_HEIGHT;
        this.x = 1000/2 - width/2;
        this.y = HEIGHT_SCREEN - 75 - height;
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
            while (i != width/Constant.BRICK_WIDTH) {
            paddleImage = AssetManager.getImage("STONE_BRICK");
            gc.drawImage(paddleImage, x + i * Constant.BRICK_WIDTH, y, Constant.BRICK_WIDTH, Constant.BRICK_HEIGHT);
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
        // (Hãy thay đổi các biến 'x', 'y', 'widthPaddle', 'heightPaddle'
        // cho khớp với tên biến trong lớp Paddle của bạn)
        return new Rectangle2D(this.x, this.y, this.width, this.height);
    }
}

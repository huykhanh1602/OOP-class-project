package game;

import java.io.IOException;

import static game.Constant.WIDTH_SCREEN;

import game.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/// Just Paddle

public class Paddle {
    /// ELEMENT PADDLE
    private double x, y;
    private int widthPaddle = 120;
    private int heightPaddle = 15;
    private double speed = 5; // SPEED PADDLE

    private final double boundaryPaddle = WIDTH_SCREEN * 3 / 4;

    private boolean moveLeft = false;
    private boolean moveRight = false; // MOVEMENT

    private Image paddleImage;

    public Paddle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /// MOVEMENT
    public void update() {
        if (moveLeft && x > 0)
            x -= speed;
        if (moveRight && x < boundaryPaddle - widthPaddle)
            x += speed;
    }

    /// RENDER
    public void render(GraphicsContext gc) {
        try {
            paddleImage = new Image(getClass().getResourceAsStream(Constant.PADDLE_IMAGE_PATH));
            gc.drawImage(paddleImage, x, y, widthPaddle, heightPaddle);
        } catch (Exception e) {
            System.out.println("Cant load paddle image");
            gc.setFill(Color.BLUE);
            gc.fillRect(x, y, widthPaddle, heightPaddle);
        }
    }

    /// CHECK KEY
    // public void moveLeft() {
    // moveLeft = moveLeft ? false : true;
    // }
    //
    // public void moveRight() {
    // moveRight = moveRight ? false : true;
    // }
    //
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

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidthPaddle() {
        return widthPaddle;
    }

    public int getHeightPaddle() {
        return heightPaddle;
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
}

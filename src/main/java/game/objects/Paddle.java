package game.objects;

import game.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

/// Just Paddle

public class Paddle {
    /// ELEMENT PADDLE
    private double x, y;
    private int widthPaddle = 120;
    private int heightPaddle = 15;
    private double speed = 5; // SPEED PADDLE

    private final double boundaryPaddle = Constant.WIDTH_SCREEN * 3 / 4;

    private boolean moveLeft = false;
    private boolean moveRight = false; // MOVEMENT

    public Paddle(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /// MOVEMENT
    public void update() {
        if (moveLeft && x > 10)
            x -= speed;
        if (moveRight && x < boundaryPaddle - widthPaddle + 10)
            x += speed;
    }

    /// RENDER
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, widthPaddle, heightPaddle);
    }

    /// CHECK KEY
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
}

package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;


/// Just Paddle

public class Paddle extends GameObject {
    //SIZE PADDLE - will be change how work later
    private static int widthPaddle = 60;
    private static int heightPaddle = 15;

    private double speed = 5;               //SPEED PADDLE
    private boolean moveLeft, moveRight;    //MOVEMENT

    private int boundaryPaddle;

    public Paddle(double x, double y, int boundaryPaddle) {
        super(x, y, widthPaddle, heightPaddle);
        this.boundaryPaddle = boundaryPaddle;
    }


    /// MOVEMENT
    @Override
    public void update() {
        if (moveLeft && x > 0) x -= speed;
        if (moveRight && x < boundaryPaddle - widthPaddle) x += speed;
    }


    /// RENDER
    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, widthPaddle, heightPaddle);
    }


    /// CHECK KEY
    public void onKeyPressed(KeyCode key) {
        if (key == KeyCode.LEFT) moveLeft = true;
        if (key == KeyCode.RIGHT) moveRight = true;
    }

    public void onKeyReleased(KeyCode key) {
        if (key == KeyCode.LEFT) moveLeft = false;
        if (key == KeyCode.RIGHT) moveRight = false;
    }
}

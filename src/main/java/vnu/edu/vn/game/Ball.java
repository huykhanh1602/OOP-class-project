package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

///  Ball movement

public class Ball extends GameObject {
    //Size ball
    private final static int widthBall = 15;
    private final static int heightBall = 15;
    //Vector speed
    private double dx = 2, dy = -2;

    private int boundaryWidth, boundaryHeight;

    public Ball(double x, double y, int boundaryWidth, int boundaryHeight) {
        super(x, y, widthBall, heightBall);
        this.boundaryWidth = boundaryWidth;
        this.boundaryHeight = boundaryHeight;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        // Bounce off walls
        if (x <= 0 || x + width >= boundaryWidth) dx *= -1;
        if (y <= 0) dy *= -1;
        if (x >= boundaryWidth) dx *= -1;
        if (y >= boundaryHeight) dy *= -1;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x, y, widthBall, heightBall);
    }

    public void bounceY() {
        dy *= -1;
    }
}

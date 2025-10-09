package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

///  Ball movement

public class Ball extends GameObject {
    //Size ball
    private static int widthBall = 15;
    private static int heightBall = 15;
    //Speed Ball
    private double speedball = 2;
    private double dx, dy;

    private int boundaryWidth, boundaryHeight;

    public Ball(double x, double y, int boundaryWidth, int boundaryHeight) {
        super(x, y, widthBall, heightBall);
        this.boundaryWidth = boundaryWidth;
        this.boundaryHeight = boundaryHeight;

        double angle = Math.toRadians(Math.random()*360);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        dx = speedball*cos;
        dy = speedball*sin;
    }

    @Override
    public void update() {
        x += dx;
        y += dy;

        // Bounce off walls
        if (x <= 0 || x + widthBall >= boundaryWidth) dx *= -1;
        if (y <= 0 || y + heightBall >= boundaryHeight) dy *= -1;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x, y, widthBall, heightBall);
    }
    public void bounceY() {
        dy *= -1;
    }
    public void bounceX() {
        dx *= -1;
    }
    public void normalizeVelocity() {
        double lengthvector = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        dx = dx/lengthvector*speedball;
        dy = dy/lengthvector*speedball;
    }
    public double getspeedballnow() {
        return speedball;
    }
    public void setspeedball(double speedball) {
        this.speedball = speedball;
        normalizeVelocity();
    }
}

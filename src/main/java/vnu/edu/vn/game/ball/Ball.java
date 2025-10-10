package vnu.edu.vn.game.ball;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.GameObject;
import vnu.edu.vn.game.Paddle;

import static java.lang.Math.abs;

///  Ball movement

public class Ball{
    /// ELEMENT BALL
    private double x, y;
    private static double radius = 7.5;                 //Size ball
    private double dx, dy;                              //Vector speed
    private double speedball = 2;


//    private int boundaryWidth =  600*3/4;
//    private int boundaryHeight = 600*5/6;

    public Ball(double x, double y) {
        this.x = x+getRadius();
        this.y = y+getRadius();

        double angle = Math.toRadians(Math.random()*360);
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        dx = speedball*cos;
        dy = -abs(speedball*sin);
    }

    public void bounceX() { dx = -dx; }
    public void bounceY() { dy = -dy; }

    public Rectangle2D getRect() {
        return new Rectangle2D(x, y, radius * 2, radius * 2);
    }

    public boolean intersects(Rectangle2D rect) {
        return rect.intersects(getRect());
    }

    public boolean collides(Paddle paddle) {
        return x + radius > paddle.getX() &&
                x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                y + radius > paddle.getY() &&
                y - radius < paddle.getY() + paddle.getHeightPaddle();
    }

    public void normalizeVelocity() {
        double lengthvector = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        dx = dx/lengthvector*speedball;
        dy = dy/lengthvector*speedball;
    }

    public void bounce() {
        dy *= -1;
    }



    public void update() {
        x += dx;
        y += dy;

//        // Bounce off walls
//        if (x - radius <= 10 || x + radius >= boundaryWidth) dx *= -1;
//        if (y <= 20 + radius) dy *= -1;
    }


    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }


//    public boolean isOutOfBounds() {
//        return y - radius > boundaryHeight + 40;
//    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public double getRadius() {
        return radius;
    }

    public double getSpeedball() {
        return speedball;
    }

    public static void setRadius(double radius) {
        Ball.radius = radius;
    }

    public void setSpeedball(double speedball) {
        this.speedball = speedball;
    }
}

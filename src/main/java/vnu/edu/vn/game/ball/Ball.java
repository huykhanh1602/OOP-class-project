package vnu.edu.vn.game.ball;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.GameObject;
import vnu.edu.vn.game.Paddle;

///  Ball movement

public class Ball{
    /// ELEMENT BALL
    private double x, y;
    private double radius = 7.5;                 //Size ball
    private double dx = 2, dy = -2;             //Vector speed

//    private int boundaryWidth =  600*3/4;
//    private int boundaryHeight = 600*5/6;

    public Ball(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void bounceX() { dx = -dx; }
    public void bounceY() { dy = -dy; }

    public Rectangle2D getRect() {
        return new Rectangle2D(x, y, radius * 2, radius * 2);
    }

    public boolean intersects(Rectangle2D rect) {
        return rect.intersects(getRect());
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


    public boolean collides(Paddle paddle) {
        return x + radius > paddle.getX() &&
                x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                y + radius > paddle.getY() &&
                y - radius < paddle.getY() + paddle.getHeightPaddle();
    }

    public void bounce() {
        dy *= -1;
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
}

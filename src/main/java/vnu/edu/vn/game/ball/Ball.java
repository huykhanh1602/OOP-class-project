package vnu.edu.vn.game.ball;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.GameObject;
import vnu.edu.vn.game.Paddle;
import vnu.edu.vn.game.bricks.Bricks;

import static java.lang.Math.abs;

///  Ball movement

public class Ball{
    /// ELEMENT BALL
    private double x, y;
    private static double radius = 7.5;                 //Size ball
    private double dx, dy;                              //Vector speed
    private double speedball = 2;
    private boolean isRunning = false;

    private double fixBug = 2;                          //GIẢM KHOẢNG CÁCH VA CHẠM


//    private int boundaryWidth =  600*3/4;
//    private int boundaryHeight = 600*5/6;

    public Ball(double x, double y) {
        this.x = x + radius;                                        //POSITION
        this.y = y + radius;

        double angle = Math.toRadians(Math.random()*120+30);        //RANDOM ANGLE
        double sin = Math.sin(angle);
        double cos = Math.cos(angle);
        dx = speedball*cos;
        dy = -abs(speedball*sin);
    }

    public void bounceX() { dx = -dx; }                             //RIGHT & LEFT
    public void bounceY() { dy = -dy; }                             //UP & DOWN

    public Rectangle2D getRect() {
        return new Rectangle2D(x, y, radius * 2, radius * 2);
    }

    public boolean intersects(Rectangle2D rect) {                   //Trả về thuộc tính để kiểm tra va chạm
        return rect.intersects(getRect());
    }

    public void collides(Paddle paddle) {                           //Va chạm với Paddle

        if (x + radius > paddle.getX() &&                                       //EDGE
                x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                y + radius > paddle.getY() &&
                        y + radius < paddle.getY() + speedball * 1.5
        ) {
            bounceY();
        }
         if (y + radius > paddle.getY() &&                                      //FLAT
                 y - radius < paddle.getY() + paddle.getHeightPaddle() &&
                 ((x + radius > paddle.getX() &&
                         x + radius < paddle.getX() + speedball * 1.5) ||
                         (x - radius < paddle.getX() +  paddle.getWidthPaddle() &&
                                 x - radius > paddle.getX() + paddle.getWidthPaddle() - speedball * 1.5))
         ) {
             bounceX();
        }
    }

    public void collides(Ball ball) {                           //Va chạm với tường

        if (x <= 10 + radius || x + radius * 2 >= 600 * 3 / 4) bounceX();       //WALL
        if (y <= 20 + radius) bounceY();                                        //FLOOR
    }

    public void collides(Bricks brick) {                        //Va chạm với brick

        if ((x + radius > brick.getX()&&
                x - radius < (brick.getX() + brick.getWidthBrick())) &&
                ((y + radius > brick.getY() &&
                y + radius < brick.getY() + speedball * 3) ||                                           //UP
                        (y - radius < brick.getY() + brick.getHeightBrick() &&
                                y - radius > brick.getY() + brick.getHeightBrick() - speedball * 3))    //DOWN
        ) {
            bounceY();
            System.out.println("Bounce Y");
        }
        if (y + radius > brick.getY() &&
                y - radius < (brick.getY() + brick.getHeightBrick()) &&
                ((x + radius > brick.getX() &&
                        x + radius < brick.getX() + speedball * 3) ||                                   //LEFT
                        (x - radius < brick.getX() +  brick.getWidthBrick() &&
                                x - radius > brick.getX() + brick.getWidthBrick() - speedball * 3))     //RIGHT
        ) {
            bounceX();
        }
    }

    public void normalizeVelocity() {                           //Chuẩn hóa vector

        double lengthvector = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        dx = dx/lengthvector*speedball;
        dy = dy/lengthvector*speedball;
    }

    public void handleKeyPressed(KeyEvent key) {
        if (key.getCode() == KeyCode.W)  setRunning(true);
    }

    public void handleKeyReleased(KeyEvent key) {
        if (key.getCode() == KeyCode.W)  setRunning(false);
    }





    public void update() {
        if(isRunning) {
            x += dx;
            y += dy;
        }

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

    public void setRunning(boolean running) {
        isRunning = running;
    }
}

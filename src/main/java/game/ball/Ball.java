package game.ball;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import game.Constant;
import game.bricks.Bricks;
import game.objects.Paddle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

///  Ball movement

public class Ball {

    private Image ballImage;
    /// ELEMENT BALL
    private double x, y;
<<<<<<< Updated upstream
    private static double radius = 7.5; // Size ball
    private double dx, dy; // Vector speed
    private double speedball = 2;
=======
    private static double radius = 10
    ; // Size ball
    private double dx, dy; // Vector speed
    private double speedball = 5; // Ball speed
>>>>>>> Stashed changes
    public boolean isRunning = false;

    private double friction = 0.1; // Ma sát

    public Ball(double x, double y) {
        this.x = x + radius; // POSITION
        this.y = y + radius;
        try {
            ballImage = new Image(getClass().getResourceAsStream("/vnu/edu/vn/game/images/ball.png"));
        } catch (Exception e) {
            System.out.println("Cant load ball image");
            ballImage = null;
        }

    }

    public void bounceX() {
        dx = -dx;
    } // RIGHT & LEFT

    public void bounceY() {
        dy = -dy;
    } // UP & DOWN

    public Rectangle2D getRect() {
        return new Rectangle2D(x, y, radius * 2, radius * 2);
    }

    public boolean intersects(Rectangle2D rect) { // Return attribute for collision detection
        return rect.intersects(getRect());
    }

    public void collides(Paddle paddle) { // paddle collision
<<<<<<< Updated upstream

        if (x + radius > paddle.getX() && // EDGE
                x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                y + radius > paddle.getY() &&
                y + radius < paddle.getY() + speedball * 1.5) {
            y = paddle.getY() - radius;
            bounceY();

            // Paddle friction
            double paddleMoment = paddle.getSpeed();
                if(paddle.getMoveLeft())
                    dx -= paddleMoment * friction;
                if(paddle.getMoveRight())
                    dx += paddleMoment * friction;

            // double maxSpeedX = Math.abs(dy) * 1.2;
            // if (dx > maxSpeedX)
            //     dx = maxSpeedX;
            // if (dx < -maxSpeedX)
            //     dx = -maxSpeedX;
        }
        if (y + radius > paddle.getY() && // SIDE
                y - radius < paddle.getY() + paddle.getHeightPaddle() &&
                ((x + radius > paddle.getX() &&
                        x + radius < paddle.getX() + speedball * 1.5) ||
                        (x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                                x - radius > paddle.getX() + paddle.getWidthPaddle() - speedball * 1.5))) {
            bounceX();
        }
    }

    public void collides(Ball ball) { // wall collision

        if (x <= 10 + radius || x + radius * 2 >= 1000)
            bounceX(); // WALL
        if (y <= 20 + radius)
            bounceY(); // FLOOR
    }

    // public void collides(Bricks brick) { //Va chạm với brick
    //
    // if (x + radius > brick.getX()&& //UP
    // x - radius < (brick.getX() + brick.getWidth()) &&
    // y + radius > brick.getY() &&
    // y + radius < brick.getY() + dy * fixBug) {
    // y = brick.getY() - radius;
    // bounceY();
    // System.out.println("Bounce Y Up");
    // }
    //
    // if ((x + radius > brick.getX()&& //DOWN
    // x - radius < (brick.getX() + brick.getWidth())) &&
    // y - radius < brick.getY() + brick.getHeight() &&
    // y - radius > brick.getY() + brick.getHeight() - abs(dy) * fixBug) {
    // y = brick.getY() + brick.getHeight() + radius;
    // bounceY();
    // System.out.println("Bounce Y Down");
    // }
    //
    // if (y + radius > brick.getY() && //Left
    // y - radius < (brick.getY() + brick.getHeight()) &&
    // x + radius > brick.getX() &&
    // x + radius < brick.getX() + dx * fixBug) {
    // x = brick.getX() - radius;
    // bounceX();
    // System.out.println("Bounce X Left");
    // }
    //
    // if (y + radius > brick.getY() && //Right
    // y - radius < (brick.getY() + brick.getHeight()) &&
    // x - radius < brick.getX() + brick.getWidth() &&
    // x - radius > brick.getX() + brick.getWidth() + abs(dx) * fixBug) {
    // x = brick.getX() + brick.getWidth() + radius;
    // bounceX();
    // System.out.println("Bounce X Right");
    // }
    // }

    public void collides(Bricks brick) {
        // Ball center coordinates
=======
        // Tâm bóng
>>>>>>> Stashed changes
        double ballCenterX = x;
        double ballCenterY = y;

        // Tìm điểm gần nhất trên paddle so với tâm bóng
        double closestX = clamp(ballCenterX, paddle.getX(), paddle.getX() + paddle.getWidth());
        double closestY = clamp(ballCenterY, paddle.getY(), paddle.getY() + paddle.getHeight());

<<<<<<< Updated upstream
        // Calculate the distance between the centers of the ball and the brick on each axis
        double diffX = ballCenterX - brickCenterX;
        double diffY = ballCenterY - brickCenterY;
=======
        // Tính khoảng cách từ tâm bóng đến điểm gần nhất
        double distanceX = ballCenterX - closestX;
        double distanceY = ballCenterY - closestY;
>>>>>>> Stashed changes

        double distanceSquared = distanceX * distanceX + distanceY * distanceY;

        // Nếu khoảng cách nhỏ hơn bán kính bình phương => va chạm
        if (distanceSquared < radius * radius) {
            // Xác định hướng va chạm chính
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                bounceX();
                if (distanceX > 0) {
                    x = closestX + radius;
                } else {
                    x = closestX - radius;
                }
            } else {
                bounceY();
                if (distanceY > 0) {
                    y = closestY + radius;
                } else {
                    y = closestY - radius;
                }
                double paddleMoment = paddle.getSpeed();
                if (paddle.getMoveLeft()) {
                    dx -= paddleMoment * friction;
                }
                if (paddle.getMoveRight()) {
                    dx += paddleMoment * friction;
                }
            }
        }
    }

    public void collides(Ball ball) { // wall collision
        if (x <= 10 + radius || x + radius * 2 >= 1000) {
            bounceX(); // WALL
        }
        if (y <= 20 - radius) {
            bounceY(); // FLOOR
        }
        
    }

    public void collides(Bricks brick) {
        double brickCenterX = brick.getX() + brick.getWidth()/2.0f;
        double brickCenterY = brick.getY() + brick.getHeight()/2.0f;

        double closestX = Math.abs(x - brickCenterX);
        double closestY = Math.abs(y - brickCenterY);

        if (closestX == closestY) {
            bounceX();
            bounceY();
        } else if (closestX < closestY) {
            bounceY();
        } else {
            bounceX();
        }
    }

    // Hàm clamp để giới hạn giá trị trong khoảng min-max
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public void launchBall(double vx, double vy) {
        if (!isRunning) {
            isRunning = true;
            this.dx = vx*speedball;
            this.dy = vy*speedball;
        }
    }

    public void update(Paddle paddle) {
        if (dx/dy == 1) {dy += 0.01;}
        normalizeVelocity();
        if (dx > speedball) dx = speedball;
        if (dx < -speedball) dx = -speedball;
        if (isRunning) {
            x += dx;
            y += dy;
        } else {
            this.x = paddle.getX() + paddle.getWidth() / 2;
            this.y = paddle.getY() - radius;
        }
<<<<<<< Updated upstream
        normalizeVelocity();
        // // Bounce off walls
        // if (x - radius <= 10 || x + radius >= boundaryWidth) dx *= -1;
        // if (y <= 20 + radius) dy *= -1;
=======
>>>>>>> Stashed changes
    }

    public void render(GraphicsContext gc) {
        if (ballImage != null) {
            // use drawImage method instead of fillOval
            gc.drawImage(ballImage, x - radius, y - radius, radius * 2, radius * 2);
        } else {
            // If no image, draw a red oval as a fallback
            gc.setFill(Color.RED);
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

    }

    public void normalizeVelocity() {
        double length = sqrt(dx * dx + dy * dy);
        if (length != 0) {
            dx = dx / length * speedball;
            dy = dy / length * speedball;
        }
    }  

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

    public boolean isRunning() {
        return isRunning;
    }
}

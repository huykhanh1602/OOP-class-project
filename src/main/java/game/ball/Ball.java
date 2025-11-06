package game.ball;

import game.AssetManager;
import game.abstraction.Bricks;
import game.objects.Paddle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

///  Ball movement

public abstract class Ball {
    /// ELEMENT BALL
    private double x, y;
    private double radius = 10; // Size ball
    private double dx, dy; // Vector speed
    private double speedball = 5;
    public boolean isRunning = false;
    private double damege;
    private double Maxcollision;

    private double friction = 0.2; // Ma sát

    // private double fixBug = 5; // colliding distance

    // private int boundaryWidth = 600*3/4;
    // private int boundaryHeight = 600*5/6;

    public Ball(double x, double y) {
        this.x = x + radius; // POSITION
        this.y = y + radius;
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

        if (x + radius > paddle.getX() && // EDGE
                x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                y + radius > paddle.getY() &&
                y + radius < paddle.getY() + speedball * 1.5) {
            y = paddle.getY() - radius;
            bounceY();
            AssetManager.playSound("ball_collide");

            // Paddle friction
            double paddleMoment = paddle.getSpeed();
            if (paddle.getMoveLeft())
                dx -= paddleMoment * friction;
            if (paddle.getMoveRight())
                dx += paddleMoment * friction;

            double maxSpeedX = Math.abs(dy) * 1.2;
            if (dx > maxSpeedX)
                dx = maxSpeedX;
            if (dx < -maxSpeedX)
                dx = -maxSpeedX;
        }
        if (y + radius > paddle.getY() && // SIDE
                y - radius < paddle.getY() + paddle.getHeightPaddle() &&
                ((x + radius > paddle.getX() &&
                        x + radius < paddle.getX() + speedball * 1.5) ||
                        (x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                                x - radius > paddle.getX() + paddle.getWidthPaddle() - speedball * 1.5))) {
            bounceX();
            AssetManager.playSound("ball_collide");
        }
    }

    public void collides(Ball ball) { // wall collision

        if (x <= 10 + radius || x + radius * 2 >= 1000) {
            bounceX(); // WALL
            AssetManager.playSound("ball_collide");
        }

        if (y <= 20 + radius) {
            bounceY(); // FLOOR
            AssetManager.playSound("ball_collide");
        }
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
        double ballCenterX = x;
        double ballCenterY = y;

        // Brick center coordinates
        double brickCenterX = brick.getX() + brick.getWidth() / 2.0f;
        double brickCenterY = brick.getY() + brick.getHeight() / 2.0f;

        // Calculate the distance between the centers of the ball and the brick on each
        // axis
        double diffX = ballCenterX - brickCenterX;
        double diffY = ballCenterY - brickCenterY;

        // Calculate the combined half-widths and half-heights
        double combinedHalfWidths = radius + brick.getWidth() / 2.0f;
        double combinedHalfHeights = radius + brick.getHeight() / 2.0f;

        // Check for collision
        if (Math.abs(diffX) < combinedHalfWidths && Math.abs(diffY) < combinedHalfHeights) {
            // Collision detected, now determine the collision direction

            // Calculate the overlap on each axis
            double overlapX = combinedHalfWidths - Math.abs(diffX);
            double overlapY = combinedHalfHeights - Math.abs(diffY);

            // The direction with the LESS overlap is the main collision direction
            if (overlapX < overlapY) {
                // Collision occurs horizontally (left or right)
                bounceX();
                // Push the ball out of the brick to avoid sticking
                if (diffX > 0) { // Ball is to the right of the brick
                    x = brick.getX() + brick.getWidth() + radius;
                    // System.out.println("Bounce X Right");
                } else { // Ball is to the left of the brick
                    x = brick.getX() - radius;
                    // System.out.println("Bounce X Left");
                }
            } else {
                // Collision occurs vertically (top or bottom)
                bounceY();
                // Push the ball out of the brick to avoid sticking
                if (diffY > 0) { // Ball is below the brick
                    y = brick.getY() + brick.getHeight() + radius;
                    // System.out.println("Bounce Y Down");
                } else { // Ball is above the brick
                    y = brick.getY() - radius;
                    // System.out.println("Bounce Y Up");
                }
            }
        }
    }

    public void normalizeVelocity() { // Normalize vector

        double lengthvector = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        dx = dx / lengthvector * speedball;
        dy = dy / lengthvector * speedball;
    }

    public void launchBall(double dx, double dy) {
        if (!isRunning) {
            isRunning = true;
            this.dx = dx;
            this.dy = dy;
        }
    }

    public void update(Paddle paddle) {
        if (isRunning) {
            x += dx;
            y += dy;
        } else {
            this.x = paddle.getX() + paddle.getWidthPaddle() / 2;
            this.y = paddle.getY() - radius;
        }
        normalizeVelocity();
        // // Bounce off walls
        // if (x - radius <= 10 || x + radius >= boundaryWidth) dx *= -1;
        // if (y <= 20 + radius) dy *= -1;
    }

    public void render(GraphicsContext gc) {
        if (AssetManager.getImage("ball") != null) {
            // use drawImage method instead of fillOval
            gc.drawImage(AssetManager.getImage("ball"), x - radius, y - radius, radius * 2, radius * 2);
        } else {
            // If no image, draw a red oval as a fallback
            gc.setFill(Color.RED);
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

    }

    // public boolean isOutOfBounds() {
    // return y - radius > boundaryHeight + 40;
    // }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDx() {
        return dx;
    }
    public void setDx(double dx) {
        this.dx = dx;
    }
    public void setDy(double dy) {
        this.dy = dy;
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

    public void setRadius(double radius) {
        this.radius = radius;
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

    public double getDamege() {
        return damege;
    }
    public void setDamege(double damge) {
        this.damege = damge;
    }
    public double getMaxcollision() {
        return Maxcollision;
    }
    public void setMaxcollision(double maxcollision) {
        Maxcollision = maxcollision;
    }
}

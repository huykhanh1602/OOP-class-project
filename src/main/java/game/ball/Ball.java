package game.ball;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

import game.AssetManager;
import game.Constant;
import game.abstraction.Bricks;
import game.objects.Paddle;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

///  Ball movement

public class Ball {
    /// ELEMENT BALL
    private double x, y;
    private static double radius = 10; // Size ball
    private double dx, dy; // Vector speed
    private double speedball = 5; // Ball speed
    public boolean isRunning = false;
    
    /// Aiming Arc
    private double aimAngle = 30 ;
    private boolean aimIncrease = true;
    private final double aimSpeed = 1;
    private final double ainMin = 30 ;
    private final double ainMax = 150 ;
    private boolean isPlayerAiming;

    private double friction = 0.1; // Ma sát

    Image ballImage;
    Image diretion;

    private static final int TRAIL_SIZE = 15;
    private final double[][] trail = new double[TRAIL_SIZE][2];
    private int trailIndex = 0;

    public Ball(double x, double y) {
        this.x = x + radius;
        this.y = y + radius;
        ballImage = AssetManager.getImage("ball");
        diretion = AssetManager.getImage("diretion");

    }

    public void collides(Paddle paddle) { 
        double ballCenterX = x;
        double ballCenterY = y;

        double closestX = clamp(ballCenterX, paddle.getX(), paddle.getX() + paddle.getWidth());
        double closestY = clamp(ballCenterY, paddle.getY(), paddle.getY() + paddle.getHeight());

        double distanceX = ballCenterX - closestX;
        double distanceY = ballCenterY - closestY;

        double distanceSquared = distanceX * distanceX + distanceY * distanceY;

        if (distanceSquared < radius * radius) {
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
        if (x < 10 + radius) {
            dx = Math.abs(dx);
            x = 10 + radius;
        }
        if (x > 1000 - radius) {
            dx = - Math.abs(dx);
            x = 1000 - radius;
        }
        if (y < 20 - radius) {
            dy = Math.abs(dy);
            y = 20;
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
            if (brickCenterX > x) {
                x = brickCenterX - brick.getWidth()/2.0f - radius - 2;
            } else {
                x = brickCenterX + brick.getHeight()/2.0f + radius + 2;
            }
            if (brickCenterY > y) {
                y = brickCenterY - brick.getHeight()/2.0f - radius - 7;
            } else {
                y = brickCenterY + brick.getHeight()/2.0f + radius + 7;
            }
        } else if (closestX < closestY) {
            bounceY();
            if (brickCenterY > y) {
                y = brickCenterY - brick.getHeight()/2.0f - radius - 7;
            } else {
                y = brickCenterY + brick.getHeight()/2.0f + radius + 7;
            }
        } else {
            bounceX();
            if (brickCenterX > x) {
                x = brickCenterX - brick.getWidth()/2.0f - radius - 7;
            } else {
                x = brickCenterX + brick.getHeight()/2.0f + radius + 7;
            }
        }
    }

    // Hàm clamp để giới hạn giá trị trong khoảng min-max
    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public void launchBall() {
        if (!isRunning) {
            isRunning = true;
            double rad = Math.toRadians(aimAngle);
            double vx = Math.cos(rad);
            double vy = -Math.sin(rad);
            this.dx = vx*speedball;
            this.dy = vy*speedball;
        }
    }

    public void update(Paddle paddle) {
        if (dx > speedball) dx = speedball;
        if (dx < -speedball) dx = -speedball;
        if (isRunning) {
            x += dx;
            y += dy;
            updateTrail();
            collides(this);
        } else {
            this.x = paddle.getX() + paddle.getWidth() / 2;
            this.y = paddle.getY() - radius;
        }
        if (isPlayerAiming) {
            if (aimIncrease) {
                aimAngle += aimSpeed;
            } else {
                aimAngle -= aimSpeed;
            }

            if (aimAngle > ainMax) {
                aimAngle = ainMax;
                aimIncrease = false;
            } else if (aimAngle < ainMin) {
                aimAngle = ainMin;
                aimIncrease = true;
            }
        } else {
            aimAngle = 30;
        }
    }

    public void render(GraphicsContext gc) {
        drawTrail(gc);
        if (isPlayerAiming && !isRunning) {
            double startX = x;
            double startY = y;
            double length = 100;

            double endX = startX + Math.cos(Math.toRadians(aimAngle)) * length;
            double endY = startY - Math.sin(Math.toRadians(aimAngle)) * length;
            try {
                double w = diretion.getWidth();
                double h = diretion.getHeight();
                double scale = 0.5;

                gc.save();
                gc.translate(startX, startY);
                gc.rotate(-(aimAngle + 90));
                gc.drawImage(diretion, -w/2 + w * scale / 2, 0, w * scale, h * scale);
                gc.restore();

            } catch (Exception e) {
                gc.setStroke(Color.YELLOW);
                gc.setLineWidth(2);
                gc.strokeLine(startX, startY, endX, endY);
            }
        }
        if (ballImage != null) {
            gc.drawImage( ballImage, x - radius, y - radius, radius * 2, radius * 2);
        } else {
            gc.setFill(Color.RED);
            gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
        }

    }

    private void updateTrail() {
        trail[trailIndex][0] = x;
        trail[trailIndex][1] = y;
        trailIndex = (trailIndex + 1) % TRAIL_SIZE;
    }

    public void drawTrail(GraphicsContext gc) {
        for (int i = 0; i < TRAIL_SIZE; i++) {
            int index = (trailIndex + i) % TRAIL_SIZE;
            double alpha = (double) i / TRAIL_SIZE; // mờ dần

            gc.setGlobalAlpha(alpha * Math.min(1, Math.sqrt(dx*dx + dy*dy) / 15)); // độ mờ vệt
            gc.drawImage(ballImage, trail[index][0]-radius, trail[index][1]-radius, radius*2, radius*2);
        }
        gc.setGlobalAlpha(1.0); // KHÔNG ảnh hưởng vật khác
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

    private void normalizeVelocity() {
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

    public boolean isPlayerAiming() {
        return isPlayerAiming;
    }

    public void setPlayerAiming(boolean isPlayerAiming) {
        this.isPlayerAiming = isPlayerAiming;
    }
}

package game.ball;

import static java.lang.Math.sqrt;

import game.AssetManager;
import game.Constant;
import game.GameContext;
import game.GameManager;
import game.abstraction.Bricks;
import game.objects.Paddle;
import game.scenes.SkinBallSceneController;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

///  Ball movement

public abstract class Ball {
    /// ELEMENT BALL
    private double x, y;
    private double radius = 10; // Size ball
    private double dx, dy; // Vector speed
    private double speedball = 5; // Ball speed
    public boolean isRunning = false;
    private double damege;
    private double Maxcollision;
    private boolean isClone = false;
    private boolean isSpecialPowerupBall = false;
    private boolean iscollision = false;

    /// Aiming Arc
    private double aimAngle = 30 ;
    private boolean aimIncrease = true;
    private final double aimSpeed = 1;
    private final double ainMin = 30 ;
    private final double ainMax = 150 ;
    private boolean isPlayerAiming;

    private final double friction = 0.1; // Ma sát

    Image ballImage;
    Image diretion;

    private static final int TRAIL_SIZE = 15;
    private final double[][] trail = new double[TRAIL_SIZE][2];
    private int trailIndex = 0;

    public Ball(double x, double y) {
        this.x = x + radius;
        this.y = y + radius;
        System.out.println(GameContext.getInstance().getNameBall());
        ballImage = new Image(getClass().getResource(GameContext.getInstance().getNameBall()).toExternalForm());
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
            if(!ball.iscollision){
                ball.iscollision = true;
            }
            dy = Math.abs(dy);
            y = 20 + radius;
        }
        else ball.iscollision = false;
    }
    public void collides(Bricks brick,Ball ball) {
        //điểm gần nhất gạch gần tâm bóng
        double closestX = clamp(this.x, brick.getX(), brick.getX() + brick.getWidth());
        double closestY = clamp(this.y, brick.getY(), brick.getY() + brick.getHeight());
        //khoảng cách giữa tâm và điểm đó
        double distanceX = this.x - closestX;
        double distanceY = this.y - closestY;
        //bình phương khoảng cách
        double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
        //nếu khoảng cách bé hơn bán kính
        double distance = Math.sqrt(distanceSquared);
        if (distance < this.radius) {
            //tính độ lún sâu
            double overlapX = (this.radius + brick.getWidth() / 2) - Math.abs(this.x - (brick.getX() + brick.getWidth() / 2));
            double overlapY = (this.radius + brick.getHeight() / 2) - Math.abs(this.y - (brick.getY() + brick.getHeight() / 2));
            //tính đúng khoảng cách cần đẩy ra
            double penetration = this.radius - distance;
            if (distance == 0) {
                this.y -= penetration;
            } else {
                this.x += (distanceX / distance) * penetration;
                this.y += (distanceY / distance) * penetration;
            }
            //so sánh ko sẽ bị đẩy cả 2 gây va chạm liên tục
            if(ball.iscollision) {
                bounceX();
            }
               else if (overlapX < overlapY) {
                    bounceX();
                    } else {
                    bounceY();
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
        return new Rectangle2D(x-radius, y-radius, radius * 2, radius * 2);
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
    public void setIsClone(boolean isClone) {
        this.isClone = isClone;
    }
    public boolean isClone() {
        return this.isClone;
    }
    public boolean isSpecialPowerupBall(){
        return isSpecialPowerupBall;
    }
    public void setIsSpecialPowerupBall(boolean isSpecialPowerupBall) {
        this.isSpecialPowerupBall = isSpecialPowerupBall;
    }
    public boolean isPlayerAiming() {
        return isPlayerAiming;
    }
    public boolean iscollision(){
        return iscollision;
    }
    public void setIscollision(boolean iscollision) {
        this.iscollision = iscollision;
    }
    public void setPlayerAiming(boolean isPlayerAiming) {
        this.isPlayerAiming = isPlayerAiming;
    }
}

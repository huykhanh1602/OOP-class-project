package vnu.edu.vn.game.ball;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.GameManager;
import vnu.edu.vn.game.bricks.BrickLoader;
import vnu.edu.vn.game.objects.Paddle;
import vnu.edu.vn.game.bricks.Bricks;
import vnu.edu.vn.game.Constant;

import java.sql.Time;
import java.util.Random;

import static java.lang.Math.abs;
import static java.lang.Math.floor;

///  Ball movement

public class Ball {

    private Image ballImage;
    /// ELEMENT BALL
    private double x, y;
    private static double radius = 7.5; // Size ball
    private double dx, dy; // Vector speed
    private double speedball = 2;
    public boolean isRunning = false;

    private double friction = 0.2; // Ma sát

    private double fixBug = 5; // KHOẢNG CÁCH VA CHẠM

    // private int boundaryWidth = 600*3/4;
    // private int boundaryHeight = 600*5/6;

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

    public boolean intersects(Rectangle2D rect) { // Trả về thuộc tính để kiểm tra va chạm
        return rect.intersects(getRect());
    }

    public void collides(Paddle paddle) { // Va chạm với Paddle

        if (x + radius > paddle.getX() && // EDGE
                x - radius < paddle.getX() + paddle.getWidthPaddle() &&
                y + radius > paddle.getY() &&
                y + radius < paddle.getY() + speedball * 1.5) {
            y = paddle.getY() - radius;
            bounceY();

            // Ma sát với paddle
            double paddleMoment = paddle.getSpeed();
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
        }
    }

    public void collides(Ball ball) { // Va chạm với tường

        if (x <= 10 + radius || x + radius * 2 >= Constant.WIDTH_SCREEN * 3 / 4)
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
        // Tọa độ tâm của quả bóng
        double ballCenterX = x;
        double ballCenterY = y;

        // Tọa độ tâm của viên gạch
        double brickCenterX = brick.getX() + brick.getWidth() / 2.0f;
        double brickCenterY = brick.getY() + brick.getHeight() / 2.0f;

        // Tính khoảng cách giữa tâm của bóng và tâm của gạch trên mỗi trục
        double diffX = ballCenterX - brickCenterX;
        double diffY = ballCenterY - brickCenterY;

        // Tính tổng một nửa chiều rộng/cao của gạch và bán kính của bóng
        // Đây là khoảng cách tối thiểu giữa các tâm để chúng không va chạm
        double combinedHalfWidths = radius + brick.getWidth() / 2.0f;
        double combinedHalfHeights = radius + brick.getHeight() / 2.0f;

        // Kiểm tra xem có va chạm hay không
        if (Math.abs(diffX) < combinedHalfWidths && Math.abs(diffY) < combinedHalfHeights) {
            // Có va chạm, giờ cần xác định hướng va chạm

            // Tính toán độ chồng lấn trên mỗi trục
            double overlapX = combinedHalfWidths - Math.abs(diffX);
            double overlapY = combinedHalfHeights - Math.abs(diffY);

            // Hướng có độ chồng lấn ÍT HƠN chính là hướng va chạm chính
            if (overlapX < overlapY) {
                // Va chạm xảy ra theo chiều ngang (trái hoặc phải)
                bounceX();
                // Đẩy quả bóng ra khỏi gạch để tránh bị kẹt
                if (diffX > 0) { // Bóng ở bên phải gạch
                    x = brick.getX() + brick.getWidth() + radius;
                    // System.out.println("Bounce X Right");
                } else { // Bóng ở bên trái gạch
                    x = brick.getX() - radius;
                    // System.out.println("Bounce X Left");
                }
            } else {
                // Va chạm xảy ra theo chiều dọc (trên hoặc dưới)
                bounceY();
                // Đẩy quả bóng ra khỏi gạch để tránh bị kẹt
                if (diffY > 0) { // Bóng ở bên dưới gạch
                    y = brick.getY() + brick.getHeight() + radius;
                    // System.out.println("Bounce Y Down");
                } else { // Bóng ở bên trên gạch
                    y = brick.getY() - radius;
                    // System.out.println("Bounce Y Up");
                }
            }
        }
    }

    public void normalizeVelocity() { // Chuẩn hóa vector

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
        if (ballImage != null) {
            // DÙNG LỆNH VẼ ẢNH THAY VÌ VẼ HÌNH OVAL
            gc.drawImage(ballImage, x - radius, y - radius, radius * 2, radius * 2);
        } else {
            // Nếu không có ảnh, vẽ hình oval màu đỏ như cũ để dự phòng
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

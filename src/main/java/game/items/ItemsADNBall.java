package game.items;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.ball.NormalBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ItemsAddBallLEVER4 extends ItemsForBall {
    private static final int SHATTER_BALL_COUNT = 2;
    private static final Random random = new Random();
    private static final double SHATTER_SPEED = 7;
    private static final double SHATTER_RADIUS = 7.0;
    private static final double SHATTER_DAMAGE = 1.0;
    public ItemsAddBallLEVER4() {
        super("Bóng ADN", "Hiệu lực 10s. Khi nhặt: tạo 1 bóng mới trên thanh chắn. Khi bóng va chạm gạch, nó được buff +5 damege vĩnh viễn.Va chạm gạch có tỉ lệ 80% sẽ tạo ra 2 bóng con trong", 10, 10);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isClone() || Math.random() <= 0.8) {
            return;
        }
        // Tạo ra các bóng con
        List<Ball> newBalls = this.shatter(collidingBall);
        pendingBalls.addAll(newBalls);
    }

    @Override
    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        for (int i = 0; i < SHATTER_BALL_COUNT; i++) {
            // Tạo bóng mới tại vị trí bóng cha
            NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
            // Tạo góc ngẫu nhiên (tránh bay thẳng xuống)
            double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15; // 15 đến 165 độ
            double angleRadians = Math.toRadians(randomAngleDegrees);
            double vx = SHATTER_SPEED * Math.cos(angleRadians);
            double vy = -SHATTER_SPEED * Math.sin(angleRadians);
            //Thiết lập chỉ số cho bóng con
            newBall.setRadius(SHATTER_RADIUS);
            newBall.setSpeedball(SHATTER_SPEED);
            newBall.setMaxcollision(5);
<<<<<<<< HEAD:src/main/java/game/ball/ItemsAddBallLEVER4.java
            newBall.setDamege(SHATTER_DAMAGE);
========
            // Dùng tên hàm 'setDamage' từ file Ball.java
            newBall.setDamage(SHATTER_DAMAGE);
            // --- Hết ---

            // Thiết lập vận tốc và trạng thái chạy
>>>>>>>> 83a514c703b0febfb1e51b37f5b0bb4d87cddf9f:src/main/java/game/items/ItemsADNBall.java
            newBall.setDx(vx);
            newBall.setDy(vy);
            newBall.setRunning(true);
            newBall.setIsClone(true);
            newBalls.add(newBall);
        }
        return newBalls;
    }
}
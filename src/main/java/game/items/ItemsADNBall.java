package game.items;

import game.abstraction.Bricks;
import game.ball.Ball;
import game.ball.NormalBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ItemsADNBall extends ItemsForBall {
    private static final int SHATTER_BALL_COUNT = 2;
    private static final Random random = new Random();
    // 1. THÊM CÁC HẰNG SỐ CÒN THIẾU
    // Bạn có thể tùy chỉnh các giá trị này
    private static final double SHATTER_SPEED = 7;
    private static final double SHATTER_RADIUS = 7.0;
    private static final double SHATTER_DAMAGE = 1.0;

    public ItemsADNBall() {
        super("Bóng ADN", "Va chạm gạch có tỉ lệ 80% sẽ tạo ra 2 bóng con trong", 10, 80);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isClone() || Math.random() <= 0.8) {
            return;
        }
        // Tạo ra các bóng con
        List<Ball> newBalls = this.shatter(collidingBall);
        // Thêm chúng trực tiếp vào danh sách bóng chính của GameManager
        pendingBalls.addAll(newBalls);
    }

    /**
     * 4. SỬA TÊN HÀM/BIẾN TRONG SHATTER
     * (Hàm này giờ là private vì chỉ lớp này cần)
     */
    private List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();

        for (int i = 0; i < SHATTER_BALL_COUNT; i++) {
            // Tạo bóng mới tại vị trí bóng cha
            NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());

            // Tạo góc ngẫu nhiên (tránh bay thẳng xuống)
            double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15; // 15 đến 165 độ
            double angleRadians = Math.toRadians(randomAngleDegrees);

            // Dùng các hằng số đã định nghĩa
            double vx = SHATTER_SPEED * Math.cos(angleRadians);
            double vy = -SHATTER_SPEED * Math.sin(angleRadians); // Dấu âm để bay lên

            // --- Thiết lập chỉ số cho bóng con ---
            newBall.setRadius(SHATTER_RADIUS);
            newBall.setSpeedball(SHATTER_SPEED);
            newBall.setMaxcollision(5);
            // Dùng tên hàm 'setDamage' từ file Ball.java
            newBall.setDamage(SHATTER_DAMAGE);
            // --- Hết ---

            // Thiết lập vận tốc và trạng thái chạy
            newBall.setDx(vx);
            newBall.setDy(vy);
            newBall.setRunning(true);
            newBall.setIsClone(true);
            newBalls.add(newBall);
        }
        return newBalls;
    }
}
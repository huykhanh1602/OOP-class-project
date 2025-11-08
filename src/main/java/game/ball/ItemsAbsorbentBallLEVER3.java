package game.ball;

import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER3 extends ItemsAbsorbentBallLEVER5 {
    public ItemsAbsorbentBallLEVER3() {
        super("Bóng Hấp Thụ(Cấp 3)","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả các quả bóng khác sẽ được tăng nhẹ về tốc độ, sức tấn công, độ lớn trong 5s",5,40);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        // Lặp qua TẤT CẢ các quả bóng
        for (Ball ball : allBalls) {
            // Bỏ qua quả bóng vừa mới va chạm
            double currentSpeed = ball.getSpeedball();
            double currentDamege = ball.getDamege();
            double currentSize = ball.getRadius();

            // Tăng chỉ số cho TẤT CẢ CÁC BÓNG KHÁC
            ball.setSpeedball(currentSpeed * 1.001);
            ball.setDamege(currentDamege * 1.002);
            ball.setRadius(currentSize * 1.002);
        }
    }
}
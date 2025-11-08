package game.ball;

import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER4 extends ItemsAbsorbentBallLEVER5 {
    public ItemsAbsorbentBallLEVER4() {
        super("Bóng Hấp Thụ(Cấp 4)","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả các quả bóng khác sẽ được tăng nhẹ tạm thời về tốc độ, sức tấn công, độ lớn trong 10s",10,15);
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
            ball.setSpeedball(currentSpeed * 1.002);
            ball.setDamege(currentDamege * 1.004);
            ball.setRadius(currentSize * 1.004);
        }
    }
}
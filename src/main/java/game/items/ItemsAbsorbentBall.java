package game.items;

import game.abstraction.Ball;
import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBall extends ItemsForBall {
    public ItemsAbsorbentBall() {
        super("Bóng Hấp Thụ","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả các quả bóng khác sẽ được tăng nhẹ về tốc độ, sức tấn công, độ lớn trong 5s",5,50);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        // Lặp qua TẤT CẢ các quả bóng
        for (Ball ball : allBalls) {
            // Bỏ qua quả bóng vừa mới va chạm
            double currentSpeed = ball.getSpeedball();
            double currentDamege = ball.getDamage();
            double currentSize = ball.getRadius();

            // Tăng chỉ số cho TẤT CẢ CÁC BÓNG KHÁC
            ball.setSpeedball(currentSpeed * 0.998);
            ball.setDamage(currentDamege * 1.004);
            ball.setRadius(currentSize * 1.004);
        }
    }
}
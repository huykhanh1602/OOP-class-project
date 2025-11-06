package game.ball;

import java.util.List;

public class ItemsAbsorbentBall extends ItemsForBall {
    public ItemsAbsorbentBall() {
        super("Bóng Hấp Thụ","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả các quả bóng khác sẽ được tăng nhẹ về tốc độ, sức tấn công, độ lớn",10,10);
    }
    @Override
    public void onBrickCollision(Ball collidingBall,List<Ball> allBalls) {
        // Lặp qua TẤT CẢ các quả bóng
        for (Ball ball : allBalls) {
            // Bỏ qua quả bóng vừa mới va chạm
            double currentSpeed = ball.getSpeedball();
            double currentDamege = ball.getDamege();
            double currentSize = ball.getRadius();

            // Tăng chỉ số cho TẤT CẢ CÁC BÓNG KHÁC
            ball.setSpeedball(currentSpeed * 1.02);
            ball.setDamege(currentDamege * 1.02);
            ball.setRadius(currentSize * 1.02);
        }
    }
}
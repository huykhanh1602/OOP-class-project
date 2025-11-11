package game.items;

import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;

public class ItemsAbsorbentBall extends ItemsForBall {
    public ItemsAbsorbentBall() {
        super("Bóng Hấp Thụ","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả các quả bóng khác sẽ được tăng nhẹ về tốc độ, sức tấn công, độ lớn trong 5s",5,50);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        for (Ball ball : allBalls) {
            double currentSpeed = ball.getSpeedball();
            double currentDamege = ball.getDamage();
            double currentSize = ball.getRadius();

            // Increase stats for all balls
            ball.setSpeedball(currentSpeed * 0.998);
            ball.setDamage(currentDamege * 1.004);
            ball.setRadius(currentSize * 1.004);
        }
    }
}
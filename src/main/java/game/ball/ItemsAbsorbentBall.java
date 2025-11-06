package game.ball;

import game.abstraction.Bricks;

import java.util.Iterator;

public class ItemsAbsorbentBall extends ItemsForBall {
    public ItemsAbsorbentBall() {
        super("Bóng Hấp Thụ","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả cách quả bóng khác sẽ được tăng nhẹ về tốc độ, sức tấn công, độ lớn",10,0.3);
    }
    @Override
    public void onBrickCollision(Ball ball) {
        double currentSpeed = ball.getSpeedball();
        double currentDamege = ball.getDamege();
        double currentSize = ball.getRadius();
        ball.setSpeedball(currentSpeed * 1.1);
        ball.setDamege(currentDamege * 1.1);
        ball.setRadius(currentSize * 1.1);
    }
}
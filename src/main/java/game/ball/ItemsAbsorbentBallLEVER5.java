package game.ball;

import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER5 extends ItemsForBall {
    public ItemsAbsorbentBallLEVER5() {
        super("Bóng Hấp Thụ(Cấp 5)","Mỗi khi quả bóng bất kì va chạm gạch," +
                "tất cả các quả bóng đều sẽ được tăng nhẹ vĩng viễn về tốc độ, sức tấn công, độ lớn trong 10s",10,10);
    }
    public ItemsAbsorbentBallLEVER5(String name, String description, double timeuse, double percent) {
        super(name, description, timeuse, percent);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        for (Ball ball : allBalls) {
            double currentSpeed = ball.getSpeedball();
            double currentDamege = ball.getDamege();
            double currentSize = ball.getRadius();
            ball.setSpeedball(currentSpeed * 1.002);
            ball.setDamege(currentDamege * 1.004);
            ball.setRadius(currentSize * 1.004);
        }
    }
}
package game.items;

import game.abstraction.Ball;
import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER3 extends ItemsForBall {
    public ItemsAbsorbentBallLEVER3() {
        super("Bóng Hấp Thụ(Cấp 5)","Hiệu lực 10s. Khi nhặt: tạo 1 bóng mới trên sân. Với mỗi quả bóng"+
                " va chạm sẽ tăng damege, tốc độ, size cho mọi quả bóng",10,10);
                setItemName("");
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        for (Ball ball : allBalls) {
            double currentSpeed = ball.getSpeedball();
            double currentDamege = ball.getDamage();
            double currentSize = ball.getRadius();
            ball.setSpeedball(currentSpeed * 1.002);
            ball.setDamage(currentDamege * 1.004);
            ball.setRadius(currentSize * 1.004);
        }
    }
    @Override
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        List<Ball> newBalls = this.shatter(collidingBall);
        pendingBalls.addAll(newBalls);
    }
}
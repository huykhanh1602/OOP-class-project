package game.ball;
import game.abstraction.Bricks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsAbsorbentBallLEVER3 extends ItemsAbsorbentBallLEVER5 {
    private static final Random random = new Random();
    public ItemsAbsorbentBallLEVER3() {
        super("Bóng Hấp Thụ(Cấp 3)","Hiệu lực 8s. Khi nhặt: tạo 1 bóng mới trên sân. Khi bóng MỚI va chạm gạch, nó được buff damege, tốc độ x 1.1",8,6);
    }
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        List<Ball> newBalls = this.shatter(collidingBall);
        if (!newBalls.isEmpty()) {
            Ball newBall = newBalls.get(0);
            newBall.setIsSpecialPowerupBall(true);
            pendingBalls.addAll(newBalls);
        }
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isSpecialPowerupBall()) {
            collidingBall.setDamege(collidingBall.getDamege()*1.1);
            collidingBall.setSpeedball(collidingBall.getSpeedball()*1.1);
        }
    }
    @Override
    public void onExpired(List<Ball> allBalls) {
        for (Ball ball : allBalls) {
            if (ball.isSpecialPowerupBall()) {
                ball.setIsSpecialPowerupBall(false);
            }
        }
    }
    @Override
    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
        double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15; // 15 đến 165 độ
        double angleRadians = Math.toRadians(randomAngleDegrees);
        double vx =  currentBall.getSpeedball() * Math.cos(angleRadians);
        double vy = -currentBall.getSpeedball() * Math.sin(angleRadians);
        newBall.setDx(vx);
        newBall.setDy(vy);
        newBall.setRunning(true);
        newBalls.add(newBall);
        return newBalls;
    }
}

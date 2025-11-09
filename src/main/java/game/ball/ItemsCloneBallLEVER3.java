package game.ball;
import game.abstraction.Bricks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class ItemsCloneBallLEVER3 extends ItemsForBall {
    private static final Random random = new Random();

    public ItemsCloneBallLEVER3() {
        super("Bóng Nhân Bản(Cấp 3)", "Hiệu lực 8s. Khi nhặt:Tạo 1 bóng mới trên sân.Khi "+
                "bóng mới va chạm gạch, nó sẽ tạo ra 3 bóng siêu yếu", 8, 6);
    }

    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isSpecialPowerupBall() && !collidingBall.isClone()) {
            List<Ball> newBalls = this.shatter(collidingBall);
            pendingBalls.addAll(newBalls);
        }
    }
    @Override
    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        for (int i = 0; i < 3.6; i++) {

            NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
            double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15;
            double angleRadians = Math.toRadians(randomAngleDegrees);
            double vx = Math.cos(angleRadians);
            double vy = -Math.sin(angleRadians);

            newBall.setRadius(currentBall.getRadius());
            newBall.setMaxcollision(3.6);
            newBall.setDamege(3.6);
            newBall.setSpeedball(currentBall.getSpeedball() * 3.6);
            newBall.setDx(vx);
            newBall.setDy(vy);
            newBall.setRunning(true);
            newBall.setIsClone(true);
            newBalls.add(newBall);
        }
        return newBalls;
    }
    @Override
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        List<Ball> newBalls = super.shatter(collidingBall);
        if (!newBalls.isEmpty()) {
            Ball newBall = newBalls.get(0);
            newBall.setIsSpecialPowerupBall(true);
            pendingBalls.addAll(newBalls);
        }
    }
    public void onExpired(List<Ball> allBalls) {
        for (Ball ball : allBalls) {
            if (ball.isSpecialPowerupBall()) {
                ball.setIsSpecialPowerupBall(false);
            }
            if (ball.isClone()) {
                ball.setIsClone(false);
            }
        }
    }
}
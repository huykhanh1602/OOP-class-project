package game.ball;

import game.abstraction.Bricks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ItemsCloneBallLEVER5 extends ItemsForBall {
    private static final Random random = new Random();
    public ItemsCloneBallLEVER5() {
        super("Bóng Nhân Bản(Cấp 5)", "Khi nhặt: gấp đôi bóng trên sân", 10, 1);
    }
    @Override
    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
        double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15;
        double angleRadians = Math.toRadians(randomAngleDegrees);
        double vx = Math.cos(angleRadians);
        double vy = -Math.sin(angleRadians);
        newBall.setRadius(currentBall.getRadius()/2);
        newBall.setMaxcollision(currentBall.getMaxcollision()/2);
        newBall.setDamege(currentBall.getDamege()/2);
        newBall.setSpeedball(currentBall.getSpeedball()/2);
        newBall.setDx(vx);
        newBall.setDy(vy);
        newBall.setRunning(true);
        newBall.setIsClone(true);
        newBalls.add(newBall);
        return newBalls;
    }
    @Override
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        for (Ball ballOnField : allBalls) {
            List<Ball> cloneBallList = this.shatter(ballOnField);
            if (!cloneBallList.isEmpty()) {
                pendingBalls.addAll(cloneBallList);
            }
        }
    }
    @Override
    public void onExpired(List<Ball> allBalls) {
        for (Ball ball : allBalls) {
            if (ball.isClone()) {
                ball.setIsClone(false);
            }
        }
    }
}
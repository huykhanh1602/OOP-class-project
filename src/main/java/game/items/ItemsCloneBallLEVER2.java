package game.items;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.ball.NormalBall;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ItemsCloneBallLEVER2 extends ItemsForBall {
    private static final Random random = new Random();
    public ItemsCloneBallLEVER2() {
        super("Bóng Nhân Bản(Cấp 2)", "Khi nhặt:Tạo 3 bóng mới trên sân", 10, 20);
        setItemName("exp_potion");
    }
    @Override
    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
            double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15;
            double angleRadians = Math.toRadians(randomAngleDegrees);
            double vx = Math.cos(angleRadians)*5;
            double vy = -Math.sin(angleRadians)*5;
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
        List<Ball> newBalls = this.shatter(collidingBall);
        pendingBalls.addAll(newBalls);
    }
}
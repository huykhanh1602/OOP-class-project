package game.items;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.ball.NormalBall;
import game.items.ItemsForBall;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ItemsCloneBallLEVER4 extends ItemsForBall {
    private static final Random random = new Random();
    public ItemsCloneBallLEVER4() {
        super("Bóng Nhân Bản(Cấp 4)", "Hiệu lực 9s. Khi nhặt: khi bóng va chạm gạch, nó có tỉ lệ 80% sẽ tạo ra 2 bóng con yếu gấp đôi", 9, 5);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isClone() || Math.random() <= 0.8) {
            return;
        }
        // Tạo ra các bóng con
        List<Ball> newBalls = this.shatter(collidingBall);
        pendingBalls.addAll(newBalls);
    }

    @Override
    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
            double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15;
            double angleRadians = Math.toRadians(randomAngleDegrees);
            double vx =Math.cos(angleRadians);
            double vy = -Math.sin(angleRadians);

            newBall.setRadius(1/Math.sqrt(currentBall.getRadius()));
            newBall.setMaxcollision(1/Math.sqrt(currentBall.getMaxcollision()));
            newBall.setDamage(1/Math.sqrt(currentBall.getDamage()));
            newBall.setSpeedball(1/Math.sqrt(currentBall.getSpeedball()));
            newBall.setDx(vx);
            newBall.setDy(vy);
            newBall.setRunning(true);
            newBall.setIsClone(true);
            newBalls.add(newBall);
        }
        return newBalls;
    }
}
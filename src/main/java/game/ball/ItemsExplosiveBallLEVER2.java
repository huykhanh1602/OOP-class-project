package game.ball;

import game.abstraction.Bricks;
import game.objects.Paddle;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsExplosiveBallLEVER2 extends ItemsForBall {
    private static final Random random = new Random();
    public ItemsExplosiveBallLEVER2() {
        super("Bóng Nổ(Cấp 2)", "Hiệu lực 7s. Khi nhặt:tạo 1 bóng mới trên sân. Khi bóng va chạm gạch, bóng mới sẽ tạo ra 1"+
                " vụ nổ có bán kính nổ gấp 3 bán kính bóng và gây 30 sát thương", 7, 9);
    }
    @Override
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        List<Ball> newBalls = this.shatter(collidingBall);
        if (!newBalls.isEmpty()) {
            Ball newBall = newBalls.get(0);
            newBall.setIsSpecialPowerupBall(true);
            pendingBalls.addAll(newBalls);
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
        double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15;
        double angleRadians = Math.toRadians(randomAngleDegrees);
        double vx =  currentBall.getSpeedball() * Math.cos(angleRadians);
        double vy = -currentBall.getSpeedball() * Math.sin(angleRadians);
        newBall.setDx(vx);
        newBall.setDy(vy);
        newBall.setRunning(true);
        newBalls.add(newBall);
        return newBalls;
    }
    @Override
    public void onPaddleCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls, Paddle paddle) {
        if (collidingBall.isSpecialPowerupBall()) {
            RenderExplosive(collidingBall, allBalls, allBricks, pendingBalls, 3, 30);
        }
    }
}
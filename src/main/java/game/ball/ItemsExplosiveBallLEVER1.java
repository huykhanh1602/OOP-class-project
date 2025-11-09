package game.ball;

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemsExplosiveBallLEVER1 extends ItemsForBall {
    private static final Random random = new Random();
    private static final double EXPLOSION_DAMAGE = 10;
    private static final double RADIUS_MULTIPLIER = 3;
    public ItemsExplosiveBallLEVER1() {
        super("Bóng Nổ(Cấp 1)", "Hiệu lực 6s. Khi nhặt:tạo 1 bóng mới trên sân. Khi bóng đó va chạm gạch, nó tạo ra 1"+
                " vụ nổ có bán kính nổ gấp 3 bán kính bóng và gây 10 sát thương", 6, 13);
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
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isSpecialPowerupBall()) {
            RenderExplosive(collidingBall, allBalls, allBricks, pendingBalls, 3, 10);
        }
    }
}
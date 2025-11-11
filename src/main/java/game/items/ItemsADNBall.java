package game.items;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.ball.NormalBall;
public class ItemsADNBall extends ItemsForBall {
    private static final int SHATTER_BALL_COUNT = 2;
    private static final Random random = new Random();
    private static final double SHATTER_SPEED = 7;
    private static final double SHATTER_RADIUS = 7.0;
    private static final double SHATTER_DAMAGE = 1.0;

    public ItemsADNBall() {
        super("Bóng ADN", "Va chạm gạch có tỉ lệ 80% sẽ tạo ra 2 bóng con trong", 10, 80);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        if (collidingBall.isClone() || Math.random() <= 0.8) {
            return;
        }
        // Create new balls
        List<Ball> newBalls = this.shatter(collidingBall);
        // Add them directly to the main ball list in GameManager
        pendingBalls.addAll(newBalls);
    }

    /**
     * 4. Rename function/variables in shatter
     * (This function is now private because only this class needs it)
     */
    private List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();

        for (int i = 0; i < SHATTER_BALL_COUNT; i++) {
            NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());

            // Create random angle (avoid going straight down)
            double randomAngleDegrees = random.nextDouble() * (165 - 15) + 15; // 15 to 165 degrees
            double angleRadians = Math.toRadians(randomAngleDegrees);

            // Use defined constants
            double vx = SHATTER_SPEED * Math.cos(angleRadians);
            double vy = -SHATTER_SPEED * Math.sin(angleRadians); // Negative to go upwards

            // --- Set stats for the new ball ---
            newBall.setRadius(SHATTER_RADIUS);
            newBall.setSpeedball(SHATTER_SPEED);
            newBall.setMaxcollision(5);
            // Use the 'setDamage' method from Ball.java
            newBall.setDamage(SHATTER_DAMAGE);
            // --- End ---

            // Set velocity and running state
            newBall.setDx(vx);
            newBall.setDy(vy);
            newBall.setRunning(true);
            newBall.setIsClone(true);
            newBalls.add(newBall);
        }
        return newBalls;
    }
}
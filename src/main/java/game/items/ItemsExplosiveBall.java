package game.items;

import java.util.List;

import game.AssetManager;
import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.particle.ParticleManager;
import javafx.scene.paint.Color;

public class ItemsExplosiveBall extends ItemsForBall {

    private static final double EXPLOSION_DAMAGE = 10;    // Explosion damage
    private static final double RADIUS_MULTIPLIER = 3.0; // 3 times the radius

    public ItemsExplosiveBall() {
        // (You need to add Color to the constructor of ItemsForBall.java if you want)
        super("Explosive Ball", "When colliding with bricks, creates an explosion causing 10 damage", 15, 50);
    }
    /**
     * CAUTION: This is a new signature for the method!
     * You need to update the abstract method in ItemsForBall.java as well.
     */
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {

        // 1. Get explosion parameters
        double explosionX = collidingBall.getX();
        double explosionY = collidingBall.getY();
        double explosionRadius = collidingBall.getRadius() * RADIUS_MULTIPLIER;

        // 2. Create visual effect (to "draw an area" as you want)

        ParticleManager.getInstance().createBrickBreakEffect(explosionX, explosionY, 20, Color.ORANGE);
        AssetManager.playSound("brick_break"); // (Use this sound temporarily)

        // 3. Loop through ALL bricks to deal damage
        for (Bricks brick : allBricks) {

            if (brick.isBroken()) continue; // Skip broken bricks

            // 4. Check collision (AABB vs Circle)
            // Find the closest point on the rectangle to the circle's center
            double closestX = Math.max(brick.getX(), Math.min(explosionX, brick.getX() + brick.getWidth()));
            double closestY = Math.max(brick.getY(), Math.min(explosionY, brick.getY() + brick.getHeight()));

            // Calculate distance
            double distanceX = explosionX - closestX;
            double distanceY = explosionY - closestY;
            double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

            // 5. If the brick is within the explosion radius, deal damage
            if (distanceSquared < (explosionRadius * explosionRadius)) {
                brick.hit(EXPLOSION_DAMAGE); // Deal 10 damage
            }
        }
    }
}
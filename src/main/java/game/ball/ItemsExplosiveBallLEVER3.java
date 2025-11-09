package game.ball;

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;
import java.util.List;

public class ItemsExplosiveBallLEVER3 extends ItemsForBall {
    private static final double EXPLOSION_DAMAGE = 30;
    private static final double RADIUS_MULTIPLIER = 2;
    public ItemsExplosiveBallLEVER3() {
        super("Bóng Nổ(Cấp 3)", "Hiệu lực 8s. Khi nhặt: khi bóng va chạm gạch, nó tạo ra 1"+
                " vụ nổ có bán kính nổ gấp 2 bán kính bóng và gây 30 sát thương", 8, 6);
    }

    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        double explosionX = collidingBall.getX();
        double explosionY = collidingBall.getY();
        double explosionRadius = collidingBall.getRadius() * RADIUS_MULTIPLIER;

        AssetManager.playSound("brick_break");
        for (Bricks brick : allBricks) {
            if (brick.isBroken()) continue;
            double closestX = Math.max(brick.getX(), Math.min(explosionX, brick.getX() + brick.getWidth()));
            double closestY = Math.max(brick.getY(), Math.min(explosionY, brick.getY() + brick.getHeight()));
            double distanceX = explosionX - closestX;
            double distanceY = explosionY - closestY;
            double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
            if (distanceSquared < (explosionRadius * explosionRadius)) {
                brick.hit(EXPLOSION_DAMAGE);
            }
        }
    }
}
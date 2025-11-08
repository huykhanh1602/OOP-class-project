package game.ball; // (Đặt nó cùng package với ItemsForBall)

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;
import java.util.List;

public class ItemsExplosiveBall extends ItemsForBall {
    private static final double EXPLOSION_DAMAGE = 10;
    private static final double RADIUS_MULTIPLIER = 3.0;
    public ItemsExplosiveBall() {
        super("Bóng Nổ", "Khi va chạm gạch, tạo ra 1 vụ nổ gây 10 sát thương", 15, 50);
    }
    /**
     * CHÚ Ý: Đây là chữ ký hàm MỚI (new signature).
     * Nó cần 'allBricks' để gây sát thương và 'pendingBalls' để sửa lỗi.
     */
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        double explosionX = collidingBall.getX();
        double explosionY = collidingBall.getY();
        double explosionRadius = collidingBall.getRadius() * RADIUS_MULTIPLIER;
        //Tạo hiệu ứng hình ảnh (để "vẽ 1 vùng" như bạn muốn)
        ParticleManager.getInstance().createBrickBreakEffect(explosionX, explosionY, 20, Color.ORANGE);
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
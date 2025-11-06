package game.ball; // (Đặt nó cùng package với ItemsForBall)

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;
import java.util.List;

public class ItemsExplosiveBall extends ItemsForBall {

    private static final double EXPLOSION_DAMAGE = 10;    // Sát thương vụ nổ
    private static final double RADIUS_MULTIPLIER = 3.0; // Gấp 3 lần bán kính

    public ItemsExplosiveBall() {
        // (Bạn cần thêm Color vào constructor của ItemsForBall.java nếu muốn)
        super("Bóng Nổ", "Khi va chạm gạch, tạo ra 1 vụ nổ gây 10 sát thương", 15, 5);
    }

    /**
     * CHÚ Ý: Đây là chữ ký hàm MỚI (new signature).
     * Nó cần 'allBricks' để gây sát thương và 'pendingBalls' để sửa lỗi.
     */
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {

        // 1. Lấy thông tin vụ nổ
        double explosionX = collidingBall.getX();
        double explosionY = collidingBall.getY();
        double explosionRadius = collidingBall.getRadius() * RADIUS_MULTIPLIER;

        // 2. Tạo hiệu ứng hình ảnh (để "vẽ 1 vùng" như bạn muốn)
        // (Giả sử bạn có hàm createExplosionEffect trong ParticleManager)
        ParticleManager.getInstance().createBrickBreakEffect(explosionX, explosionY, 20, Color.ORANGE);
        AssetManager.playSound("brick_break"); // (Dùng tạm âm thanh này)

        // 3. Lặp qua TẤT CẢ gạch để gây sát thương
        for (Bricks brick : allBricks) {

            if (brick.isBroken()) continue; // Bỏ qua gạch đã vỡ

            // 4. Kiểm tra va chạm (AABB vs Circle)
            // Tìm điểm gần nhất trên hình chữ nhật so với tâm vòng tròn
            double closestX = Math.max(brick.getX(), Math.min(explosionX, brick.getX() + brick.getWidth()));
            double closestY = Math.max(brick.getY(), Math.min(explosionY, brick.getY() + brick.getHeight()));

            // Tính khoảng cách
            double distanceX = explosionX - closestX;
            double distanceY = explosionY - closestY;
            double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);

            // 5. Nếu gạch nằm trong bán kính nổ, gây sát thương
            if (distanceSquared < (explosionRadius * explosionRadius)) {
                brick.hit(EXPLOSION_DAMAGE); // Gây 10 sát thương
            }
        }
    }
}
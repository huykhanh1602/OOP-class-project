package game.ball;

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;
import java.util.List;

public class ItemsExplosiveBallLEVER5 extends ItemsForBall {
    private double numberCollision = 0;
    public ItemsExplosiveBallLEVER5() {
        super("Bóng Nổ(Cấp 5)", "Hiệu lực 10s. Khi nhặt: khi bóng va chạm gạch, nó tạo ra" +
                " 1 vụ nổ có bán kính nổ gấp 4 bán kính bóng và gây sát thương theo số lần va chạm của bóng", 10, 1);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        numberCollision++;
        RenderExplosive(collidingBall, allBalls, allBricks, pendingBalls, 4, 50 * numberCollision);
    }
}
package game.ball;

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;
import java.util.List;

public class ItemsExplosiveBallLEVER3 extends ItemsForBall {
    public ItemsExplosiveBallLEVER3() {
        super("Bóng Nổ(Cấp 3)", "Hiệu lực 8s. Khi nhặt: khi bóng va chạm gạch, nó tạo ra 1"+
                " vụ nổ có bán kính nổ gấp 2 bán kính bóng và gây 30 sát thương", 8, 6);
    }

    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        RenderExplosive(collidingBall, allBalls, allBricks, pendingBalls, 2, 30);
    }
}
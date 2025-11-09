package game.ball;

import game.abstraction.Bricks;
import game.particle.ParticleManager; // (Chúng ta sẽ dùng manager này để "vẽ" vụ nổ)
import game.AssetManager;
import javafx.scene.paint.Color;
import java.util.List;

public class ItemsExplosiveBallLEVER4 extends ItemsForBall {
    private double number = 0;

    public ItemsExplosiveBallLEVER4() {
        super("Bóng Nổ(Cấp 4)", "Khi nhặt:nó tạo ra 1" +
                " vụ nổ siêu to và gây 100 x số lần nhặt sát thương", 9, 5);
    }
    @Override
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        number++;
        RenderExplosive(collidingBall, allBalls, allBricks, pendingBalls, 20, 100 * number);
    }
}
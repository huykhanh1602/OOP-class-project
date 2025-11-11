package game.items;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import java.util.List;

public class ItemsExplosiveBallLEVER2 extends ItemsForBall {
    private double numberCollision = 0;

    public ItemsExplosiveBallLEVER2() {
        super("Bóng Nổ(Cấp 5)", "Hiệu lực 10s. Khi nhặt: khi bóng va chạm gạch, nó tạo ra" +
                " 1 vụ nổ có bán kính nổ gấp 2 bán kính bóng và gây sát thương theo số lần va chạm của bóng", 10, 10);
        setItemName("star");

    }

    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls) {
        numberCollision++;
        RenderExplosive(collidingBall, allBalls, allBricks, pendingBalls, 2, 50 * numberCollision);
    }
}
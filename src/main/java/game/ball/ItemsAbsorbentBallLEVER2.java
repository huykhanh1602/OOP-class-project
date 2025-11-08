package game.ball;

import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER2 extends ItemsAbsorbentBallLEVER5 {
    public ItemsAbsorbentBallLEVER2() {
        super("Bóng Hấp Thụ(Cấp 2)","Hiệu lực 7s. Khi nhặt: khi bóng va chạm gạch, nó được buff 1 damege vĩnh viễn.",7,11);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        double currentDamege = collidingBall.getDamege();
        collidingBall.setDamege(currentDamege + 1);
    }
}
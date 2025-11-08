package game.ball;

import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER4 extends ItemsAbsorbentBallLEVER5 {
    public ItemsAbsorbentBallLEVER4() {
        super("Bóng Hấp Thụ(Cấp 4)","Hiệu lực 9s. Khi nhặt: khi bóng va chạm gạch, nó được buff damege,size x 1.01 vĩnh viễn.",9,5);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        // Lặp qua TẤT CẢ các quả bóng
        for (Ball ball : allBalls) {
            double currentDamege = ball.getDamege();
            double currentSize = ball.getRadius();
            ball.setDamege(currentDamege * 1.01);
            ball.setRadius(currentSize * 1.01);
        }
    }
}
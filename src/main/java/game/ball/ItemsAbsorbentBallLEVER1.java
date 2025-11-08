package game.ball;

import game.abstraction.Bricks;

import java.util.List;

public class ItemsAbsorbentBallLEVER1 extends ItemsAbsorbentBallLEVER5 {
    public ItemsAbsorbentBallLEVER1() {
        super("Bóng Hấp Thụ(Cấp 1)","Tạo 1 quả bóng khi va chạm, bóng đó sẽ tăng nhẹ vĩnh viễn cho bóng đó tốc độ trong 10s" +
                ", damege và size vĩnh viễn ",10,100);
    }
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        List<Ball> newBalls = this.shatter(collidingBall);
        pendingBalls.addAll(newBalls);
    }
    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        collidingBall.setSpeedball(collidingBall.getSpeedball()*1.02);
        collidingBall.setDamege(collidingBall.getDamege()*1.02);
        collidingBall.setRadius(collidingBall.getRadius()*1.02);
    }
}

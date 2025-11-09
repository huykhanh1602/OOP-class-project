package game.ball;
import game.abstraction.Bricks;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class ItemsCloneBallLEVER1 extends ItemsForBall {
    private static final Random random = new Random();
    public ItemsCloneBallLEVER1() {
        super("Bóng Nhân Bản(Cấp 1)", "Khi nhặt:Tạo 1 bóng mới trên thanh chắn", 10, 13);
    }
    @Override
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
        List<Ball> newBalls = this.shatter(collidingBall);
        pendingBalls.addAll(newBalls);
    }
}
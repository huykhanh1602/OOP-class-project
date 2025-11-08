package game.powerup;
import java.util.List;
import game.abstraction.Bricks;
import game.ball.Ball;
import game.ball.ItemsForBall;
import game.objects.Paddle;
public class ActivePowerup {
    private ItemsForBall itemType;
    private double timeLeft;
    public ActivePowerup(ItemsForBall itemType) {
        this.itemType = itemType;
        this.timeLeft = itemType.getTimeuse();
    }
    public void update(double dt) {
        this.timeLeft -= dt;
    }
    public boolean isExpired() {
        return this.timeLeft <= 0;
    }
    public void applyOnBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        itemType.onBrickCollision(collidingBall, allBalls, allBricks, pendingBalls);
    }
    public void applyOnPaddleCollision(Ball ball, Paddle paddle, List<Bricks> allBricks) {
        itemType.onPaddleCollision(ball, paddle, allBricks);
    }
    public void applyOnFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        itemType.onFallingCollision(collidingBall, allBalls, allBricks, pendingBalls);
    }
    public ItemsForBall getItemType() {
        return this.itemType;
    }
}
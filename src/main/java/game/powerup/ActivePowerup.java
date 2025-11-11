package game.powerup;

import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.items.ItemsForBall;

/**
 * Represents a power-up that is currently ACTIVE in the game.
 * Contains the item type (prototype) and a countdown timer.
 */
public class ActivePowerup {

    private ItemsForBall itemType; 
    private double timeLeft;       

    public ActivePowerup(ItemsForBall itemType) {
        this.itemType = itemType;
        this.timeLeft = itemType.getTimeuse();
    }

    /**
     * Updates the remaining time of the power-up.
     * @param dt Delta time (time between frames) in seconds.
     */
    public void update(double dt) {
        this.timeLeft -= dt;
    }

    /**
     * @return true if this power-up has expired.
     */
    public boolean isExpired() {
        return this.timeLeft <= 0;
    }

    // ---  (Delegate) ---

    public void applyOnBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        itemType.onBrickCollision(collidingBall, allBalls, allBricks, pendingBalls);
    }

    public void applyOnPaddleCollision(Ball ball) {
        itemType.onPaddleCollision(ball);
    }

    public ItemsForBall getItemType() {
        return itemType;
    }

    public void setItemType(ItemsForBall itemType) {
        this.itemType = itemType;
    }
}
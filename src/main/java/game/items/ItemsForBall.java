package game.items;
import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;

/**
 * Abstract class for items that affect ball behavior.
 * Applied when picking up items dropped from broken bricks
 */
public abstract class ItemsForBall {
    /**
     * @param name Name of the ball item
     * @param description Description of the ball's function
     * @param timeuse Duration of effect
     * @param percent Drop rate when breaking bricks
     */
    private final String name;
    private String description;
    private double timeuse;
    private double percent;
    public ItemsForBall(String name, String description, double timeuse, double percent) {
        this.name = name;
        this.timeuse = timeuse;
        this.percent = percent;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public double getTimeuse() {
        return timeuse;
    }
    public void setTimeuse(double timeuse) {
        this.timeuse = timeuse;
    }
    public double getPercent() {
        return percent;
    }
    public String getDescription() {
        return description;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }
    public void setDescription(String description) {
        this.description = description;
    }



    // Method to apply effect when the upgrade is purchased/attached to the ball
    public void applyOnCreation(Ball ball) {
    }
    /**
     * Handle effect when the ball collides with a BRICK
     * @param collidingBall The ball that JUST collided
     * @param allBalls ALL balls on the field
     * @param allBricks ALL bricks on the field (FOR EXPLOSIVE BALL)
     * @param pendingBalls Pending list (TO FIX ITEMSAND ISSUE)
     */
    public void onBrickCollision(
        Ball collidingBall,
        List<Ball> allBalls,
        List<Bricks> allBricks,
        List<Ball> pendingBalls
    ) {

    }
    // Method to handle effect when the ball collides with the PADDLE
    public void onPaddleCollision(Ball ball) {
    }
}
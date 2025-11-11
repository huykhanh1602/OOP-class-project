package game.manager; // (Chung package với lớp trên)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.items.ItemsForBall;
import game.powerup.ActivePowerup;

/**
 * This class manages all active power-ups in the game.
 * It handles adding new power-ups, updating their timers,
 */
public class PowerupManager {

    private final List<ActivePowerup> activePowerups;

    public PowerupManager() {
        this.activePowerups = new ArrayList<>();
    }

    /**
     * Called when the player picks up an item.
     * Adds a new power-up to the list.
     * @param itemType The type of item (prototype) just picked up.
     */
    public void addPowerup(ItemsForBall itemType) {
        ActivePowerup newPowerup = new ActivePowerup(itemType);
        this.activePowerups.add(newPowerup);

    }

    /**
     * Updates ALL active power-ups.
     * Decreases their timers and removes expired power-ups.
     * @param dt Delta time
     */
    public void update(double dt) {
        // Use Iterator to safely remove elements while iterating
        Iterator<ActivePowerup> it = activePowerups.iterator();
        while (it.hasNext()) {
            ActivePowerup powerup = it.next();
            powerup.update(dt); // Decrease time

            // If expired, remove it from the list
            if (powerup.isExpired()) {
                it.remove();
            }
        }
    }
    /**
     * Called FROM GameManager when a ball-brick collision occurs.
     * Iterates through ALL power-ups and applies their effects.
     */
    public void handleBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnBrickCollision(collidingBall, allBalls, allBricks, pendingBalls);
        }
    }
    /**
     * Called FROM GameManager when a ball-paddle collision occurs.
     * Iterates through ALL power-ups and applies their effects.
     */
    public void handlePaddleCollision(Ball ball) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnPaddleCollision(ball);
        }
    }
}
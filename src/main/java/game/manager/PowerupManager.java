package game.manager; // (Chung package với lớp trên)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.items.ItemsForBall;
import game.objects.Paddle;
import game.powerup.ActivePowerup;

public class PowerupManager {
    private List<ActivePowerup> activePowerups;
    private List<ItemsForBall> availableItems;

    public PowerupManager() {
        this.activePowerups = new ArrayList<>();
    }

    public void addPowerup(ItemsForBall itemType, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls) {
        ActivePowerup newPowerup = new ActivePowerup(itemType);
        this.activePowerups.add(newPowerup);
        if (!allBalls.isEmpty()) {
            itemType.onFallingCollision(allBalls.get(0), allBalls, allBricks, pendingBalls);
        }
    }

    public void update(double dt, List<Ball> allBalls) {
        Iterator<ActivePowerup> it = activePowerups.iterator();
        while (it.hasNext()) {
            ActivePowerup powerup = it.next();
            powerup.update(dt);
            if (powerup.isExpired()) {
                powerup.getItemType().onExpired(allBalls);
                it.remove();
            }
        }
    }

    public void handleBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnBrickCollision(collidingBall, allBalls, allBricks, pendingBalls);
        }
    }

    public void handlePaddleCollision(Ball ball, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls,
            Paddle paddle) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnPaddleCollision(ball, allBalls, allBricks, pendingBalls, paddle);
        }
    }
}

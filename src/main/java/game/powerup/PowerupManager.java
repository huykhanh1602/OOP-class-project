package game.powerup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.abstraction.Bricks;
import game.ball.Ball;
import game.ball.ItemsForBall;
import game.objects.Paddle;
public class PowerupManager {
    private List<ActivePowerup> activePowerups;
    public PowerupManager() {
        this.activePowerups = new ArrayList<>();
    }
     //itemType Loại vật phẩm (prototype) vừa nhặt được.
    public void addPowerup(ItemsForBall itemType) {
        ActivePowerup newPowerup = new ActivePowerup(itemType);
        this.activePowerups.add(newPowerup);
    }
    public void update(double dt) {
        Iterator<ActivePowerup> it = activePowerups.iterator();
        while (it.hasNext()) {
            ActivePowerup powerup = it.next();
            powerup.update(dt);
            if (powerup.isExpired()) {
                it.remove();
            }
        }
    }
    public void handleBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnBrickCollision(collidingBall, allBalls, allBricks, pendingBalls);
        }
    }
    public void handlePaddleCollision(Ball ball, Paddle paddle, List<Bricks> allBricks) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnPaddleCollision(ball, paddle, allBricks);
        }
    }
    public void handleFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnFallingCollision(collidingBall, allBalls, allBricks, pendingBalls);
        }
    }
}
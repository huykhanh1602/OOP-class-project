package game.manager;

import java.util.ArrayList;
import java.util.List;

import game.GameContext;
import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.ball.EyeOfDragonBall;
import game.ball.FireBall;
import game.ball.SlimeBall;
import game.ball.SnowBall;
import game.bricks.BrickLoader;
import game.items.ItemsForBall;
import game.objects.Paddle;
import game.objects.Portal;
import game.powerup.FallingItem;

//Khởi tạo Game

public final class GameWorld {
    private Paddle paddle;
    private List<Ball> balls;
    private List<Bricks> bricks;
    private PowerupManager powerupManager;
    private final List<ItemsForBall> availableItems;
    private List<FallingItem> fallingItems;
    private List<Ball> pendingBallsToAdd;
    private boolean isAiming = false;

    private boolean isPause = false; 

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }

    public GameWorld() {
        this.availableItems = new ArrayList<>();
        reset();
    }

    private Portal portalLeft;
    private Portal portalRight;

    public void reset() {
        if (GameContext.getInstance().getCurrentLevel() > GameContext.getInstance().getLevelSpawnPortal()) {
            portalLeft = new Portal(0, 250);
            portalRight = new Portal(1000 - 65, 200);
            portalLeft.link(portalRight);
            portalRight.link(portalLeft);
        }
        this.fallingItems = new ArrayList<>();
        paddle = new Paddle();
        balls = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            switch (GameContext.getInstance().getNameBall()) {
                case "slime_ball":
                    balls.add(new SlimeBall(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
                    break;
                case "eod_ball":
                    balls.add(new EyeOfDragonBall(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
                    break;
                case "fire_ball":
                    balls.add(new FireBall(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
                    break;
                case "snow_ball":
                    balls.add(new SnowBall(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
                default :
                    balls.add(new SlimeBall(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - paddle.getHeight()));
            }
        }
        bricks = BrickLoader.loadBricks();
        // clear particles when reset game
        this.pendingBallsToAdd = new ArrayList<>();
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public List<Bricks> getBricks() {
        return bricks;
    }

    public PowerupManager getPowerupManager() {
        return powerupManager;
    }

    public List<ItemsForBall> getAvailableItems() {
        return availableItems;
    }

    public List<FallingItem> getFallingItems() {
        return fallingItems;
    }

    public List<Ball> getPendingBallsToAdd() {
        return pendingBallsToAdd;
    }

    public Portal getPortalLeft() {
        return portalLeft;
    }

    public Portal getPortalRight() {
        return portalRight;
    }

    public boolean isIsAiming() {
        return isAiming;
    }

    public void setIsAiming(boolean isAiming) {
        this.isAiming = isAiming;
    }
}

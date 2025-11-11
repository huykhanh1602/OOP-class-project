package game.manager;

import java.util.Iterator;

import game.AssetManager;
import game.GameContext;
import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.items.ItemsForBall;
import game.powerup.AvailableItems;
import game.powerup.FallingItem;

import game.particle.ParticleManager;

public class CollisionSystem {
    public CollisionSystem() {
    }
    public void checkCollisions(PowerupManager powerupManager, GameWorld gw) {
        for (Iterator<Ball> BALL = gw.getBalls().iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();
            ball.collides(ball);
            if (ball.getRect().intersects(gw.getPaddle().getBounds())) {
                ball.collides(gw.getPaddle());
                powerupManager.handlePaddleCollision(ball);
            }
            if (gw.getPortalLeft() != null && gw.getPortalRight() != null) {
                if (gw.getPortalLeft().checkCollision(ball)) {
                    gw.getPortalLeft().teleport(ball);
                }
                if (gw.getPortalRight().checkCollision(ball)) {
                    gw.getPortalRight().teleport(ball);
                }
                AssetManager.playSound("portal");
            }

            for (Iterator<Bricks> BRICK = gw.getBricks().iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();
                double dame = ball.getDamage();
                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    ball.collides(brick);
                    powerupManager.handleBrickCollision(ball, gw.getBalls(), gw.getBricks(), gw.getPendingBallsToAdd());
                    brick.hit(dame);
                    if (brick.isDestroyable()) {
                        brick.hitAnimation();
                        ball.setMaxcollision(ball.getMaxcollision() - 1);
                    }
                    if (brick.isBroken()) {
                        AssetManager.playSound("brick_break");
                        BRICK.remove();
                        GameContext.getInstance().addScore(brick.getPoint());
                        ItemsForBall itemType = AvailableItems.getRandomItem();
                        if (itemType != null) {
                            double spawnX = brick.getRectBrick().getMinX() + brick.getRectBrick().getWidth() / 2;
                            double spawnY = brick.getRectBrick().getMinY() + brick.getRectBrick().getHeight() / 2;
                            FallingItem newItem = new FallingItem(spawnX, spawnY, itemType);
                            gw.getFallingItems().add(newItem);
                        }
                        double brickCenterX = brick.getX();
                        double brickCenterY = brick.getY();
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY,
                                6, brick.getColor());
                        if (brick.getType() == "CHEST") {
                            CoinManager.getInstance().addCoin(5);
                        }
                    }
                    if (ball.getMaxcollision() <= 0) {
                        BALL.remove();
                    }
                    break; // tránh va chạm nhiều brick 1 frame
                }
            }
            // TODO: fix ratio
            if (ball.getY() > 1000) {
                BALL.remove();
            }
        }
        Iterator<FallingItem> itemIt = gw.getFallingItems().iterator();
        while (itemIt.hasNext()) {
            FallingItem item = itemIt.next();
            if (gw.getPaddle().getBounds().intersects(item.getBounds())) {
                powerupManager.addPowerup(item.getItemType(), gw.getBalls(), gw.getBricks(), gw.getPendingBallsToAdd());
                item.remove();
            }
        }
    }
}

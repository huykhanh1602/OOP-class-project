package game.manager;

import java.util.Iterator;

import game.AssetManager;
import game.GameContext;
import game.abstraction.Bricks;
import game.ball.Ball;
import game.items.ItemsForBall;
import game.particle.ParticleManager;
import game.powerup.FallingItem;
import game.powerup.PowerupManager;

public class CollisionSystem {

    public CollisionSystem() {}

    public void checkCollisions(PowerupManager powerupManager, GameWorld gw) {
        for (Iterator<Ball> BALL = gw.getBalls().iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();
            ball.collides(ball);
            if (ball.getRect().intersects(gw.getPaddle().getBounds())) {
                // Bây giờ mới gọi hàm void để xử lý nảy
                ball.collides(gw.getPaddle());
                // Và gọi powerup
                powerupManager.handlePaddleCollision(ball);
            }
            
            // if (gw.getPortalLeft().checkCollision(ball)) {
            //     gw.getPortalLeft().teleport(ball);
            // }
            // if (gw.getPortalRight().checkCollision(ball)) {
            //     gw.getPortalRight().teleport(ball);
            // }
            
            for (Iterator<Bricks> BRICK = gw.getBricks().iterator(); BRICK.hasNext();) {
                Bricks brick = BRICK.next();
                double dame = ball.getDamage();
                if (!brick.isBroken() && ball.intersects(brick.getRectBrick())) {
                    ball.collides(brick);
                    powerupManager.handleBrickCollision(ball, gw.getBalls(), gw.getBricks(), gw.getPendingBallsToAdd());
                    brick.hit(dame);
                    if (brick.isDestroyable()) {brick.hitAnimation(); ball.setMaxcollision(ball.getMaxcollision()-1);}
                    if (brick.isBroken()) {
                        AssetManager.playSound("ball_collide");
                        BRICK.remove();
                        GameContext.getInstance().addScore(brick.getPoint());

                        double brickCenterX = brick.getX();
                        double brickCenterY = brick.getY() ;
                        ParticleManager.getInstance().createBrickBreakEffect(brickCenterX, brickCenterY, 
                        6, brick.getColor());
                        for (ItemsForBall itemPrototype : gw.getAvailableItems()) {
                            double dropChance = itemPrototype.getPercent();
                            if (Math.random() < (dropChance / 100.0)) {
                                FallingItem newItem = new FallingItem(brickCenterX,brickCenterY, itemPrototype);
                                gw.getFallingItems().add(newItem);
                                System.out.println("Vật phẩm đã rơi: " + itemPrototype.getName());
                                break; // Chỉ rơi 1 vật phẩm mỗi gạch
                            }
                        }
                    }
                if(ball.getMaxcollision() <= 0) {
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
    }
    
}

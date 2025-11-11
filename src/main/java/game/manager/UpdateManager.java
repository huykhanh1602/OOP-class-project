package game.manager;

import game.abstraction.Ball;

public class UpdateManager {
    public void update(GameWorld gw) {
        gw.getPaddle().update();
        gw.getBalls().forEach(b -> b.update(gw.getPaddle()));
        gw.getBalls().forEach(b -> b.setPlayerAiming(gw.isIsAiming()));
        gw.getBricks().forEach(br -> br.update());
        if (gw.getPortalLeft() != null && gw.getPortalRight() != null) {
            gw.getPortalLeft().update();
            gw.getPortalRight().update();

            for(Ball ball : gw.getBalls()) {
            gw.getPortalLeft().updateCooldown(ball);
            gw.getPortalRight().updateCooldown(ball);
        }
        }
    }
}

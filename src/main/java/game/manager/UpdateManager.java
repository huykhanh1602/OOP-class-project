package game.manager;

import java.util.Iterator;

import game.particle.ParticleManager;
import game.powerup.FallingItem;

public class UpdateManager {
    public void update(GameWorld gw, PowerupManager powerupManager, double deltaTime) {
        gw.getPaddle().update();
        gw.getBalls().forEach(b -> b.update(gw.getPaddle()));
        gw.getBalls().forEach(b -> b.setPlayerAiming(gw.isIsAiming()));
        gw.getBricks().forEach(br -> br.update());
        if (gw.getPortalLeft() != null && gw.getPortalRight() != null) {
            gw.getPortalLeft().update();
            gw.getPortalRight().update();
        }
        powerupManager.update(deltaTime, gw.getBalls());
        updateFallingItems(gw, deltaTime);
        ParticleManager.getInstance().update(deltaTime);
    }
    private void updateFallingItems(GameWorld gw, double deltaTime) {
        Iterator<FallingItem> itemIt = gw.getFallingItems().iterator();
        while (itemIt.hasNext()) {
            FallingItem item = itemIt.next();
            item.update(deltaTime);
            if (item.isRemoved() || item.getY() > 800) {
                itemIt.remove();
            }
        }
    }
}

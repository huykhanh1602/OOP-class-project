package game.manager;

import java.util.Iterator;
import game.powerup.FallingItem;

public class UpdateManager {
    public void update(GameWorld gw, double deltaTime) {
        gw.getPaddle().update();
        gw.getBalls().forEach(b -> b.update(gw.getPaddle()));
        gw.getBalls().forEach(b -> b.setPlayerAiming(gw.isIsAiming()));
        gw.getBricks().forEach(br -> br.update());
        if (gw.getPortalLeft() != null && gw.getPortalRight() != null) {
            gw.getPortalLeft().update();
            gw.getPortalRight().update();
        }
        updateFallingItems(gw, deltaTime);
    }
    private void updateFallingItems(GameWorld gw, double deltaTime) {
        Iterator<FallingItem> itemIt = gw.getFallingItems().iterator();
        while (itemIt.hasNext()) {
            FallingItem item = itemIt.next();
            if (item.isRemoved() || item.getY() > 800) {
                itemIt.remove(); // Xóa khỏi danh sách
            }
            else {
                item.update(deltaTime);
            }
        }
    }
}

package game.manager;

import java.util.Iterator;

import game.particle.ParticleManager;
import game.powerup.FallingItem;

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
        }
    }
}

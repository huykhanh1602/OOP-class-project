package game.manager;

import game.GameContext;
import game.abstraction.Bricks;
import game.ball.Ball;
import game.particle.ParticleManager;
import game.powerup.FallingItem;
import javafx.scene.canvas.GraphicsContext;

public class GameRenderer {
    private final int widthScreen, heightScreen;

    public GameRenderer(int widthScreen, int heightScreen) {
        this.widthScreen = widthScreen;
        this.heightScreen = heightScreen;
    }

    public void render(GraphicsContext gc, GameWorld gw) {
        gc.clearRect(0, 0, widthScreen, heightScreen); 
        gw.getPaddle().render(gc);
        if (GameContext.getInstance().getCurrentLevel() > 2) {
            gw.getPortalLeft().render(gc);
            gw.getPortalRight().render(gc);
        }

        for (Ball ball : gw.getBalls()) {
            ball.render(gc);
        }

        for (Bricks brick : gw.getBricks()) {
            brick.render(gc);
        }
        for (FallingItem item : gw.getFallingItems()) {
            item.render(gc);
        }
        ParticleManager.getInstance().render(gc);
    }
}

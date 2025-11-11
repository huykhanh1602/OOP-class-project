package game.manager;

import game.App;
import game.GameContext;
import game.abstraction.Ball;
import game.particle.ParticleManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameManager {
    private final App app;
    private GameWorld gw;
    private final GameRenderer renderer;
    private final CollisionSystem collisionSystem;
    private final UpdateManager updateManager;
    private final PowerupManager powerupManager;

    public GameManager(int width, int height, App app) {
        this.app = app;
        this.gw = new GameWorld();
        this.renderer = new GameRenderer(width, height);
        this.collisionSystem = new CollisionSystem();
        this.powerupManager = new PowerupManager();
        this.updateManager = new UpdateManager();
    }

    public void update(double deltaTime) {

        // 2. Update the state of entities (movement)
        updateManager.update(gw);

        // 3. Handle collisions
        collisionSystem.checkCollisions(powerupManager, gw);

        // 4. Update other systems
        powerupManager.update(deltaTime);
        ParticleManager.getInstance().update(deltaTime);

        // 5. Check win/lose conditions
        checkGameRules();
    }

    public void render(GraphicsContext gc) {
        // Delegate rendering entirely to the renderer
        renderer.render(gc, gw);
    }

    private void checkGameRules() {
        if (gw.getBricks().stream().allMatch(brick -> !brick.isDestroyable())) {
            app.switchToMerchantScene();
        }
        if (gw.getBalls().isEmpty()) {
            app.gameOver(getScore());

        }
    }

    public void handleKeyPress(KeyEvent key) {
        gw.getPaddle().handleKeyPressed(key);

        if (key.getCode() == KeyCode.SPACE && !gw.isIsAiming()) {
            this.gw.setIsAiming(true);
        }
    }

    public void handleKeyRelease(KeyEvent key) {
        gw.getPaddle().handleKeyReleased(key);
        if (key.getCode() == KeyCode.SPACE && gw.isIsAiming()) {
            this.gw.setIsAiming(false);
            shootBall();
        }
    }

    private void shootBall() {
        if (gw.getBalls().isEmpty())
            return;

        for (Ball ball : gw.getBalls()) {
            if (!ball.isRunning) {
                ball.launchBall();
                ball.setRunning(true);
                break;
            }
        }
    }

    public int getScore() {
        return GameContext.getInstance().getCurrentScore();
    }

    public int getBalls() {
        return gw.getBalls().size();
    }

    public void reset() {
        this.gw = new GameWorld();
    }
}
package game.manager;

import java.util.Iterator;

import game.App;
import game.GameContext;
import game.abstraction.Ball;
import game.particle.ParticleManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameManager {
    // Sở hữu các hệ thống con
    private App app;
    private GameWorld gw;
    private GameRenderer renderer;
    private CollisionSystem collisionSystem;
    private UpdateManager updateManager;
    private PowerupManager powerupManager; // Đã có sẵn

    public GameManager(int width, int height, App app) {
        this.app = app;
        this.gw = new GameWorld(); // Khởi tạo dữ liệu
        this.renderer = new GameRenderer(width, height);
        this.collisionSystem = new CollisionSystem();
        this.powerupManager = new PowerupManager();
        this.updateManager = new UpdateManager();
    }

    public void update(double deltaTime) {
        updateManager.update(gw, deltaTime);
        collisionSystem.checkCollisions(powerupManager, gw);
        powerupManager.update(deltaTime, gw.getBalls());
        ParticleManager.getInstance().update(deltaTime);
        checkGameRules();
    }

    public void render(GraphicsContext gc) {
        renderer.render(gc, gw);
    }

    private void checkGameRules() {
        if (gw.getBricks().stream().allMatch(brick -> !brick.isDestroyable())) {
            app.switchToMerchantScene();
            return;
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

        for (Iterator<Ball> BALL = gw.getBalls().iterator(); BALL.hasNext();) {
            Ball ball = BALL.next();
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
package game.manager;

import java.util.Iterator;

import game.powerup.PowerupManager;
import game.App;
import game.GameContext;
import game.ball.Ball;
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
    private PowerupManager powerupManager; // Đã có sẵn

    public GameManager(int width, int height, App app) {
        this.app = app;
        this.gw = new GameWorld(); // Khởi tạo dữ liệu
        this.renderer = new GameRenderer(width, height);
        this.collisionSystem = new CollisionSystem();
        this.powerupManager = new PowerupManager();
    }

    public void update(double deltaTime) {

        // 2. Cập nhật trạng thái các thực thể (di chuyển)
        gw.getPaddle().update(deltaTime);
        gw.getBalls().forEach(b -> b.update(deltaTime, gw.getPaddle()));
        gw.getBalls().forEach(b -> b.setPlayerAiming(gw.isIsAiming()));
        gw.getBricks().forEach(br -> br.update());
        if (GameContext.getInstance().getCurrentLevel() > 2) {
            gw.getPortalLeft().update();
            gw.getPortalRight().update();
        }

        // 3. Xử lý va chạm
        collisionSystem.checkCollisions(powerupManager, gw);

        // 4. Cập nhật các hệ thống khác
        powerupManager.update(deltaTime);
        ParticleManager.getInstance().update(deltaTime);

        // 5. Kiểm tra điều kiện thắng/thua
        checkGameRules();
    }

    public void render(GraphicsContext gc) {
        // Ủy quyền hoàn toàn cho renderer
        renderer.render(gc, gw);
    }

    private void checkGameRules() {
        if (gw.getBricks().stream().allMatch(brick -> !brick.isDestroyable())) {
            app.nextLevel();
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

    public void reset() {
        this.gw = new GameWorld();
    }
}
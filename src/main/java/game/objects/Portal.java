package game.objects;

import java.util.HashSet;
import java.util.Set;

import game.AssetManager;
import game.Constant;
import game.ball.Ball;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Portal {
    private double x, y, width, height;
    private Portal linkedPortal;
    private Set<Ball> cooldown = new HashSet<>();
    private double diretion = 1;

    private Image portalImage = AssetManager.getImage("PORTAL");

    public Portal(double x, double y) {
        this.x = x; this.y = y; 
        this.width = Constant.BRICK_WIDTH;
        this.height = Constant.BRICK_HEIGHT;
    }

    public void updateFromUI(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean checkCollision(Ball ball) {
        return ball.getRect().intersects(new Rectangle2D(x, y, width, height));
    }

    public void teleport(Ball ball) {
        if (linkedPortal == null || cooldown.contains(ball)) return;
        ball.setX(linkedPortal.x + linkedPortal.width / 2);
        ball.setY(linkedPortal.y + linkedPortal.height / 2);
        cooldown.add(ball);
        linkedPortal.cooldown.add(ball);
        updateCooldown(ball);
    }

    public void updateCooldown(Ball ball) {
        if (!checkCollision(ball)) cooldown.remove(ball);
    }

    public void link(Portal other) {
        this.linkedPortal = other;
    }

    public void update() {
        if (y > 550 || y < 0) diretion *= -1;
        y += diretion;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(portalImage, x, y);
    }
}

package game.objects;

import java.util.HashSet;
import java.util.Set;

import game.ball.Ball;
import javafx.geometry.Rectangle2D;

public class Portal {
    private double x, y, width, height;
    private Portal linkedPortal;
    private Set<Ball> cooldown = new HashSet<>();

    public Portal(double x, double y, double width, double height) {
        this.x = x; this.y = y; this.width = width; this.height = height;
    }

    public void updateFromUI(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean checkCollision(Ball ball) {
        return ball.getRect().intersects(new Rectangle2D(x + width / 4, y + height / 3.5, width /2, height / 3.5));
    }

    public void teleport(Ball ball) {
        if (linkedPortal == null || cooldown.contains(ball)) return;
        ball.setX(linkedPortal.x + linkedPortal.width/2 + width / 4);
        ball.setY(linkedPortal.y + linkedPortal.height/2 + height / 3.5);
        cooldown.add(ball);
        linkedPortal.cooldown.add(ball);
    }

    public void updateCooldown(Ball ball) {
        if (!checkCollision(ball)) cooldown.remove(ball);
    }

    public void link(Portal other) {
        this.linkedPortal = other;
    }
}

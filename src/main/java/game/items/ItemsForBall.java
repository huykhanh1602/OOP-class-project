package game.items;

import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.ball.NormalBall;
import game.objects.Paddle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Abstract class for items that affect ball behavior.
 * Applied when picking up items dropped from broken bricks
 */
public abstract class ItemsForBall {
    private String itemName = "diamond";

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @param name        Name of the ball item
     * @param description Description of the ball's function
     * @param timeuse     Duration of effect
     * @param percent     Drop rate when breaking bricks
     */
    private final String name;
    private String description;
    private double timeuse;
    private double percent;

    public ItemsForBall(String name, String description, double timeuse, double percent) {
        this.name = name;
        this.timeuse = timeuse;
        this.percent = percent;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getTimeuse() {
        return timeuse;
    }

    public void setTimeuse(double timeuse) {
        this.timeuse = timeuse;
    }

    public double getPercent() {
        return percent;
    }

    public String getDescription() {
        return description;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    // Phương thức áp dụng hiệu ứng khi nâng cấp được mua
    public void applyOnCreation(Ball ball) {
    }

    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls) {
    }

    /**
     * @param collidingBall Quả bóng VỪA va chạm
     * @param allBalls      TẤT CẢ các quả bóng trên sân
     * @param allBricks     TẤT CẢ các viên gạch trên sân (CHO BÓNG NỔ)
     * @param pendingBalls  Danh sách chờ (ĐỂ SỬA LỖI ITEMSADN)
     */
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls) {
    }

    public void onPaddleCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls, Paddle paddle) {
    }

    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
        newBalls.add(newBall);
        return newBalls;
    }

    public void RenderExplosive(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks,
            List<Ball> pendingBalls, double RADIUS_MULTIPLIER, double EXPLOSION_DAMAGE) {
        double explosionX = collidingBall.getX();
        double explosionY = collidingBall.getY();
        double explosionRadius = collidingBall.getRadius() * RADIUS_MULTIPLIER;
        for (Iterator<Bricks> it = allBricks.iterator(); it.hasNext();) {
            Bricks brick = it.next();
            if (brick.isBroken())
                continue;
            double closestX = Math.max(brick.getX(), Math.min(explosionX, brick.getX() + brick.getWidth()));
            double closestY = Math.max(brick.getY(), Math.min(explosionY, brick.getY() + brick.getHeight()));
            double distanceX = explosionX - closestX;
            double distanceY = explosionY - closestY;
            double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
            if (distanceSquared < (explosionRadius * explosionRadius)) {
                brick.hit(EXPLOSION_DAMAGE);
                if (brick.isBroken()) {
                    it.remove();
                }
            }
        }
    }

    public void onExpired(List<Ball> allBalls) {
    }
}
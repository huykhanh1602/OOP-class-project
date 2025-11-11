package game.powerup;

import java.util.List;

import game.abstraction.Ball;
import game.abstraction.Bricks;
import game.items.ItemsForBall;

/**
 * Đại diện cho một vật phẩm đang CÓ HIỆU LỰC trong game.
 * Chứa loại vật phẩm (prototype) và bộ đếm thời gian.
 */
public class ActivePowerup {

    private ItemsForBall itemType; // Loại vật phẩm (ví dụ: ItemsAbsorbentBall)
    private double timeLeft;       // Thời gian hiệu lực còn lại (tính bằng giây)

    public ActivePowerup(ItemsForBall itemType) {
        this.itemType = itemType;
        this.timeLeft = itemType.getTimeuse(); // Lấy thời gian từ prototype
    }

    /**
     * Cập nhật bộ đếm thời gian.
     * @param dt Delta time (thời gian giữa các khung hình)
     */
    public void update(double dt) {
        this.timeLeft -= dt;
    }

    /**
     * @return true nếu vật phẩm này đã hết thời gian hiệu lực.
     */
    public boolean isExpired() {
        return this.timeLeft <= 0;
    }

    // --- Các hàm ủy quyền (Delegate) ---
    // Gọi các hàm xử lý của "loại" vật phẩm

    public void applyOnBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        itemType.onBrickCollision(collidingBall, allBalls, allBricks, pendingBalls);
    }

    public void applyOnPaddleCollision(Ball ball) {
        itemType.onPaddleCollision(ball);
    }
}
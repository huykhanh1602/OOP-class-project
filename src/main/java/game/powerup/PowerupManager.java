package game.powerup; // (Chung package với lớp trên)

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import game.ball.Ball;
import game.ball.ItemsForBall;

/**
 * Đây là lớp "ListPowerup" mà bạn muốn.
 * Nó quản lý TẤT CẢ các vật phẩm đang có hiệu lực trong game.
 */
public class PowerupManager {

    // Đây là "SetList" của bạn, lưu tất cả các vật phẩm đang kích hoạt
    private List<ActivePowerup> activePowerups;

    public PowerupManager() {
        this.activePowerups = new ArrayList<>();
    }

    /**
     * Được gọi khi người chơi nhặt được một vật phẩm.
     * Thêm một vật phẩm mới vào danh sách.
     * @param itemType Loại vật phẩm (prototype) vừa nhặt được.
     */
    public void addPowerup(ItemsForBall itemType) {
        // Tạo một "thể hiện" mới của vật phẩm với bộ đếm thời gian riêng
        ActivePowerup newPowerup = new ActivePowerup(itemType);
        this.activePowerups.add(newPowerup);

        // (Bạn có thể thêm code áp dụng 1 lần ngay khi nhặt ở đây nếu muốn)
        // Ví dụ: itemType.applyOnCreation(...);
    }

    /**
     * Cập nhật TẤT CẢ các vật phẩm đang kích hoạt.
     * Giảm thời gian và xóa bỏ những vật phẩm đã hết hạn.
     * @param dt Delta time
     */
    public void update(double dt) {
        // Dùng Iterator để có thể xóa phần tử ngay trong lúc duyệt
        Iterator<ActivePowerup> it = activePowerups.iterator();
        while (it.hasNext()) {
            ActivePowerup powerup = it.next();
            powerup.update(dt); // Giảm thời gian

            // Nếu hết hạn, xóa nó khỏi danh sách
            if (powerup.isExpired()) {
                it.remove();
            }
        }
    }
    /**
     * Được gọi TỪ GameManager khi có va chạm bóng-gạch.
     * Duyệt qua TẤT CẢ vật phẩm và áp dụng hiệu ứng của chúng.
     */
    public void handleBrickCollision(Ball collidingBall, List<Ball> allBalls) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnBrickCollision(collidingBall, allBalls);
        }
    }

    /**
     * Được gọi TỪ GameManager khi có va chạm bóng-thanh chắn.
     * Duyệt qua TẤT CẢ vật phẩm và áp dụng hiệu ứng của chúng.
     */
    public void handlePaddleCollision(Ball ball) {
        for (ActivePowerup powerup : activePowerups) {
            powerup.applyOnPaddleCollision(ball);
        }
    }
}
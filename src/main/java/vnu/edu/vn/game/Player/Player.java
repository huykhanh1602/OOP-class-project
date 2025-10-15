package vnu.edu.vn.game.Player;
import vnu.edu.vn.game.bricks.Bricks;
import vnu.edu.vn.game.score.Score;

import java.io.Serializable;
import java.util.*;

/**
 * Lớp Player dùng để lưu trữ trạng thái người chơi
 * (Tài nguyên, Powerup đã mua, Mạng sống, v.v.).
 * Cần triển khai Serializable để lưu vào file save.
 */
public class Player implements Serializable {
    // Số serialVersionUID cho quá trình Serialization
    private static final long serialVersionUID = 1L;
    private int currentScore;
    private int coins;
    private int lives;
    // Set (Tập hợp) dùng để lưu trữ TÊN (String) của các Powerup VĨNH VIỄN đã mua
    // Dùng Set đảm bảo mỗi Powerup chỉ được lưu một lần
    private final Set<String> purchasedPowerups;
    private final Map<String,Integer> powerupLevels;
    private Score score;

    public Player(int initialLives, Map<String, Integer> powerpuLevels){
        this.lives = initialLives;
        this.coins = 0;
        this.purchasedPowerups = new HashSet<>();
        this.powerupLevels = new HashMap<>();
        score = new Score();
    }
    // --- Phương thức quản lý Cấp độ Powerup ---

    /**
     * Tăng cấp độ của một Powerup thêm 1 và trả về cấp độ mới.
     * Phương thức này được gọi khi người chơi mua Powerup.
     * Thêm một Powerup vào danh sách sở hữu của người chơi.
     * @param powerupName Tên duy nhất của Powerup (ví dụ: "Speed Boost I")
     */
    //public void addPurchasedPowerup(String powerupName){
    //    this.purchasedPowerups.add(powerupName);
    //}
    /**
     * Kiểm tra xem người chơi đã sở hữu Powerup này chưa.
     * Đây là phương thức mà hàm isPurchased() cần dùng.
     * powerupName Tên Powerup cần kiểm tra
     * @return true nếu đã mua, false nếu chưa.
     */
    public boolean hasPowerup(String powerupName){
        return this.purchasedPowerups.contains(powerupName);
    }
    public int increasePowerupLevel(String  powerupName){
        // Lấy cấp độ hiện tại (mặc định là 0 nếu chưa mua)
        int currentLevel = this.powerupLevels.getOrDefault(powerupName, 0);
        int newLevel = currentLevel + 1;
        // Lưu lại cấp độ mới
        this.powerupLevels.put(powerupName, newLevel);
        return newLevel;
    }
    /**
     * Lấy cấp độ hiện tại của một Powerup.
     */
    public int getPowerupLevel(String  powerupName){
        return this.powerupLevels.getOrDefault(powerupName, 0);
    }
}

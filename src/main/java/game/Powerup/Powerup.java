package game.Powerup;

import java.util.List;

import game.Player.Player;
import game.ball.Ball;

/**
 * Lớp cha trừu tượng (Abstract Class) cho các nâng cấp vĩnh viễn
 * mua từ Shop, áp dụng lên thuộc tính của quả bóng.
 */
public abstract class Powerup {
    private final String name;
    private final String description;
    private final double cost;
    private final double reduceball;
    private double level;

    /**
     * Constructor.
     * 
     * @param name        Tên của Powerup (ví dụ: "Tăng Tốc Độ Vĩnh Viễn")
     * @param description Mô tả chi tiết (ví dụ: "Tăng tốc độ bóng thêm 10%")
     * @param cost        Giá tiền trong Shop
     * @param reduceball  số bóng bị giảm khi mua trong shop
     */
    public Powerup(String name, String description, double cost, double reduceball) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.reduceball = reduceball;
    }

    public int getCurrentLevel(Player player) {
        return player.getPowerupLevel(this.name);
    }

    public void applyEffect(List<Ball> balls, int level) {

    }

    public boolean isPurchased(Player player) {
        return player.hasPowerup(this.name);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return this.cost;
    }
}

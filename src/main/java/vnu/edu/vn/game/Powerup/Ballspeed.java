package vnu.edu.vn.game.Powerup;
import vnu.edu.vn.game.ball.Ball;

import java.util.List;

public class Ballspeed extends Powerup {
    private static final float increasenum = 1.1f;

    /**
     * Constructor.
     *
     * @param name        Tên của Powerup (ví dụ: "Tăng Tốc Độ Vĩnh Viễn")
     * @param description Mô tả chi tiết (ví dụ: "Tăng tốc độ bóng thêm 10%")
     * @param cost        Giá tiền trong Shop
     * @param reduceball  số bóng bị giảm khi mua trong shop
     */
    public void ballspeed(String name, String description, double cost, double reduceball) {

    }

    /**
     * Constructor.
     *
     * @param name        Tên của Powerup (ví dụ: "Tăng Tốc Độ Vĩnh Viễn")
     * @param description Mô tả chi tiết (ví dụ: "Tăng tốc độ bóng thêm 10%")
     * @param cost        Giá tiền trong Shop
     * @param reduceball  số bóng bị giảm khi mua trong shop
     */
    public Ballspeed(String name, String description, double cost, double reduceball) {
        super(name, description, cost, reduceball);
    }

    public void applyEffect(List<Ball> balls, int level){
        if(level == 0) {
            return;
        }
    }
}
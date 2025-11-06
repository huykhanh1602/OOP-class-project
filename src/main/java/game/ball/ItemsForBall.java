package game.ball;
import game.abstraction.Bricks;
import java.util.Iterator;

/**
 * Lớp trừu tượng cho các nâng cấp TỨC THỜI/KẾT HỢP áp dụng trực tiếp lên một quả bóng.
 * Áp dụng khi nhặt vật phẩm rơi ra khi mà brick bị vỡ
 */
public abstract class ItemsForBall {
    /**
     * @param name tên của loại Vật phẩm này
     * @param description mô tả chức năng của bóng
     * @param timeuse thời gian hiệu lực
     * @param percent phần trăm rơi ra khi phá hủy bricks
     */
    private String name;
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
    public void setDescription(String description) {
        this.description = description;
    }



    // Phương thức áp dụng hiệu ứng khi nâng cấp được mua/gắn vào bóng
    public void applyOnCreation(Ball ball) {
    }
    // Phương thức xử lý hiệu ứng khi bóng va chạm với GẠCH (Brick)
    public void onBrickCollision(Ball ball) {
    }
    // Phương thức xử lý hiệu ứng khi bóng va chạm với THANH CHẮN (Paddle)
    public void onPaddleCollision(Ball ball) {
    }
}
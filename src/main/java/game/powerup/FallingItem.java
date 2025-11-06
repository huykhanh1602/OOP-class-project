package game.powerup; // Bạn có thể đặt nó trong package 'powerup'

import game.ball.ItemsForBall;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color; // (Tạm thời dùng màu, bạn có thể dùng hình ảnh)

public class FallingItem {

    private double x, y; // Tọa độ góc trên bên trái
    private double width = 20;
    private double height = 20;
    private double fallSpeed = 150.0; // Tốc độ rơi (pixels mỗi giây)

    private ItemsForBall itemType; // "Loại" vật phẩm mà nó đại diện
    private boolean isRemoved = false; // Đánh dấu để xóa

    public FallingItem(double x, double y, ItemsForBall itemType) {
        // Căn giữa vật phẩm tại vị trí viên gạch vỡ
        this.x = x - (width / 2);
        this.y = y;
        this.itemType = itemType;
    }

    /**
     * Cập nhật vị trí, di chuyển nó đi xuống
     * @param dt Delta Time (thời gian giữa các khung hình)
     */
    public void update(double dt) {
        this.y += fallSpeed * dt;
    }

    /**
     * Vẽ vật phẩm ra màn hình
     */
    public void render(GraphicsContext gc) {
        // TODO: Bạn có thể vẽ một hình ảnh dựa trên itemType.getName()
        // Tạm thời, chúng ta vẽ một hình vuông màu vàng
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, width, height);
    }

    /**
     * @return "Loại" vật phẩm để PowerupManager có thể kích hoạt
     */
    public ItemsForBall getItemType() {
        return itemType;
    }

    /**
     * @return Hình chữ nhật bao quanh vật phẩm để kiểm tra va chạm
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * Đánh dấu vật phẩm này cần được xóa
     */
    public void remove() {
        this.isRemoved = true;
    }

    /**
     * @return true nếu vật phẩm đã bị xóa (đã nhặt hoặc rơi ra ngoài)
     */
    public boolean isRemoved() {
        return isRemoved;
    }

    /**
     * @return Tọa độ Y để kiểm tra xem đã rơi ra ngoài màn hình chưa
     */
    public double getY() {
        return y;
    }
}
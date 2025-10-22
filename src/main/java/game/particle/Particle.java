package game.particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

import game.objects.GameObject;

/**
 * Lớp định nghĩa cho một hạt (particle) duy nhất trong hiệu ứng.
 * Mỗi hạt có vị trí, tốc độ, màu sắc và thời gian tồn tại riêng.
 */
public class Particle extends GameObject {

    private double velocityX;
    private double velocityY;
    private double lifeSpan; // Thời gian tồn tại của hạt (tính bằng giây)
    private Color color;
    private static final double GRAVITY = 0.05; // Lực hấp dẫn mô phỏng kéo hạt đi xuống

    public Particle(double x, double y) {
        // Khởi tạo hạt tại vị trí (x, y) với kích thước 2x2 pixels
        super(x, y, 5, 5, null);

        Random rand = new Random();
        // Tạo một tốc độ ngẫu nhiên ban đầu để các hạt bay ra theo nhiều hướng
        this.velocityX = (rand.nextDouble() - 0.5) * 4; // Tốc độ ngang ngẫu nhiên
        this.velocityY = (rand.nextDouble() - 0.5) * 4; // Tốc độ dọc ngẫu nhiên

        // Thời gian tồn tại ngẫu nhiên từ 1 đến 2 giây
        this.lifeSpan = rand.nextDouble() + 1.0;

        // Màu ngẫu nhiên (ví dụ: các sắc thái của màu xám, giống đá vỡ)
        double greyValue = rand.nextDouble() * 0.5 + 0.25; // giá trị từ 0.25 đến 0.75
        this.color = new Color(greyValue, greyValue, greyValue, 1.0);
    }

    /**
     * Cập nhật trạng thái của hạt trong mỗi khung hình.
     * 
     * @param deltaTime Thời gian trôi qua giữa các khung hình, giúp chuyển động
     *                  mượt mà.
     */
    public void update(double deltaTime) {
        // Giảm thời gian tồn tại của hạt
        lifeSpan -= deltaTime;

        // Cập nhật vị trí dựa trên tốc độ hiện tại
        x += velocityX;
        y += velocityY;

        // Áp dụng lực hấp dẫn để hạt bay theo đường cong và rơi xuống
        velocityY += GRAVITY;
    }

    /**
     * Vẽ hạt lên màn hình.
     * 
     * @param gc GraphicsContext để vẽ.
     */
    public void render(GraphicsContext gc) {
        // Làm cho hạt mờ dần khi sắp biến mất để hiệu ứng trông mềm mại hơn
        gc.setGlobalAlpha(lifeSpan > 0 ? lifeSpan : 0);

        gc.setFill(this.color);
        gc.fillRect(x, y, width, height);

        // Reset lại độ trong suốt để không ảnh hưởng đến việc vẽ các đối tượng khác
        gc.setGlobalAlpha(1.0);
    }

    /**
     * Kiểm tra xem hạt có còn "sống" hay không (thời gian tồn tại > 0).
     * 
     * @return true nếu hạt vẫn còn tồn tại, false nếu không.
     */
    public boolean isAlive() {
        return lifeSpan > 0;
    }
}

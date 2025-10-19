package game.particle;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Lớp chuyên dụng để quản lý tất cả các hiệu ứng hạt trong game.
 * Được thiết kế theo mẫu Singleton.
 */
public class ParticleManager {
    // 1. Singleton Pattern
    private static final ParticleManager instance = new ParticleManager();

    private ParticleManager() {
    }

    public static ParticleManager getInstance() {
        return instance;
    }

    // 2. Danh sách để chứa tất cả các hạt đang hoạt động
    private List<Particle> particles = new ArrayList<>();

    /**
     * Cập nhật trạng thái của tất cả các hạt.
     * 
     * @param deltaTime Thời gian trôi qua giữa các khung hình.
     */
    public void update(double deltaTime) {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle p = iterator.next();
            p.update(deltaTime);
            if (!p.isAlive()) {
                iterator.remove(); // Xóa các hạt đã "chết"
            }
        }
    }

    /**
     * Vẽ tất cả các hạt lên màn hình.
     * 
     * @param gc GraphicsContext để vẽ.
     */
    public void render(GraphicsContext gc) {
        for (Particle p : particles) {
            p.render(gc);
        }
    }

    /**
     * Tạo ra một vụ nổ các hạt tại một vị trí cụ thể.
     * Đây là phương thức mà GameManager sẽ gọi.
     * 
     * @param x     Vị trí theo trục X.
     * @param y     Vị trí theo trục Y.
     * @param count Số lượng hạt cần tạo.
     */
    public void createBrickBreakEffect(double x, double y, int count) {
        for (int i = 0; i < count; i++) {
            particles.add(new Particle(x, y));
        }
    }

    /**
     * Xóa tất cả các hạt, thường được gọi khi bắt đầu màn chơi mới.
     */
    public void clear() {
        particles.clear();
    }
}
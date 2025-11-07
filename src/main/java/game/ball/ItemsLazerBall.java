package game.ball;

import game.abstraction.Bricks;
import game.objects.Paddle;
import java.util.List;
import javafx.geometry.Rectangle2D;

/**
 * Vật phẩm bắn ra một tia laser từ paddle khi bóng chạm.
 */
public class ItemsLazerBall extends ItemsForBall {

    private static final double LAZER_DAMAGE = 10.0; // Sát thương của tia laser

    public ItemsLazerBall() {
        // Tên, Mô tả, Thời gian hiệu lực (giây), Tỉ lệ rơi (%)
        super("Bóng Laser",
                "Bắn 1 tia laser từ giữa paddle khi bóng chạm, gây 10 sát thương.",
                10.0, // 10 giây hiệu lực
                100.0); // 10% tỉ lệ rơi
    }

    /**
     * * @param ball Quả bóng vừa va chạm paddle
     * @param paddle Thanh chắn
     * @param allBricks Toàn bộ gạch trên màn hình
     */
    @Override
    public void onPaddleCollision(Ball ball, Paddle paddle, List<Bricks> allBricks) {

        // 1. Xác định vị trí bắn:
        //    Tia laser bắn từ tâm, mép trên của paddle
        double lazerX = paddle.getX() + (paddle.getWidth() / 2.0);
        double lazerStartY = paddle.getY();

        // 2. Tìm mục tiêu:
        //    Laser bắn thẳng lên (Y giảm), nó sẽ trúng viên gạch ĐẦU TIÊN
        //    trên đường đi, tức là viên gạch có Y LỚN NHẤT (gần paddle nhất).
        Bricks targetBrick = null;
        double targetBrickMaxY = Double.NEGATIVE_INFINITY; // Tìm Y lớn nhất (gần paddle nhất)

        for (Bricks brick : allBricks) {
            if (brick.isBroken() || !brick.isDestroyable()) {
                continue; // Bỏ qua gạch đã vỡ hoặc gạch "bất tử"
            }

            Rectangle2D rect = brick.getRectBrick();

            // Kiểm tra xem tia laser có nằm trong chiều rộng của viên gạch không
            if (lazerX >= rect.getMinX() && lazerX <= rect.getMaxX()) {

                // Gạch này nằm trên đường đạn.
                // Kiểm tra xem MÉP DƯỚI (getMaxY) của nó có gần paddle hơn
                // mục tiêu hiện tại không.
                if (rect.getMaxY() > targetBrickMaxY) {
                    targetBrickMaxY = rect.getMaxY();
                    targetBrick = brick;
                }
            }
        }

        // 3. Xử lý va chạm
        if (targetBrick != null) {
            // Tìm thấy mục tiêu! Gây sát thương
            targetBrick.hit(LAZER_DAMAGE);
            System.out.println("LAZER HIT BRICK!"); // Dùng để debug

            // TODO: Thêm hiệu ứng particle cho tia laser
            // ParticleManager.getInstance().createLazerEffect(lazerX, lazerStartY, lazerX, targetBrick.getY() + targetBrick.getHeight());

        } else {
            // Bắn trượt (không có gạch nào trên đường)
            System.out.println("LAZER MISSED!"); // Dùng để debug

            // TODO: Thêm hiệu ứng particle cho tia laser bắn lên vô cực
            // ParticleManager.getInstance().createLazerEffect(lazerX, lazerStartY, lazerX, 0);
        }

        // TODO: Thêm âm thanh
        // AssetManager.playSound("lazer_shoot");
    }


    // --- CÁC PHƯƠNG THỨC CŨ HƠN ---
    // (Bắt buộc phải có để code của bạn biên dịch được
    //  TRƯỚC KHI bạn nâng cấp hệ thống)

    @Override
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
        // Vật phẩm này không làm gì khi va chạm gạch
    }

    @Override
    public void onPaddleCollision(Ball ball) {
        // PHIÊN BẢN CŨ NÀY SẼ KHÔNG HOẠT ĐỘNG
        // vì nó không có tham số "paddle" và "allBricks".
        // Bạn cần làm theo hướng dẫn bên dưới.
    }
}
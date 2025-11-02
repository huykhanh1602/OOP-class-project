package game.particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

import game.objects.GameObject;

public class Particle extends GameObject {

    private double velocityX;
    private double velocityY;
    private double lifeSpan; // Thời gian tồn tại của hạt (tính bằng giây)
    private Color color;

    // Đơn vị: pixels / giây^2 (pixels per second squared)
    private static final double GRAVITY = 98.0; // Tăng giá trị này lên đáng kể

    private long creationTime = System.nanoTime();

    public Particle(double x, double y) {
        super(x, y, 5, 5);

        Random rand = new Random();

        // Vận tốc bây giờ là "pixels / giây"
        // (rand.nextDouble() - 0.5) cho giá trị từ -0.5 đến 0.5
        // Nhân với ~100 để có vận tốc ban đầu khoảng -50 đến 50 pixels/giây
        double initialSpeed = 100.0;
        this.velocityX = (rand.nextDouble() - 0.5) * initialSpeed;
        this.velocityY = (rand.nextDouble() - 0.5) * initialSpeed;

        this.lifeSpan = 1; // 1 giây

        double greyValue = rand.nextDouble() * 0.5 + 0.25;
        this.color = new Color(greyValue, greyValue, greyValue, 1.0);
    }

    public void update(double deltaTime) {
        lifeSpan -= deltaTime;

        // 1. Cập nhật vận tốc (velocity) dựa trên gia tốc (acceleration)
        // v = v0 + a * t
        velocityY += GRAVITY * deltaTime; // (pixels/giây^2) * giây = pixels/giây

        x += velocityX * deltaTime; // (pixels/giây) * giây = pixels
        y += velocityY * deltaTime; // (pixels/giây) * giây = pixels
    }

    public void render(GraphicsContext gc) {
        // Làm cho hạt mờ dần khi sắp biến mất
        // Đảm bảo alpha không bao giờ âm
        double alpha = Math.max(0, lifeSpan);
        gc.setGlobalAlpha(alpha);

        gc.setFill(this.color);
        gc.fillRect(x, y, width, height);

        gc.setGlobalAlpha(1.0);
    }

    public boolean isAlive() {
        if (lifeSpan <= 0) {
            // Dòng debug này vẫn rất hữu ích!
            // System.out.println(
            // "Particle died after: " + (System.nanoTime() - creationTime) /
            // 1_000_000_000.0 + " seconds");
        }
        return lifeSpan > 0;
    }
}
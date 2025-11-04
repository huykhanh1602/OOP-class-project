package game.particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

import game.objects.GameObject;

public class Particle extends GameObject {

    private double velocityX;
    private double velocityY;
    private double lifeSpan; // measure in seconds
    private Color color;

    // Đơn vị: pixels / giây^2 (pixels per second squared)
    private static final double GRAVITY = 98.0;

    public Particle(double x, double y, Color color) {
        super(x, y, 5, 5);

        Random rand = new Random();

        // random velocity
        double initialSpeed = 100.0;
        this.velocityX = (rand.nextDouble() - 0.5) * initialSpeed;
        this.velocityY = (rand.nextDouble() - 0.5) * initialSpeed;

        this.lifeSpan = 1;
        this.color = color;
    }

    public void update(double deltaTime) {
        lifeSpan -= deltaTime;

        // update position with gravity
        // v = v0 + a * t
        velocityY += GRAVITY * deltaTime;

        // s= v * t
        x += velocityX * deltaTime;
        y += velocityY * deltaTime;
    }

    public void render(GraphicsContext gc) {

        double alpha = Math.max(0, lifeSpan);
        gc.setGlobalAlpha(alpha);

        gc.setFill(this.color);
        gc.fillRect(x, y, width, height);

        gc.setGlobalAlpha(1.0);
    }

    public boolean isAlive() {
        if (lifeSpan <= 0) {
            // System.out.println(
            // "Particle life " + (System.nanoTime() - creationTime) /
            // 1_000_000_000.0 + " seconds");
        }
        return lifeSpan > 0;
    }
}
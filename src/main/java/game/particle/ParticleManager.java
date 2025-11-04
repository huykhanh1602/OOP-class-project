package game.particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleManager {
    // Singleton Pattern
    private static final ParticleManager instance = new ParticleManager();

    /// Time tracking for particle updates
    private static long lastUpdateTime = 0;

    private ParticleManager() {
    }

    public static ParticleManager getInstance() {
        return instance;
    }

    private List<Particle> particles = new ArrayList<>();

    // Calculate delta time for particle update
    private double calculateDeltaTime() {
        long currentTime = System.nanoTime();
        if (lastUpdateTime == 0) {
            lastUpdateTime = currentTime;
        }
        double dt = (currentTime - lastUpdateTime) / 1_000_000_000.0;
        lastUpdateTime = currentTime;

        // Clamp giá trị dt để tránh outlier
        if (dt < 0.001 || dt > 0.05) {
            dt = 0.016; // khoảng 60 FPS
        }
        return dt;
    }


    public void update() {
        double deltaTime = calculateDeltaTime();
        Iterator<Particle> iterator = particles.iterator();

        while (iterator.hasNext()) {
            Particle p = iterator.next();
            p.update(deltaTime);
            if (!p.isAlive()) {
                iterator.remove();
            }
        }
    }

    public void render(GraphicsContext gc) {
        for (Particle p : particles) {
            p.render(gc);
        }
    }

    public void createBrickBreakEffect(double x, double y, int count, Color color) {
        for (int i = 0; i < count; i++) {
            particles.add(new Particle(x, y, color));
        }
    }

    public void clear() {
        particles.clear();
    }

    public static void setLastUpdateTime() {
        lastUpdateTime = 0;
    }
}
package game.particle;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParticleManager {
    // Singleton Pattern
    private static final ParticleManager instance = new ParticleManager();

    private ParticleManager() {
    }

    public static ParticleManager getInstance() {
        return instance;
    }

    private List<Particle> particles = new ArrayList<>();

    public void update(double deltaTime) {
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
}
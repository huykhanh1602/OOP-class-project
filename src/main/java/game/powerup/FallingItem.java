package game.powerup;

import game.items.ItemsForBall;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FallingItem {

    private double x, y; 
    private final double width = 20;
    private final double height = 20;
    private final double fallSpeed = 150.0; // pixels per second

    private final ItemsForBall itemType;
    private boolean isRemoved = false;

    public FallingItem(double x, double y, ItemsForBall itemType) {
        this.x = x - (width / 2);
        this.y = y;
        this.itemType = itemType;
    }

    /**
     * Updates the position, moving it downward
     * @param dt Delta Time (time between frames) in seconds
     */
    public void update(double dt) {
        this.y += fallSpeed * dt;
    }

    /**
     * Renders the item on the screen
     */
    public void render(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, width, height);
    }

    /**
     * @return "Type" of the item for PowerupManager to activate
     */
    public ItemsForBall getItemType() {
        return itemType;
    }

    /**
     * @return Bounding rectangle of the item for collision detection
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * Marks this item for removal
     */
    public void remove() {
        this.isRemoved = true;
    }

    /**
     * @return true if the item has been removed (picked up or fallen off screen)
     */
    public boolean isRemoved() {
        return isRemoved;
    }

    /**
     * @return Y coordinate to check if it has fallen off the screen
     */
    public double getY() {
        return y;
    }
}
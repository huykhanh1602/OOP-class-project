package game.powerup;

import game.AssetManager;
import game.items.ItemsForBall;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FallingItem {
    private double x, y;
    private final double width = 20;
    private final double height = 20;
    private double fallSpeed = 150.0;
    private ItemsForBall itemType;
    private boolean isRemoved = false;
    private Image currentImage;

    public FallingItem(double x, double y, ItemsForBall itemType) {
        this.x = x - (width / 2);
        this.y = y;
        this.itemType = itemType;
    }

    public void update(double dt) {
        this.y += fallSpeed * dt;
    }

    public void render(GraphicsContext gc) {
        try {
            gc.drawImage(AssetManager.getImage(itemType.getItemName()), x, y, width, height);
        } catch (NullPointerException e) {
            System.out.println(itemType.getItemName());
            gc.setFill(Color.YELLOW);
            gc.fillRect(x, y, width, height);
        }
    }

    public ItemsForBall getItemType() {
        return itemType;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D(x, y, width, height);
    }

    public void remove() {
        this.isRemoved = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public double getY() {
        return y;
    }
}
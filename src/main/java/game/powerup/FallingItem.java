package game.powerup;
import game.ball.ItemsForBall;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
public class FallingItem {
    private double x, y;
    private double width = 20;
    private double height = 20;
    private double fallSpeed = 100;
    private ItemsForBall itemType;
    private boolean isRemoved = false;
    public FallingItem(double x, double y, ItemsForBall itemType) {
        this.x = x - (width / 2);
        this.y = y;
        this.itemType = itemType;
    }
    public void update(double dt) {
        this.y += fallSpeed * dt;
    }
    public void render(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, width, height);
    }
    //@return "Loại" vật phẩm để PowerupManager có thể kích hoạt
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
    public double getfallSpeed(){
        return fallSpeed;
    }
    public  void setfallSpeed(double fallSpeed) {
        this.fallSpeed = fallSpeed;
    }
}
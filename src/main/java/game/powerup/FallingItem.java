package game.powerup;
import game.ball.ItemsForBall;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Random;

public class FallingItem {
    private static final Random random = new Random();
    private List<ItemsForBall> availableItems;
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
    //chọn bất kì 1 vật phẩm trong list rồi xem nó có tỉ lệ ra ko, nếu có thì chỉ tạo ra 1 vật phẩm duy nhất,
    // nếu ko thì có tỉ lệ 90% lại chọn 1 vật phẩm bất kì tiếp
    public static FallingItem createRandomItem(double brickCenterX, double brickCenterY) {
        List<ItemsForBall> allItems = AvailableItems.getAvailableItems();
        if (allItems.isEmpty()) {
            return null;
        }
        double maxfor = 0;
        while (maxfor != 10) {
            maxfor++;
            int randomIndex = random.nextInt(allItems.size());
            ItemsForBall itemPrototype = allItems.get(randomIndex);
            double dropChance = itemPrototype.getPercent();
            if (random.nextDouble() < (dropChance / 100.0)) {
                return new FallingItem(brickCenterX, brickCenterY, itemPrototype);
            }
            if (random.nextDouble() <= 0.9) {
                continue;
            } else {
                break;
            }
        }
        return null;
    }
}
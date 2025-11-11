package game.items;
import game.abstraction.Bricks;
import game.ball.NormalBall;
import game.objects.Paddle;
import game.abstraction.Ball;
import java.util.ArrayList;
import java.util.List;
/**
 * Lớp trừu tượng cho các nâng cấp TỨC THỜI/KẾT HỢP áp dụng trực tiếp lên một quả bóng.
 * Áp dụng khi nhặt vật phẩm rơi ra khi mà brick bị vỡ
 */
public abstract class ItemsForBall {
    private String itemName = "diamond";

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * @param name tên của loại Vật phẩm này
     * @param description mô tả chức năng của bóng
     * @param timeuse thời gian hiệu lực
     * @param percent phần trăm rơi ra khi phá hủy bricks
     */
    private String name;
    private String description;
    private double timeuse;
    private double percent;
    public ItemsForBall(String name, String description, double timeuse, double percent) {
        this.name = name;
        this.timeuse = timeuse;
        this.percent = percent;
        this.description = description;
    }
    public String getName() {
        return name;
    }
    public double getTimeuse() {
        return timeuse;
    }
    public void setTimeuse(double timeuse) {
        this.timeuse = timeuse;
    }
    public double getPercent() {
        return percent;
    }
    public String getDescription() {
        return description;
    }
    public void setPercent(double percent) {
        this.percent = percent;
    }
    // Phương thức áp dụng hiệu ứng khi nâng cấp được mua
    public void applyOnCreation(Ball ball) {
    }
    public void onFallingCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls){
    }
    /**
     * @param collidingBall Quả bóng VỪA va chạm
     * @param allBalls TẤT CẢ các quả bóng trên sân
     * @param allBricks TẤT CẢ các viên gạch trên sân (CHO BÓNG NỔ)
     * @param pendingBalls Danh sách chờ (ĐỂ SỬA LỖI ITEMSADN)
     */
    public void onBrickCollision(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls) {
    }
    // Phương thức xử lý hiệu ứng khi bóng va chạm với THANH CHẮN (Paddle)

    public List<Ball> shatter(Ball currentBall) {
        List<Ball> newBalls = new ArrayList<>();
        NormalBall newBall = new NormalBall(currentBall.getX(), currentBall.getY());
        newBalls.add(newBall);
        return newBalls;
    }
    public void onPaddleCollision(Ball ball) {
    }
    public void onExpired(List<Ball> allBalls) {
    }
    public void RenderExplosive(Ball collidingBall, List<Ball> allBalls, List<Bricks> allBricks, List<Ball> pendingBalls,double RADIUS_MULTIPLIER, double EXPLOSION_DAMAGE){
        double explosionX = collidingBall.getX();
        double explosionY = collidingBall.getY();
        double explosionRadius = collidingBall.getRadius() * RADIUS_MULTIPLIER;
        for (Bricks brick : allBricks) {
            if (brick.isBroken()) continue;
            double closestX = Math.max(brick.getX(), Math.min(explosionX, brick.getX() + brick.getWidth()));
            double closestY = Math.max(brick.getY(), Math.min(explosionY, brick.getY() + brick.getHeight()));
            double distanceX = explosionX - closestX;
            double distanceY = explosionY - closestY;
            double distanceSquared = (distanceX * distanceX) + (distanceY * distanceY);
            if (distanceSquared < (explosionRadius * explosionRadius)) {
                brick.hit(EXPLOSION_DAMAGE);
            }
        }
    }
}
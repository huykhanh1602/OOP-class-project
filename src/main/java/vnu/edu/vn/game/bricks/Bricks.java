package vnu.edu.vn.game.bricks;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.Constant;
import vnu.edu.vn.game.GameObject;

/// Like this name

public class Bricks {

    private double x, y;
    private int durability;
    private int point;
    private double widthBrick = Constant.BRICK_WIDTH;
    private double heightBrick = Constant.BRICK_HEIGHT;

    public Bricks(double x, double y, int durability, int amount) {
        this.x = x;
        this.y = y;
        this.durability = durability;
        this.point = amount;
    }

    public int getPoint() { // Điểm
        return point;
    }

    // ERASE BRICK
    public boolean isBroken() { // Kiểm tra đã vỡ chưa
        return durability <= 0;
    }

    public void hit() { // Giảm độ bền
        durability--;
    }

    public Rectangle2D getRectBrick() { // Trả về thuộc tính để kiểm tra va chạm
        return new Rectangle2D(x, y, widthBrick, heightBrick);
    }

    public void update() {
        // Maybe change later
    }

    public void render(GraphicsContext gc) {
        if (durability == 0) {
            return;
        }

        switch (durability) { // Vẽ dựa theo độ bền brick
            case 1:
                Color c1 = Color.RED;
                gc.setFill(c1);
                gc.fillRect(x, y, widthBrick, heightBrick);
                break;
            case 2:
                Color c2 = Color.YELLOW;
                gc.setFill(c2);
                gc.fillRect(x, y, widthBrick, heightBrick);
                break;
            case 3:
                Color c3 = Color.GREEN;
                gc.setFill(c3);
                gc.fillRect(x, y, widthBrick, heightBrick);
                break;
        }
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, widthBrick, heightBrick);

    }

    public int getDurability() {
        return durability;
    }

    public double getWidthBrick() {
        return widthBrick;
    }

    public double getHeightBrick() {
        return heightBrick;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setWidthBrick(double widthBrick) {
        this.widthBrick = widthBrick;
    }

    public void setHeightBrick(double heightBrick) {
        this.heightBrick = heightBrick;
    }
}

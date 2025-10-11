package vnu.edu.vn.game.bricks;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.GameObject;

/// Like this name

public class Bricks {

    private double x,y;
    private int typeBrick;
    private boolean broken = false;
    private int amount;
    private double widthBrick = 50;
    private double heightBrick = 50;

    public Bricks(double x, double y, int typeBrick, int amount) {
        this.x = x;
        this.y = y;
        this.typeBrick = typeBrick;
        this.amount = amount;
    }


    public int getAmount() {                                    //Điểm
        return amount;
    }


    //ERASE BRICK
    public boolean isBroken() {                                 //Kiểm tra đã vỡ chưa
        return typeBrick <= 0;
    }

    public void hit() {                                         //Giảm độ bền
            typeBrick--;
    }

    public Rectangle2D getRectBrick() {                             //Trả về thuộc tính để kiểm tra va chạm
        return new Rectangle2D(x, y, widthBrick, heightBrick);
    }

    public void update() {
        // Maybe change later
    }

    public void render(GraphicsContext gc) {
        if (typeBrick == 0) {
            return;
        }

        switch (typeBrick) {                                    //Vẽ dựa theo độ bền brick
            case 1:
                Color c1 =  Color.RED;
                gc.setFill(c1);
                gc.fillRect(x, y, widthBrick, heightBrick);
                break;
            case 2:
                Color c2 =  Color.YELLOW;
                gc.setFill(c2);
                gc.fillRect(x, y, widthBrick, heightBrick);
                break;
            case 3:
                Color c3 =  Color.GREEN;
                gc.setFill(c3);
                gc.fillRect(x, y, widthBrick, heightBrick);
                break;
        }
        gc.setStroke(Color.BLACK);
        gc.strokeRect(x, y, widthBrick, heightBrick);

    }

    public int getTypeBrick() {
        return typeBrick;
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

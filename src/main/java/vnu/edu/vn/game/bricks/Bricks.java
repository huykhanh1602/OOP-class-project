package vnu.edu.vn.game.bricks;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vnu.edu.vn.game.GameObject;

/// Like this name

public class Bricks extends GameObject {

    private int typeBrick;
    private boolean broken = false;
    private int amount;
    private double widthBrick = 50;
    private double heightBrick = 30;

    public Bricks(double x, double y, int typeBrick, int amount) {
        super(x, y);
        this.typeBrick = typeBrick;
        this.amount = amount;
    }

    public void update() {
    // Maybe change later
    }

    public void render(GraphicsContext gc) {
        if (typeBrick == 0) {
            return;
        }

        switch (typeBrick) {
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

    public int getAmount() {
        return amount;
    }


    //ERASE BRICK
    public boolean isBroken() {
        return typeBrick <= 0;
    }

    public void hit() {
            typeBrick--;
    }

    public Rectangle2D getRectBrick() {
        return new Rectangle2D(x, y, widthBrick, heightBrick);
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
}

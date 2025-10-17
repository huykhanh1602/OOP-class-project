package vnu.edu.vn.game.bricks;

import javafx.scene.image.Image;
import vnu.edu.vn.game.Constant;

public class StoneBrick extends Bricks {
    public StoneBrick(double x, double y) {
        super(x, y, 1, 0);
        try {
            image = new Image(getClass().getResourceAsStream(Constant.StoneBrickImagePath));
        } catch (Exception e) {
            System.out.println("Cant load stone brick image");
            image = null;
        }
    }
}

package game.bricks;

import game.Constant;
import javafx.scene.image.Image;

public class StoneBrick extends Bricks {
    public StoneBrick(double x, double y) {
        super(x, y, 1, 0);
        try {
            image = new Image(getClass().getResourceAsStream(Constant.STONE_BBRICK_IMAGE_PATH));
        } catch (Exception e) {
            System.out.println("Cant load stone brick image");
            image = null;
        }
    }
}

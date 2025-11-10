package game.bricks;

import game.abstraction.Bricks;
import javafx.scene.paint.Color;
import game.Constant;

public class Chest extends Bricks {
    public Chest(double x, double y) {
        super("CHEST", x, y, Constant.CHEST_DURABILITY, 0, Color.rgb(167, 110, 32));
    }
}

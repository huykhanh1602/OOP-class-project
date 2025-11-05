package game.bricks;

import game.Constant;
import game.abstraction.Bricks;
import javafx.scene.paint.Color;

public class StoneBrick extends Bricks {
    public StoneBrick(double x, double y) {
        super("stone_brick", x, y, Constant.STONE_DURABILITY, 50, Color.GRAY);

    }
}

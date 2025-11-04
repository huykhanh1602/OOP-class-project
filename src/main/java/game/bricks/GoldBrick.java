package game.bricks;

import game.abstraction.Bricks;
import game.Constant;
import javafx.scene.paint.Color;

public class GoldBrick extends Bricks {
    public GoldBrick(double x, double y) {
        super("gold_brick", x, y, Constant.GOLD_DURABILITY, 300, Color.GOLD);
    }

}

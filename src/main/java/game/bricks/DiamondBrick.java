package game.bricks;

import game.abstraction.Bricks;
import game.Constant;
import javafx.scene.paint.Color;

public class DiamondBrick extends Bricks {
    public DiamondBrick(double x, double y) {
        super("DIAMOND_BRICK", x, y, Constant.DIAMOND_DURABILITY, 100, Color.AQUA);
    }

}

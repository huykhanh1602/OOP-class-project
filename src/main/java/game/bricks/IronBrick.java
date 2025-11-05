package game.bricks;

import game.abstraction.Bricks;
import game.Constant;
import javafx.scene.paint.Color;

public class IronBrick extends Bricks {
    public IronBrick(double x, double y) {
        super("IRON_BRICK", x, y, Constant.IRON_DURABILITY, 100, Color.SILVER);
    }

}

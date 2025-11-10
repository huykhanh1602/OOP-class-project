package game.bricks;

import game.abstraction.Bricks;
import game.Constant;
import javafx.scene.paint.Color;

public class NetheriteBrick extends Bricks {
    public NetheriteBrick(double x, double y) {
        super("NETHERITE_BRICK", x, y, Constant.NETHERITE_DURABILITY, 120, Color.rgb(77, 73, 77));
    }

}

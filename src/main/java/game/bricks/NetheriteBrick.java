package game.bricks;

import game.Constant;
import game.abstraction.Bricks;
import javafx.scene.paint.Color;
import game.GameContext;

public class NetheriteBrick extends Bricks {
    public NetheriteBrick(double x, double y) {
        super(
                "NETHERITE_BRICK",
                x,
                y,
                Constant.NETHERITE_DURABILITY,
                120 * GameContext.getInstance().getCurrentDifficulty(),
                Color.rgb(77, 73, 77));
    }

}

package game.bricks;

import game.abstraction.Bricks;
import game.Constant;
import javafx.scene.paint.Color;
import game.GameContext;

public class IronBrick extends Bricks {
    public IronBrick(double x, double y) {
        super("IRON_BRICK", x, y, Constant.IRON_DURABILITY, 100 * GameContext.getInstance().getCurrentDifficulty(),
                Color.SILVER);
    }

}

package game.bricks;

import game.Constant;
import game.abstraction.Bricks;
import javafx.scene.paint.Color;
import game.GameContext;

public class StoneBrick extends Bricks {
    public StoneBrick(double x, double y) {
        super("STONE_BRICK", x, y, Constant.STONE_DURABILITY, 50 * GameContext.getInstance().getCurrentDifficulty(),
                Color.GRAY);

    }
}

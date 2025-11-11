package game.bricks;

import game.abstraction.Bricks;
import game.Constant;
import javafx.scene.paint.Color;
import game.GameContext;

public class GoldBrick extends Bricks {
    public GoldBrick(double x, double y) {
        super("GOLD_BRICK", x, y, Constant.GOLD_DURABILITY, 300 * GameContext.getInstance().getCurrentDifficulty(),
                Color.GOLD);
    }

}

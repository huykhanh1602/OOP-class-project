package game.bricks;

import game.abstraction.Bricks;
import javafx.scene.paint.Color;
import game.Constant;

public class Bedrock extends Bricks {
    public Bedrock(double x, double y) {
        super("BED_ROCK", x, y, Constant.BEDROCK_DURABILITY, 0, Color.BLACK);
    }
}

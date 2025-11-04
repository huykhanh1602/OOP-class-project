package game.bricks;

import game.abstraction.Bricks;
import game.Constant;

public class Bedrock extends Bricks {
    public Bedrock(double x, double y) {
        super("BED_ROCK", x, y, Constant.BEDROCK_DURABILITY, 0, null);
    }

}

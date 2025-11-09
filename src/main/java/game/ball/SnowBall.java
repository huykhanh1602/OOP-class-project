package game.ball;

import game.Constant;

public class SnowBall extends Ball {
    public SnowBall(double x, double y) {
        super(x,y);
        setDamage(3);
        setSpeedball(6);
        setMaxcollision(70);
        setType("snow_ball");
    }
}

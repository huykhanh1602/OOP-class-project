package game.ball;

import game.abstraction.Ball;
import game.powerup.merchant;

public class SnowBall extends Ball {
    public SnowBall(double x, double y) {
        super(x, y);
        setDamage(3 + merchant.ballDamage);
        setSpeedball(6);
        setMaxcollision(70);
        setType("snow_ball");
    }
}

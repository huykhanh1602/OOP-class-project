package game.ball;

import game.abstraction.Ball;
import game.powerup.merchant;

public class EyeOfDragonBall extends Ball {
    public EyeOfDragonBall(double x, double y) {
        super(x, y);
        setDamage(10 + merchant.ballDamage);
        setSpeedball(5);
        setMaxcollision(50);
        setType("eod_ball");
    }
}

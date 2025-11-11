package game.ball;

import game.abstraction.Ball;
import game.powerup.merchant;

public class NormalBall extends Ball {
    public NormalBall(double x, double y) {
        super(x, y);
        setDamage(5 + merchant.ballDamage);
        setMaxcollision(50);
    }
}

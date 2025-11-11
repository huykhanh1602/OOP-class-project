package game.ball;

import game.abstraction.Ball;
import game.powerup.merchant;

public class SlimeBall extends Ball {
    public SlimeBall(double x, double y) {
        super(x, y);
        setDamage(3 + merchant.ballDamage);
        setSpeedball(6);
        setMaxcollision(70);
        setType("slime_ball");
    }
}

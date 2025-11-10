package game.ball;

import game.abstraction.Ball;

public class NormalBall extends Ball {
    public NormalBall(double x, double y) {
        super(x,y);
        setDamage(5);
        setMaxcollision(50);
    }
}

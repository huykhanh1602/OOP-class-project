package game.ball;

import game.abstraction.Ball;

public class EyeOfDragonBall extends Ball {
    public EyeOfDragonBall(double x, double y) {
        super(x, y);
        setDamage(10);
        setSpeedball(5);
        setMaxcollision(50);
        setType("eod_ball");
    } 
}

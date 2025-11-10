package game.ball;

import game.abstraction.Ball;

public class FireBall extends Ball {
    public FireBall(double x, double y) {
        super(x, y);
        setDamage(10);
        setSpeedball(5);
        setMaxcollision(50);
        setType("fire_ball");
    } 
}

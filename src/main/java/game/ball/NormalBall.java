package game.ball;

public class NormalBall extends Ball {
    public NormalBall(double x, double y) {
        super(x,y);
        setDamege(5);
        setMaxcollision(50);
    }
}

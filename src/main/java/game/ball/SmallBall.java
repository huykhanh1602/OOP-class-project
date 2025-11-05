package game.ball;

public class SmallBall extends Ball {
    public SmallBall(double x, double y) {
        super(x,y);
        setDamege(2);
        setRadius(getRadius()/2);
    }
}

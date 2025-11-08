package game.ball;

public class SlimeBall extends Ball {
    public SlimeBall(double x, double y) {
        super(x,y);
        setDamage(3);
        setSpeedball(6);
        setMaxcollision(70);
    }
}

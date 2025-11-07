package game.ball;

public class SlimeBall extends Ball {
    public SlimeBall(double x, double y) {
        super(x,y);
        setDamage(3);
        setSpeedball(8);
        setMaxcollision(70);
    }
}

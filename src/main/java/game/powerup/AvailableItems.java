package game.powerup;

import game.ball.*;

import java.util.ArrayList;
import java.util.List;

public class AvailableItems {
    private static final List<ItemsForBall> availableItems = new ArrayList<>();
    static {
        availableItems.add(new ItemsAbsorbentBallLEVER1());
        availableItems.add(new ItemsAbsorbentBallLEVER2());
        availableItems.add(new ItemsAbsorbentBallLEVER3());
        availableItems.add(new ItemsAbsorbentBallLEVER4());
        availableItems.add(new ItemsAbsorbentBallLEVER5());
        availableItems.add(new ItemsCloneBallLEVER1());
        availableItems.add(new ItemsCloneBallLEVER2());
        availableItems.add(new ItemsCloneBallLEVER3());
        availableItems.add(new ItemsCloneBallLEVER4());
        availableItems.add(new ItemsCloneBallLEVER5());
        availableItems.add(new ItemsExplosiveBallLEVER1());
        availableItems.add(new ItemsExplosiveBallLEVER2());
        availableItems.add(new ItemsExplosiveBallLEVER3());
        availableItems.add(new ItemsExplosiveBallLEVER4());
        availableItems.add(new ItemsExplosiveBallLEVER5());
    }
    public static List<ItemsForBall> getAvailableItems() {
        return availableItems;
    }
}

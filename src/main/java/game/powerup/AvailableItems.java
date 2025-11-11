package game.powerup;

import game.items.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvailableItems {
    private static final List<ItemsForBall> ITEM_POOL = new ArrayList<>();
    private static final Random random = new Random();
    static {
        ITEM_POOL.add(new ItemsAbsorbentBallLEVER1());
        ITEM_POOL.add(new ItemsAbsorbentBallLEVER3());
        ITEM_POOL.add(new ItemsCloneBallLEVER1());
        ITEM_POOL.add(new ItemsCloneBallLEVER2());
        ITEM_POOL.add(new ItemsCloneBallLEVER4());
        ITEM_POOL.add(new ItemsExplosiveBallLEVER1());
        ITEM_POOL.add(new ItemsExplosiveBallLEVER2());
    }
    public static ItemsForBall getRandomItem() {
        ItemsForBall potentialItem = ITEM_POOL.get(random.nextInt(ITEM_POOL.size()));
        double chance = potentialItem.getPercent();
        if (random.nextDouble() * 100 < chance) {
            return potentialItem;
        }
        return null;
    }
}

package game.powerup;

import game.manager.CoinManager;

public class merchant {

    public static merchant INSTANCE = new merchant();

    private merchant() {
    }

    public static merchant getInstance() {
        return INSTANCE;
    }

    public static int ballSpeed = 0;
    public static int ballDamage = 0;
    public static int paddleSize = 1;

    public enum price {
        axe(30),
        lightning(10),
        blaze(10);

        private int cost;

        price(int cost) {
            this.cost = cost;
        }

        public int getCost() {
            return cost;
        }

        public void increaseCost() {
            this.cost = cost + (cost * 10 / 100) * 5;
        }
    }

    public enum items {
        axe,
        lightning,
        blaze
    }

    public static boolean buy(items item) {
        int cost = 0;
        cost = getPrice(item);
        CoinManager coinManager = CoinManager.getInstance();
        if (coinManager.getCoin() >= cost) {
            coinManager.deductCoins(cost);
            increaseCost(item);
            return true; // Purchase successful
        } else {
            System.out.println("Not enough coins to buy " + item);
            return false; // Not enough coins
        }

    }

    public static int getPrice(items item) {
        switch (item) {
            case axe:
                return price.axe.getCost();
            case lightning:
                return price.lightning.getCost();
            case blaze:
                return price.blaze.getCost();
            default:
                return 0;
        }
    }

    public static void increaseCost(items item) {
        switch (item) {
            case axe:
                price.axe.increaseCost();
                break;
            case lightning:
                price.lightning.increaseCost();
                break;
            case blaze:
                price.blaze.increaseCost();
                break;
        }
    }
}

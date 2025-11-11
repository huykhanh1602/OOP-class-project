package game.manager;

public class CoinManager {
    private static int coin = 0;

    private static final CoinManager instance = new CoinManager();

    public static CoinManager getInstance() {
        return instance;
    }

    public void addCoin(int amount) {
        coin += amount;
    }

    public void resetCoin() {
        coin = 100;
    }

    public int getCoin() {
        return coin;
    }

    public void deductCoins(int amount) {
        if (amount <= coin) {
            coin -= amount;
        } else {
            System.out.println("Not enough coins!");
        }
    }

}

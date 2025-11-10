package game.coin;

public class CoinManager {
    private static int coin = 0;

    private static final CoinManager instance = new CoinManager();

    public static CoinManager getInstance() {
        return instance;
    }

    public static void addCoin(int amount) {
        coin += amount;
    }

    public static void resetCoin() {
        coin = 0;
    }

    public int getCoin() {
        return coin;
    }

}

package game.powerup;

public class Coin {
    private static int currentCoin  = 0;
    public static int getCoin() {
        return currentCoin;
    }
    public static void setCoin(int coin) {
        currentCoin = coin;
    }
    public static void addCoin(int amount) {
        currentCoin += amount;
    }
}

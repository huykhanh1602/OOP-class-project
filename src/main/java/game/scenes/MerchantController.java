package game.scenes;

import static game.Constant.BUY_SOUND;

import java.net.URL;
import java.util.ResourceBundle;

import game.AssetManager;
import game.abstraction.GameScene;
import game.powerup.merchant;
import game.manager.CoinManager;
import game.manager.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MerchantController extends GameScene {
    @FXML
    private ImageView itemImage1;

    @FXML
    private ImageView itemImage2;

    @FXML
    private ImageView itemImage3;

    @FXML
    private Label coinLabel;

    @FXML
    private Label itemPrice1;

    @FXML
    private Label itemPrice2;

    @FXML
    private Label itemPrice3;

    @FXML
    private Label messageLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        super.initialize(url, rb);

        AssetManager.playSound("buy");
        messageLabel.setText("I have some powerful items for you!");
        update();
    }

    public void update() {
        int coin = CoinManager.getInstance().getCoin();
        coinLabel.setText("Coins: " + coin);

        itemPrice1.setText(merchant.getPrice(merchant.items.axe) + "$");
        itemPrice2.setText(merchant.getPrice(merchant.items.lightning) + "$");
        itemPrice3.setText(merchant.getPrice(merchant.items.blaze) + "$");

    }

    @FXML
    protected void nextLevel(ActionEvent e) {
        AssetManager.playSound("click");
        if (app != null) {
            app.nextLevel();
        } else {
            System.out.println("Error: App reference is null");
        }
    }

    @FXML
    protected void buyItem1(ActionEvent e) {
        if (merchant.getInstance().buy(merchant.items.axe)) {
            System.out.println("Bought item 1");
            messageLabel.setText("A wise choice!");
            AssetManager.playSound("buy");
            merchant.paddleSize += 1;

        } else {
            System.out.println("Failed to buy item 1");
            messageLabel.setText("Not enough coins to buy that!");
            AssetManager.playSound("cantbuy");
        }
        update();
        // Implement buying logic for item 1
    }

    @FXML
    protected void buyItem2(ActionEvent e) {
        if (merchant.getInstance().buy(merchant.items.lightning)) {
            System.out.println("Bought item 2");
            messageLabel.setText("I'm rich!");
            AssetManager.playSound("buy");
            merchant.ballSpeed += 0.5;
        } else {
            System.out.println("Failed to buy item 2");
            messageLabel.setText("Nothing is free!");
            AssetManager.playSound("cantbuy");
        }
        update();
        // Implement buying logic for item 2
    }

    @FXML
    protected void buyItem3(ActionEvent e) {
        if (merchant.getInstance().buy(merchant.items.blaze)) {
            System.out.println("Bought item 3");
            messageLabel.setText("Thank you for your purchase!");
            AssetManager.playSound("buy");
            merchant.ballDamage += 5;
        } else {
            System.out.println("Failed to buy item 3");
            messageLabel.setText("Get your ass out of here!");
            AssetManager.playSound("cantbuy");
        }
        update();
        // Implement buying logic for item 3
    }

}

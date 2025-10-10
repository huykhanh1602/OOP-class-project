module vnu.edu.vn {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens vnu.edu.vn.game.gameOver to javafx.fxml;
    opens vnu.edu.vn to javafx.fxml;
    exports vnu.edu.vn.game.gameOver;
    exports vnu.edu.vn;
}

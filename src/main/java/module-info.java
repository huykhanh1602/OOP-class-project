module vnu.edu.vn {
    requires javafx.media;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.compiler;
    requires java.sql;

    opens vnu.edu.vn.game.scenes to javafx.fxml;
    opens vnu.edu.vn to javafx.fxml;

    exports vnu.edu.vn;
}

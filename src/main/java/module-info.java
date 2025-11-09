module vnu.edu.vn {
    requires javafx.media;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.compiler;
    requires java.sql;
    requires javafx.graphics;
    requires javafx.base;

    opens game.scenes to javafx.fxml;
    opens game to javafx.fxml;
    opens game.abstraction to javafx.fxml;

    exports game;
}

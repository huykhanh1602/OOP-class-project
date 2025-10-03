module vnu.edu.vn {
    requires javafx.controls;
    requires javafx.fxml;

    opens vnu.edu.vn to javafx.fxml;
    exports vnu.edu.vn;
}

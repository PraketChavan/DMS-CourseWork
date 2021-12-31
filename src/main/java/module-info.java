module com.example.breakout_clone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;
    requires javafx.media;


    exports com.example.migrateFx;
    exports com.example.migrateFx.view;
    opens com.example.migrateFx to javafx.fxml;
    exports com.example.migrateFx.controller.sprite;
    opens com.example.migrateFx.controller.sprite to javafx.fxml;
    exports com.example.migrateFx.controller.game;
    opens com.example.migrateFx.controller.game to javafx.fxml;
    exports com.example.migrateFx.model.sprite;
    exports com.example.migrateFx.model.game;
    exports com.example.migrateFx.util;
    opens com.example.migrateFx.util to javafx.fxml;
}
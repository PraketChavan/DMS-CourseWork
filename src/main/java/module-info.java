module com.example.breakout_clone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;
    requires javafx.media;


    exports com.example.breakout_clone;
    exports com.example.breakout_clone.view;
    opens com.example.breakout_clone to javafx.fxml;
    exports com.example.breakout_clone.controller.sprite;
    opens com.example.breakout_clone.controller.sprite to javafx.fxml;
    exports com.example.breakout_clone.controller.game;
    opens com.example.breakout_clone.controller.game to javafx.fxml;
    exports com.example.breakout_clone.model.sprite;
    exports com.example.breakout_clone.model.game;
    exports com.example.breakout_clone.util;
    opens com.example.breakout_clone.util to javafx.fxml;
}
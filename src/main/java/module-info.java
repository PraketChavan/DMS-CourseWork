module com.example.breakout_clone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;
    requires javafx.media;


    opens com.example.breakout_clone to javafx.fxml;
    exports com.example.breakout_clone;
    exports com.example.migrateFx;
    exports com.example.migrateFx.controller;
    exports com.example.migrateFx.model;
    exports com.example.migrateFx.view;
    opens com.example.migrateFx.controller to  javafx.fxml;
    opens com.example.migrateFx to javafx.fxml;
}
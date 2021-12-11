module com.example.breakout_clone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires javafx.swing;


    opens com.example.breakout_clone to javafx.fxml;
    exports com.example.breakout_clone;
    exports com.example.breakout_clone_javafx;
    exports com.example.util;
    exports com.example.migrateFx;
    opens com.example.breakout_clone_javafx to javafx.fxml;
    opens com.example.util to javafx.fxml;

}
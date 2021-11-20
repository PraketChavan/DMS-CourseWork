module com.example.breakout_clone {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.breakout_clone to javafx.fxml;
    exports com.example.breakout_clone;
}
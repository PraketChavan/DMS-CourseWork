package com.example.breakout_clone_javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScreen extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                StartScreen.class.getResource("StartGameScreen-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Breakout Game!");
        stage.setScene(scene);
        stage.show();
    }
}
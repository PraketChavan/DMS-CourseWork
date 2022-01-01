package com.example.migrateFx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartGame extends Application {

    private static final int SCREEN_WIDTH = 740;
    private static final int SCREEN_HEIGHT = 500;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("StartScreenView.fxml"));
        Scene root = new Scene(fxmlLoader.load(), SCREEN_WIDTH,
                               SCREEN_HEIGHT);
        stage.setTitle("BREAKOUT GAME!!");
        stage.setScene(root);
        stage.setResizable(false);
        root.getRoot().requestFocus();
        stage.show();
    }
    // use [space] to start/pause the game
    // use [←] to move the player left
    // use [→] to move the player right
    // use [esc] to enter/exit pause menu
    // use [alt+shift+f1] at any time to display debug panel
}

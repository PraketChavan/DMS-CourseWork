package com.example.breakout_clone;

import com.example.breakout_clone.util.ResourceHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The main class that is used to start the game and application
 * @author Praket Chavan - modified
 */
public class StartGame extends Application {

    /**
     * Defines the width of the screen
     */
    private static final int SCREEN_WIDTH = 740;

    /**
     * Defines the height of the screen
     */
    private static final int SCREEN_HEIGHT = 500;
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     *
     * Method is used to load the start screen fxml file and start the game.
     * It sets the theme of the game to blue by default which the player can
     * change later.
     * @param stage the stage on which the scene will be set to
     * @throws Exception when the FXMLLoader fails to load the fxml file
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/view/StartScreenView.fxml"));
        Scene root = new Scene(fxmlLoader.load(), SCREEN_WIDTH,
                               SCREEN_HEIGHT);
       root.getStylesheets()
                  .add(ResourceHandler.THEME_CSS_PATH);
        root.getRoot().getStyleClass().add("Blue");
        stage.setTitle("BREAKOUT GAME!!");
        stage.setScene(root);
        stage.setResizable(false);
        root.getRoot().requestFocus();
        stage.show();
    }
}

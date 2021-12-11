package com.example.migrateFx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;

public class StartGame extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GameFrame frame = new GameFrame();
        Scene scene = new Scene(frame.getLayout(), 600, 450);
        stage.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(
                    ObservableValue<? extends Boolean> observableValue,
                    Boolean aBoolean, Boolean t1) {
                if (aBoolean)
                    frame.windowGainedFocus(null);
                if (t1)
                    frame.windowLostFocus(null);
            }
        });
        stage.setTitle((GameFrame.getDefTitle()));
        stage.setScene(scene);
        stage.show();
    }
    // use [space] to start/pause the game
    // use [←] to move the player left
    // use [→] to move the player right
    // use [esc] to enter/exit pause menu
    // use [alt+shift+f1] at any time to display debug panel
}

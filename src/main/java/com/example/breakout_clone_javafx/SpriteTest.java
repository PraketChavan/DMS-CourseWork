package com.example.breakout_clone_javafx;

import com.example.util.Sprite;
import com.example.util.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class SpriteTest {
    @FXML
    private Pane playfield;

    @FXML
    private void initialize() {
        Sprite ball = new RubberBall(new Vector2D(playfield.getWidth(), playfield.getHeight()));
        ball.initialize();
        Pane layerpane = new Pane();
        playfield.getChildren().addAll(layerpane);
        layerpane.getChildren().add(ball);
        playfield.setOnMouseClicked(e->{
            System.out.println("hello");
            ball.getTransforms().add(new Translate(20, 20));
        });
//        Scene scene = playfield.getScene();
//        scene.setOnMouseClicked(e->{
//           ball.display();
//        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ball.move();
            }
        };
       timer.start();
    }
}

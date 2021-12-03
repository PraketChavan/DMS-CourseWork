package com.example.breakout_clone_javafx;

import com.example.util.Sprite;
import com.example.util.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
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
    Sprite ball, brick;

    @FXML
    private void initialize() {

    }


    @FXML
    private void run() {
        ball = new RubberBall(new Vector2D(playfield.getWidth()/4, playfield.getHeight()/4));
        ball.initialize();
        brick = new ClayBrick(new Vector2D(playfield.getWidth()/2, playfield.getHeight()/2));
        brick.initialize();
        playfield.getChildren().add(ball);
        playfield.getChildren().add(brick);
//        playfield.setOnMouseClicked(e->{
//            System.out.println("hello");
//            ball.getTransforms().add(new Translate(20, 20));
//        });
////        Scene scene = playfield.getScene();
//        scene.setOnMouseClicked(e->{
//           ball.display();
//        });
        Task<Object> task = new Task<>() {
            @Override
            protected Object call(){
                timer();
                return null;
            }
        };
        task.run();
    }

    private void timer(){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                ball.move() ;
                brick.move();
            }
        };
        timer.start();
    }
}

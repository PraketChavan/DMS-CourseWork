package com.example.breakout_clone_javafx;

import com.example.util.Sprite;
import com.example.util.Vector2D;
import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class SpriteTest {
    @FXML
    private Pane playfield;
    ArrayList<Sprite> balls = new ArrayList<>();
    ArrayList<Sprite> bricks = new ArrayList<>();

    @FXML
    private void initialize() {

    }


    @FXML
    private void run() {
        Sprite ball = new RubberBall(new Vector2D(playfield.getWidth()/2, playfield.getHeight()/2));
        ball.initialize();
        Sprite brick = new ClayBrick(new Vector2D(playfield.getWidth()/2, playfield.getHeight()/2));
        brick.initialize();
        balls.add(ball);
        bricks.add( brick);
        playfield.getChildren().add(ball);
        playfield.getChildren().add(brick);
        for (Sprite bric : bricks) {
            bric.display();
        }
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
                timer(new ImpactHandler(balls, bricks, null));
                return null;
            }
        };
        task.run();
    }

    private void timer(ImpactHandler handler){
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (Sprite ball : balls) {
                    ball.move();
                }
                handler.handleImpacts();
            }
        };
        timer.start();
    }
}

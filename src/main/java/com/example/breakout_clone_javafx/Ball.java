package com.example.breakout_clone_javafx;

import com.example.util.CircleSprite;
import com.example.util.Vector2D;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class Ball extends CircleSprite {
    private static final SimpleDoubleProperty HEIGHT = new SimpleDoubleProperty(25);
    private static final SimpleDoubleProperty WIDTH = new SimpleDoubleProperty(25);
    private static final SimpleDoubleProperty DEF_RADIUS =
            new SimpleDoubleProperty(12.5);
    private static final SimpleObjectProperty<Vector2D> DEF_BALL_SPEED =
            new SimpleObjectProperty<>(new Vector2D(1, 1));


    public Ball(SimpleObjectProperty<Vector2D> position) {
        super(DEF_BALL_SPEED, position, DEF_RADIUS, HEIGHT, WIDTH);
    }

    @Override
    public void move() {
        getPosition().addVector(getSpeed());
        display();
    }

    @Override
    public void display() {
       relocate(getPosition().getX(), getPosition().getY());
       setRotate(0);
    }
}

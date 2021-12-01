package com.example.breakout_clone_javafx;

import com.example.util.CircleSprite;
import com.example.util.Vector2D;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class Ball extends CircleSprite implements Impactable{
    protected static final SimpleDoubleProperty HEIGHT = new SimpleDoubleProperty(25);
    protected static final SimpleDoubleProperty WIDTH = new SimpleDoubleProperty(25);
    protected static final SimpleDoubleProperty DEF_RADIUS =
            new SimpleDoubleProperty(12.5);
    protected static final SimpleObjectProperty<Vector2D> DEF_BALL_SPEED =
            new SimpleObjectProperty<>(new Vector2D(1, 1));


    public Ball(Pane pane, Vector2D position) {
        super(pane, DEF_BALL_SPEED.get(), position, DEF_RADIUS.get(), HEIGHT.get(), WIDTH.get());
    }

    private static void setDefRadius(int defRadius) {
        DEF_RADIUS.set(defRadius);
    }

    private static void setDefBallSpeed(
            Vector2D defBallSpeed) {
        DEF_BALL_SPEED.set(defBallSpeed);
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

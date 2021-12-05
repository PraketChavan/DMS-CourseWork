package com.example.breakout_clone_javafx;

import com.example.util.CircleSprite;
import com.example.util.Vector2D;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class Ball extends CircleSprite {
    private static final SimpleDoubleProperty HEIGHT = new SimpleDoubleProperty(
            25);
    private static final SimpleDoubleProperty WIDTH = new SimpleDoubleProperty(
            25);
    private static final SimpleDoubleProperty DEF_RADIUS =
            new SimpleDoubleProperty(12.5);
    private static final double DEF_INITIAL_SPEED_X = 1;
    private static final double DEF_INITIAL_SPEED_Y = 1;


    public Ball(SimpleObjectProperty<Vector2D> position) {
        super(new SimpleObjectProperty<>(
                      new Vector2D(DEF_INITIAL_SPEED_X, DEF_INITIAL_SPEED_Y)),
              position, DEF_RADIUS, HEIGHT, WIDTH);
    }


    @Override
    public void move() {
        onImpact(findImpact(null));
        getPosition().addVector(getSpeed());
        display();
    }

    @Override
    public void display() {
        relocate(getPosition().getX() - this.getRadius(),
                 getPosition().getY() - this.getRadius());
        setRotate(0);
    }
}

package com.example.breakout_clone_javafx;

import com.example.util.RectSprite;
import com.example.util.Vector2D;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;



public abstract class Brick extends RectSprite {
    private static final SimpleDoubleProperty HEIGHT = new SimpleDoubleProperty(25);
    private static final SimpleDoubleProperty WIDTH = new SimpleDoubleProperty(25);
    private static final SimpleObjectProperty<Vector2D> DEF_BRICK_SPEED =
            new SimpleObjectProperty<>(new Vector2D(0, 0));
    private static final SimpleIntegerProperty STRENGTH = new SimpleIntegerProperty(1);
    private static final SimpleBooleanProperty BROKEN = new SimpleBooleanProperty(false);

    public Brick(SimpleObjectProperty<Vector2D> position) {
        super(DEF_BRICK_SPEED, position, HEIGHT, WIDTH);
    }


    @Override
    public void move() {
        display();
    }

    @Override
    public void display() {
        relocate(getPosition().getX(), getPosition().getY());
        setRotate(0);
    }
}

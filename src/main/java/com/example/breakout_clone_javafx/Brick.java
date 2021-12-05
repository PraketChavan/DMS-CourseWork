package com.example.breakout_clone_javafx;

import com.example.util.RectSprite;
import com.example.util.Vector2D;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.List;


public abstract class Brick extends RectSprite {
    private static final SimpleDoubleProperty HEIGHT = new SimpleDoubleProperty(
            25);
    private static final SimpleDoubleProperty WIDTH = new SimpleDoubleProperty(
            25);
    private static final SimpleObjectProperty<Vector2D> DEF_BRICK_SPEED =
            new SimpleObjectProperty<>(new Vector2D(0, 0));
    private static final SimpleIntegerProperty STRENGTH = new SimpleIntegerProperty(
            1);
    private static final SimpleBooleanProperty BROKEN = new SimpleBooleanProperty(
            false);
    private static final List<Ball> m_Balls = new LinkedList<>();

    public static List<Ball> getBalls() {
        return m_Balls;
    }

    public Brick(SimpleObjectProperty<Vector2D> position) {
        super(DEF_BRICK_SPEED, position, HEIGHT, WIDTH);
    }

    public static void addBall(Ball ball) {
        getBalls().add(ball);
    }

    @Override
    public void move() {
        display();
    }

    @Override
    public void display() {
        relocate(getPosition().getX(), getPosition().getY());
        setRotate(0);
        this.setBackground(new Background(new BackgroundFill(
                Color.valueOf("ff00ff"), null, null)));

    }
}

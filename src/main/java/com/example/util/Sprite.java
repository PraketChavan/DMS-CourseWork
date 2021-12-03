package com.example.util;

import com.example.breakout_clone_javafx.Impactable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region implements Movable, Impactable {

    private final SimpleObjectProperty<Vector2D> SPEED;
    private final SimpleObjectProperty<Vector2D> POSITION;
    private final SimpleDoubleProperty RADIUS;
    private final SimpleDoubleProperty WIDTH;
    private final SimpleDoubleProperty HEIGHT;

    private Node m_View;

    public Vector2D getPosition() {
        return POSITION.get();
    }

    public void setPosition(Vector2D position) {
        this.POSITION.set(position);
    }

    public double getRadius() {
        return RADIUS.get();
    }

    public void setRadius(double radius) {
        this.RADIUS.set(radius);
    }

    public Vector2D getSpeed() {
        return SPEED.get();
    }

    public void setSpeed(Vector2D speed) {
        this.SPEED.set(speed);
    }

    public Node getView() {
        return m_View;
    }

    public void setView(Node view) {
        m_View = view;
    }

    public void setmWidth(double width) {
        this.WIDTH.set(width);
    }

    public double getmWidth() {
        return WIDTH.get();
    }

    public void setmHeight(double height) {
        this.HEIGHT.set(height);
    }

    public double getmHeight() {
        return HEIGHT.get();
    }

    public Sprite(SimpleObjectProperty<Vector2D> speed,
                  SimpleObjectProperty<Vector2D> position,
                  SimpleDoubleProperty radius,
                  SimpleDoubleProperty height,
                  SimpleDoubleProperty width) {

        SPEED = speed;
        POSITION = position;
        RADIUS = radius;

        HEIGHT = height;
        WIDTH = width;

    }

    public void initialize() {
        setView(createView());
        setPrefSize(getmWidth(),getmHeight());
        getChildren().add(getView());
    }

    public SimpleObjectProperty<Vector2D> speedProperty() {
        return SPEED;
    }

    public SimpleObjectProperty<Vector2D> positionProperty() {
        return POSITION;
    }

    public abstract Node createView();

    public abstract void display();

    public SimpleDoubleProperty radiusProperty() {
        return RADIUS;
    }
}

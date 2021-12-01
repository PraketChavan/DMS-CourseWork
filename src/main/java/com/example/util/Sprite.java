package com.example.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;

public abstract class Sprite extends Region {

    private final SimpleObjectProperty<Vector2D> SPEED = new SimpleObjectProperty<>();
    private final SimpleObjectProperty<Vector2D> POSITION = new SimpleObjectProperty<>();
    private final SimpleDoubleProperty RADIUS = new SimpleDoubleProperty();
    private final SimpleDoubleProperty m_Width = new SimpleDoubleProperty();
    private final SimpleDoubleProperty m_Height = new SimpleDoubleProperty();

    protected Node m_View;

    public Pane getPane() {
        return m_Pane;
    }

    public void setPane(Pane pane) {
        m_Pane = pane;
    }

    private Pane m_Pane = null;

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
        this.m_Width.set(width);
    }

    public double getmWidth() {
        return m_Width.get();
    }

    public void setmHeight(double height) {
        this.m_Height.set(height);
    }

    public double getmHeight() {
        return m_Height.get();
    }

    public Sprite(Pane pane, Vector2D speed, Vector2D position, double height, double width) {
        setSpeed(speed);
        setPosition(position);
        setmHeight(height);
        setmWidth(width);
        setPane(pane);
    }

    public void initialize() {
        m_View = createView();
        setPrefSize(getmWidth(),getmHeight());
        getChildren().add(getView());
       // getPane().getChildren().add(this);
    }

    public SimpleObjectProperty<Vector2D> speedProperty() {
        return SPEED;
    }

    public SimpleObjectProperty<Vector2D> positionProperty() {
        return POSITION;
    }

    public abstract Node createView();

    public abstract void move();

    public abstract void display();

    public SimpleDoubleProperty radiusProperty() {
        return RADIUS;
    }
}

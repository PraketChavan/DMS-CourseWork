package com.example.breakout_clone_javafx;

import com.example.util.Vector2D;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class RubberBall extends Ball{

    public RubberBall(Vector2D position) {
        super(new SimpleObjectProperty<>(position));
    }

    @Override
    public void onImpact(int side) {
        switch (side) {
            case UP, DOWN -> getSpeed().invertY();
            case RIGHT, LEFT -> getSpeed().invertX();
        }
    }

    @Override
    public Node createView() {
        Circle circle = new Circle();
        circle.setRadius(getRadius());
        circle.setCenterX(getPosition().getX());
        circle.setCenterY(getPosition().getY());
        circle.setStroke(Color.GREEN);
        circle.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.3));
        return circle;
    }
}

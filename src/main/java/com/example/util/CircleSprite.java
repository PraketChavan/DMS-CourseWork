package com.example.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;

public abstract class CircleSprite extends Sprite {

    public CircleSprite(Pane pane, Vector2D speed, Vector2D position, double radius, double height, double width) {
        super(pane, speed, position, height, width);
        this.setRadius(radius);
    }

}

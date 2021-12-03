package com.example.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;


public abstract class RectSprite extends Sprite {

    public RectSprite(
            SimpleObjectProperty<Vector2D> speed,
            SimpleObjectProperty<Vector2D> position,
            SimpleDoubleProperty height,
            SimpleDoubleProperty width) {
        super(speed, position, new SimpleDoubleProperty(0), height, width);
    }

}

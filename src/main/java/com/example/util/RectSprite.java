package com.example.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;


public abstract class RectSprite extends Sprite {

    public RectSprite(
            SimpleObjectProperty<Vector2D> speed,
            SimpleObjectProperty<Vector2D> position,
            SimpleDoubleProperty radius,
            SimpleDoubleProperty height,
            SimpleDoubleProperty width) {
        super(speed, position, radius, height, width);
    }

//    private RectSprite(Vector2D speed, Vector2D position,double height, double width) {
//        super(speed, position, height, width);
//        setRadius(0);
//    }

}

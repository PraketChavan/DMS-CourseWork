package com.example.util;

import javafx.beans.property.SimpleObjectProperty;

public abstract class RectSprite extends Sprite {

    private RectSprite(Vector2D speed, Vector2D position,double height, double width) {
        super(null, speed, position, height, width);
        setRadius(0);
    }

}

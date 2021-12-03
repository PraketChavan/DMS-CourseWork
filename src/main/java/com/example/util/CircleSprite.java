package com.example.util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.layout.Pane;

public abstract class CircleSprite extends Sprite {

    public CircleSprite(SimpleObjectProperty<Vector2D> speed,
                        SimpleObjectProperty<Vector2D> position,
                        SimpleDoubleProperty radius,
                        SimpleDoubleProperty height,
                        SimpleDoubleProperty width) {
        super(speed, position, radius, height, width);

    }

}

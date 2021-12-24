package com.example.migrateFx.model;

import javafx.geometry.Point2D;

public class PaddleModel extends SpriteModel{

    public PaddleModel(Point2D location) {
        super(location);
        setSpeed(new Point2D(0, 0));
    }
}

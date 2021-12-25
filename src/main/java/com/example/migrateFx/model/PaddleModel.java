package com.example.migrateFx.model;

import com.example.migrateFx.Ball;
import com.example.migrateFx.Impactable;
import javafx.geometry.Point2D;

public class PaddleModel extends SpriteModel implements Impactable {

    public PaddleModel(Point2D location) {
        super(location);
        setSpeed(new Point2D(0, 0));
    }

    @Override
    public int findImpact(Impactable parent) {
        if (getBounds().contains(((BallModel)parent).getCenter()) && getBounds().contains(((BallModel)parent).bottomProperty().get()))
            parent.onImpact(UP);
        return UP;
    }

    @Override
    public void onImpact(int side) {

    }
}

package com.example.migrateFx.wrappers.brick;

import javafx.geometry.Point2D;

public class UnbreakableBrick extends Brick {
    public UnbreakableBrick(String url, Point2D location) {
        super(url, location, false);
        getModel().setFullStrength(Integer.MAX_VALUE);
        getModel().setStrength(Integer.MAX_VALUE);
        getModel().setName("Unbreakable");
    }
}

package com.example.migrateFx.wrappers.brick;

import javafx.geometry.Point2D;

public class SteelBrick extends Brick {
    public SteelBrick(String url, Point2D location) {
        super(url, location, false, "Steel");
        getModel().setFullStrength(2);
        getModel().setStrength(2);
    }
}

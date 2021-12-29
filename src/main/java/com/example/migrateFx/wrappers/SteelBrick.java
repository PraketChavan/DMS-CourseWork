package com.example.migrateFx.wrappers;

import javafx.geometry.Point2D;

public class SteelBrick extends Brick{
    public SteelBrick(String url, Point2D location) {
        super(url, location, false);
        getModel().setFullStrength(2);
        getModel().setStrength(2);
    }
}

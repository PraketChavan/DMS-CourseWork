package com.example.migrateFx.wrappers.brick;

import javafx.geometry.Point2D;

public class ClayBrick extends Brick {

    public ClayBrick(String url, Point2D location, boolean isSpec) {
        super(url, location, isSpec);
        getModel().setName("Clay");
    }
}

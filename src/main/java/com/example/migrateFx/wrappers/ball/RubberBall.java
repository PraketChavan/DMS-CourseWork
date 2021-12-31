package com.example.migrateFx.wrappers.ball;

import javafx.geometry.Point2D;


public class RubberBall extends Ball {
    private static final double BALL_SIZE = 15;

    public RubberBall(String url, Point2D location) {
        super(url, location, BALL_SIZE);
    }

}

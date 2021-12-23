package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.view.BallView;
import javafx.geometry.Point2D;


public class RubberBall extends Ball{
    private static final double BALL_SIZE = 20;

    public RubberBall(String url, Point2D location) {
        super(url, location, BALL_SIZE);
    }

}

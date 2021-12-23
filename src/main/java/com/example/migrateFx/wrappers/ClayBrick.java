package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.controller.BrickController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.view.BallView;
import com.example.migrateFx.view.BrickView;
import javafx.geometry.Point2D;

import javax.swing.text.View;

public class ClayBrick extends Brick{

    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 12;

    public ClayBrick(String url, Point2D location) {
        super(url, location, BRICK_WIDTH, BRICK_HEIGHT);
    }
}

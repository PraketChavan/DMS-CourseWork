package com.example.migrateFx.controller;

import com.example.migrateFx.*;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import com.example.migrateFx.Movable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class BallController extends SpriteController {

    public BallController(SpriteModel model, SpriteView view) {
        super(model, view);
    }

    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        ((BallModel)getModel()).setRadius(getView().getView().getImage().getHeight()/2);
        ((BallModel) getModel()).initializeCenter();
    }

    public void move() {
        ((BallModel)getModel()).move();
    }

    public int findImpact(Impactable parent) {
        return ((BallModel)getModel()).findImpact(parent);
    }
    public void setXSpeed(double xSpeed) {
        getModel().setSpeed(new Point2D(xSpeed, getModel().getSpeed().getY()));
    }
    public void setYSpeed(double ySpeed) {
        getModel().setSpeed(new Point2D(getModel().getSpeed().getX(), ySpeed));
    }
    public void setSpeed(Point2D speed) {
        getModel().setSpeed(speed);
    }
    public void reset() {
        getModel().reset();
    }

    public void onImpact(int side) {
        ((BallModel)getModel()).onImpact(side);
    }
}

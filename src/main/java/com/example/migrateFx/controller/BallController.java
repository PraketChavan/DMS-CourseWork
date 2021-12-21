package com.example.migrateFx.controller;

import com.example.migrateFx.*;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import com.example.util.Movable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.layout.Pane;

public class BallController extends SpriteController implements Movable, Impactable {

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

    @Override
    public void move() {
        getModel().setLocation(getModel().getLocation().add(getModel().getSpeed()));
        ((BallModel)getModel()).centerProperty().set(((BallModel)getModel()).centerProperty().get().add(getModel().getSpeed()));
        ((BallModel) getModel()).updateLocations();
    }

    @Override
    public void findImpact(Pane parent) {
        Bounds bound = parent.getBoundsInParent();
        BallModel model = (BallModel) getModel();
        if (model.bottomProperty().get().getY() >= bound.getMaxY())
            onImpact(DOWN);
        if (model.topProperty().get().getY() <= bound.getMinY())
            onImpact(UP);
        if (model.leftProperty().get().getX() <= bound.getMinX())
            onImpact(LEFT);
        if (model.rightProperty().get().getX() >= bound.getMaxX())
            onImpact(RIGHT);
    }

    @Override
    public void onImpact(int side) {
        switch (side){
            case UP, DOWN -> getModel().setSpeed(new Point2D(getModel().getSpeed().getX(), -getModel().getSpeed().getY()));
            case LEFT, RIGHT -> getModel().setSpeed(new Point2D(-getModel().getSpeed().getX(), getModel().getSpeed().getY()));

        }
    }
}

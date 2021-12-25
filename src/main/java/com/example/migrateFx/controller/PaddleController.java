package com.example.migrateFx.controller;

import com.example.migrateFx.Movable;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import javafx.geometry.Point2D;

public class PaddleController extends SpriteController implements Movable {
    public PaddleController(SpriteModel model,
                            SpriteView view) {
        super(model, view);
    }

    @Override
    public void move() {
        getModel().setLocation(getModel().getLocation().add(getModel().getSpeed()));
    }

    public void moveLeft() {
        if (getModel().getSpeed().getX() <= 0)
            getModel().setSpeed(new Point2D(-5, 0));
    }

    public void moveRight() {
        if (getModel().getSpeed().getX() >= 0)
            getModel().setSpeed(new Point2D(5, 0));
    }

    public void stop() {
        getModel().setSpeed(new Point2D(0, 0));
    }
    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getModel().boundsProperty().bind(getView().getView()
                                                  .boundsInParentProperty());
    }
}

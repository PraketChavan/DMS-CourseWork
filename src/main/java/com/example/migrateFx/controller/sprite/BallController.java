package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.BallModel;
import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.view.SpriteView;
import javafx.geometry.Point2D;

public class BallController extends SpriteController {

    public BallController(SpriteModel model, SpriteView view) {
        super(model, view);
    }

    public void move() {
        ((BallModel) getModel()).move();
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

    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        ((BallModel) getModel()).setRadius(
                getView().getView().getImage().getHeight() / 2);
        ((BallModel) getModel()).initializeCenter();
    }
}

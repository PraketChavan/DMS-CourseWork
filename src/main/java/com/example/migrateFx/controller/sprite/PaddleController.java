package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.util.Movable;
import com.example.migrateFx.view.SpriteView;
import javafx.geometry.Point2D;

public class PaddleController extends SpriteController {
    public PaddleController(SpriteModel model,
                            SpriteView view) {
        super(model, view);
    }

    public void move() {
        ((Movable) getModel()).move();
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

    public void reset() {
        getModel().reset();
    }


    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getModel().boundsProperty().bind(getView().getView()
                                                  .boundsInParentProperty());
    }
}

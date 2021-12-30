package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.controller.PowerUpController;
import com.example.migrateFx.controller.SpriteController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.PowerUpModel;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.BallView;
import com.example.migrateFx.view.PowerUpView;
import com.example.migrateFx.view.SpriteView;
import javafx.geometry.Point2D;

public class PowerUp extends Sprite{
    private static final double POWERUP_SIZE = 20;
    @Override
    public PowerUpController getController() {
        return (PowerUpController) super.getController();
    }

    @Override
    public PowerUpModel getModel() {
        return (PowerUpModel) super.getModel();
    }

    @Override
    public PowerUpView getView() {
        return (PowerUpView) super.getView();
    }

    public PowerUp(String url, Point2D location, String type) {
        setModel(new PowerUpModel(location));
        setView(new PowerUpView(url, POWERUP_SIZE, POWERUP_SIZE));
        setController(new PowerUpController(getModel(), getView()));
        getModel().setName(type);
        getModel().setSpeed(new Point2D(0, 3));
    }

}

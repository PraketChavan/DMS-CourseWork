package com.example.migrateFx.wrappers.powerup;

import com.example.migrateFx.controller.sprite.PowerUpController;
import com.example.migrateFx.model.sprite.PowerUpModel;
import com.example.migrateFx.view.PowerUpView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

public class PowerUp extends Sprite {
    private static final double POWERUP_SIZE = 20;

    public PowerUp(String url, Point2D location, PowerUpType type) {
        setModel(new PowerUpModel(location, type));
        setView(new PowerUpView(url, POWERUP_SIZE, POWERUP_SIZE));
        setController(new PowerUpController(getModel(), getView()));
        getModel().setSpeed(new Point2D(0, 3));
    }

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

}

package com.example.migrateFx.wrappers.powerup;

import com.example.migrateFx.controller.sprite.PowerUpController;
import com.example.migrateFx.model.sprite.PowerUpModel;
import com.example.migrateFx.view.PowerUpView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

/**
 * Wrapper class for the PowerUp MVC it is responsible for creating the
 * PowerUpModel, PowerUpView, PowerUpController and sets up the necessary
 * connections
 * <br>
 * @author Praket Chavan
 */
public class PowerUp extends Sprite {
    /**
     * Defines the size of the PowerUp sprite view
     */
    private static final double POWER_UP_SIZE = 20;

    /**
     * Constructor for creating and setting up the {@link PowerUpModel},
     * {@link PowerUpView} and {@link PowerUpController} objects for the
     * PowerUp sprite
     * @param url the path to the image resource for the sprite
     * @param location the starting location of the sprite
     * @param type the type of PowerUp
     */
    public PowerUp(String url, Point2D location, PowerUpType type) {
        setModel(new PowerUpModel(location, type));
        setView(new PowerUpView(url, POWER_UP_SIZE, POWER_UP_SIZE));
        setController(new PowerUpController(getModel(), getView()));
        getModel().setSpeed(new Point2D(0, 3));
    }

    /**
     * Overloaded getter method that returns the PowerUpController instead of
     * SpriteController
     * @return
     */
    @Override
    public PowerUpController getController() {
        return (PowerUpController) super.getController();
    }

    /**
     * Overloaded getter method that returns the PowerUpModel instead of
     * SpriteModel
     * @return
     */
    @Override
    public PowerUpModel getModel() {
        return (PowerUpModel) super.getModel();
    }

    /**
     * Overloaded getter method that returns the PowerUpView instead of
     * SpriteView
     * @return
     */
    @Override
    public PowerUpView getView() {
        return (PowerUpView) super.getView();
    }

}

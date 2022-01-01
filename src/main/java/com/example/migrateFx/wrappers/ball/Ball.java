package com.example.migrateFx.wrappers.ball;

import com.example.migrateFx.controller.sprite.BallController;
import com.example.migrateFx.model.sprite.BallModel;
import com.example.migrateFx.view.BallView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

/**
 * Wrapper class for the Ball MVC it is responsible for creating the
 * BallModel, BallView, BallController and sets up the necessary connections
 * <br>
 * The class has been migrated from the original code and has been broken into
 * the three different classes namely the {@link BallController},
 * {@link BallView}, {@link BallModel}. This was done to achieve single
 * responsibility for each classes
 * @author Praket Chavan - modified
 * @see Sprite
 */
public abstract class Ball extends Sprite {

    /**
     * Constructor for setting up the MVC member variables
     *
     * @param url the path of the image resource for the SpriteView
     * @param location the starting location of the sprite
     * @param size the radius of ball sprite
     */
    Ball(String url, Point2D location, double size) {
        setModel(new BallModel(location));
        setView(new BallView(url, size, size));
        setController(new BallController(getModel(), getView()));

    }

    /**
     * Overloaded getter method that returns the BallController instead of
     * SpriteController
     * @return
     */
    public BallController getController() {
        return (BallController) super.getController();
    }

    /**
     * Overloaded getter method that returns the BallModel instead of
     * SpriteController
     * @return
     */
    public BallModel getModel() {
        return (BallModel) super.getModel();
    }

    /**
     * Overloaded getter method that returns the BallView instead of
     * SpriteController
     * @return
     */
    public BallView getView() {
        return (BallView) super.getView();
    }
}

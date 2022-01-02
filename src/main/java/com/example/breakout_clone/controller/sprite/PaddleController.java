package com.example.breakout_clone.controller.sprite;

import com.example.breakout_clone.model.sprite.PaddleModel;
import com.example.breakout_clone.model.sprite.SpriteModel;
import com.example.breakout_clone.view.SpriteView;
import javafx.geometry.Point2D;

/**
 * Controller class for the Paddle MVC. It sets up the connection
 * and binds PaddleModel attributes to the PaddleView. It handles the
 * requests to change the PaddleModel attributes.
 * @author Praket Chavan
 * @see com.example.breakout_clone.view.PaddleView
 * @see PaddleModel
 * @see SpriteController
 */
public class PaddleController extends SpriteController {

    private static final int MOVEMENT_SPEED = 5;

    /**
     * Constructor to create the controller object using the super constructor
     * @param model PaddleModel object that will be connected to the MVC
     * @param view PaddleView object that will be connected to the MVC
     */
    public PaddleController(SpriteModel model,
                            SpriteView view) {
        super(model, view);
    }

    /**
     * Handles the move request for the paddle by calling the move method of
     * the model
     * @see PaddleModel#move()
     */
    public void move() {
        ((PaddleModel) getModel()).move();
    }

    /**
     * Handle the request to move the paddle to the left by setting the speed
     * of the paddle to {@link #MOVEMENT_SPEED} in the negative X direction
     * @see PaddleModel#setSpeed(Point2D)
     */
    public void moveLeft() {
        if (getModel().getSpeed().getX() <= 0)
            getModel().setSpeed(new Point2D(-MOVEMENT_SPEED, 0));
    }

    /**
     * Handle the request to move the paddle to the right by setting the speed
     * of the paddle to {@link #MOVEMENT_SPEED} in the positive X direction
     * @see PaddleModel#setSpeed(Point2D)
     */
    public void moveRight() {
        if (getModel().getSpeed().getX() >= 0)
            getModel().setSpeed(new Point2D(MOVEMENT_SPEED, 0));
    }

    /**
     * Handle the request to stop the paddle  by setting the speed
     * of the paddle to zero
     * @see PaddleModel#setSpeed(Point2D)
     */
    public void stop() {
        getModel().setSpeed(new Point2D(0, 0));
    }

    /**
     * Handles the request to reset the PaddleModel by calling the models
     * {@link PaddleModel#reset()} function
     */
    public void reset() {
        getModel().reset();
    }


    /**
     * {@inheritDoc}
     * <br>
     * Binds the views layout X and Y property to the models X and Y location
     * property
     */
    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getModel().boundsProperty().bind(getView().getView()
                                                  .boundsInParentProperty());
    }
}

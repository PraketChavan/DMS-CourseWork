package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.BallModel;
import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.view.SpriteView;
import javafx.geometry.Point2D;

/**
 * Controller class for the Ball view. The class creates the binding of the
 * view properties to the respective model properties.
 * The class handles all the update request to the BallModel class and calls
 * the appropriate function.
 * @author Praket Chavan
 */
public class BallController extends SpriteController {

    /**
     * Constructor that is used to call the super constructor
     * @param model BallModel object that needs to be linked to the
     *              controller and view
     * @param view  BallView object that will be linked to the controller
     *              and model
     * @see SpriteController#SpriteController(SpriteModel, SpriteView)
     * @see BallModel
     * @see com.example.migrateFx.view.BallView
     */
    public BallController(SpriteModel model, SpriteView view) {
        super(model, view);
    }

    /**
     * Call the BallModels move method
     * @see BallModel#move()
     */
    public void move() {
        ((BallModel) getModel()).move();
    }


    /**
     * Sets the model x speed to the specified value by calling the model method
     * @param xSpeed the new X speed of the Ball
     * @see BallModel#setSpeed(Point2D)
     */
    public void setXSpeed(double xSpeed) {
        getModel().setSpeed(new Point2D(xSpeed, getModel().getSpeed().getY()));
    }

    /**
     * Sets the model y speed to the specified value by calling the model method
     * @param ySpeed the new Y speed of the Ball
     * @see BallModel#setSpeed(Point2D)
     */
    public void setYSpeed(double ySpeed) {
        getModel().setSpeed(new Point2D(getModel().getSpeed().getX(), ySpeed));
    }

    /**
     * Sets the model speed to the new speed specified by calling the model
     * method
     * @param speed the new speed of the ball
     */
    public void setSpeed(Point2D speed) {
        getModel().setSpeed(speed);
    }

    /**
     * Calls the {@link BallModel#reset()} method when this method is called
     */
    public void reset() {
        getModel().reset();
    }

    /**
     * {@inheritDoc}
     * <br>
     * Initialises the radius property by getting the ball views size
     */
    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        ((BallModel) getModel()).setRadius(
                getView().getView().getImage().getHeight() / 2);
        ((BallModel) getModel()).initializeCenter();
    }
}

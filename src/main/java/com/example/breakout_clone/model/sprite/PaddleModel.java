package com.example.breakout_clone.model.sprite;

import com.example.breakout_clone.util.Impactable;
import com.example.breakout_clone.util.Movable;
import javafx.geometry.Point2D;

/**
 * The Model class for the Paddle MVC. It stores all the data of a Paddle object
 * and provides the controller with functions to allow it to modify the
 * state/data of the model
 * <br>This class contains the main paddle logic on how to move the paddle.<br>
 * The class implements the Movable and the Impactable interface and all
 * its method
 * @author Praket Chavan
 * @see SpriteModel
 * @see Impactable
 * @see Movable
 */
public class PaddleModel extends SpriteModel implements Impactable, Movable {

    private static final double QUARTER = 0.25;

    /**
     * The original starting location of the model when it is created
     * @see #getStart_Location()
     */
    private final Point2D START_LOCATION;

    /**
     * Gets the Starting location of the paddle
     * @return Point2D object
     */
    private Point2D getStart_Location() {
        return START_LOCATION;
    }

    /**
     * Initialises the properties of the paddle to its default values
     * @param location the starting location of the paddle
     */
    public PaddleModel(Point2D location) {
        super(location);
        START_LOCATION = location;
        setSpeed(new Point2D(0, 0));
    }

    /**
     * Resets the location of the paddle to its original start location
     */
    @Override
    public void reset() {
        setLocation(getStart_Location().subtract(getWidth() / 2, 0));
    }

    /**
     * Finds the impact between the paddle and the ball
     * @param parent the ball object
     * @return a defined constant depending on where the ball hits the paddle
     * @see BallModel
     */
    @Override
    public int findImpact(Impactable parent) {
        //if the center of the ball is in the paddle then the ball is already
        // lost
        if (getBounds().contains(((BallModel)parent).getCenter()))
            return NO_IMPACT;
        //check to see if the paddle contains the bottom of the ball
        if (getBounds().contains(((BallModel)parent).bottomProperty().get())){
            //divide the paddle into 4 parts
            double centerBounds = getBounds().getCenterX();
            double rightBounds =
                    getBounds().getCenterX() + getWidth() * QUARTER;
            double leftBounds = getBounds().getCenterX() - getWidth() * QUARTER;
            double ballX = ((BallModel)parent).bottomProperty().get().getX();
            //check which part the ball has hit and return the value
            if (ballX > centerBounds) {
                if (ballX > rightBounds) {
                    return RIGHT_MOST_IMPACT;
                } else {
                    return RIGHT_IMPACT;
                }
            } else if (ballX < centerBounds) {
                if (ballX < leftBounds) {
                    return LEFT_MOST_IMPACT;
                } else {
                    return LEFT_IMPACT;
                }
            } else
                return MIDDLE_IMPACT;
        }
        //if the paddle does not contain the bottom point of the ball
        return NO_IMPACT;
    }

    @Override
    public void onImpact(int side) {
    }

    /**
     * Check to see if the new location of the paddle is in the bounds of the
     * game, if it is not then do not update the paddle's location
     */
    @Override
    public void move() {
        Point2D newLocation = getLocation().add(getSpeed());
        if (newLocation.getX() <= GAME_BOUNDS.getMinX()
                || newLocation.getX() + getWidth() >= GAME_BOUNDS.getMaxX())
            return;
        setLocation(newLocation);
    }
}

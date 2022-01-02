package com.example.breakout_clone.wrappers.ball;

import javafx.geometry.Point2D;

/**
 * Wrapper class for the creating the MVC classes for the RubberBall Sprite
 * <br>
 * This class has been migrated from the original Ball1 class.
 * @author Praket Chavan - modified
 * @see Ball
 */
public class RubberBall extends Ball {
    /**
     * Constant defines the radius of the radius of the ball
     */
    private static final double BALL_SIZE = 13;

    /**
     * Constructor for calling the super class constructor
     * @param url the path of the image resource for the sprite
     * @param location the starting location of the sprite
     */
    public RubberBall(String url, Point2D location) {
        super(url, location, BALL_SIZE);
    }

}

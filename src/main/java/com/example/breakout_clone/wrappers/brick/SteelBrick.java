package com.example.breakout_clone.wrappers.brick;

import javafx.geometry.Point2D;

/**
 * Wrapper class for creating steel brick MVC classe
 * <br>
 *  The Steel brick is the intermediate type of obstacle in the game that
 *  the
 *   user can break after 2 hits
 *   <br>
 * This class has been migrated from the original Brick2 class.
 * @author Praket Chavan - modified
 * @see Brick
 */
public class SteelBrick extends Brick {

    /**
     * Constant to define the strength of the steel brick
     */
    private static final int STEEL_BRICK_FULL_STRENGTH = 2;

    /**
     * Constructor used for calling the super constructor and setting the
     * bricks full strength and strength to {@link #STEEL_BRICK_FULL_STRENGTH}
     *
     * @param url the path of the image resource of the sprite
     * @param location the starting location of sprite
     * @see Brick#Brick(String, Point2D, boolean, String)
     */
    public SteelBrick(String url, Point2D location) {
        super(url, location, false, "Steel");
        getModel().setFullStrength(STEEL_BRICK_FULL_STRENGTH);
        getModel().setStrength(STEEL_BRICK_FULL_STRENGTH);
    }
}

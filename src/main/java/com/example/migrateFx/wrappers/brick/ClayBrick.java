package com.example.migrateFx.wrappers.brick;

import javafx.geometry.Point2D;

/**
 * Wrapper class for creating ClayBricks MVC classes
 * <br>
 * This class has been migrated from the original Brick1 class.
 * @author Praket Chavan - modified
 *  @see Brick
 */
public class ClayBrick extends Brick {

    /**
     * Constructor used to call the super class constructor
     * and set the name of the sprite to be "Clay"
     * @param url the path of the sprite image resource
     * @param location the starting location of the sprite
     * @param isSpec the boolean representing whether the brick is special
     *               or not
     * @see Brick#Brick(String, Point2D, boolean, String)
     */
    public ClayBrick(String url, Point2D location, boolean isSpec) {
        super(url, location, isSpec, "Clay");
    }
}

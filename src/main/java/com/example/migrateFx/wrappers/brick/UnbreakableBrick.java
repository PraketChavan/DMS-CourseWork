package com.example.migrateFx.wrappers.brick;

import javafx.geometry.Point2D;

/**
 * Wrapper class for the creating the MVC classes
 * This class has been migrated from the original Brick3 class.
 * @author Praket Chavan - modified
 * @see Brick
 */
public class UnbreakableBrick extends Brick {
    /**
     * Constructor for calling the super constructor and initialising the
     * brick strength to the maximum integer value
     * @param url the path to the image resource for the sprite
     * @param location the starting location of the sprite
     * @see Brick#Brick(String, Point2D, boolean, String)
     */
    public UnbreakableBrick(String url, Point2D location) {
        super(url, location, false, "Unbreakable");
        getModel().setFullStrength(Integer.MAX_VALUE);
        getModel().setStrength(Integer.MAX_VALUE);
    }
}

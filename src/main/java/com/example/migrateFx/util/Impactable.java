package com.example.migrateFx.util;


import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

/**
 * This interface provides the functionality for sprites to be impactable.
 * i.e. they can collide with other Imapctable sprites
 * @author Praket Chavan
 */
public interface Impactable {
    //Constant to define the direction of the collision
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int NO_IMPACT = -1;
    int LEFT_MOST_IMPACT = 4;
    int LEFT_IMPACT = 5;
    int MIDDLE_IMPACT = 6;
    int RIGHT_IMPACT = 7;
    int RIGHT_MOST_IMPACT = 8;
    /**
     * The bounding box of the main game area
     */
    Bounds GAME_BOUNDS = new BoundingBox(0, 0, 640, 500);

    /**
     * Find the impact between the current object and the passed impactable
     * object
     * @param parent the object to detect the impact with
     * @return the direction of the collision
     */
    int findImpact(Impactable parent);

    /**
     * function to carry out steps once a collision has been detected
     * @param side the direction of the collision
     */
    void onImpact(int side);
}

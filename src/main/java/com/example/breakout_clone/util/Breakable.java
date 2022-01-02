package com.example.breakout_clone.util;

/**
 * This interface provides functionality for sprites that need to break
 * <br> The subclasses of this class represent sprites that can be broken
 * @author Praket Chavan
 */
public interface Breakable {
    /**
     * Constant value for when a Special object is broken
     */
    int SPECIAL_BREAK = 1;
    /**
     * Constant value for when a normal object is broken
     */
    int NORMAL_BREAK = 2;

    /**
     * This method checks whether the sprite is broken
     * @return
     */
    boolean checkBroken();

    /**
     * This method provides steps to take once a sprite is broken
     * @return
     */
    int onBreak();
}

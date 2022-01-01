package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickFactory;

/**
 * Abstract Level class that is used to define the layout of the bricks for a
 * given level. Subclasses of this class need to implement the
 * {@link #createLevel()} method.
 *
 * @author Praket Chavan
 */
public abstract class Level {
    /**
     * Constant to define the Brick Width
     */
    protected static final double BRICK_WIDTH = 80;
    /**
     * Constant to define the Brick height
     */
    protected static final double BRICK_HEIGHT = 16;

    /**
     * Constant to define the maximum number of bricks that can be created on
     * a line
     */
    protected static final int MAX_BRICK_ON_LINE = 8;

    /**
     * Constant BrickFactory object that is used to create the bricks that
     * are in the level
     * @see BrickFactory
     */
    protected static final BrickFactory BRICK_FACTORY = new BrickFactory();

    /**
     * Integer value that signifies the difficulty of the level. Higher the
     * value the higher the difficulty
     * @see #getDifficulty()
     * @See #setDifficulty()
     */
    private int m_difficulty;

    /**
     * Gets the current difficulty of the level
     * @return the int that represent the difficulty
     * @see #setDifficulty(int)
     */
    public int getDifficulty() {
        return m_difficulty;
    }

    /**
     * Sets the current difficulty to the new value that is passed
     * @param difficulty the new difficulty that is to be set
     * @see #getDifficulty()
     */
    public void setDifficulty(int difficulty) {
        m_difficulty = difficulty;
    }

    /**
     * Level constructor that is used to initialise the value of the difficulty
     * @param difficulty the difficulty that is to be set for the level
     */
    public Level(int difficulty) {
        setDifficulty(difficulty);
    }

    /**
     * Method that is used to create the levels
     * @return an array of Brick objects that represent the layout of the
     * bricks on the screen
     */
    public abstract Brick[] createLevel();
}

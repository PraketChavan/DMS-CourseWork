package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickType;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * This class is new level type that will create a number of columns of clay
 * bricks that are spaces out. The number of rows and column depend on the
 * difficulty of the level
 *
 * @author Praket Chavan
 * @see Level
 */
public class Level2 extends Level {

    /**
     * Constant that defines the maximum allocated space for the bricks
     */
    private static final double MAX_WIDTH = BRICK_WIDTH * MAX_BRICK_ON_LINE;
    private static final double HALF = 0.5;
    private static final int THREE = 3;

    /**
     * Constructor that is used to initialise the difficulty value
     * @param difficulty the difficulty of the level
     */
    public Level2(int difficulty) {
        super(difficulty);
    }

    /**
     *  {@inheritDoc}
     *  It will create columns of Clay bricks on the screen and the number of
     *  rows and columns depends on the difficulty. The fewer columns there
     *  are the more spaced out they will be.
     * @return an array of Brick that represents the layout of the brick view
     * on the screen
     */
    public Brick[] createLevel() {
        Random rand = new Random();
        //Randomise the number of rows and columns each time
        int numberOfLines = Math.max(getDifficulty(), getDifficulty() * THREE);
        int numberOfColumn = Math.max(THREE, rand.nextInt(MAX_BRICK_ON_LINE));
        int numberOfBricks = numberOfLines * numberOfColumn;

        Brick[] temp = new Brick[numberOfBricks];

        //find the space that each column of bricks will get
        double brickAllocatedSize = (MAX_WIDTH / numberOfColumn);
        //find the offset to center the bricks in the allocated space
        double offset = (brickAllocatedSize - BRICK_WIDTH) * HALF;

        Point2D p;
        for (int i = 0; i < temp.length; i++) {
            int line = i / numberOfColumn;
            if (line == numberOfLines)
                break;
            double x = (i % numberOfColumn) * (brickAllocatedSize) + offset;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            temp[i] = BRICK_FACTORY.createBrick(BrickType.CLAY, p);
        }

        return temp;
    }

}

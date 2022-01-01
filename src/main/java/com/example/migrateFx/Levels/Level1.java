package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickType;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * This class is extracted from the original Wall class's makeSingleTypeLevel
 * method. This will create a level containing only clay bricks in a simple
 * row like structure. The level of difficulty will influence the number of
 * rows there will be.
 * @see Level
 */
public class Level1 extends Level {

    /**
     * Constructor that is used to initialise the difficulty value
     * @param difficulty the difficulty of the level
     */
    public Level1(int difficulty) {
        super(difficulty);
    }

    /**
     *  {@inheritDoc}
     *  It will create rows of Clay bricks on the screen and the number of
     *  rows depends on the difficulty
     * @return an array of Brick that represents the layout of the brick view
     * on the screen
     */
    @Override
    public Brick[] createLevel() {
        Random rand = new Random();
        //Randomise the number of rows each time
        int numberOfLines = Math.max(getDifficulty(), rand.nextInt(
                getDifficulty() + getDifficulty()));
        int numberOfBricks = numberOfLines * MAX_BRICK_ON_LINE;

        Brick[] temp = new Brick[numberOfBricks];

        Point2D p;
        //Create the bricks and find their location depending on the row and
        // column number
        for (int i = 0; i < temp.length; i++) {
            int line = i / MAX_BRICK_ON_LINE;
            if (line == numberOfLines)
                break;
            double x = (i % MAX_BRICK_ON_LINE) * BRICK_WIDTH;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            temp[i] = BRICK_FACTORY.createBrick(BrickType.CLAY, p);
        }
        return temp;
    }
}
package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickType;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * This Level type introduces the unbreakable brick type. The level is
 * similar to Level1 however there is a chance of randomly generating some
 * number of unbreakable bricks in the level.
 * @author Praket Chavan
 * @see Level
 */
public class Level4 extends Level {
    //Constants defined to remove magic numbers
    private static final int TEN = 10;
    private static final int TWO = 2;
    private static final int EIGHT = 8;

    /**
     * Constructor that is used to initialise the difficulty value
     * @param difficulty the difficulty of the level
     */
    public Level4(int difficulty) {
        super(difficulty);
    }

    /**
     * {@inheritDoc}
     * This generates a level similar to Level 1, except it also generates
     * unbreakable bricks at random.
     * @return the brick array that represents the layout of the bricks on
     *        the screen
     */
    @Override
    public Brick[] createLevel() {
        Random rand = new Random();
        //randomise the number of rows
        int numberOfLines = Math.max(getDifficulty(), rand.nextInt(
                getDifficulty() + getDifficulty()));
        int numberOfBricks = numberOfLines * MAX_BRICK_ON_LINE;

        Brick[] temp = new Brick[numberOfBricks];

        Point2D p;
        for (int i = 0; i < temp.length; i++) {
            int line = i / MAX_BRICK_ON_LINE;
            if (line == numberOfLines)
                break;
            double x = (i % MAX_BRICK_ON_LINE) * BRICK_WIDTH;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            //Randomise the generation of unbreakable Bricks and make sure
            // that the level does not become impossible
            if (rand.nextInt(TEN) > EIGHT && i % TWO == 0)
                temp[i] = BRICK_FACTORY.createBrick(BrickType.UNBREAKABLE, p);
            else
                temp[i] = BRICK_FACTORY.createBrick(BrickType.CLAY, p);
        }
        return temp;
    }
}

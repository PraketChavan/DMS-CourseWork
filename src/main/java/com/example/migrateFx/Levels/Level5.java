package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickType;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * The level generates rows of steel bricks that are tightly packed together
 * @author Praket Chavan
 * @see Level
 */

public class Level5 extends Level {

    public Level5(int difficulty) {
        super(difficulty);
    }

    /**
     * {@inheritDoc}
     * It will create rows of steel bricks on the screen and the number of
     *  rows depends on the difficulty
     * @return an array of Brick that represents the layout of the brick view
     *  on the screen
     */
    @Override
    public Brick[] createLevel() {
        Random rand = new Random();
        //randomise the number of rows each time
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
            temp[i] = BRICK_FACTORY.createBrick(BrickType.STEEL, p);
        }
        return temp;

    }
}

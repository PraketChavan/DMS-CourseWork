package com.example.breakout_clone.Levels;

import com.example.breakout_clone.wrappers.brick.Brick;
import com.example.breakout_clone.wrappers.brick.BrickType;
import javafx.geometry.Point2D;

import java.util.Random;
/**
 * This class is extracted from the original Wall class's
 * makeChessBoardLevel method. This will create a level containing clay
 * bricks and steel bricks in an alternating pattern and a  row like
 * structure. The level of difficulty will influence the number of
 * rows there will be.
 * @author Praket Chavan - modified
 * @see Level
 */
public class Level3 extends Level {

    private static final int TWO = 2;

    /**
     * Constructor that is used to initialise the difficulty value
     * @param difficulty the difficulty of the level
     */
    public Level3(int difficulty) {
        super(difficulty);
    }

    /**
     * {@inheritDoc}
     * This will create a level containing clay
     * bricks and steel bricks in an alternating pattern and a  row like
     * structure. The level of difficulty will influence the number of
     * rows there will be.
     * @return the brick array that represents the layout of the bricks on
     * the screen
     */
    @Override
    public Brick[] createLevel() {
        Random rand = new Random();
        //randomise the number of lines each time to create variation
        int numberOfLines = Math.max(getDifficulty(), rand.nextInt(
                getDifficulty() + getDifficulty()));
        int numberOfBricks = numberOfLines * MAX_BRICK_ON_LINE;

        Brick[] temp = new Brick[numberOfBricks];

        //Generate brick on the odd rows
        for (int i = 0; i < temp.length; i++) {
            int line = i / MAX_BRICK_ON_LINE;
            if (line % TWO != 0)
                continue;
            Point2D p;
            if (line == numberOfLines)
                break;
            double x = (i % MAX_BRICK_ON_LINE) * BRICK_WIDTH;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            if (i % TWO == 0) //alternate the bricks
                temp[i] = BRICK_FACTORY.createBrick(BrickType.CLAY, p);
            else
                temp[i] = BRICK_FACTORY.createBrick(BrickType.STEEL, p);
        }

        //Generate bricks on the even rows
        for (int i = 0; i < temp.length; i++) {
            int line = i / MAX_BRICK_ON_LINE;
            if (line % TWO == 0)
                continue;
            Point2D p;
            if (line == numberOfLines)
                break;
            double x = (i % MAX_BRICK_ON_LINE) * BRICK_WIDTH;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            if (i % TWO != 0) //alternate the bricks
                temp[i] = BRICK_FACTORY.createBrick(BrickType.CLAY, p);
            else
                temp[i] = BRICK_FACTORY.createBrick(BrickType.STEEL, p);
        }
        return temp;

    }

}

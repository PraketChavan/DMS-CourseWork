package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.Brick;
import javafx.geometry.Point2D;

import java.util.Random;

public class Level2 extends Level{

    private static final int THREE = 3;
    private static final int FIVE = 5;
    private static final int TEN = 10;
    public static final double MAX_WIDTH = 640.0;
    public static final double HALF = 0.5;

    public Level2(int difficulty) {
        super(difficulty);
    }

    public Brick[] createLevel() {
        Random rand = new Random();
        int numberOfLines = Math.max(getDifficulty(), getDifficulty() * THREE);
        int numberOfColumn = Math.max(THREE, rand.nextInt(MAX_BRICK_ON_LINE));
        int numberOfBricks = numberOfLines * numberOfColumn;

        Brick[] temp = new Brick[numberOfBricks];

        double brickAllocatedSize = (MAX_WIDTH / numberOfColumn);
        double offset = (brickAllocatedSize - BRICK_WIDTH) * HALF;

        Point2D p;
        for (int i = 0; i < temp.length; i++) {
            int line = i / numberOfColumn;
            if (line == numberOfLines)
                break;
            double x = (i % numberOfColumn) * (brickAllocatedSize) + offset;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            temp[i] = BRICK_FACTORY.createBrick(1, p);
        }

        return temp;
    }

}

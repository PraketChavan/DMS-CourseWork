package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.ClayBrick;
import javafx.geometry.Point2D;

import java.util.Random;

public class Level1 extends Level {

    public Level1(int difficulty) {
        super(difficulty);
    }


    @Override
    public Brick[] createLevel() {
        Random rand = new Random();
        int numberOfLines = Math.max(5, rand.nextInt(7));
        int numberOfBricks = Math.max(numberOfLines * 5, rand.nextInt(10) * getDifficulty());

        Brick[] temp = new Brick[numberOfBricks];

        int brickOnLine = numberOfBricks / numberOfLines;

        while (brickOnLine > MAX_BRICK_ON_LINE) {
            numberOfLines++;
            brickOnLine = numberOfBricks / numberOfLines;
        }
        double brickAllocatedSize = (640.0 / brickOnLine);
        double offset = (brickAllocatedSize - BRICK_WIDTH) / 2.0;

        Point2D p;
        System.out.println(numberOfBricks + "\t" + brickOnLine + "\t" );
        int i;
        for (i = 0; i < temp.length; i++) {
            int line = i / brickOnLine;
            if (line == numberOfLines)
                break;
            double x = (i % brickOnLine) * (brickAllocatedSize) + offset;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            temp[i] = BRICK_FACTORY.createBrick(1, p);
        }

        for (double y = BRICK_HEIGHT; i < temp.length; i++, y += 2 * BRICK_HEIGHT) {
            double x = (brickOnLine * BRICK_WIDTH) - (BRICK_WIDTH / 2);
            p = new Point2D(x, y);
            temp[i] = BRICK_FACTORY.createBrick(1, p);
        }
        return temp;

    }
}
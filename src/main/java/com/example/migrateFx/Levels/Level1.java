package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickType;
import javafx.geometry.Point2D;

import java.util.Random;

public class Level1 extends Level {
    private static final int BRICK_ON_LINE = 8;

    public Level1(int difficulty) {
        super(difficulty);
    }

    @Override
    public Brick[] createLevel() {
        Random rand = new Random();
        int numberOfLines = Math.max(getDifficulty(), rand.nextInt(
                getDifficulty() + getDifficulty()));
        int numberOfBricks = numberOfLines * BRICK_ON_LINE;

        Brick[] temp = new Brick[numberOfBricks];

        Point2D p;
        for (int i = 0; i < temp.length; i++) {
            int line = i / BRICK_ON_LINE;
            if (line == numberOfLines)
                break;
            double x = (i % BRICK_ON_LINE) * BRICK_WIDTH;
            double y = (line) * BRICK_HEIGHT;
            p = new Point2D(x, y);
            temp[i] = BRICK_FACTORY.createBrick(BrickType.CLAY, p);
        }
        return temp;

    }
}
package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.brick.BrickFactory;

public abstract class Level {
    protected static final double BRICK_WIDTH = 80;
    protected static final double BRICK_HEIGHT = 16;
    protected static final int MAX_BRICK_ON_LINE = 8;
    protected static final BrickFactory BRICK_FACTORY = new BrickFactory();
    private int m_difficulty;

    public int getDifficulty() {
        return m_difficulty;
    }

    public void setDifficulty(int difficulty) {
        m_difficulty = difficulty;
    }

    public Level(int difficulty) {
        setDifficulty(difficulty);
    }

    public abstract Brick[] createLevel();
}

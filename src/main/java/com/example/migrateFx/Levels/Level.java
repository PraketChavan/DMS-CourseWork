package com.example.migrateFx.Levels;

import com.example.migrateFx.factory.BrickFactory;
import com.example.migrateFx.wrappers.Brick;

public abstract class Level {
    protected static final double BRICK_WIDTH = 80;
    protected static final double BRICK_HEIGHT = 16;
    protected static final int MAX_BRICK_ON_LINE = 8;
    protected static final BrickFactory BRICK_FACTORY = new BrickFactory();
    private Brick[] m_bricks;
    private int m_difficulty;

    public Brick[] getBricks() {
        return m_bricks;
    }

    public void setBricks(Brick[] bricks) {
        m_bricks = bricks;
    }

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

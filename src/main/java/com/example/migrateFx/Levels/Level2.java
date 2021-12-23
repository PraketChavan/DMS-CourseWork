package com.example.migrateFx.Levels;

import com.example.migrateFx.wrappers.Brick;

public class Level2 extends Level{
    @Override
    public Brick[] createLevel() {
        return new Brick[0];
    }

    public Level2(int difficulty) {
        super(difficulty);
    }
}

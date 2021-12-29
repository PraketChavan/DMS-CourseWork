package com.example.migrateFx.factory;

import com.example.migrateFx.Levels.Level;
import com.example.migrateFx.Levels.Level1;
import com.example.migrateFx.Levels.Level2;
import com.example.migrateFx.wrappers.Brick;

public class LevelFactory {

    public Level createLevel(int levelno, int difficulty) {
        Level level = null;
        switch (levelno) {
            case 1 -> level = new Level1(difficulty);
            case 2 -> level = new Level2(difficulty);
//            case 3 -> level = new Level3(difficulty);
//            case 4 -> level = new Level4(difficulty);
//            case 5 -> level = new Level5(difficulty);
        }

//        m_bricks = level.createLevel();
        return level;
    }
}

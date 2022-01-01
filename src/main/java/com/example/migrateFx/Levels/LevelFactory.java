package com.example.migrateFx.Levels;

/**
 * This class is used to implement the Factor Design Pattern for the Level types
 * Provides functionality for the creation of different type of level without
 * worrying about the concrete type
 * @author Praket Chavan
 * @see Level
 */
public class LevelFactory {

    /**
     * Method that is used to create the level type depending on the level no
     * and the difficulty setting passed to the function
     * @param levelno the level that is to be created
     * @param difficulty the difficulty of the level
     * @return an abstract Level object
     */
    public Level createLevel(int levelno, int difficulty) {
        Level level = null;
        switch (levelno) {
            case 1 -> level = new Level1(difficulty);
            case 2 -> level = new Level2(difficulty);
            case 3 -> level = new Level3(difficulty);
            case 4 -> level = new Level4(difficulty);
            case 5 -> level = new Level5(difficulty);
        }

        return level;
    }
}

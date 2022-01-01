package com.example.migrateFx.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Utility class for creating HighScore objects to display on the highscore
 * list
 * @author Praket Chavan
 * @see com.example.migrateFx.controller.game.HighScoreController
 */
public class HighScore {
    /**
     * Name of the player with the high score entry
     * @see #getName()
     * @see #setName(String)
     * @see #nameProperty()
     */
    private final SimpleStringProperty m_Name;

    /**
     * The score of the player
     * @see #getScore()
     * @see #setScore(int)
     * @see #scoreProperty()
     */
    private final SimpleIntegerProperty m_Score;

    /**
     * Gets the name of the player
     * @return
     * @see #nameProperty()
     * @see #setName(String)
     */
    public String getName() {
        return nameProperty().get();
    }

    /**
     * Sets the name of the player to the passed String
     * @param name the name of the player to be set
     * @see #getName()
     * @see #nameProperty()
     */
    public void setName(String name) {
        this.nameProperty().set(name);
    }

    /**
     * Gets the score of the player
     * @return
     * @see #scoreProperty()
     * @see #setScore(int)
     */
    public int getScore() {
        return scoreProperty().get();
    }

    /**
     * Sets the score of the player to the passed value
     * @param score
     * @see #getScore()
     * @see #scoreProperty()
     */
    public void setScore(int score) {
        this.scoreProperty().set(score);
    }

    /**
     * Constructor to initialise the properties and their value
     * @param name the name of the player
     * @param score the score of the player
     */
    public HighScore(String name,
                     Integer score) {
        m_Name = new SimpleStringProperty(name);
        m_Score = new SimpleIntegerProperty(score);
    }

    /**
     * Gets the StringProperty containing the name
     * @return
     */
    private SimpleStringProperty nameProperty() {
        return m_Name;
    }

    /**
     * Gets the IntegerProperty containing the score of the property
     * @return
     */
    private SimpleIntegerProperty scoreProperty() {
        return m_Score;
    }
}

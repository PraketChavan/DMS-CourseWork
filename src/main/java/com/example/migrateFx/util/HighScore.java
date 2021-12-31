package com.example.migrateFx.util;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 */
public class HighScore {
    private final SimpleStringProperty m_Name;
    private final SimpleIntegerProperty m_Score;

    public String getName() {
        return m_Name.get();
    }

    public void setName(String name) {
        this.m_Name.set(name);
    }

    public int getScore() {
        return m_Score.get();
    }

    public void setScore(int score) {
        this.m_Score.set(score);
    }

    public HighScore(String name,
                     Integer score) {
        m_Name = new SimpleStringProperty(name);
        m_Score = new SimpleIntegerProperty(score);
    }

    public SimpleStringProperty nameProperty() {
        return m_Name;
    }

    public SimpleIntegerProperty scoreProperty() {
        return m_Score;
    }
}

package com.example.breakout_clone_javafx;

import com.example.breakout_clone.GameBoard;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.swing.*;

public class GameFrameModel {
    private static GameFrameModel m_GameInstance;
    public final JComponent GAMEBOARD;
    private final StringProperty TITLE;
    private BooleanProperty m_Gaming;
    private GameFrameController m_gameFrameController;

    public GameFrameController getGameFrameController() {
        return m_gameFrameController;
    }

    public void setGameFrameController(GameFrameController gameFrameController) {
        m_gameFrameController = gameFrameController;
    }

    public BooleanProperty getGaming() {
        return m_Gaming;
    }

    public void setGaming(BooleanProperty gaming) {
        m_Gaming = gaming;
    }

    public StringProperty getTITLE() {
        return TITLE;
    }

    private GameFrameModel(GameFrameController gameFrameController) {
        TITLE = new SimpleStringProperty("""
                                                 Breakout Clone\t +
                                                 space = start/pause\t" +
                                                 ←/→ = move left/right\t" +
                                                 esc = menu
                                                 """);
        GAMEBOARD = new GameBoard(null);
    }

    public static GameFrameModel getGameFrameModel(GameFrameController gameFrameController) {
        if (m_GameInstance == null)
            m_GameInstance = new GameFrameModel(gameFrameController);
        return m_GameInstance;
    }
}

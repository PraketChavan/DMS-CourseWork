package com.example.breakout_clone_javafx;

import javafx.fxml.FXML;
import javafx.embed.swing.SwingNode;
import javafx.stage.Stage;

public class GameFrameController {
    @FXML private SwingNode m_GameBoard;
    private GameFrameModel m_model;

    public GameFrameModel getModel() {
        return m_model;
    }

    public void setModel(GameFrameModel model) {
        m_model = model;
    }

    @FXML
    private void initialize() {
         setModel(GameFrameModel.getGameFrameModel(this));
         m_GameBoard.setContent(m_model.GAMEBOARD);
    }
}

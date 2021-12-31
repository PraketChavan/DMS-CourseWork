package com.example.migrateFx.controller.game;

import com.example.migrateFx.model.game.GameModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PauseMenuController {
    @FXML private Button m_Exit;
    @FXML private Button m_Continue;
    @FXML private Button m_Restart;
    private GameModel m_Model;

    public Button getContinue() {
        return m_Continue;
    }

    public Button getExit() {
        return m_Exit;
    }

    public GameModel getModel() {
        return m_Model;
    }

    public void setModel(GameModel model) {
        m_Model = model;
    }

    public Button getRestart() {
        return m_Restart;
    }

    @FXML
    private void initialize() {
        m_Model = GameModel.getGameInstance();
    }

    @FXML
    private void onContinueClick() {
        getContinue().getScene().getWindow().hide();
        getModel().onContinueClick();

    }

    @FXML
    private void onExitClick() {
        getModel().onExitClick();
    }

    @FXML
    private void onRestartClick() {
        getModel().onRestartClick();
    }

}

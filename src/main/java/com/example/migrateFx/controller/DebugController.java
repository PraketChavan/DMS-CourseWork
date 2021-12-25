package com.example.migrateFx.controller;

import com.example.migrateFx.model.GameModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;

public class DebugController {
    private GameModel m_Model;
    @FXML
    private Button m_ResetBalls;
    @FXML
    private Button m_SkipLevel;
    @FXML
    private Slider m_BallXSpeed;
    @FXML
    private Slider m_BallYSpeed;

    public Slider getBallXSpeed() {
        return m_BallXSpeed;
    }

    public Slider getBallYSpeed() {
        return m_BallYSpeed;
    }

    public GameModel getModel() {
        return m_Model;
    }

    public void setModel(GameModel model) {
        this.m_Model = model;
    }

    public Button getResetBalls() {
        return m_ResetBalls;
    }

    public Button getSkipLevel() {
        return m_SkipLevel;
    }


    @FXML
    private void initialize() {
        setModel(GameModel.getGameInstance());
        getBallYSpeed().valueProperty()
                       .addListener(((observableValue, number, t1) -> {
                           setBallYSpeed();
                       }));
        getBallXSpeed().valueProperty()
                       .addListener(((observableValue, number, t1) -> {
                           setBallXSpeed();
                       }));

    }

    @FXML
    private void setBallXSpeed() {
        getModel().setBallXSpeed((int) getBallXSpeed().getValue());
    }


    @FXML
    private void setBallYSpeed() {
        getModel().setBallYSpeed((int) getBallYSpeed().getValue());
    }

    @FXML
    private void resetBall() {
        getModel().resetBall();
    }

    @FXML
    private void skipLevel() {
        getModel().skipLevel();
    }
}

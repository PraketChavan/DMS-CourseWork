package com.example.migrateFx.controller.game;

import com.example.migrateFx.handler.ResourceHandler;
import com.example.migrateFx.model.game.GameModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

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
    @FXML
    private Button m_CompleteGame;

    public Slider getBallXSpeed() {
        return m_BallXSpeed;
    }

    public Slider getBallYSpeed() {
        return m_BallYSpeed;
    }

    public Button getCompleteGame() {
        return m_CompleteGame;
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
                       .addListener(((observableValue, number, t1) ->
                               setBallYSpeed()));
        getBallXSpeed().valueProperty()
                       .addListener(((observableValue, number, t1) ->
                               setBallXSpeed()));

    }

    @FXML
    private void onCompleteClick() {
        onGameComplete();
    }

    private void onGameComplete() {
        getCompleteGame().getScene().getWindow().hide();
        getModel().stopTimer();
        FXMLLoader root = new FXMLLoader(getClass().getResource(
                "/com/example/migrateFx/GameCompleteView.fxml"));
        try {
            Scene scene = new Scene(root.load());
            Stage.getWindows().stream().filter(Window::isShowing)
                 .forEach(stage -> ((Stage) stage).setScene(scene));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void resetBall() {
        getModel().gameReset();
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
    private void skipLevel() {
        if (getModel().hasNextLevel()) {
            if (getModel().getLevelSound() != null)
                getModel().getLevelSound().stop();
            Media media = new Media(
                    ResourceHandler.getSoundResource(
                            "Phase " + getModel().getLevelNumber() + ".mp3"));
            getModel().setLevelSound(new MediaPlayer(media));
            getModel().nextLevel();
            getModel().getLevelSound().play();
            getModel().getLevelSound().setCycleCount(MediaPlayer.INDEFINITE);
        }
    }
}

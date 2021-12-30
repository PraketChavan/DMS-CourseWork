package com.example.migrateFx.controller;

import com.example.migrateFx.Impactable;
import com.example.migrateFx.factory.PowerUpFactory;
import com.example.migrateFx.handler.ImpactHandler;
import com.example.migrateFx.handler.ResourceHandler;
import com.example.migrateFx.model.GameModel;
import com.example.migrateFx.wrappers.Ball;
import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.PowerUp;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class GameController {
    private GameModel m_model;
    @FXML private Pane m_gamePane;
    @FXML private Label m_brickCount;
    @FXML private Label m_score;
    @FXML private Label m_ballCount;
    @FXML private Label m_levelComplete;
    @FXML private Label m_messageText;

    public Label getBallCount() {
        return m_ballCount;
    }

    public Label getBrickCount() {
        return m_brickCount;
    }

    public Pane getGamePane() {
        return m_gamePane;
    }

    public Label getLevelComplete() {
        return m_levelComplete;
    }

    public Label getMessageText() {
        return m_messageText;
    }

    public GameModel getModel() {
        return m_model;
    }

    public void setModel(GameModel model) {
        m_model = model;
    }

    public Label getScore() {
        return m_score;
    }

    private void gameTimer() {
        getModel().move();
        int impact = getModel().getImpactHandler().handleImpacts();
        if (impact == ImpactHandler.SPECIAL_BRICK_BROKEN)
            getModel().createPowerUp(new PowerUpFactory().createPowerUP());
        getModel().updateBrickCount();
        if (getModel().isBallLost()) {
            getModel().onBallLost();
            if (getModel().getBallCount() <= 0)
                onGameOver();
        } else if (getModel().islevelComplete()) {
            if (getModel().hasNextLevel()) {
                getModel().gameReset();
                nextLevel();
//                getModel().nextLevel();
            } else
                onGameComplete();
        }
    }

    @FXML
    private void initialize() {
        getGamePane().requestFocus();
        setModel(GameModel.getGameInstance());
        initializeBinding();
        initializeListener();
        getModel().getPlayer().getView().createView(getGamePane());

        getModel().initialize();
        nextLevel();
        getModel().setGameTimer(new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameTimer();
            }
        });
        getModel().gameReset();
        getLevelComplete().setVisible(false);

    }

    private void initializeBinding() {
        getBallCount().textProperty().bind(getModel().ballCountProperty()
                                                     .asString());
        getScore().textProperty().bind(getModel().scoreCountProperty()
                                                 .asString());
        getBrickCount().textProperty().bind(getModel().brickCountProperty()
                                                      .asString());
        getModel().gameBoundsProperty()
                  .bind(getGamePane().boundsInParentProperty());
        getMessageText().textProperty().bind(getModel().messageProperty());

    }

    private void initializeListener() {
        getModel().getPowerUps().addListener(
                (ListChangeListener<? super PowerUp>) change -> {
                    if (change.next()) {
                        for (PowerUp power : change.getAddedSubList())
                            power.getView().createView(getGamePane());
                        for (PowerUp power : change.getRemoved())
                            getGamePane().getChildren()
                                         .remove(power.getView().getView());
                    }
                });

        getGamePane().focusedProperty()
                     .addListener((observableValue, aBoolean, t1) -> {
                         if (t1)
                             getModel().onFocusGain();
                         else
                             getModel().onFocusLost();
                     });
        getModel().getBricks()
                  .addListener((ListChangeListener<? super Brick>) change -> {
                      if (change.next()) {
                          for (Brick brick : change.getAddedSubList()) {
                              brick.getView().createView(getGamePane());
                          }
                          for (Brick brick :
                                  change.getRemoved()) {
                              getGamePane().getChildren().remove(brick.getView()
                                                                      .getView());
                              getModel().setBrickCount(
                                      getModel().getBallCount() - 1);
                          }
                      }
                  });
        getModel().getBalls()
                  .addListener((ListChangeListener<? super Ball>) change -> {
                      if (change.next()) {
                          for (Ball ball : change.getAddedSubList()) {
                              ball.getView().createView(getGamePane());
                          }
                      }
                  });
        getModel().getImpactHandler().getPowerUp().addListener(
                (ListChangeListener<? super Impactable>) change -> {
                    if (change.next()) {
                        getModel().getPowerUps().removeAll(
                                 getModel().getPowerUps().stream()
                                          .filter(powerUp ->change.getRemoved()
                                                           .contains(
                                                            powerUp.getModel())
                                                    ).toList());
                    }
                });
    }

    private void nextLevel() {
        if (getModel().getLevelSound() != null)
            getModel().getLevelSound().stop();
        Media media = new Media(ResourceHandler.getSoundResource(
                "Phase " + getModel().getLevelNumber() + ".mp3"));
        getModel().setLevelSound(new MediaPlayer(media));
        getModel().nextLevel();
        getModel().getLevelSound().play();
        getModel().getLevelSound().setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void onGameComplete() {
        getModel().stopTimer();
        FXMLLoader root = new FXMLLoader(getClass().getResource(
                "/com/example/migrateFx/GameCompleteView.fxml"));
        try {
            Scene scene = new Scene(root.load());
            ((Stage) getGamePane().getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void onGameOver() {
        getModel().stopTimer();
        FXMLLoader root = new FXMLLoader(getClass().getResource(
                "/com/example/migrateFx/GameOverView.fxml"));
        try {
            Scene scene = new Scene(root.load());
            ((Stage) getGamePane().getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> getModel().leftKeyPressed();
            case RIGHT -> getModel().rightKeyPressed();
            case SPACE -> getModel().spaceKeyPressed();
            case ESCAPE -> {
                getModel().escapeKeyPressed();
                getGamePane().getScene().getRoot()
                             .setEffect(new GaussianBlur());

                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                popupStage.initOwner(getGamePane().getScene().getWindow());
                popupStage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/com/example/migrateFx/PauseView.fxml"));
                try {
                    popupStage.setScene(
                            new Scene(loader.load(), Color.TRANSPARENT));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                popupStage.show();
            }
//                this.setShowPauseMenu(!this.isShowPauseMenu());
//                drawPauseMenu();
//                this.getGameTimer().stop();
//            }
            case F1 -> {
                getModel().f1KeyPressed(event);

                if (event.isAltDown() && event.isShiftDown()) {
                    Stage stage = new Stage();
                    FXMLLoader root;
//                    Parent root = null;
                    try {
                        root = new FXMLLoader(getClass().getResource(
                                "/com/example/migrateFx/DebugView.fxml"));
                        stage.setScene(new Scene(root.load()));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                //m_debugConsole.setVisible(true);

            }
        }
    }

    @FXML
    private void onKeyReleased(KeyEvent event) {
        getModel().stopPlayer();
    }

    private void onNextLevel() {
        getModel().stopTimer();
        getLevelComplete().setVisible(true);
        FadeTransition transition = new FadeTransition(
                Duration.millis(3000), getLevelComplete());
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.play();
    }
}

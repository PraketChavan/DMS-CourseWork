package com.example.migrateFx.controller;

import com.example.migrateFx.model.GameModel;
import com.example.migrateFx.wrappers.Ball;
import com.example.migrateFx.wrappers.Brick;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GameController {
    private GameModel m_model;
    @FXML private Pane m_gamePane;
    @FXML private Label m_brickCount;
    @FXML private Label m_score;
    @FXML private Label m_ballCount;
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

    @FXML
    private void initialize() {
        getGamePane().requestFocus();
        setModel(GameModel.getGameInstance());
        getBallCount().textProperty().bind(getModel().ballCountProperty()
                                                     .asString());
        getScore().textProperty().bind(getModel().scoreCountProperty()
                                                 .asString());
        getBrickCount().textProperty().bind(getModel().brickCountProperty()
                                                      .asString());
        getModel().gameBoundsProperty()
                  .bind(getGamePane().boundsInParentProperty());
        getGamePane().focusedProperty()
                     .addListener((observableValue, aBoolean, t1) -> {
                         if (t1)
                             getModel().onFocusGain();
                         else
                             getModel().onFocusLost();
                     });
        getMessageText().textProperty().bind(getModel().messageProperty());
        getModel().getBricks()
                  .addListener((ListChangeListener<? super Brick>) change -> {
                      if (change.next()) {
                          for (Brick brick : change.getAddedSubList()) {
                              brick.getView().createView(getGamePane());
                          }
                          for (Brick brick:
                               change.getRemoved()) {
                              getGamePane().getChildren().remove(brick.getView()
                                                                    .getView());
                          }
                      }
                  });
        getModel().getPlayer().getView().createView(getGamePane());
        getModel().getBalls()
                  .addListener((ListChangeListener<? super Ball>) change -> {
                      if (change.next()) {
                          for (Ball ball : change.getAddedSubList()) {
                              ball.getView().createView(getGamePane());
                          }
                      }
                  });
        getModel().initialize();
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> getModel().leftKeyPressed();
            case RIGHT -> getModel().rightKeyPressed();
            case SPACE -> getModel().spaceKeyPressed();
//                if (getModel().isShowPauseMenu())
//                    if (!pause) {
//                        this.getGameTimer().stop();
//                        pause = true;
//                    }
//                    else {
//                        this.getGameTimer().start();
//                        pause = false;
//                    }
//            }
            case ESCAPE -> {
                getModel().escapeKeyPressed();
                getGamePane().getScene().getRoot().setEffect(new GaussianBlur());

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
}

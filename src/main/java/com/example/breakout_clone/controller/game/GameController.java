package com.example.breakout_clone.controller.game;

import com.example.breakout_clone.util.ImpactHandler;
import com.example.breakout_clone.util.ResourceHandler;
import com.example.breakout_clone.model.game.GameModel;
import com.example.breakout_clone.util.Impactable;
import com.example.breakout_clone.wrappers.ball.Ball;
import com.example.breakout_clone.wrappers.brick.Brick;
import com.example.breakout_clone.wrappers.powerup.PowerUp;
import com.example.breakout_clone.wrappers.powerup.PowerUpFactory;
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

/**
 * This class is the main controller for the game, which handles all the user
 * input when playing the game.
 * <p>
 * This class has been built from the GameFrame and the Wall class taking the
 * method that handles the inputs
 * <p>
 * This class is the controller to the GameView.fxml
 *
 * @author Praket Chavan - modified
 * @see GameModel
 */
public class GameController {

    /**
     * This constant defines the value that is awarded when a special brick
     * is broken
     */
    private static final int SPECIAL_BRICK_SCORE = 15;

    /**
     * This constant defines the value that is awarded when a normal brick
     * is broken
     */
    private static final int NORMAL_BRICK_SCORE = 10;

    public static final Duration ANIMATION_TIME = Duration.millis(3000);

    /**
     * This holds the GameModel object that the controller is connected to
     *
     * @see #setModel(GameModel)
     * @see #getModel()
     */
    private GameModel m_model;

    /**
     * Stores the reference of the main game Pane from the fxml view. This
     * object is effectively final.
     *
     * @see #getGamePane()
     */
    @FXML private Pane m_gamePane;

    /**
     * Stores the reference of the brick count Label from the fxml view. This
     * object is effectively final.
     *
     * @see #getBrickCount()
     */
    @FXML private Label m_brickCount;

    /**
     * Stores the reference of the score count label from the fxml view.
     * This
     * object is effectively final.
     *
     * @see #getScore()
     */
    @FXML private Label m_score;

    /**
     * Stores the reference of the ball count Label from the fxml view. This
     * object is effectively final.
     *
     * @see #getBallCount()
     */
    @FXML private Label m_ballCount;

    /**
     * Stores the reference of the level complete Label from the fxml view. This
     * object is effectively final.
     *
     * @see #getLevelComplete()
     */
    @FXML private Label m_levelComplete;

    /**
     * Stores the reference of the message Label from the fxml view. This
     * object is effectively final.
     *
     * @see #getMessageText() ()
     */
    @FXML private Label m_messageText;

    /**
     * Gets the ball count Label linked with the fxml view
     *
     * @return the Label object that shows the ball count
     */
    private Label getBallCount() {
        return m_ballCount;
    }

    /**
     * Gets the brick count Label linked with the fxml view
     *
     * @return the Label object that shows the brick count
     */
    private Label getBrickCount() {
        return m_brickCount;
    }

    /**
     * Gets the main game Pane linked with the fxml view
     *
     * @return the Pane object that is the main game area
     */
    private Pane getGamePane() {
        return m_gamePane;
    }

    /**
     * Gets the level complete Label linked with fxml view
     *
     * @return the Label object that displays the LEVEL COMPLETE message
     */
    private Label getLevelComplete() {
        return m_levelComplete;
    }

    /**
     * Gets the message Label linked with the fxml view
     *
     * @return the Label object that shows the message
     */
    private Label getMessageText() {
        return m_messageText;
    }

    /**
     * Gets the GameModel object that the controller is currently linked with
     *
     * @return the GameModel object
     * @see #setModel(GameModel)
     */
    private GameModel getModel() {
        return m_model;
    }

    /**
     * Sets the GameModel object that the controller is linked with to the
     * passed parameter
     *
     * @param model the GameModel object that is to be linked with the
     *              controller
     */
    private void setModel(GameModel model) {
        m_model = model;
    }

    /**
     * Gets the score Label linked with the fxml view
     *
     * @return the Label object that shows the game score
     */
    private Label getScore() {
        return m_score;
    }

    /**
     * Runs an iteration of the game loop.
     * It moves the sprites on the screen by calling the {@link GameModel#move()}
     * models move method.
     *<br>
     * It handles the impacts by calling the
     * {@link ImpactHandler#handleImpacts()} method of the model ImpactHandler.
     *
     * It calls checks for whether the ball has been lost and calls the
     * appropriate methods.
     *
     * It checks if the player has finished the level and calls the
     * appropriate methods.
     *
     * @see #onGameOver()
     * @see #onGameComplete()
     */
    private void gameTimer() {
        getModel().move();
        int impact = getModel().getImpactHandler().handleImpacts();
        //Check to see if a normal or special brick is broken
        if (impact == ImpactHandler.SPECIAL_BRICK_BROKEN) {
            getModel().createPowerUp(new PowerUpFactory().createPowerUP());
            getModel().addScore(SPECIAL_BRICK_SCORE);
        }
        else if (impact == ImpactHandler.NORMAL_BRICK_BROKEN)
            getModel().addScore(NORMAL_BRICK_SCORE);
        getModel().updateBrickCount();//Update the brick count every iteration
        if (getModel().isBallLost()) {
            getModel().onBallLost();
            if (getModel().getBallCount() <= 0)
                onGameOver();
        } else if (getModel().islevelComplete()) {
            if (getModel().hasNextLevel()) {
                getModel().gameReset();
                nextLevel();
            } else //If no Levels are left then the game has been completed
                onGameComplete();
        }
    }

    /**
     * This method is called automatically when the view is created.
     * It initialises the model object and created the AnimationTimer that
     * runs the gameLoop.
     * <br>
     * This calls methods the {@link #initializeBinding()} method to
     * initialise the binding between the view objects and the respective
     * model property.
     * <br>
     * Calls the {@link #initializeListener()} to add listeners to some model
     * list properties.
     */
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

    /**
     * Binds the view objects with the respective model properties to
     * allow the view to listen to changes to the model and update itself.
     * @see #initialize()
     */
    private void initializeBinding() {
        //Bind the ball count label to the ball count property in the model
        getBallCount().textProperty().bind(getModel().ballCountProperty()
                                                     .asString());
        //Bind the score label to the score count property
        getScore().textProperty().bind(getModel().scoreCountProperty()
                                                 .asString());
        //Bind the brick count label to the brick count property in the model
        getBrickCount().textProperty().bind(getModel().brickCountProperty()
                                                      .asString());
        //Binds the message Label to the message property in the model.
        getMessageText().textProperty().bind(getModel().messageProperty());

        getModel().focusProperty().bind(getGamePane().focusedProperty());

    }

    /**
     * Adds the change listeners to observable list in the model.
     * @see #initialize()
     */
    private void initializeListener() {
        /*
        Add listener to the focused property of the model and calls the
        respective method
         */
        getGamePane().focusedProperty()
                     .addListener((observableValue, aBoolean, t1) -> {
                         if (t1)
                             getModel().onFocusGain();
                         else
                             getModel().onFocusLost();
                     });

        /*
        Adds change listener to the different sprite observable list and for
        each sprite object added, create its view; and for each sprite object
        removed from the list also remove it from the gamePane
         */
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
                                      getModel().getBrickCount() - 1);
                          }
                      }
                  });

        getModel().getBalls()
                  .addListener((ListChangeListener<? super Ball>) change -> {
                      if (change.next()) {
                          for (Ball ball : change.getAddedSubList()) {
                              ball.getView().createView(getGamePane());
                          }
                          for (Ball ball : change.getRemoved()) {
                              getGamePane().getChildren().remove(ball.getView().getView());
                          }
                      }
                  });
        /*For each item removed from the impactHandler's powerUp list remove
         the wrapper object associated with the powerUp from the powerUp
         list in the model*/
        getModel().getImpactHandler().getPowerUp().addListener(
                (ListChangeListener<? super Impactable>) change -> {
                        if(change.next())
                        getModel().getPowerUps().removeAll(
                        getModel().getPowerUps().stream().filter(
                                powerUp -> change.getRemoved().contains(
                                        powerUp.getModel())
                                  ).toList());});
    }

    /**
     * Checks to see if the model has any more levels. If it does then stops
     * the level music and plays the next level music and starts the next level.
     * <br>
     * Also starts the animation for the LevelComplete Label
     *
     * @see GameModel#hasNextLevel()
     * @see GameModel#nextLevel()
     */
    private void nextLevel() {
        getLevelComplete().setVisible(true);
        FadeTransition completeAnimation =
                new FadeTransition(ANIMATION_TIME, getLevelComplete());
        completeAnimation.setToValue(0);
        completeAnimation.setFromValue(1);
        completeAnimation.play();
        if (getModel().getLevelSound() != null)
            getModel().getLevelSound().stop();
        Media media = new Media(ResourceHandler.getSoundResource(
                "Phase " + getModel().getLevelNumber() + ".mp3"));
        getModel().setLevelSound(new MediaPlayer(media));
        getModel().nextLevel();
        getModel().getLevelSound().play();
        getModel().getLevelSound().setCycleCount(MediaPlayer.INDEFINITE);
    }

    /**
     * Creates the GameCompleteView and replaces the GameView scene with it
     */
    private void onGameComplete() {
        getModel().stopTimer();
        FXMLLoader root = new FXMLLoader(getClass().getResource(
                "/view/GameCompleteView.fxml"));
        try {
            Scene scene = new Scene(root.load());
            ((Stage) getGamePane().getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the GameOverView and replaces the GameView scene with it
     */
    private void onGameOver() {
        getModel().stopTimer();
        FXMLLoader root = new FXMLLoader(getClass().getResource(
                "/view/GameOverView.fxml"));
        try {
            Scene scene = new Scene(root.load());
            ((Stage) getGamePane().getScene().getWindow()).setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Listens to the user input when the game timer starts and calls the
     * method from the model depending on the input
     * @param event the key event that the user inputted
     */
    @FXML
    private void onKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT -> getModel().leftKeyPressed();
            case RIGHT -> getModel().rightKeyPressed();
            case SPACE -> getModel().spaceKeyPressed();
            case ESCAPE -> { //Pause menu is created
                getModel().escapeKeyPressed();
                getGamePane().getScene().getRoot()
                             .setEffect(new GaussianBlur());

                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                popupStage.initOwner(getGamePane().getScene().getWindow());
                popupStage.initModality(Modality.APPLICATION_MODAL);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/view/PauseView.fxml"));
                try {
                    popupStage.setScene(
                            new Scene(loader.load(), Color.TRANSPARENT));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                popupStage.show();
            }
            case F1 -> { //create the debug window if the shift and alt is down
                getModel().f1KeyPressed(event);

                if (event.isAltDown() && event.isShiftDown()) {
                    Stage stage = new Stage();
                    FXMLLoader root;
//                    Parent root = null;
                    try {
                        root = new FXMLLoader(getClass().getResource(
                                "/view/DebugView.fxml"));
                        stage.setScene(new Scene(root.load()));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Listens for when the user stops pressing a key and stops the player
     * @param event the KeyEvent inputted by the user
     */
    @FXML
    private void onKeyReleased(KeyEvent event) {
        getModel().stopPlayer();
    }
}
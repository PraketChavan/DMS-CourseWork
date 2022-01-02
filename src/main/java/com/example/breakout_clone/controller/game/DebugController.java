package com.example.breakout_clone.controller.game;

import com.example.breakout_clone.util.ResourceHandler;
import com.example.breakout_clone.model.game.GameModel;
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

/**
 * The controller class for the debug window. This class is instantiated
 * automatically when the debug view is created.
 *
 * The class has been renamed from DebugPanel to DebugController to easily
 * identify controller classes.
 *
 * @author Praket Chavan - modified
 */
public class DebugController {

    /**
     * Stores the model that the controller is connected to.
     * @see #getModel()
     * @see #setModel(GameModel)
     */
    private GameModel m_Model;

    /**
     * Stores the reference to the Reset Ball button from the fxml view
     * @see #getResetBalls()
     */
    @FXML
    private Button m_ResetBalls;

    /**
     Stores the reference to the Skip Level button from the fxml view
     * @see #getSkipLevel() ()
     */
    @FXML
    private Button m_SkipLevel;

    /**
     * Stores the reference to the Ball X Speed Slider from the fxml view
     * @see #getBallXSpeed()
     */
    @FXML
    private Slider m_BallXSpeed;

    /**
     * Stores the reference to the Ball Y Speed Slider from the fxml view
     * @see #getBallYSpeed()
     */
    @FXML
    private Slider m_BallYSpeed;

    /**
     * Stores the reference of the Complete Game button from the fxml view
     * @see #getCompleteGame()
     */
    @FXML
    private Button m_CompleteGame;

    /**
     * Gets the Ball X Speed slider associated to the fxml view
     * @return the Slider object
     */
    private Slider getBallXSpeed() {
        return m_BallXSpeed;
    }

    /**
     * Gets the Ball Y Speed Slider object associated to the fxml view
     * @return the Slider object
     */
    private Slider getBallYSpeed() {
        return m_BallYSpeed;
    }

    /**
     * Gets the Complete Game button object associated with the view
     * @return the Button object
     */
    private Button getCompleteGame() {
        return m_CompleteGame;
    }

    /**
     * Gets the current GameModel object associated with the controller
     * @return the GameModel object
     * @see #setModel(GameModel)
     */
    private GameModel getModel() {
        return m_Model;
    }

    /**
     * Set the current GameModel object to the parameter passed
     *
     * @param model The GameModel object that is to be linked to this controller
     *              It cannot be null.
     * @throws Exception if the model object passed is null
     * @see #getModel()
     */
    private void setModel(GameModel model) throws Exception{
        if (model == null)
            throw new Exception("Controller cannot link to a null model");
        this.m_Model = model;
    }

    /**
     * Gets the Reset Ball button object associated with the view
     * @return the Button object
     */
    public Button getResetBalls() {
        return m_ResetBalls;
    }

    /**
     * Gets the Skip Level button object associated with the view
     * @return the Button object
     */
    public Button getSkipLevel() {
        return m_SkipLevel;
    }

    /**
     * This method is called automatically when the view is created.
     * It initialises the model object, and adds the listeners to the Slider
     * objects from the view
     */
    @FXML
    private void initialize() {
        try {
            setModel(GameModel.getGameInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        getBallYSpeed().valueProperty()
                       .addListener(((observableValue, number, t1) ->
                               setBallYSpeed()));
        getBallXSpeed().valueProperty()
                       .addListener(((observableValue, number, t1) ->
                               setBallXSpeed()));

    }

    /**
     * This method is called when the Complete Game button is pressed.
     * It creates the GameEndView and finished the game.
     * @see com.example.breakout_clone.controller.game.GameEndController
     */
    @FXML
    private void onCompleteClick() {
        getCompleteGame().getScene().getWindow().hide();
        getModel().stopTimer();
        FXMLLoader root = new FXMLLoader(getClass().getResource(
                "/view/GameCompleteView.fxml"));
        try {
            Scene scene = new Scene(root.load());
            Stage.getWindows().stream().filter(Window::isShowing)
                 .forEach(stage -> ((Stage) stage).setScene(scene));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called when the Reset Ball Button is pressed.
     * It calls the gameReset method of the linked GameModel
     */
    @FXML
    private void resetBall() {
        getModel().gameReset();
    }

    /**
     * This method is called when there is a change in the Ball X Speed
     * slider value.
     * It calls the setBallXSpeed method of the linked model and passes the
     * new slider value as an argument
     *
     * @see GameModel#setBallXSpeed(double)
     */
    @FXML
    private void setBallXSpeed() {
        getModel().setBallXSpeed((int) getBallXSpeed().getValue());
    }

    /**
     *This method is called when there is a change in the Ball Y Speed
     *slider value.
     *It calls the setBallYSpeed method of the linked model and passes the
     *new slider value as an argument
     *
     *@see GameModel#setBallYSpeed(double)
     */
    @FXML
    private void setBallYSpeed() {
        getModel().setBallYSpeed((int) getBallYSpeed().getValue());
    }

    /**
     * This method is called when the SkipLevel Button is pressed
     * It only skips the level if there are more level to be played. If no
     * more levels are available it does nothing.
     * If more levels are available it calls the nextLevel method of the
     * model and also changes the game level music to the next track
     *
     * @see GameModel#nextLevel()
     * @see GameModel#getLevelSound()
     * @see GameModel#setLevelSound(MediaPlayer)
     * @see ResourceHandler#getSoundResource(String)
     */
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

package com.example.breakout_clone.controller.game;

import com.example.breakout_clone.model.game.GameModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Controller class for the PauseMenuView view. It creates the GameModel - View
 * connection (binding) and created the controller - model connection
 * <br>
 * It is responsible for handling the user inputs when the pause menu is showing
 *<br>
 * Some method has been extracted from the old GameFrame class, to provide
 * abstraction and adhere to single responsibility
 * @author Praket Chavan
 * @see GameModel
 */
public class PauseMenuController {

    /**
     * The exit Button that is linked to the fxml view
     * @see #getExit()
     */
    @FXML private Button m_Exit;

    /**
     * The continue Button that is linked to the fxml view
     * @see #getContinue()
     */
    @FXML private Button m_Continue;

    /**
     * The restart Button that is linked to the fxml view
     * @see #getRestart()
     */
    @FXML private Button m_Restart;

    /**
     * The {@link GameModel} instance connected to the controller
     * @see #getModel()
     * @see #setModel(GameModel)
     */
    private GameModel m_Model;

    /**
     * Gets the continue Button object
     * @return
     */
    private Button getContinue() {
        return m_Continue;
    }

    /**
     * Gets the exit Button object
     * @return
     */
    public Button getExit() {
        return m_Exit;
    }

    /**
     * Gets the GameModel object connected to the controller
     * @return the GameModel instance
     */
    private GameModel getModel() {
        return m_Model;
    }

    /**
     * Sets the m_Model member to the model parameter passed
     * @param model the new game model that the controller will be linked to
     */
    private void setModel(GameModel model) {
        m_Model = model;
    }

    /**
     * Gets the restart Button object
     * @return
     */
    public Button getRestart() {
        return m_Restart;
    }

    /**
     * This method is called automatically when the view is created
     * Initializes the connection between model and the controller
     */
    @FXML
    private void initialize() {
        /*gets the singleton GameModel
         instance*/
        setModel(GameModel.getGameInstance());
    }

    /**
     * This method is call when the continue button is clicked.
     * Hides the pause menu window and calls the
     * {@link GameModel#onContinueClick()} method
     *
     */
    @FXML
    private void onContinueClick() {
        getContinue().getScene().getWindow().hide();
        getModel().onContinueClick();

    }

    /**
     * Method is called when the exit button is clicked. Calls the
     * {@link GameModel#onExitClick()}
     */
    @FXML
    private void onExitClick() {
        getModel().onExitClick();
    }

    /**
     * Method is called when the exit button is clicked. Calls the
     * {@link GameModel#onRestartClick()}
     */
    @FXML
    private void onRestartClick() {
        getModel().onRestartClick();
    }

}

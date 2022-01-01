package com.example.migrateFx.controller.game;

import com.example.migrateFx.model.game.GameModel;
import com.example.migrateFx.util.HighScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Controller class for the GameOverView and the GameCompleteView. It is
 * responsible for handling the user inputs and creating the binding between
 * the model and view.
 *
 * @author Praket Chavan
 * @see GameModel
 */
public class GameEndController {
    /**
     * The list to store the high score value
     * @see #getList()
     */
    private final ObservableList<HighScore> m_list
            = FXCollections.observableArrayList();

    /**
     * Properties object that is used to read the highscore.properties file
     * @see #getHighScoreFile()
     */
    private final Properties m_highScoreFile = new Properties();

    /**
     * TableView object that is linked to the fxml view
     * @see #getHighScore()
     */
    @FXML private TableView<HighScore> m_HighScore;

    /**
     * The Restart Button object is linked to the fxml view
     * @see #getRestart()
     */
    @FXML private Button m_Restart;

    /**
     * The exit Button object is linked to the fxml view
     * @see #getExit()
     */
    @FXML private Button m_Exit;

    /**
     * The submit Button linked to the fxml view
     * @see #getSubmit()
     */
    @FXML private Button m_Submit;

    /**
     * The user-name TextField that is linked to the fxml view
     * @see #getName()
     */
    @FXML private TextField m_Name;

    /**
     * The name column in the table view linked to the fxml view
     * @see #getPlayerName()
     */
    @FXML private TableColumn<HighScore, String> m_PlayerName;

    /**
     * The score column in the table view linked to the fxml view
     * @see #getPlayerScore()
     */
    @FXML private TableColumn<HighScore, Integer> m_PlayerScore;

    /**
     * The GameModel that the controller is linked to
     * @see #getModel()
     * @see #setModel(GameModel)
     */
    private GameModel m_model;

    /**
     * Gets the exit Button object
     * @return the Button object
     */
    public Button getExit() {
        return m_Exit;
    }

    /**
     * Gets the Table View object that is in the fxml view
     * @return TableView object
     */
    private TableView<HighScore> getHighScore() {
        return m_HighScore;
    }

    /**
     * Gets the properties object
     * @return properties object
     */
    private Properties getHighScoreFile() {
        return m_highScoreFile;
    }

    /**
     * Gets the list containing the high scores
     * @return ObservableList object
     */
    private ObservableList<HighScore> getList() {
        return m_list;
    }

    /**
     * Gets the GameModel that is linked with the controller
     * @return the GameModel object
     * @see #setModel(GameModel)
     */
    private GameModel getModel() {
        return m_model;
    }

    /**
     * Sets the m_model member variable to the GameModel object passed
     * @param model the GameMobel instance to be linked with the controller
     * @see #getModel()
     */
    private void setModel(GameModel model) {
        m_model = model;
    }

    /**
     * Gets the name TextField
     * @return
     */
    private TextField getName() {
        return m_Name;
    }

    /**
     * Gets the player name TableColumn in the high score table
     * @return
     */
    private TableColumn<HighScore, String> getPlayerName() {
        return m_PlayerName;
    }

    /**
     * Gets the player score TableColumn in the high score table
     * @return
     */
    private TableColumn<HighScore, Integer> getPlayerScore() {
        return m_PlayerScore;
    }

    /**
     * Gets the restart Button linked in the fxml view
     * @return
     */
    public Button getRestart() {
        return m_Restart;
    }

    /**
     * Gets the submit Button linked in the fxml view
     * @return
     */
    public Button getSubmit() {
        return m_Submit;
    }

    /**
     * This method is called automatically whenever the GameOverView or the
     * GameCompleteView is created.<br> It links the GameModel instance with
     * the
     * controller and the initialises the connection between the view and the
     * model
     */
    @FXML
    private void initialize() {
        setModel(GameModel.getGameInstance());
        /*Load in the highscore.properties file to read in the previous high
         scores*/
        try {
            FileInputStream file = new FileInputStream(
                    getClass().getResource("/highscore.properties").getFile());
            getHighScoreFile().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readScores();
        //sort the highscore list in ascending order
        getList().sort((o1, o2)->Integer.compare(o2.getScore(), o1.getScore()));

        //connect the highScore list to the table view
        getHighScore().setItems(getList());
        //create the cell factory for the column in the table
        getPlayerName().setCellValueFactory(new PropertyValueFactory<>("name"));
        getPlayerScore().setCellValueFactory(
                new PropertyValueFactory<>("score"));
    }

    /**
     * Call the {@link GameModel#onExitClick()} method when the exit button
     * is pressed
     */
    @FXML
    private void onExitClick() {
        getModel().onExitClick();
    }

    /**
     * Call the {@link GameModel#onRestartClick()} method when the exit button
     * is pressed
     */
    @FXML
    private void onRestartClick() {
        getModel().onRestartClick();
    }

    /**
     * Creates a new HighScore object by reading in the name from the
     * TextField and the current game score and add a new entry in the
     * properties file and saves it.
     */
    @FXML
    private void onSubmitClick() {
        try {
            String text;
            FileOutputStream outputStream = new FileOutputStream(
                    getClass().getClassLoader()
                              .getResource("highscore.properties")
                              .getPath());
            getHighScoreFile().setProperty(getName().getText(),
                                           "" + getModel().getScoreCount());
            getHighScoreFile().store(outputStream, null);
            text = getName().getText();
            //Check to see if the player has entered a name or not
            text = text.compareTo("") == 0 ? "New Player" : text;
            getList().add(new HighScore(text, getModel().getScoreCount()));
            getList().sort(
                    (o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Reads the key, value pair from the highscore.properties file and adds
     * the key, value as a new HighScore object in the high score list.
     */
    private void readScores() {
        getHighScoreFile().forEach((k, v) -> getList().add(
                new HighScore((String) k, Integer.valueOf((String) v))));
    }
}

package com.example.breakout_clone.controller.game;

import com.example.breakout_clone.util.HighScore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Controller class for the HighScoreView. It is responsible for initializing
 * the connection between the view and controller.
 * @author Praket Chavan
 * @see HighScore
 */
public class HighScoreController {
    /**
     * List for storing the high scores read from the highscore.properties file
     * @see #getList()
     */
    private final ObservableList<HighScore> m_list
            = FXCollections.observableArrayList();

    /**
     * The Properties object that will be used to read the
     * highscore.properties file
     * @see #getProperties()
     */
    private final Properties m_properties = new Properties();

    /**
     * The table that will display the high scores
     * @see #getHighScoreList()
     */
    @FXML private TableView<HighScore> m_highScoreList;

    /**
     * The column for the player name in the high score table
     * @see #getName()
     */
    @FXML private TableColumn<HighScore, String> m_Name;

    /**
     * The column for the player score in the high score table
     * @see #getScore()
     */
    @FXML private TableColumn<HighScore, Integer> m_Score;

    /**
     * Gets the TableView object that is linked with the fxml view
     * @return TableView object
     */
    private TableView<HighScore> getHighScoreList() {
        return m_highScoreList;
    }

    /**
     * Gets the list storing the high score that is read from the file
     * @return ObservableList containing the high scores
     */
    private ObservableList<HighScore> getList() {
        return m_list;
    }

    /**
     * Gets the TableColumn containing the player name that is linked in the
     * fxml
     * view
     * @return TableColumn object
     */
    private TableColumn<HighScore, String> getName() {
        return m_Name;
    }

    /**
     * Gets the Properties object that is used to read the .properties file
     * @return Properties object
     */
    private Properties getProperties() {
        return m_properties;
    }

    /**
     * Gets the TableColumn containing the player score that is linked in the
     * fxml view
     * @return TableView object
     */
    private TableColumn<HighScore, Integer> getScore() {
        return m_Score;
    }

    /**
     * This method is called automatically when the HighScore view is created.
     * Initialises the {@link FileInputStream} that is used to read the
     * highscore.properties file and is loaded using the properties
     * @see #readScores()
     */
    @FXML
    private void initialize() {
        try {
            FileInputStream file = new FileInputStream(
                    getClass().getResource("/properties/highscore.properties").getFile());
            getProperties().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readScores(); //read in the highscores
        //sort the list in ascending order
        getList().sort(
                (o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
        getHighScoreList().setItems(getList());
        getName().setCellValueFactory(new PropertyValueFactory<>("name"));
        getScore().setCellValueFactory(new PropertyValueFactory<>("score"));
    }

    /**
     * Reads all the key, value pairs from the .properties file create new
     * HighScore objects and add it to the high score list
     *
     * @see HighScore
     */
    private void readScores() {
        getProperties().forEach((k, v) -> getList().add(
                new HighScore((String) k,
                              Integer.valueOf((String) v))));
    }
}

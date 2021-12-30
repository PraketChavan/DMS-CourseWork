package com.example.migrateFx.controller;

import com.example.migrateFx.model.GameModel;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import java.util.Comparator;
import java.util.Properties;

public class GameEndController {
    private final ObservableList<HighScore> m_list
            = FXCollections.observableArrayList();
    private final Properties m_highScoreFile = new Properties();
    @FXML private TableView<HighScore> m_HighScore;
    @FXML private Button m_Restart;
    @FXML private Button m_Exit;
    @FXML private Button m_Submit;
    @FXML private TextField m_Name;
    @FXML private TableColumn<HighScore, String> m_PlayerName;
    @FXML private TableColumn<HighScore, Integer> m_PlayerScore;

    public TableColumn<HighScore, String> getPlayerName() {
        return m_PlayerName;
    }

    public TableColumn<HighScore, Integer> getPlayerScore() {
        return m_PlayerScore;
    }

    private GameModel m_model;

    public Button getExit() {
        return m_Exit;
    }

    public TableView<HighScore> getHighScore() {
        return m_HighScore;
    }

    public Properties getHighScoreFile() {
        return m_highScoreFile;
    }

    public ObservableList<HighScore> getList() {
        return m_list;
    }

    public GameModel getModel() {
        return m_model;
    }

    public void setModel(GameModel model) {
        m_model = model;
    }

    public TextField getName() {
        return m_Name;
    }

    public Button getRestart() {
        return m_Restart;
    }

    public Button getSubmit() {
        return m_Submit;
    }

    @FXML
    private void initialize() {
        setModel(GameModel.getGameInstance());
        try {
            FileInputStream file = new FileInputStream(
                    getClass().getResource("/highscore.properties").getFile());
            getHighScoreFile().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readScores();
        getList().sort((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
        getHighScore().setItems(getList());
        getPlayerName().setCellValueFactory(new PropertyValueFactory<>("name"));
        getPlayerScore().setCellValueFactory(new PropertyValueFactory<>("score"));


    }

    @FXML
    private void onExitClick() {
        getModel().onExitClick();
    }

    @FXML
    private void onRestartClick() {
        getModel().onRestartClick();
    }

    @FXML
    private void onSubmitClick() {
        try {
            FileOutputStream outputStream = new FileOutputStream(
                    getClass().getClassLoader()
                              .getResource("highscore.properties")
                              .getPath());
            getHighScoreFile().setProperty(getName().getText(),"" + getModel().getScoreCount());
            getHighScoreFile().store(outputStream, null);
            getList().add(new HighScore(getName().getText(), getModel().getScoreCount()));
            getList().sort((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readScores() {
        getHighScoreFile().forEach((k, v) -> getList().add(
                new HighScore((String) k, Integer.valueOf((String) v))));
    }

    public static class HighScore {
        private final SimpleStringProperty m_Name;
        private final SimpleIntegerProperty m_Score;

        public String getName() {
            return m_Name.get();
        }

        public void setName(String name) {
            this.m_Name.set(name);
        }

        public int getScore() {
            return m_Score.get();
        }

        public void setScore(int score) {
            this.m_Score.set(score);
        }

        HighScore(String name,
                  Integer score) {
            m_Name = new SimpleStringProperty(name);
            m_Score = new SimpleIntegerProperty(score);
        }

        public SimpleStringProperty nameProperty() {
            return m_Name;
        }

        public SimpleIntegerProperty scoreProperty() {
            return m_Score;
        }
    }
}

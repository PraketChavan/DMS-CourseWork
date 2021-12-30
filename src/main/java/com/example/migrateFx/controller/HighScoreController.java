package com.example.migrateFx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HighScoreController {
    private final ObservableList<GameEndController.HighScore> m_list
            = FXCollections.observableArrayList();
    private final Properties m_properties = new Properties();
    @FXML private TableView<GameEndController.HighScore> m_highScoreList;
    @FXML private TableColumn<GameEndController.HighScore, String> m_Name;
    @FXML private TableColumn<GameEndController.HighScore, Integer> m_Score;

    public ObservableList<GameEndController.HighScore> getList() {
        return m_list;
    }

    public Properties getProperties() {
        return m_properties;
    }

    public TableView<GameEndController.HighScore> getHighScoreList() {
        return m_highScoreList;
    }

    public TableColumn<GameEndController.HighScore, String> getName() {
        return m_Name;
    }

    public TableColumn<GameEndController.HighScore, Integer> getScore() {
        return m_Score;
    }

    @FXML
    private void initialize() {
        try {
            FileInputStream file = new FileInputStream(
                    getClass().getResource("/highscore.properties").getFile());
            getProperties().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        readScores();
        getList().sort((o1, o2) -> Integer.compare(o2.getScore(), o1.getScore()));
        getHighScoreList().setItems(getList());
        getName().setCellValueFactory(new PropertyValueFactory<>("name"));
        getScore().setCellValueFactory(new PropertyValueFactory<>("score"));
//        getHighScoreList().getScene().getWindow().setOnCloseRequest(
//                windowEvent -> ((Stage) getHighScoreList()
//                        .getScene().getWindow())
//                        .getOwner().getScene().getRoot().setEffect(null));
    }

    private void readScores() {
        getProperties().forEach((k, v) -> getList().add(
                new GameEndController.HighScore((String) k, Integer.valueOf((String) v))));
    }
}

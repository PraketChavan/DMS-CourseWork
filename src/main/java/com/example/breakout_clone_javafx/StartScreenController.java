package com.example.breakout_clone_javafx;


import com.example.breakout_clone.GameFrame;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

public class StartScreenController {
    private final String DARKTHEME = StartScreen.class.getResource(
            "css.theme/darkTheme.css").toExternalForm();
    @FXML
    private ListView<String> m_HighScoreList;
    @FXML
    private Button m_StartButton;
    @FXML
    private ToggleButton m_DarkModeToggle;
    private ObservableList<String> m_observableList;

    public ToggleButton getDarkModeToggle() {
        return m_DarkModeToggle;
    }

    public void setDarkModeToggle(ToggleButton darkModeToggle) {
        m_DarkModeToggle = darkModeToggle;
    }

    public ListView<String> getHighScoreList() {
        return m_HighScoreList;
    }

    public void setHighScoreList(ListView<String> highScoreList) {
        m_HighScoreList = highScoreList;
    }

    public ObservableList<String> getObservableList() {
        return m_observableList;
    }

    public void setObservableList(ObservableList<String> observableList) {
        m_observableList = observableList;
    }

    public Button getStartButton() {
        return m_StartButton;
    }

    public void setStartButton(Button startButton) {
        m_StartButton = startButton;
    }

    @FXML
    public void initialize() {
        setObservableList(FXCollections.observableArrayList());
        initialiseHighScore();
    }

    private void initialiseHighScore() {
        getHighScoreList().setItems(getObservableList());
    }

    @FXML
    private void onButtonClick() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(
                StartScreen.class.getResource("GameFrame.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //SwingUtilities.invokeLater(GameFrame::new);
        Stage stage = (Stage) getStartButton().getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    private void onDarkToggle() {
        Scene scene = getDarkModeToggle().getScene();
        if (getDarkModeToggle().isSelected()) {
            scene.getStylesheets().add(DARKTHEME);
        } else {
            scene.getStylesheets().remove(DARKTHEME);
        }

    }
}
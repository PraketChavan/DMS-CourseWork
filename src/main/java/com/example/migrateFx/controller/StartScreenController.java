package com.example.migrateFx.controller;

import com.example.migrateFx.handler.ResourceHandler;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.media.Media;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class StartScreenController {
    private final Properties m_properties = new Properties();
    private final FileInputStream file;
    @FXML private Label m_title;
    @FXML private ChoiceBox<String> m_themeChoice;
    @FXML private Button m_start;
    @FXML private Button m_highScore;
    private MediaPlayer m_intoSound;

    public Button getHighScore() {
        return m_highScore;
    }

    public MediaPlayer getIntoSound() {
        return m_intoSound;
    }

    public Properties getProperties() {
        return m_properties;
    }

    public Button getStart() {
        return m_start;
    }

    public ChoiceBox<String> getThemeChoice() {
        return m_themeChoice;
    }

    public Label getTitle() {
        return m_title;
    }

    public StartScreenController() {
        FileInputStream file1;
        try {
            file1 = new FileInputStream(
                    getClass().getResource("/theme.properties").getFile());
        } catch (FileNotFoundException e) {
            file1 = null;
            e.printStackTrace();
        }
        file = file1;
    }

    public void setIntoSound(MediaPlayer intoSound) {
        m_intoSound = intoSound;
    }

    @FXML
    private void initialize() {
        Media welcomeSound = new Media(
                ResourceHandler.getSoundResource("Intro Theme.mp3"));
        setIntoSound(new MediaPlayer(welcomeSound));
        getIntoSound().play();
        try {
            getProperties().load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        getProperties().setProperty("theme", "blue");
        getStart().getParent().getParent().setLayoutY(500);
        TranslateTransition translateTransition = new TranslateTransition(
                Duration.millis(5000), getStart().getParent().getParent());
        translateTransition.setByY(-500);
        FadeTransition fadeTransition = new FadeTransition(
                Duration.millis((5000)), getStart().getParent().getParent());
        fadeTransition.setFromValue(0.01);
        fadeTransition.setToValue(1);
        fadeTransition.play();
        translateTransition.play();
    }

    @FXML
    private void onHighScoreClick() {
//        getStart().getScene().getRoot().setEffect(new GaussianBlur());

        Stage popupStage = new Stage();
        popupStage.initOwner(getStart().getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/migrateFx/HighScoreView.fxml"));
        try {
            popupStage.setScene(
                    new Scene(loader.load(), Color.TRANSPARENT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        popupStage.show();
    }

    @FXML
    private void onStartClick() {
        try {
            getProperties().store(new FileOutputStream(
                    getClass().getResource("/theme.properties")
                              .getFile()), null);

            FXMLLoader root = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/migrateFx/GameView.fxml"));
            Scene scene = new Scene(root.load());
            ((Stage)getThemeChoice().getScene().getWindow()).setScene(scene);
            scene.getRoot().requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getIntoSound().stop();

    }

    @FXML
    private void onThemeSelected() {
        getProperties().setProperty("theme", getThemeChoice().getValue());
    }
}

package com.example.breakout_clone.controller.game;

import com.example.breakout_clone.util.ResourceHandler;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Controller class for the StartScreenView.fxml. It sets up the connection
 * and binds GameModel attributes to the View. It is also responsible
 * for handling the user inputs and setting the games color theme.
 *
 * @author Praket Chavan
 */
public class StartScreenController {
    /**
     * Constant for the Starting point of the screen animation
     */
    private static final int START_SCREEN_OFFSET = 500;

    /**
     * Constant to define the duration of the animation in milliseconds
     */
    private static final int ANIMATION_DURATION = 5000;

    /**
     * Constant to define the starting opacity for the fading animation
     */
    private static final double FADE_START_VALUE = 0.1;

    /**
     * Properties object that is used to load and write to the
     * theme.properties file
     *
     * @see #getProperties()
     */
    private final Properties m_properties = new Properties();

    /**
     * FileInputStream object used to read the theme.properties file
     *
     * @see #getFile()
     */
    private final FileInputStream file;

    /**
     * Game Title Label object that is linked to the fxml file
     *
     * @see #getTitle()
     */
    @FXML private Label m_title;

    /**
     * Theme ChoiceBox object that is linked to the fxml file
     *
     * @see #getThemeChoice()
     */
    @FXML private ChoiceBox<String> m_themeChoice;

    /**
     * Start Button object that is linked to the fxml file
     *
     * @see #getStart()
     */
    @FXML private Button m_start;

    /**
     * Highscore Button object that is linked to the fxml file
     *
     * @see #getHighScore()
     */
    @FXML private Button m_highScore;

    /**
     * MediaPlayer objects that is used to play the game into song
     * @see #getIntroSound()
     * @see #setIntroSound(MediaPlayer)
     */
    private MediaPlayer m_introSound;

    /**
     * Gets the FileInputStream that is reading the theme.properties file
     * @return FileInputStream object
     */
    private FileInputStream getFile() {
        return file;
    }

    /**
     * Gets the high score Button object
     *
     * @return Button object
     */
    public Button getHighScore() {
        return m_highScore;
    }

    /**
     * Gets the Media player object used to play the intro sound for the game
     *
     * @return MediaPlayer object
     */
    private MediaPlayer getIntroSound() {
        return m_introSound;
    }

    /**
     * Sets the MediaPlayer object that is used to play the intro sound
     *
     * @param introSound the MediaPlayer object that need to be set to
     */
    private void setIntroSound(MediaPlayer introSound) {
        m_introSound = introSound;
    }

    /**
     * Gets the Properties object that has been initialised
     * @return Properties object
     */
    private Properties getProperties() {
        return m_properties;
    }

    /**
     * Gets the start Button from the view
     * @return Button object
     */
    private Button getStart() {
        return m_start;
    }

    /**
     * Gets the ChoiceBox object from the view
     * @return ChoiceBox object
     */
    private ChoiceBox<String> getThemeChoice() {
        return m_themeChoice;
    }

    /**
     * Gets the get title Label object
     * @return Label object
     */
    public Label getTitle() {
        return m_title;
    }

    /**
     * Constructor to initialise the FileInputStream to read the
     * theme.properties file.
     * <br>
     * The constructor is called automatically when the StartScreenView is
     * created.
     */
    public StartScreenController() {
        FileInputStream file1;
        try {
            file1 = new FileInputStream(
                    getClass().getResource("/properties/theme.properties").getFile());
        } catch (FileNotFoundException e) {
            file1 = null;
            e.printStackTrace();
        }
        file = file1;
    }

    /**
     * This method is called automatically when the StartScreenView is
     * created. It initialises the MediaPlayer with the sound resource and
     * loads in the properties file.
     *<br>
     * Creates and starts the screen translate and fade animation for the
     * into screen
     * @see FadeTransition
     * @see TranslateTransition
     */
    @FXML
    private void initialize() {
        Media welcomeSound = new Media(
                ResourceHandler.getSoundResource("Intro Theme.mp3"));
        setIntroSound(new MediaPlayer(welcomeSound));
        getIntroSound().play();
        try {
            getProperties().load(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //sets the default theme to be blue
        getProperties().setProperty("theme", "blue");

        //Create the translate animation for the start screen
        getStart().getParent().getParent().setLayoutY(START_SCREEN_OFFSET);
        TranslateTransition translateTransition = new TranslateTransition(
                Duration.millis(ANIMATION_DURATION),
                getStart().getParent().getParent());
        translateTransition.setByY(-START_SCREEN_OFFSET);

        //create the fade transition for the start screen
        FadeTransition fadeTransition = new FadeTransition(
                Duration.millis((ANIMATION_DURATION)),
                getStart().getParent().getParent());
        fadeTransition.setFromValue(FADE_START_VALUE);
        fadeTransition.setToValue(1);

        //start both the animation
        fadeTransition.play();
        translateTransition.play();
    }

    /**
     * Method is called when the High score Button is pressed.
     * Create the HighScoreView view as a popup which displays the highscore.
     */
    @FXML
    private void onHighScoreClick() {
//        getStart().getScene().getRoot().setEffect(new GaussianBlur());

        Stage popupStage = new Stage();
        popupStage.initOwner(getStart().getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/view/HighScoreView.fxml"));
        try {
            popupStage.setScene(
                    new Scene(loader.load(), Color.TRANSPARENT));
        } catch (IOException e) {
            e.printStackTrace();
        }
        popupStage.show();
    }

    /**
     * Method is called when the start button is pressed on StartScreenView
     * Reads the theme from the theme selection and saves it in the
     * properties file.
     * <br>
     * Creates the GameView view and replaces the StartScreenView with. Also
     * sets the background color of the GameView to the color that the user
     * selects
     */
    @FXML
    private void onStartClick() {
        try {
            getProperties().store(new FileOutputStream(
                    getClass().getResource("/properties/theme.properties")
                              .getFile()), null);

            FXMLLoader root = new FXMLLoader(
                    getClass().getResource(
                            "/view/GameView.fxml"));
            Scene scene = new Scene(root.load());
            scene.getStylesheets().add(ResourceHandler.THEME_CSS_PATH);
            scene.getRoot().getStyleClass().add(getThemeChoice().getValue());
            //Replace the current scene with the new GameView scene
            ((Stage) getThemeChoice().getScene().getWindow()).setScene(scene);
            scene.getRoot().requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Stop the intro sound
        getIntroSound().stop();

    }

    /**
     * This method is called when the user updated their theme choice
     * It reads the new value from the ChoiceBox object and the writes to the
     * theme.properties file.
     * <br>
     * It also set the background color of the screen to the color that the
     * user selects. The colors are defined in the theme.css file in the
     * resource folder
     */
    @FXML
    private void onThemeSelected() {
        getStart().getScene().getRoot().getStyleClass().removeIf(s -> s.compareTo(
                getProperties().getProperty("theme")) == 0);
        getProperties().setProperty("theme", getThemeChoice().getValue());
        getStart().getScene().getRoot().getStyleClass().add(getThemeChoice()
                                                                    .getValue());

    }
}

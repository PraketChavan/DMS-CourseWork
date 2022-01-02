package com.example.breakout_clone.model.game;

import com.example.breakout_clone.Levels.Level;
import com.example.breakout_clone.Levels.LevelFactory;
import com.example.breakout_clone.controller.sprite.PaddleController;
import com.example.breakout_clone.util.ImpactHandler;
import com.example.breakout_clone.util.ResourceHandler;
import com.example.breakout_clone.wrappers.ball.Ball;
import com.example.breakout_clone.wrappers.ball.RubberBall;
import com.example.breakout_clone.wrappers.brick.Brick;
import com.example.breakout_clone.wrappers.paddle.Paddle;
import com.example.breakout_clone.wrappers.powerup.PowerUp;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The Singleton class that represents the model for the Game MVC. It
 * holds all the data related to the game and provides the controllers
 * methods to update the data based on some events.
 *<br>
 * This class has been developed from the GameFrame and the Wall class
 * @author Praket Chavan - modified
 * @see com.example.breakout_clone.controller.game.GameController
 * @see com.example.breakout_clone.controller.game.GameEndController
 * @see com.example.breakout_clone.controller.game.PauseMenuController
 * @see com.example.breakout_clone.controller.game.DebugController
 */
public class GameModel {

    /**
     * Constant that defines the player starting x position
     */
    private static final double PLAYER_START_X = 300;

    /**
     * Constant that defines the players starting y position
     */
    private static final double PLAYER_START_Y = 480;

    /**
     * Constant that defines the balls starting x position
     */
    private static final double BALL_START_X = PLAYER_START_X;

    /**
     * Constant that defines the balls starting y position
     */
    private static final double BALL_START_Y = PLAYER_START_X - 50;

    private static final Point2D BALL_INITIAL_SPEED = new Point2D(2, -2);

    private static final int MAX_LEVEL = 5;

    private static final int DIFFICULTY = 6;

    /**
     * The singleton game model instance
     * @see #getGameInstance()
     * @see #setGameInstance(GameModel)
     */
    private static GameModel m_GameInstance;

    /**
     * IntegerProperty that stores the number of lives for the player
     * @see #ballCountProperty()
     * @see #getBallCount()
     * @see #setBallCount(int)
     */
    private final SimpleIntegerProperty m_ballCount;

    /**
     * Integer property that stores the score count of the player
     * @see #getScoreCount()
     * @see #setScoreCount(int)
     * @see #scoreCountProperty()
     */
    private final SimpleIntegerProperty m_scoreCount;

    /**
     * Integer property that stores the remaining brick in the level
     * @see #getBrickCount()
     * @see #brickCountProperty()
     * @see #setBrickCount(int)
     */
    private final SimpleIntegerProperty m_brickCount;

    /**
     * The Boolean property that signifies whether the game is paused or not
     * @see #setPause(boolean)
     * @see #isPause()
     * @see #pauseProperty()
     */
    private final SimpleBooleanProperty m_pause;

    /**
     * The Paddle object that represent the player
     * @see #getPlayer()
     */
    private final Paddle m_player;

    /**
     * ImpactHandler object that will be used to handle the game impacts
     * @see #getImpactHandler()
     */
    private final ImpactHandler m_impactHandler;

    /**
     * BooleanProperty that signifies whether the game is in focus
     *  or not
     * @see #isFocus()
     * @see #focusProperty()
     * @see #setFocus(boolean)
     */
    private final SimpleBooleanProperty m_focus;

    /**
     * Boolean property that signifies whether the ball has been lost or not
     * @see #isBallLost()
     * @see #setBallLost(boolean)
     * @see #ballLostProperty()
     */
    private final SimpleBooleanProperty m_ballLost;

    /**
     * String Property that stores the message for the player
     * @see #getMessage()
     * @see #setMessage(String)
     * @see #messageProperty()
     */
    private final SimpleStringProperty m_message;

    /**
     * The Observable list of all the bricks that should currently be on the
     * screen
     * @see #getBricks()
     * @see #setBricks(ObservableList)
     */
    private final ObservableList<Brick> m_bricks;

    /**
     * The Observable list of all the balls that  should currently be on the
     * screen
     * @see #getBalls()
     */
    private final ObservableList<Ball> m_balls;

    /**
     * The Observable list of all the power ups that should currently be on
     * the screen
     * @see #getPowerUps()
     */
    private final ObservableList<PowerUp> m_powerUps;

    /**
     * The Queue that holds the level objects that will be removed and
     * generated on the screen as the game is progressed
     * @see #getLevels()
     */
    private final Queue<Level> m_levels;

    /**
     * Simple Integer property that stores the current level number
     * @see #getLevelNumber()
     */
    private final SimpleIntegerProperty m_LevelNumber;

    /**
     * Animation timer object that is used to run the main game loop
     * @see #getGameTimer()
     * @see #setGameTimer(AnimationTimer)
     */
    private AnimationTimer m_gameTimer;

    /**
     * The MediaPlayer object that is used to play the sound for the levels
     * @see #getLevelSound()
     */
    private MediaPlayer m_levelSound;

    /**
     * Gets the number of balls from the ball count property
     * @return the number that shows the remaining lives of the player
     * @see #setBallCount(int)
     * @see #ballCountProperty()
     */
    public int getBallCount() {
        return ballCountProperty().get();
    }

    /**
     * Sets the ball count property to the new value that is passed
     * @param ballCount the new value to be set
     * @see #getBallCount()
     * @see #ballCountProperty()
     */
    private void setBallCount(int ballCount) {
        ballCountProperty().set(ballCount);
    }

    /**
     * Gets the observable list containing all the ball objects that should
     * be on the screen
     * @return ObservableList object
     */
    public ObservableList<Ball> getBalls() {
        return m_balls;
    }

    /**
     * Gets the current number of brick that is stored in the brick count
     * property
     * @return the number representing the number of bricks on the screen
     * @see #brickCountProperty()
     * @see #setBallCount(int)
     */
    public int getBrickCount() {
        return brickCountProperty().get();
    }

    /**
     * Sets the brickCount property value to the new value that is passed
     * @param brickCount the new value to be set
     * @see #getBrickCount()
     * @see #brickCountProperty()
     */
    public void setBrickCount(int brickCount) {
        brickCountProperty().set(brickCount);
    }

    /**
     * Gets the observable list containing all the bricks that should be on
     * the screen
     * @return ObservableList object
     */
    public ObservableList<Brick> getBricks() {
        return m_bricks;
    }

    /**
     * Gets all the bricks from the new list and adds them to the m_bricks
     * Observable list
     * @param bricks the new list of bricks that need to be added
     * @see #getBricks()
     */
    private void setBricks(ObservableList<Brick> bricks) {
        for (Brick brick : bricks) {
            getBricks().add(brick);
        }
    }

    /**
     * Static method to get the singleton instance of the GameModel
     * <br> If the instance has not been initialised then create a new
     * GameModel object and return it
     * @return the singleton GameModel instance
     * @see #setGameInstance(GameModel)
     * @see #GameModel()
     */
    public static GameModel getGameInstance() {
        if (m_GameInstance == null)
            setGameInstance(new GameModel());
        return m_GameInstance;
    }

    /**
     * Sets the member variable that holds the singleton instance to a new
     * GameModel instance
     * @param m_GameInstance the new GameModel instance the needs to be set
     * @see #GameModel()
     */
    private static void setGameInstance(
            GameModel m_GameInstance) {
        GameModel.m_GameInstance = m_GameInstance;
    }

    /**
     * Gets the Animation Timer object that is used to run the main game loop
     * @return Animation timer object
     * @see #setGameTimer(AnimationTimer)
     */
    private AnimationTimer getGameTimer() {
        return m_gameTimer;
    }

    /**
     * Sets the m_gameTimer member to the Animation timer object that is passed
     * @param gameTimer The new Animation Timer object that is to be set
     * @see #getGameTimer()
     */
    public void setGameTimer(AnimationTimer gameTimer) {
        m_gameTimer = gameTimer;
    }

    /**
     * Gets the ImpactHandler object
     * @return ImpactHandler obeject
     */
    public ImpactHandler getImpactHandler() {
        return m_impactHandler;
    }

    /**
     * Gets the current level number from the levelNumber property
     * @return an int representing the current level number
     * @see #levelNumberProperty()
     * @see #setLevelNumber(int)
     */
    public int getLevelNumber() {
        return levelNumberProperty().get();
    }

    /**
     * Sets the level number to the new integer that is passed
     * @param levelNumber the new level number
     * @see #levelNumberProperty()
     * @see #getLevelNumber()
     */
    private void setLevelNumber(int levelNumber) {
        levelNumberProperty().set(levelNumber);
    }

    /**
     * Returns the MediaPlayer object that is running the game music
     * @return Media Player object
     * @see #setLevelSound(MediaPlayer)
     */
    public MediaPlayer getLevelSound() {
        return m_levelSound;
    }

    /**
     * Sets the MediaPlayer object to the passed parameter to play a
     * different track
     * @param levelSound new MediaPlayer object that is running the music
     * @see #getLevelSound()
     */
    public void setLevelSound(MediaPlayer levelSound) {
        m_levelSound = levelSound;
    }

    /**
     * Returns the Queue of Levels that are ready to be rendered to the screen
     * @return Queue object
     */
    private Queue<Level> getLevels() {
        return m_levels;
    }

    /**
     * Gets the message property value and returns it
     * @return String that is stored in the message property
     * @see #setMessage(String)
     * @see #messageProperty()
     */
    private String getMessage() {
        return messageProperty().get();
    }

    /**
     * Sets the message property value to the new String that is passed
     * @param message the new String that is to be set in the message property
     * @see #messageProperty()
     * @see #getMessage()
     */
    private void setMessage(String message) {
        messageProperty().set(message);
    }

    /**
     * Gets the current player object that is on the screen
     * @return Paddle object
     */
    public Paddle getPlayer() {
        return m_player;
    }

    /**
     * Gets the observable list containing all the powerUp that should be on
     * the screen
     * @return ObservableList object
     */
    public ObservableList<PowerUp> getPowerUps() {
        return m_powerUps;
    }

    /**
     * Gets the current score count of the game
     * @return the current game score
     * @see #scoreCountProperty()
     * @see #setScoreCount(int)
     */
    public int getScoreCount() {
        return scoreCountProperty().get();
    }

    /**
     * Sets the game score to the new value that is passed
     * @param scoreCount the new game score
     * @see #scoreCountProperty()
     * @see #getScoreCount()
     */
    private void setScoreCount(int scoreCount) {
        scoreCountProperty().set(scoreCount);
    }

    /**
     * Gets the boolean value from the ballLost property
     */
    public boolean isBallLost() {
        return ballLostProperty().get();
    }

    /**
     * Sets the ball lost property value to the boolean value that is passed
     * @param ballLost the new ball lost property value
     */
    private void setBallLost(boolean ballLost) {
        ballLostProperty().set(ballLost);
    }

    /**
     * Gets the boolean value from the focus property
     * @see #focusProperty()
     * @see #setFocus(boolean)
     */
    public boolean isFocus() {
        return focusProperty().get();
    }

    /**
     * Sets the focus property to the new value that is passed
     * @param focus new value that is to be set
     * @see #focusProperty()
     * @see #isFocus()
     */
    private void setFocus(boolean focus) {
        focusProperty().set(focus);
    }

    /**
     * Gets the boolean value from the pause property
     * @see #pauseProperty()
     * @see #setPause(boolean)
     */
    private boolean isPause() {
        return m_pause.get();
    }

    /**
     * Sets the Pause property to the new boolean value that is passed
     * <br>
     * If the new value is true then the message property is set to the "Game
     * Paused" value
     * <br>
     * Otherwise the message property is set to be an empty String
     * @param pause the new pause value to be set
     *
     * @see #isPause()
     * @see #pauseProperty()
     * @see #setMessage(String)
     */
    private void setPause(boolean pause) {
        pauseProperty().set(pause);
        if (isPause())
            setMessage("Game Paused. Press Space");
        else
            setMessage("");
    }

    /**
     * Constructor of the singleton class. It initialises the final property
     * objects and sets there value to be zero.
     */
    private GameModel() {
        m_brickCount = new SimpleIntegerProperty();
        m_scoreCount = new SimpleIntegerProperty();
        m_ballCount = new SimpleIntegerProperty();
        m_pause = new SimpleBooleanProperty(true);
        m_focus = new SimpleBooleanProperty(true);
        m_impactHandler = new ImpactHandler();
        m_ballLost = new SimpleBooleanProperty(false);
        m_player = createPlayer();
        m_message = new SimpleStringProperty();
        m_bricks = FXCollections.observableArrayList();
        m_balls = FXCollections.observableArrayList();
        m_levels = new LinkedList<>();
        m_LevelNumber = new SimpleIntegerProperty(0);
        m_powerUps = FXCollections.observableArrayList();
    }

    /**
     * Sets all the property values to their default values.
     * This method essentially restarts the gameModel object
     */
    private void restart() {
        setBrickCount(0);
        setBallCount(0);
        setScoreCount(0);
        setPause(true);
        setMessage("");
        getBricks().clear();
        getBalls().clear();
        getLevels().clear();
        setLevelNumber(0);
        initialize();
    }

    /**
     * Add power up object that is passed to the power ups observable list
     * and the impact handlers observable list
     * @param power the new PowerUp object that needs to be added
     */
    public void createPowerUp(PowerUp power) {
        getPowerUps().add(power);
        getImpactHandler().addPower(power);
    }

    /**
     * Checks to see if there is any more levels left to be played
     * @return false if the Level Queue is not empty else true
     */
    public boolean hasNextLevel() {
        return !getLevels().isEmpty();
    }

    /**
     * Initialize the level queue and other counters to start the game
     */
    public void initialize() {
        for (int i = 2; i < MAX_LEVEL; i++)
            createLevel(i, DIFFICULTY);
        setBallCount(3);
        createBall();
        for (Ball ball : getBalls()) {
            ballLostProperty().bind(ball.getModel().lostProperty());
        }
    }

    /**
     * Gets the ball lost boolean Property
     */
    private SimpleBooleanProperty ballLostProperty() {
        return m_ballLost;
    }

    /**
     * Gets the message String Property
     */
    public SimpleStringProperty messageProperty() {
        return m_message;
    }

    /**
     * Gets the focus BooleanProperty
     */
    public SimpleBooleanProperty focusProperty() {
        return m_focus;
    }

    /**
     * Gets the pause BooleanProperty
     */
    private SimpleBooleanProperty pauseProperty() {
        return m_pause;
    }

    /**
     * Stops the player by calling the stop method on the PaddleController
     * @see com.example.breakout_clone.controller.sprite.PaddleController
     */
    public void stopPlayer() {
        getPlayer().getController().stop();
    }

    /**
     * Gets the ball count IntegerProperty
     */
    public SimpleIntegerProperty ballCountProperty() {
        return m_ballCount;
    }

    /**
     * Gets the score count IntegerProperty
     */
    public SimpleIntegerProperty scoreCountProperty() {
        return m_scoreCount;
    }

    /**
     * Gets the brick count IntegerProperty
     */
    public SimpleIntegerProperty brickCountProperty() {
        return m_brickCount;
    }

    /**
     * Method is called when the left arrow key is pressed.
     * Calls the
     * {@link PaddleController#moveLeft()} method
     */
    public void leftKeyPressed() {
        getPlayer().getController().moveLeft();
    }

    /**
     * Method is called when the right arrow key is pressed.
     * Calls the
     * {@link PaddleController#moveRight()} method
     */
    public void rightKeyPressed() {
        getPlayer().getController().moveRight();
    }

    /**
     * Method is called when the space key pressed. If the pause property is
     * true then it starts the gameTimer else stops it.
     */
    public void spaceKeyPressed() {
        if (isPause())
            startTimer();
        else
            stopTimer();

    }

    /**
     * Stops the game timer
     */
    public void escapeKeyPressed() {
        stopTimer();
    }

    /**
     * Stops the game timer
     * @param event
     */
    public void f1KeyPressed(KeyEvent event) {
        stopTimer();
    }

    /**
     * Sets the pause value to be false and starts the gameTimer
     */
    private void startTimer() {

        setPause(false);
        getGameTimer().start();
    }

    /**
     * Sets the pause value to be true and stops the GameTimer
     */
    public void stopTimer() {
        setPause(true);
        getGameTimer().stop();
    }

    /**
     * Method is called when the game gains focus.
     * Sets the focus property to be false and starts the gameTimer
     * @see #startTimer()
     * @see #setFocus(boolean)
     */
    public void onFocusGain() {
        setFocus(true);
        setMessage("");
        startTimer();
    }

    /**
     * Method is called when the game gains focus.
     * Sets the focus property to be true and stops the gameTimer
     * @see #startTimer()
     * @see #setFocus(boolean)
     */
    public void onFocusLost() {
        setFocus(false);
        setMessage("Focus Lost");
        stopPlayer();
    }

    /**
     * Resets the game when a life is lost or a level has been completed
     * <br>Calls the reset method of the Paddle Controller and the ball
     * Controller.
     * <br>
     * Also stops the gameTimer and sets the pause property to be true
     * @see com.example.breakout_clone.controller.sprite.BallController
     * @see PaddleController
     */
    public void gameReset() {
        getBalls().get(0).getController().reset();
        getPlayer().getController().reset();
        getBalls().forEach(ball -> ball.getModel().setLost(false));
        stopTimer();
        setPause(true);
    }

    /**
     * Gets the level number IntegerProperty
     * @see #setLevelNumber(int)
     * @see #getLevelNumber()
     */
    private SimpleIntegerProperty levelNumberProperty() {
        return m_LevelNumber;
    }

    /**
     * Clear the list of bricks and power ups. <br>
     * Gets the next available level and sets the bricks observable list to
     * the new levels brick array.
     * <br>
     * Increments the level number counter
     */
    public void nextLevel() {
        Level level = getLevels().remove();
        getBricks().clear();
        setBricks(FXCollections.observableArrayList(level.createLevel()));
        getImpactHandler().getBricks().clear();
        getImpactHandler().getPowerUp().clear();
        getPowerUps().clear();
        getBricks().forEach(brick -> getImpactHandler().addBrick(brick.getModel()));
        updateBrickCount();
        setLevelNumber((getLevelNumber() + 1) % DIFFICULTY);
    }

    /**
     * Sets the ball X speed to the passed value by calling the
     * {@link com.example.breakout_clone.controller.sprite.BallController#setXSpeed(double)} method
     * @param xSpeed the new X speed of the ball
     */
    public void setBallXSpeed(double xSpeed) {
        getBalls().forEach(ball -> ball.getController().setXSpeed(xSpeed));

    }

    /**
     * Sets the ball Y speed to the passed value by calling the
     * {@link com.example.breakout_clone.controller.sprite.BallController#setXSpeed(double)} method
     * @param ySpeed the new Y speed of the ball
     */
    public void setBallYSpeed(double ySpeed) {
        getBalls().forEach(ball -> ball.getController().setYSpeed(ySpeed));
    }

    /**
     * Removes the blur effect from the game screen and starts the gameTimer
     */
    public void onContinueClick() {
        Stage.getWindows().stream().filter(Window::isShowing)
             .forEach(window -> window.getScene().getRoot().setEffect(null));
        startTimer();
    }

    /**
     * Exits the program
     */
    public void onExitClick() {
        Platform.exit();
    }

    /**
     * Stops the timer and restarts the game by calling the
     * {@link #restart()} method
     */
    public void onRestartClick() {
        stopTimer();
        restart();
    }

    /**
     * Checks to see if the level has been completed
     * @return true if there is no brick remaining else false
     */
    public boolean islevelComplete() {
        return getBrickCount() <= 0;
    }

    /**
     * Calls the move method of all the ball, player and power up object
     */
    public void move() {
        getBalls().forEach(ball -> ball.getController().move());
        getPlayer().getController().move();
        getPowerUps().forEach(powerUp -> powerUp.getController().move());
    }

    /**
     * Sets the message property to "Ball Lost" and decreases the remaining
     * lives
     */
    public void onBallLost() {
        setMessage("Ball Lost");
        setBallCount(getBallCount() - 1);
        gameReset();
    }

    /**
     * Updates the brick counter by counting the remaining bricks that are
     * not unbreakable
     */
    public void updateBrickCount() {
        setBrickCount((int) getBricks().stream().filter(
                brick -> !brick.getController().isBroken() &&
                        brick.getModel().getName().compareTo("Unbreakable") != 0
        ).count());
    }

    /**
     * Add the num value to the score property
     * @param num the amount of points to add
     */
    public void addScore(int num) {
        setScoreCount(getScoreCount() + num);
    }

    /**
     * Remove all the bricks from the list
     */
    public void clearLevel() {
        getBricks().clear();
    }

    /**
     * Add an extra life for the player
     */
    public void addLife() {
        setBallCount(getBallCount() + 1);
    }

    /**
     * Create a new Ball object and set it speed to the Ball initial speed
     */
    private void createBall() {
        Ball ball = new RubberBall(ResourceHandler.getResource("ball0"),
                                   new Point2D(BALL_START_X, BALL_START_Y));

        getBalls().add(ball);
        ball.getController().setSpeed(BALL_INITIAL_SPEED);
        getImpactHandler().addBall(ball.getModel());
    }

    /**
     * Creates level objects using the level Factory
     * @param level the level number that is to be created
     * @see LevelFactory
     */
    private void createLevel(int level, int difficulty) {
        LevelFactory factory = new LevelFactory();
        getLevels().add(factory.createLevel(level, difficulty));
    }

    /**
     * Creates a new Paddle object and add it to the impact handler
     * @return the new Paddle object
     */
    private Paddle createPlayer() {
        Paddle player = new Paddle(ResourceHandler.getResource("paddle"),
                                   new Point2D(PLAYER_START_X, PLAYER_START_Y));
        getImpactHandler().addPaddle(player.getModel());
        return player;
    }
}

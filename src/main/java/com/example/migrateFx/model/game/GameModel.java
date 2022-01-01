package com.example.migrateFx.model.game;

import com.example.migrateFx.Levels.Level;
import com.example.migrateFx.Levels.LevelFactory;
import com.example.migrateFx.handler.ImpactHandler;
import com.example.migrateFx.handler.ResourceHandler;
import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.wrappers.ball.Ball;
import com.example.migrateFx.wrappers.ball.RubberBall;
import com.example.migrateFx.wrappers.brick.Brick;
import com.example.migrateFx.wrappers.paddle.Paddle;
import com.example.migrateFx.wrappers.powerup.PowerUp;
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

public class GameModel implements Impactable {

    private static final double PLAYER_START_X = 300;
    private static final double PLAYER_START_Y = 480;
    private static final double BALL_START_X = PLAYER_START_X;
    private static final double BALL_START_Y = PLAYER_START_X - 50;
    private static GameModel m_GameInstance;
    private final SimpleIntegerProperty m_ballCount;
    private final SimpleIntegerProperty m_scoreCount;
    private final SimpleIntegerProperty m_brickCount;
    private final SimpleBooleanProperty m_pause;
    private final Paddle m_player;
    private final ImpactHandler m_impactHandler;
    private final SimpleBooleanProperty m_focus;
    private final SimpleBooleanProperty m_ballLost;
    private final SimpleStringProperty m_message;
    private final ObservableList<Brick> m_bricks;
    private final ObservableList<Ball> m_balls;
    private final ObservableList<PowerUp> m_powerUps;
    private final Queue<Level> m_levels;
    private final SimpleIntegerProperty m_LevelNumber;
    private AnimationTimer m_gameTimer;
    private MediaPlayer m_levelSound;

    public int getBallCount() {
        return m_ballCount.get();
    }

    public void setBallCount(int ballCount) {
        this.m_ballCount.set(ballCount);
    }

    public ObservableList<Ball> getBalls() {
        return m_balls;
    }

    public int getBrickCount() {
        return m_brickCount.get();
    }

    public void setBrickCount(int brickCount) {
        this.m_brickCount.set(brickCount);
    }

    public ObservableList<Brick> getBricks() {
        return m_bricks;
    }

    private void setBricks(ObservableList<Brick> bricks) {
        for (Brick brick : bricks) {
            getBricks().add(brick);
        }
    }

    public static GameModel getGameInstance() {
        if (m_GameInstance == null)
            setGameInstance(new GameModel());
        return m_GameInstance;
    }

    public static void setGameInstance(
            GameModel m_GameInstance) {
        GameModel.m_GameInstance = m_GameInstance;
    }

    public AnimationTimer getGameTimer() {
        return m_gameTimer;
    }

    public void setGameTimer(AnimationTimer gameTimer) {
        m_gameTimer = gameTimer;
    }

    public ImpactHandler getImpactHandler() {
        return m_impactHandler;
    }

    public int getLevelNumber() {
        return m_LevelNumber.get();
    }

    public void setLevelNumber(int levelNumber) {
        this.m_LevelNumber.set(levelNumber);
    }

    public MediaPlayer getLevelSound() {
        return m_levelSound;
    }

    public void setLevelSound(MediaPlayer levelSound) {
        m_levelSound = levelSound;
    }

    public Queue<Level> getLevels() {
        return m_levels;
    }

    public String getMessage() {
        return m_message.get();
    }

    public void setMessage(String message) {
        this.m_message.set(message);
    }

    public Paddle getPlayer() {
        return m_player;
    }

    public ObservableList<PowerUp> getPowerUps() {
        return m_powerUps;
    }

    public int getScoreCount() {
        return m_scoreCount.get();
    }

    public void setScoreCount(int scoreCount) {
        this.m_scoreCount.set(scoreCount);
    }

    public boolean isBallLost() {
        return m_ballLost.get();
    }

    public void setBallLost(boolean ballLost) {
        this.m_ballLost.set(ballLost);
    }

    public boolean isFocus() {
        return m_focus.get();
    }

    public void setFocus(boolean focus) {
        this.m_focus.set(focus);
    }

    public boolean isPause() {
        return m_pause.get();
    }

    public void setPause(boolean pause) {
        this.m_pause.set(pause);
        if (isPause())
            setMessage("Game Paused. Press Space");
        else
            setMessage("");
    }

    private GameModel() {
        m_brickCount = new SimpleIntegerProperty();
        m_scoreCount = new SimpleIntegerProperty();
        m_ballCount = new SimpleIntegerProperty(0);
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

    public void createPowerUp(PowerUp power) {
        getPowerUps().add(power);
        getImpactHandler().addPower(power);
    }

    public boolean hasNextLevel() {
        return !getLevels().isEmpty();
    }

    public void initialize() {
        createLevel(3);
        createLevel(1);
        setBallCount(3);
        createBall();
        for (Ball ball : getBalls()) {
            ballLostProperty().bind(ball.getModel().lostProperty());
        }
    }

    public SimpleBooleanProperty ballLostProperty() {
        return m_ballLost;
    }

    public SimpleStringProperty messageProperty() {
        return m_message;
    }

    public SimpleBooleanProperty focusProperty() {
        return m_focus;
    }

    public SimpleBooleanProperty pauseProperty() {
        return m_pause;
    }

    public void stopPlayer() {
        getPlayer().getController().stop();
    }

    public SimpleIntegerProperty ballCountProperty() {
        return m_ballCount;
    }

    public SimpleIntegerProperty scoreCountProperty() {
        return m_scoreCount;
    }

    public SimpleIntegerProperty brickCountProperty() {
        return m_brickCount;
    }

    public void leftKeyPressed() {
        getPlayer().getController().moveLeft();
    }

    public void rightKeyPressed() {
        getPlayer().getController().moveRight();
    }

    public void spaceKeyPressed() {
        if (isPause())
            startTimer();
        else
            stopTimer();

    }

    public void escapeKeyPressed() {
        stopTimer();
    }

    public void f1KeyPressed(KeyEvent event) {
        stopTimer();
    }

    public void startTimer() {

        setPause(false);
        getGameTimer().start();
    }

    public void stopTimer() {
        setPause(true);
        getGameTimer().stop();
    }

    public void onFocusGain() {
        setFocus(true);
        setMessage("");
        startTimer();
    }

    public void onFocusLost() {
        setFocus(false);
        setMessage("Focus Lost");
        stopPlayer();
    }

    public void gameReset() {
        getBalls().get(0).getController().reset();
        getPlayer().getController().reset();
        getBalls().forEach(ball -> ball.getModel().setLost(false));
        stopTimer();
        setPause(true);
    }

    public SimpleIntegerProperty levelNumberProperty() {
        return m_LevelNumber;
    }

    public void nextLevel() {
        Level level = getLevels().remove();
        getBricks().clear();
        setBricks(FXCollections.observableArrayList(level.createLevel()));
        getImpactHandler().getBricks().clear();
        getImpactHandler().getPowerUp().clear();
        getPowerUps().clear();
        getBricks().forEach(brick -> getImpactHandler().addBrick(brick.getModel()));
        countBricks();
        setLevelNumber((getLevelNumber() + 1) % 6);
    }

    public void setBallXSpeed(double xSpeed) {
        getBalls().forEach(ball -> ball.getController().setXSpeed(xSpeed));

    }

    public void setBallYSpeed(double ySpeed) {
        getBalls().forEach(ball -> ball.getController().setYSpeed(ySpeed));
    }

    public void onContinueClick() {
        Stage.getWindows().stream().filter(Window::isShowing)
             .forEach(window -> window.getScene().getRoot().setEffect(null));
        startTimer();
    }

    public void onExitClick() {
        Platform.exit();
    }

    public void onRestartClick() {
        stopTimer();
        System.out.println("Restarting");
    }

    public boolean islevelComplete() {
        return getBricks().size() == 0;
    }

    public void move() {
        getBalls().forEach(ball -> ball.getController().move());
        getPlayer().getController().move();
        getPowerUps().forEach(powerUp -> powerUp.getController().move());
    }

    public void onBallLost() {
        setMessage("Ball Lost");
        setBallCount(getBallCount() - 1);
        gameReset();
    }

    public void updateBrickCount() {
        setBrickCount((int) getBricks().stream().filter(
                brick -> !brick.getController().isBroken() &&
                        brick.getModel().getName().compareTo("Unbreakable") != 0
        ).count());
    }

    public void addScore(int num) {
        setScoreCount(getScoreCount() + num);
    }

    public void clearLevel() {
        getBricks().clear();
    }

    public void addLife() {
        setBallCount(getBallCount() + 1);
    }

    @Override
    public int findImpact(Impactable parent) {
        return 0;
    }

    @Override
    public void onImpact(int side) {

    }

    private void countBricks() {
        setBrickCount(
                (int) getBricks()
                        .stream()
                        .filter(brick -> brick
                                .getModel()
                                .getName().compareTo("Unbreakable") != 0)
                        .count());
    }

    private void createBall() {
        Ball ball = new RubberBall(ResourceHandler.getResource("ball0"),
                                   new Point2D(BALL_START_X, BALL_START_Y));

        getBalls().add(ball);
//            ball.getController().setYSpeed(2);
        ball.getController().setSpeed(new Point2D(2, 2));
        getImpactHandler().addBall(ball.getModel());
    }

    private void createLevel(int level) {
        LevelFactory factory = new LevelFactory();
        getLevels().add(factory.createLevel(level, 5));
    }

    private Paddle createPlayer() {
        Paddle player = new Paddle(ResourceHandler.getResource("paddle"),
                                   new Point2D(PLAYER_START_X, PLAYER_START_Y));
        getImpactHandler().addPaddle(player.getModel());
        return player;
    }
}

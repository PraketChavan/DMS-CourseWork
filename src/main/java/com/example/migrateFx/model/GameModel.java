package com.example.migrateFx.model;

import com.example.migrateFx.Impactable;
import com.example.migrateFx.Levels.Level;
import com.example.migrateFx.factory.LevelFactory;
import com.example.migrateFx.handler.ImpactHandler;
import com.example.migrateFx.handler.ResourceHandler;
import com.example.migrateFx.wrappers.Ball;
import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.Paddle;
import com.example.migrateFx.wrappers.RubberBall;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.*;

public class GameModel implements Impactable {

    private static final double PLAYER_START_X = 300;
    private static final double PLAYER_START_Y = 480;
    private static GameModel m_GameInstance;
    private final SimpleIntegerProperty m_ballCount;
    private final SimpleIntegerProperty m_scoreCount;
    private final SimpleIntegerProperty m_brickCount;
    private final SimpleObjectProperty<Bounds> m_gameBounds;
    private final SimpleBooleanProperty m_pause;
    private final AnimationTimer m_gameTimer;
    private final Paddle m_player;
    private final ImpactHandler m_impactHandler;
    private final SimpleBooleanProperty m_focus;
    private final SimpleBooleanProperty m_ballLost;
    private final SimpleStringProperty m_message;

    private void setBricks(ObservableList<Brick> bricks) {
        for (Brick brick : bricks) {
            getBricks().add(brick);
        }
    }

    private ObservableList<Brick> m_bricks;
    private final ObservableList<Ball> m_balls;
    private final Queue<Level> m_levels;

    public Queue<Level> getLevels() {
        return m_levels;
    }

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

    public Bounds getGameBounds() {
        return m_gameBounds.get();
    }

    public void setGameBounds(Bounds gameBounds) {
        this.m_gameBounds.set(gameBounds);
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

    public ImpactHandler getImpactHandler() {
        return m_impactHandler;
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
        m_ballCount = new SimpleIntegerProperty();
        m_gameBounds = new SimpleObjectProperty<>();
        m_pause = new SimpleBooleanProperty(true);
        m_focus = new SimpleBooleanProperty(true);
        m_impactHandler = new ImpactHandler(this);
        m_ballLost = new SimpleBooleanProperty(false);
        m_player = createPlayer();
        m_message = new SimpleStringProperty();
        m_bricks = FXCollections.observableArrayList();
        m_balls = FXCollections.observableArrayList();
        m_levels = new LinkedList<>();
        m_gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameTimer();
            }
        };
//        startTimer();
    }

    public void initialize() {
        createLevel();
        nextLevel();
//        createPlayer();
        createBall();
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

    public SimpleObjectProperty<Bounds> gameBoundsProperty() {
        return m_gameBounds;
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

        setPause(!isPause());
    }

    public void escapeKeyPressed() {
        stopTimer();
    }

    public void f1KeyPressed(KeyEvent event) {
        stopTimer();
    }

    public void startTimer() {
        getGameTimer().start();
    }

    public void stopTimer() {
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

    @Override
    public int findImpact(Impactable parent) {
        return 0;
    }

    @Override
    public void onImpact(int side) {

    }

    private Paddle createPlayer() {
        Paddle player = new Paddle(ResourceHandler.getResource("paddle"),
                new Point2D(PLAYER_START_X, PLAYER_START_Y));
        getImpactHandler().addPaddle(player.getModel());
        return player;
    }

    public void gameReset() {
        getBalls().get(0).getController().reset();
        getPlayer().getController().reset();
        stopTimer();
        setPause(true);
    }

    private void createBall() {
        Ball ball = new RubberBall(ResourceHandler.getResource("ball0"),
                                   new Point2D(PLAYER_START_X, PLAYER_START_Y));

        getBalls().add(ball);
//            ball.getController().setYSpeed(2);
        ball.getController().setSpeed(new Point2D(2, 2));
        getImpactHandler().addBall(ball.getModel());
    }

    private void createLevel(){
        LevelFactory factory = new LevelFactory();
        getLevels().add(factory.createLevel(1, 5));
    }

    public void nextLevel() {
        Level level = getLevels().remove();
        setBricks(FXCollections.observableArrayList(level.createLevel()));
        getBricks().forEach(brick -> {
            getImpactHandler().addBrick(brick.getModel());
        });
    }
    private void move() {
        getPlayer().getController().move();
        getBalls().forEach(ball -> ball.getController().move());
    }

    private void gameTimer() {
        //m_wall.move();
        move();
//        m_wall.findImpacts();
//        this.getWall().findImpacts();
        getImpactHandler().handleImpacts();
//        m_line.setFill(Color.RED);
//        m_line.setStartX(this.getWall().getBall().getModel().getXLocationProperty().get());
//        m_line.setStartY(this.getWall().getBall().getModel().getYLocationProperty().get());
//        m_line.setEndX(this.getWall().getBall().getModel().getXLocationProperty().get());
//        m_line.setEndX(this.getWall().getBall().getModel().getYLocationProperty().get());

//        m_message = String.format("Bricks: %d Balls %d", m_wall.getBrickCount(), m_wall
//                .getBallCount());
//        System.out.println(getMessage());
        if (isBallLost()) {
            onBallLost();
        } else if (islevelComplete()) {
            nextLevel();
//            if (this.getWall().hasLevel()) {
//                this.setMessage("Go to Next Level");
//                this.getGameTimer().stop();
//                this.getWall().ballReset();
//                this.getWall().wallReset();
//                this.getWall().nextLevel();
//            } else {
//                this.setMessage("ALL WALLS DESTROYED");
//                this.getGameTimer().stop();
//            }
        }
    }

    private void onBallLost() {
        setMessage("Ball Lost");
        gameReset();
    }

    private boolean islevelComplete() {
        return getBricks().size() == 0;
    }

    public void setBallXSpeed(double xSpeed) {
        getBalls().forEach(ball -> ball.getController().setXSpeed(xSpeed));

    }

    public void setBallYSpeed(double ySpeed) {
        getBalls().forEach(ball -> ball.getController().setYSpeed(ySpeed));
    }

    public void onContinueClick() {
        Stage.getWindows().stream().filter(Window::isShowing).forEach(window -> {
            window.getScene().getRoot().setEffect(null);
        });
        startTimer();
    }

    public void onExitClick() {
        Platform.exit();
    }

    public void onRestartClick() {
        stopTimer();
        System.out.println("Restarting");
    }
}

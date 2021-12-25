package com.example.migrateFx.model;

import com.example.migrateFx.GameBoard;
import com.example.migrateFx.Impactable;
import com.example.migrateFx.Wall;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;

public class GameModel implements Impactable {
    private static GameModel m_gameInstance = null;
    private final Wall m_Wall;
    private final GameBoard m_GameBoard;
    private SimpleObjectProperty<Bounds> m_Bounds;

    public Bounds getBounds() {
        return boundsProperty().get();
    }

    public void setBounds(Bounds bounds) {
        this.boundsProperty().set(bounds);
    }

    public void setBounds(SimpleObjectProperty<Bounds> bounds) {
        m_Bounds = bounds;
    }

    public GameBoard getGameBoard() {
        return m_GameBoard;
    }

    public static GameModel getGameInstance() {
        if (getM_gameInstance() == null)
            setGameInstance(new GameModel(Wall.getWallInstance()));
        return getM_gameInstance();
    }

    public static void setGameInstance(
            GameModel gameInstance) {
        GameModel.setM_gameInstance(gameInstance);
    }

    public static GameModel getM_gameInstance() {
        return m_gameInstance;
    }

    public static void setM_gameInstance(
            GameModel m_gameInstance) {
        GameModel.m_gameInstance = m_gameInstance;
    }

    public Wall getWall() {
        return m_Wall;
    }

    private GameModel(Wall wall) {
        m_Wall = wall;
        m_GameBoard = GameBoard.getGameBoardInstance();
        setBounds(new SimpleObjectProperty<>());
        initialize();
    }

    public SimpleObjectProperty<Bounds> boundsProperty() {
        return m_Bounds;
    }

    public void setBallXSpeed(int xSpeed) {
        getWall().setBallXSpeed(xSpeed);
    }

    public void setBallYSpeed(int ySpeed) {
        getWall().setBallYSpeed(ySpeed);
    }

    public void skipLevel() {
        getWall().wallReset();
        getWall().nextLevel();
    }

    public void resetBall() {
        getWall().resetBallCount();
    }

    public void onContinueClick() {
        getGameBoard().getGameTimer().start();
        getGameBoard().getParent().setEffect(null);
    }

    public void onExitClick() {
        Platform.exit();
    }

    public void onRestartClick() {
        getGameBoard().getGameTimer().stop();
        System.out.println("Restarting");
    }

    @Override
    public int findImpact(Impactable parent) {
        return 0;
    }

    @Override
    public void onImpact(int side) {

    }

    private void initialize() {
        setBounds(getGameBoard().getBoundsInParent());
    }
}

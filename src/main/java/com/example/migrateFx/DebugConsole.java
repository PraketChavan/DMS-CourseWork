package com.example.migrateFx;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import com.example.migrateFx.wrappers.Ball;

public class DebugConsole extends JDialog implements WindowListener {

    private static final String TITLE = "Debug Console";


    private JFrame m_owner;
    private DebugPanel debugPanel;
    private GameBoard gameBoard;
    private Wall wall;

    public JFrame getM_owner() {
        return m_owner;
    }

    public void setM_owner(JFrame m_owner) {
        this.m_owner = m_owner;
    }

    public DebugPanel getDebugPanel() {
        return debugPanel;
    }

    public void setDebugPanel(DebugPanel debugPanel) {
        this.debugPanel = debugPanel;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public DebugConsole(JFrame owner, Wall wall, GameBoard gameBoard) {

        this.setWall(wall);
        this.setM_owner(owner);
        this.setGameBoard(gameBoard);
        initialize();

        setDebugPanel(new DebugPanel(wall));
        this.add(getDebugPanel(), BorderLayout.CENTER);


        this.pack();
    }

    public void initialize() {
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }

    public void setLocation() {
        final float HALF = 0.5f;
        int x = (int) (((getM_owner().getWidth() - this.getWidth()) * HALF) + getM_owner().getX());
        int y = (int) (((getM_owner().getHeight() - this.getHeight()) * HALF) + getM_owner().getY());
        this.setLocation(x, y);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        Ball b = getWall().getBall();
        getDebugPanel().setValues((int) b.getModel().getSpeed().getX(), (int) b.getModel().getSpeed().getY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}

package com.example.migrateFx;

import javafx.scene.layout.BorderPane;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame implements WindowFocusListener {

    public static final String DEF_TITLE = "Breakout Clone\t" +
                                           "space = start/pause\t" +
                                           "←/→ = move left/right\t" +
                                           "esc = menu";
    private GameBoard m_gameBoard;
    private BorderPane m_layout;
    private boolean m_gaming;

    public GameBoard getGameBoard() {
        return m_gameBoard;
    }

    public BorderPane getLayout() {
        return m_layout;
    }

    public boolean isGaming() {
        return m_gaming;
    }

    public void setGaming(boolean m_gaming) {
        this.m_gaming = m_gaming;
    }

    public static String getDefTitle() {
        return DEF_TITLE;
    }

    public GameFrame() {
        super();
        this.setGaming(false);
        setLayout(new BorderPane());
        m_gameBoard = new GameBoard(getLayout());
        getLayout().setCenter(this.getGameBoard());
        //gaming = false; /*Removed direct assignment*/
        //this.add(m_gameBoard, BorderLayout.CENTER); /*Removed the direct access and used getter method*/
        initialize();
    }

    public void setLayout(BorderPane layout) {
        m_layout = layout;
    }

    private void initialize() {
//        this.setTitle(DEF_TITLE); Removed direct access and used getter
        //this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //this.autoLocate();
    }

//    private void autoLocate() {
//        final double HALF = 0.5; //Declare symbolic constant to remove magic number
//
//        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (int) ((size.width - this.getWidth()) * HALF);
//        int y = (int) ((size.height - this.getHeight()) * HALF);
////        int x = (size.width - this.getWidth()) / 2; Removed Magic nuumber
////        int y = (size.height - this.getHeight()) / 2; Removed magic number
//        this.setLocation(x, y);
//    }

    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
          the first time the frame loses focus is because it has been disposed
          to install the GameBoard, so went it regains the focus it's ready to
          play. of course calling a method such as 'onLostFocus' is useful
          only if the GameBoard as been displayed at least once
         */ //Used multiline comments to keep the number of charters at 80

        this.setGaming(true);
        //m_gaming = true; removed direct assignment and used setter method
    }

    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (this.isGaming())
            this.getGameBoard().onLostFocus();
//        if (m_gaming) //Removed direct access
//            m_gameBoard.onLostFocus();

    }
}

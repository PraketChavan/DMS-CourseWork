package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


public class GameFrame extends JFrame implements WindowFocusListener {

    public static final String DEF_TITLE = "Breakout Clone\t" +
                                           "space = start/pause\t" +
                                           "←/→ = move left/right\t" +
                                           "esc = menu";
    private final GameBoard m_gameBoard;
    private boolean m_gaming;

    public GameBoard getGameBoard() {
        return m_gameBoard;
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
        //gaming = false; /*Removed direct assignment*/
        this.setLayout(new BorderLayout());
        m_gameBoard = new GameBoard(this);
        this.add(this.getGameBoard(), BorderLayout.CENTER);
        //this.add(m_gameBoard, BorderLayout.CENTER); /*Removed the direct access and used getter method*/
        initialize();
        this.addWindowFocusListener(this);
    }

    private void initialize() {
        this.setTitle(getDefTitle());
//        this.setTitle(DEF_TITLE); Removed direct access and used getter
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.autoLocate();
        this.setVisible(true);
    }

    private void autoLocate() {
        final double HALF = 0.5; //Declare symbolic constant to remove magic number

        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((size.width - this.getWidth()) * HALF);
        int y = (int) ((size.height - this.getHeight()) * HALF);
//        int x = (size.width - this.getWidth()) / 2; Removed Magic nuumber
//        int y = (size.height - this.getHeight()) / 2; Removed magic number
        this.setLocation(x, y);
    }

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

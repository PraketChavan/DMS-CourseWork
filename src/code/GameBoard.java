package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;


public class GameBoard extends JComponent implements KeyListener, MouseListener, MouseMotionListener {

    public static final int DELAY = 10;
    public static final int BRICK_COUNT = 30; //added to remove magic number
    public static final int LINE_COUNT = 3; //added to remove magic number
    public static final int X = 300;
    public static final int Y = 430;
    public static final double BRICK_DIMENSION_RATIO = 6.0 / 2;
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = new Color(0, 255, 0);
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;
    private Timer m_gameTimer;

    private Wall m_wall;

    private String m_message;

    private boolean m_showPauseMenu;

    private Font m_menuFont;

    private Rectangle m_continueButtonRect;
    private Rectangle m_exitButtonRect;
    private Rectangle m_restartButtonRect;
    private int m_strLen;

    private DebugConsole m_debugConsole;

    /*
    Added getters and setter for non final member variable
     */

    public Timer getGameTimer() {
        return m_gameTimer;
    }

    public void setGameTimer(Timer gameTimer) {
        this.m_gameTimer = gameTimer;
    }

    public Wall getWall() {
        return m_wall;
    }

    public void setWall(Wall wall) {
        this.m_wall = wall;
    }

    public String getMessage() {
        return m_message;
    }

    public void setMessage(String message) {
        this.m_message = message;
    }

    public boolean isShowPauseMenu() {
        return m_showPauseMenu;
    }

    public void setShowPauseMenu(boolean showPauseMenu) {
        this.m_showPauseMenu = showPauseMenu;
    }

    public Font getMenuFont() {
        return m_menuFont;
    }

    public void setMenuFont(Font menuFont) {
        this.m_menuFont = menuFont;
    }

    public Rectangle getContinueButtonRect() {
        return m_continueButtonRect;
    }

    public void setContinueButtonRect(Rectangle continueButtonRect) {
        this.m_continueButtonRect = continueButtonRect;
    }

    public Rectangle getExitButtonRect() {
        return m_exitButtonRect;
    }

    public void setExitButtonRect(Rectangle exitButtonRect) {
        this.m_exitButtonRect = exitButtonRect;
    }

    public Rectangle getRestartButtonRect() {
        return m_restartButtonRect;
    }

    public void setRestartButtonRect(Rectangle restartButtonRect) {
        this.m_restartButtonRect = restartButtonRect;
    }

    public int getStrLen() {
        return m_strLen;
    }

    public void setStrLen(int strLen) {
        this.m_strLen = strLen;
    }

    public DebugConsole getDebugConsole() {
        return m_debugConsole;
    }

    public void setDebugConsole(DebugConsole debugConsole) {
        this.m_debugConsole = debugConsole;
    }

    public GameBoard(JFrame owner) {
        super();

        //m_strLen = 0;
        this.setStrLen(0);

        //m_showPauseMenu = false;
        this.setShowPauseMenu(false);

        //m_menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);
        this.setMenuFont(new Font("Monospaced", Font.PLAIN, TEXT_SIZE));

        this.initialize();
        //m_message = "Press SPACE to start";
        this.setMessage("Press SPACE to start");

        //m_wall = new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 30, 3, 6.0 / 2, new Point(300, 430));
        this.setWall(new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT),
                              BRICK_COUNT, LINE_COUNT, BRICK_DIMENSION_RATIO,
                             new Point(X, Y)));

        //m_debugConsole = new DebugConsole(owner, m_wall, this);
        this.setDebugConsole(new DebugConsole(owner, m_wall, this));

        //initialize the first level
        //m_wall.nextLevel();
        this.getWall().nextLevel();

        /*m_gameTimer = new Timer(10, e -> {
            m_wall.move();
            m_wall.findImpacts();
            m_message = String.format("Bricks: %d Balls %d", m_wall.getBrickCount(), m_wall.getBallCount());
            if (m_wall.isBallLost()) {
                if (m_wall.ballEnd()) {
                    m_wall.wallReset();
                    m_message = "Game over";
                }
                m_wall.ballReset();
                m_gameTimer.stop();
            } else if (m_wall.isDone()) {
                if (m_wall.hasLevel()) {
                    m_message = "Go to Next Level";
                    m_gameTimer.stop();
                    m_wall.ballReset();
                    m_wall.wallReset();
                    m_wall.nextLevel();
                } else {
                    m_message = "ALL WALLS DESTROYED";
                    m_gameTimer.stop();
                }
            }

            repaint();
        });*/ //Extracted lambda expression method to reduce indentation level
        //m_gameTimer = new Timer(DELAY, e -> timeActionListener());
        this.setGameTimer(new Timer(DELAY, e -> timeActionListener()));
    }

    private void initialize() {
        this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        final int X_COORDINATE = 250;
        final int Y_COORDINATE = 225;

        Graphics2D g2d = (Graphics2D) g;

        clear(g2d);

        g2d.setColor(Color.BLUE);
        //g2d.drawString(m_message, 250, 225);
        g2d.drawString(this.getMessage(), X_COORDINATE, Y_COORDINATE);

        drawBall(this.getWall().getBall(), g2d);

        for (Brick b : this.getWall().getBricks())
            if (!b.isBroken())
                drawBrick(b, g2d);

        //drawPlayer(m_wall.player, g2d);
        drawPlayer(this.getWall().getPlayer(), g2d);

        if (isShowPauseMenu())
            drawMenu(g2d);

        Toolkit.getDefaultToolkit().sync();
    }

    private void clear(Graphics2D g2d) {
        Color tmp = g2d.getColor();
        g2d.setColor(BG_COLOR);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(tmp);
    }

    private void drawBrick(Brick brick, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        g2d.setColor(brick.getInnerColor());
        g2d.fill(brick.getBrick());

        g2d.setColor(brick.getBorderColor());
        g2d.draw(brick.getBrick());


        g2d.setColor(tmp);
    }

    private void drawBall(Ball ball, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = ball.getBallFace();

        g2d.setColor(ball.getInnerColor());
        g2d.fill(s);

        g2d.setColor(ball.getBorderColor());
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawPlayer(Paddle p, Graphics2D g2d) {
        Color tmp = g2d.getColor();

        Shape s = p.getPaddleFace();
        g2d.setColor(Paddle.INNER_COLOR);
        g2d.fill(s);

        g2d.setColor(Paddle.BORDER_COLOR);
        g2d.draw(s);

        g2d.setColor(tmp);
    }

    private void drawMenu(Graphics2D g2d) {
        obscureGameBoard(g2d);
        drawPauseMenu(g2d);
    }

    private void obscureGameBoard(Graphics2D g2d) {
        final float ALPHA = 0.55f;

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();

        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                                                       ALPHA);
        g2d.setComposite(ac);

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, DEF_WIDTH, DEF_HEIGHT);

        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    private void drawPauseMenu(Graphics2D g2d) {
        final float HALF = 0.5f;
        final float TENTH = 0.1f;
        final float EIGTH = 0.125f;
        final float QUATER = 0.25f;
        final int DOUBLE = 2;
        final int TRIPLE = 3;


        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();


        g2d.setFont(this.getMenuFont());
        g2d.setColor(MENU_COLOR);

        if (this.getStrLen() == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            //m_strLen = m_menuFont.getStringBounds(PAUSE, frc).getBounds().width;
            this.setStrLen(this.getMenuFont()
                               .getStringBounds(PAUSE, frc)
                               .getBounds().width);
        }

        int x = (int) ((this.getWidth() - this.getStrLen()) * HALF);
        int y = (int) (this.getHeight() * TENTH);

        g2d.drawString(PAUSE, x, y);

        x = (int) (this.getWidth() * EIGTH);
        y = (int) (this.getHeight() * QUATER);


        //if (m_continueButtonRect == null) {
        if (this.getContinueButtonRect() == null) {
            FontRenderContext frc = g2d.getFontRenderContext();
            //m_continueButtonRect = m_menuFont.getStringBounds(CONTINUE, frc).getBounds();
            //m_continueButtonRect.setLocation(x, y - m_continueButtonRect.height);
            this.setContinueButtonRect(this.getMenuFont()
                                           .getStringBounds(CONTINUE, frc)
                                           .getBounds());
            this.getContinueButtonRect().setLocation(x, y - this.getContinueButtonRect().height);
        }

        g2d.drawString(CONTINUE, x, y);

        y *= DOUBLE;

//        if (m_restartButtonRect == null) {
//            m_restartButtonRect = (Rectangle) m_continueButtonRect.clone();
//            m_restartButtonRect.setLocation(x, y - m_restartButtonRect.height);
//        }
        if (this.getRestartButtonRect() == null) {
            this.setRestartButtonRect((Rectangle) this.getContinueButtonRect()
                                                      .clone());
            this.getRestartButtonRect()
                .setLocation(x, y - this.getRestartButtonRect().height);
        }

        g2d.drawString(RESTART, x, y);

        y *= TRIPLE * HALF;

//        if (m_exitButtonRect == null) {
//            m_exitButtonRect = (Rectangle) m_continueButtonRect.clone();
//            m_exitButtonRect.setLocation(x, y - m_exitButtonRect.height);
//        }
        if (this.getExitButtonRect() == null) {
            this.setExitButtonRect((Rectangle) this.getContinueButtonRect()
                                                   .clone());
            this.getExitButtonRect().setLocation(x, y - this.getExitButtonRect().height);
        }

        g2d.drawString(EXIT, x, y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_LEFT) {
            this.getWall().getPlayer().moveLeft();
        }
        if (code == KeyEvent.VK_RIGHT) {
            this.getWall().getPlayer().movRight();
        }
        if (code == KeyEvent.VK_SPACE) {
            //if (!m_showPauseMenu)
            if (!this.isShowPauseMenu())
//                if (m_gameTimer.isRunning())
//                    m_gameTimer.stop();
                if (this.getGameTimer().isRunning())
                    this.getGameTimer().stop();
                else
                    this.getGameTimer().start();
        }
        if (code == KeyEvent.VK_ESCAPE) {
            // m_showPauseMenu = !m_showPauseMenu;
            this.setShowPauseMenu(!this.isShowPauseMenu());
            repaint();
            //m_gameTimer.stop();
            this.getGameTimer().stop();
        }
        if (code == KeyEvent.VK_F1) {
            if (e.isAltDown() && e.isShiftDown())
                //m_debugConsole.setVisible(true);
                this.getDebugConsole().setVisible(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        this.getWall().getPlayer().stop();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!this.isShowPauseMenu())
            return;
        //if (m_continueButtonRect.contains(p)) {
        if (this.getContinueButtonRect().contains(p)) {
            this.setShowPauseMenu(false);
            repaint();
        } else if (this.getRestartButtonRect().contains(p)) {
            this.setMessage("Restarting Game...");
            this.getWall().ballReset();
            this.getWall().wallReset();
            this.setShowPauseMenu(false);
            repaint();
        } else if (this.getExitButtonRect().contains(p)) {
            System.exit(0);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
//        if (m_exitButtonRect != null && m_showPauseMenu) {
//            if (m_exitButtonRect.contains(p) || m_continueButtonRect.contains(p) || m_restartButtonRect
//                    .contains(p))
        if (this.getExitButtonRect() != null && this.isShowPauseMenu()) {
            if (this.getExitButtonRect().contains(p) ||
                this.getContinueButtonRect().contains(p) ||
                this.getRestartButtonRect().contains(p))
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        } else {
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void onLostFocus() {
        //m_gameTimer.stop();
        this.getGameTimer().stop();
//        m_message = "Focus Lost";
        this.setMessage("Focus Lost");
        repaint();
    }

    private void timeActionListener() {
        //m_wall.move();
        this.getWall().move();
//        m_wall.findImpacts();
        this.getWall().findImpacts();
//        m_message = String.format("Bricks: %d Balls %d", m_wall.getBrickCount(), m_wall
//                .getBallCount());
        this.setMessage(String.format("Bricks: %d Balls %d",
                                      this.getWall().getBrickCount(),
                                      this.getWall().getBallCount()));
        if (this.getWall().isBallLost()) {
            if (this.getWall().ballEnd()) {
                this.getWall().wallReset();
                this.setMessage("Game Over");
            }
            this.getWall().ballReset();
            this.getGameTimer().stop();
        } else if (this.getWall().isDone()) {
            if (this.getWall().hasLevel()) {
                this.setMessage("Go to Next Level");
                this.getGameTimer().stop();
                this.getWall().ballReset();
                this.getWall().wallReset();
                this.getWall().nextLevel();
            } else {
                this.setMessage("ALL WALLS DESTROYED");
                this.getGameTimer().stop();
            }
        }
        repaint();
    }


}

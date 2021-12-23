package com.example.migrateFx;

import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.Ball;

import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameBoard extends Pane {

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
    private static final Color MENU_COLOR = Color.rgb(0, 255, 0);
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;
    private AnimationTimer m_gameTimer;

    private Wall1 m_wall;

    private String m_message;
    private boolean pause;
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

    public AnimationTimer getGameTimer() {
        return m_gameTimer;
    }

    public void setGameTimer(AnimationTimer gameTimer) {
        this.m_gameTimer = gameTimer;
    }

    public Wall1 getWall() {
        return m_wall;
    }

    public void setWall(Wall1 wall) {
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

    public GameBoard(BorderPane parent) {

        //m_strLen = 0;
        this.setStrLen(0);

        //m_showPauseMenu = false;
        this.setShowPauseMenu(false);

        //m_menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);
        this.setMenuFont(new Font("Monospaced", TEXT_SIZE));

        this.initialize(parent);
        //m_message = "Press SPACE to start";
        this.setMessage("Press SPACE to start");
        TestModel model = new TestModel(new Point2D(0, 0));
        model.size = new SimpleObjectProperty<>(new Dimension2D(DEF_WIDTH, DEF_HEIGHT));
        model.bounds = new SimpleObjectProperty<>(this.getBoundsInParent());
        //m_wall = new Wall(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT), 30, 3, 6.0 / 2, new Point(300, 430));
        this.setWall(new Wall1(new Rectangle(0, 0, DEF_WIDTH, DEF_HEIGHT),
                               BRICK_COUNT, LINE_COUNT, BRICK_DIMENSION_RATIO,
                               new Point2D(X, Y)));
        this.getWall().setModel(model);

        //m_debugConsole = new DebugConsole(owner, m_wall, this);
        //this.setDebugConsole(new DebugConsole(owner, m_wall, this));

        //initialize the first level
        //m_wall.nextLevel();
        this.getWall().nextLevel();
        //  paint(this.getGraphicsContext2D());
        paint();

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
        this.setGameTimer(new AnimationTimer() {
            @Override
            public void handle(long l) {
                timeActionListener();
            }
        });
        getGameTimer().start();
    }

    private void initialize(BorderPane parent) {
        this.setHeight(DEF_HEIGHT);
        this.setWidth(DEF_WIDTH);
//        this.setPrefSize(DEF_WIDTH, DEF_HEIGHT);
        //this.setPreferredSize(new Dimension(DEF_WIDTH, DEF_HEIGHT));
        this.setFocused(true);
        //this.setFocusable(true);
        this.requestFocus();
        //this.requestFocusInWindow();
        this.setOnKeyPressed(this::keyPressed);
        this.setOnKeyReleased(keyEvent -> keyReleased(keyEvent));
        //parent.addKeyListener(this);
        this.setOnMouseMoved(this::mouseMoved);
        this.setOnMouseClicked(this::mouseClicked);
        this.addEventFilter(MouseEvent.ANY, (e) -> this.requestFocus());
        //this.addMouseListener(this);
        this.setOnMouseMoved(this::mouseMoved);
//        this.addMouseMotionListener(this);
    }

    public void paint() {
        final int X_COORDINATE = 250;
        final int Y_COORDINATE = 225;


        clear();
        Text text = new Text();
        text.setFill(Color.BLUE);
//        g.setFill(Color.BLUE);
        //g2d.drawString(m_message, 250, 225);
        text.setText(this.getMessage());
        this.getChildren().add(text);
        text.setLayoutX(X_COORDINATE);
        text.setLayoutY(Y_COORDINATE);
//        g.drawString(this.getMessage(), X_COORDINATE, Y_COORDINATE);
        if (!this.getChildren().contains(this.getWall().getBall()))
            drawBall(this.getWall().getBall());

        for (Brick b : this.getWall().getBricks())
            if (!b.getModel().isBroken())
                drawBrick(b);

        //drawPlayer(m_wall.player, g2d);
        drawPlayer(this.getWall().getPlayer());

        if (isShowPauseMenu())
            System.out.println("DRAW PAUSE MENU");
        //drawMenu(g);

        //Toolkit.getDefaultToolkit().sync();
    }

    private void clear() {
//        g2d.setFill(BG_COLOR);
//        g2d.fillRect(0, 0, (int)getWidth(), (int)getHeight());
//        g2d.setFill(tmp);
    }

    private void drawBrick(Brick brick) {
//        Color tmp = (Color) g2d.getFill();

//        g2d.setFill(brick.getInnerColor());
//        g2d.fillRect(((Rectangle)brick.getBrickFace()).getX(), ((Rectangle) brick.getBrickFace()).getY(),
//                     brick.getSize().getWidth(), brick.getSize().getHeight());

//        g2d.setFill(brick.getBorderColor());
//        g2d.strokeRect(((Rectangle)brick.getBrickFace()).getX(), ((Rectangle) brick.getBrickFace()).getY(),
//                       brick.getSize().getWidth(), brick.getSize().getHeight());
        if (!this.getChildren().contains(brick))
            brick.getView().createView(this);
//        g2d.setFill(tmp);
    }

    private void drawBall(Ball ball) {
//        Color tmp = (Color) g2d.getFill();
        if (!this.getChildren().contains(ball))
            ball.getView().createView(this);
//        Shape s = ball.getBallFace();
//        Bounds bou = ball.getBallFace().getBoundsInParent();
//        g2d.setFill(ball.getInnerColor());
//        g2d.fillOval(bou.getMinX(), bou.getMinY() ,bou.getWidth(),bou.getHeight());
//
//        g2d.setFill(ball.getBorderColor());
//        g2d.strokeOval(bou.getMinX(), bou.getMinY() ,bou.getWidth(), bou.getHeight());
//
//        g2d.setFill(tmp);
//
//        g2d.strokeLine(bou.getMinX(), bou.getMinY(), bou.getMinX() + bou.getWidth(), bou.getMinY());
//        g2d.strokeLine(bou.getMinX(), bou.getMinY(), bou.getMinX(), bou.getMinY() + bou.getHeight());
//        g2d.strokeLine(bou.getMaxX(), bou.getMaxY(), bou.getMaxX() - bou.getWidth(), bou.getMaxY());
//        g2d.strokeLine(bou.getMaxX(), bou.getMaxY(), bou.getMaxX(), bou.getMaxY() - bou.getHeight());

    }

    private void drawPlayer(Paddle p) {
        Rectangle paddle = new Rectangle(40, 10);
        this.getChildren().add(paddle);
        paddle.setLayoutX(this.getWidth()/2 - 20);
        paddle.setLayoutY(this.getHeight() - 20);
//        Color tmp = (Color) g2d.getFill();
//
//        Shape s = p.getPaddleFace();
//        g2d.setFill(Paddle.INNER_COLOR);
//        g2d.fillRect(((Rectangle)s).getX(), ((Rectangle) s).getY() , ((Rectangle) s).getWidth(), ((Rectangle) s).getHeight());
//
//        g2d.setFill(Paddle.BORDER_COLOR);
//        g2d.strokeRect(((Rectangle)s).getX(), ((Rectangle) s).getY() , ((Rectangle) s).getWidth(), ((Rectangle) s).getHeight());
//
//        g2d.setFill(tmp);
    }

//    private void drawMenu(GraphicsContext g2d) {
//        obscureGameBoard(g2d);
//        drawPauseMenu(g2d);
//    }
//
//    private void obscureGameBoard(GraphicsContext g2d) {
//        final float ALPHA = 0.55f;
//
//        //Composite tmp = g2d.get();
//        Color tmpColor = (Color) g2d.getFill();
//
////        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
////                                                       ALPHA);
////        g2d.setComposite(ac);
//
//        g2d.setFill(Color.BLACK);
//        g2d.fillRect(0, 0, DEF_WIDTH, DEF_HEIGHT);
//
////        g2d.setComposite(tmp);
//        g2d.setFill(tmpColor);
//    }

//    private void drawPauseMenu(GraphicsContext g2d) {
//        final float HALF = 0.5f;
//        final float TENTH = 0.1f;
//        final float EIGTH = 0.125f;
//        final float QUATER = 0.25f;
//        final int DOUBLE = 2;
//        final int TRIPLE = 3;
//
//
//        Font tmpFont = g2d.getFont();
//        Color tmpColor = (Color) g2d.getFill();
//
//
//        g2d.setFont(this.getMenuFont());
//        g2d.setFill(MENU_COLOR);
//
//        if (this.getStrLen() == 0) {
////            FontRenderContext frc = g2d.getFontRenderContext();
//            //m_strLen = m_menuFont.getStringBounds(PAUSE, frc).getBounds().width;
//            this.setStrLen((int) this.getMenuFont().getSize());
//                               //.getStringBounds(PAUSE, frc)
//                               //.getBounds().width);
//        }
//
//        int x = (int) ((this.getWidth() - this.getStrLen()) * HALF);
//        int y = (int) (this.getHeight() * TENTH);
//
//        g2d.strokeText(PAUSE, x, y);
//
//        x = (int) (this.getWidth() * EIGTH);
//        y = (int) (this.getHeight() * QUATER);
//
//
//        //if (m_continueButtonRect == null) {
//        if (this.getContinueButtonRect() == null) {
//            //FontRenderContext frc = g2d.getFontRenderContext();
//            //m_continueButtonRect = m_menuFont.getStringBounds(CONTINUE, frc).getBounds();
//            //m_continueButtonRect.setLocation(x, y - m_continueButtonRect.height);
//            this.setContinueButtonRect(g2d.g);
////                                           .getStringBounds(CONTINUE, frc)
////                                           .getBounds());
//            this.getContinueButtonRect().setLocation(x, y - this.getContinueButtonRect().height);
//        }
//
//        g2d.drawString(CONTINUE, x, y);
//
//        y *= DOUBLE;
//
////        if (m_restartButtonRect == null) {
////            m_restartButtonRect = (Rectangle) m_continueButtonRect.clone();
////            m_restartButtonRect.setLocation(x, y - m_restartButtonRect.height);
////        }
//        if (this.getRestartButtonRect() == null) {
//            this.setRestartButtonRect((Rectangle) this.getContinueButtonRect()
//                                                      .clone());
//            this.getRestartButtonRect()
//                .setLocation(x, y - this.getRestartButtonRect().height);
//        }
//
//        g2d.drawString(RESTART, x, y);
//
//        y *= TRIPLE * HALF;
//
////        if (m_exitButtonRect == null) {
////            m_exitButtonRect = (Rectangle) m_continueButtonRect.clone();
////            m_exitButtonRect.setLocation(x, y - m_exitButtonRect.height);
////        }
//        if (this.getExitButtonRect() == null) {
//            this.setExitButtonRect((Rectangle) this.getContinueButtonRect()
//                                                   .clone());
//            this.getExitButtonRect().setLocation(x, y - this.getExitButtonRect().height);
//        }
//
//        g2d.drawString(EXIT, x, y);
//
//        g2d.setFont(tmpFont);
//        g2d.setColor(tmpColor);
//    }

    public void keyPressed(KeyEvent e) {
        switch (e.getCode()){
            case LEFT -> this.getWall().getPlayer().moveLeft();
            case RIGHT -> this.getWall().getPlayer().movRight();
            case SPACE -> {
                if (!this.isShowPauseMenu())
                    if (!pause)
                        this.getGameTimer().stop();
                    else
                        this.getGameTimer().start();
            }
            case ESCAPE -> {
                this.setShowPauseMenu(!this.isShowPauseMenu());
                //repaint();
                //m_gameTimer.stop();
                this.getGameTimer().stop();
            }
            case F1 -> {
                if (e.isAltDown() && e.isShiftDown())
                    //m_debugConsole.setVisible(true);
                    this.getDebugConsole().setVisible(true);
            }
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
        this.getWall().getPlayer().stop();
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        //Point p = mouseEvent.getPoint();
        Point2D p = new Point2D(mouseEvent.getX(), mouseEvent.getY());

        if (!this.isShowPauseMenu())
            return;
        //if (m_continueButtonRect.contains(p)) {
        if (this.getContinueButtonRect().contains(p)) {
            this.setShowPauseMenu(false);
            //repaint();
        } else if (this.getRestartButtonRect().contains(p)) {
            this.setMessage("Restarting Game...");
            this.getWall().ballReset();
            this.getWall().wallReset();
            this.setShowPauseMenu(false);
            paint();
        } else if (this.getExitButtonRect().contains(p)) {
            System.exit(0);
        }

    }


    public void mouseMoved(MouseEvent mouseEvent) {
        Point2D p = new Point2D(mouseEvent.getX(), mouseEvent.getY());
//        if (m_exitButtonRect != null && m_showPauseMenu) {
//            if (m_exitButtonRect.contains(p) || m_continueButtonRect.contains(p) || m_restartButtonRect
//                    .contains(p))
        if (this.getExitButtonRect() != null && this.isShowPauseMenu()) {
            if (this.getExitButtonRect().contains(p) ||
                    this.getContinueButtonRect().contains(p) ||
                    this.getRestartButtonRect().contains(p))
                this.setCursor(Cursor.OPEN_HAND);
            else
                this.setCursor(Cursor.DEFAULT);
        } else {
            this.setCursor(Cursor.DEFAULT);
        }
    }

    public void onLostFocus() {
        //m_gameTimer.stop();
        //this.getGameTimer().stop();
//        m_message = "Focus Lost";
        this.setMessage("Focus Lost");
        paint();
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
//        paint();
    }


}

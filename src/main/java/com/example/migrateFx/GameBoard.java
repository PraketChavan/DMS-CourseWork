package com.example.migrateFx;

import com.example.migrateFx.handler.ImpactHandler;
import com.example.migrateFx.model.GameModel;
import com.example.migrateFx.wrappers.Ball;
import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.Paddle;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Arrays;


public class GameBoard extends Pane {

    public static final int DELAY = 10;
    private static final String CONTINUE = "Continue";
    private static final String RESTART = "Restart";
    private static final String EXIT = "Exit";
    private static final String PAUSE = "Pause Menu";
    private static final int TEXT_SIZE = 30;
    private static final Color MENU_COLOR = Color.rgb(0, 255, 0);
    private static final int DEF_WIDTH = 640;
    private static final int DEF_HEIGHT = 450;
    private static final Color BG_COLOR = Color.WHITE;
    private AnimationTimer m_gameTimer;
    private final ImpactHandler m_impactHandler;
    private Wall m_wall;
    private Line m_line = new Line();

    private String m_message;
    private boolean pause;
    private boolean m_showPauseMenu;

    private Font m_menuFont;

    private Rectangle m_continueButtonRect;
    private Rectangle m_exitButtonRect;
    private Rectangle m_restartButtonRect;
    private int m_strLen;

    /*
    Added getters and setter for non final member variable
     */

    public AnimationTimer getGameTimer() {
        return m_gameTimer;
    }

    public void setGameTimer(AnimationTimer gameTimer) {
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

    private static GameBoard m_GameBoardInstance;

    public static GameBoard getGameBoardInstance(BorderPane parent) {
        if (m_GameBoardInstance == null)
            setGameBoardInstance(new GameBoard(parent));
        return m_GameBoardInstance;
    }

    public static GameBoard getGameBoardInstance() {
        return m_GameBoardInstance;
    }

    public static void setGameBoardInstance(
            GameBoard m_GameBoardInstance) {
        GameBoard.m_GameBoardInstance = m_GameBoardInstance;
    }

    private GameBoard(BorderPane parent) {

        //m_strLen = 0;
        this.setStrLen(0);

        //m_showPauseMenu = false;
        this.setShowPauseMenu(false);

        //m_menuFont = new Font("Monospaced", Font.PLAIN, TEXT_SIZE);
        this.setMenuFont(new Font("Monospaced", TEXT_SIZE));

        this.initialize(parent);
        //m_message = "Press SPACE to start";
        this.setMessage("Press SPACE to start");
        GameModel model = GameModel.getGameInstance();

        this.setWall(Wall.getWallInstance());

        this.getWall().nextLevel();
        m_impactHandler = new ImpactHandler(model);

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
        this.getChildren().add(m_line);

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

        for (Brick b : this.getWall().getBricks()) {
            Arrays.stream(this.getWall().getBricks()).toList().forEach(
                    System.out::println);
            if (!b.getModel().checkBroken())
                drawBrick(b);
        }

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

        if (!this.getChildren().contains(brick)) {
            getImpactHandler().addBrick(brick.getModel());
            brick.getView().createView(this);
        }
    }

    private void drawBall(Ball ball) {
//        Color tmp = (Color) g2d.getFill();
        if (!this.getChildren().contains(ball)) {
            getImpactHandler().addBall(ball.getModel());
            ball.getView().createView(this);
        }

    }

    private void drawPlayer(Paddle p) {
        if (!this.getChildren().contains(p.getView().getView())) {
            getImpactHandler().addPaddle(p.getModel());
            p.getView().createView(this);
        }
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

    private void drawPauseMenu() {
        getGameTimer().stop();
        getParent().setEffect(new GaussianBlur());

        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(getParent().getScene().getWindow());
        popupStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PauseView.fxml"));
        try {
            popupStage.setScene(new Scene(loader.load(), Color.TRANSPARENT));
        } catch (IOException e) {
            e.printStackTrace();
        }

        popupStage.show();
    }
//    }

    public void keyPressed(KeyEvent e) {
        switch (e.getCode()){
            case LEFT -> this.getWall().getPlayer().getController().moveLeft();
            case RIGHT -> this.getWall().getPlayer().getController().moveRight();
            case SPACE -> {
                if (!this.isShowPauseMenu())
                    if (!pause) {
                        this.getGameTimer().stop();
                        pause = true;
                    }
                    else {
                        this.getGameTimer().start();
                        pause = false;
                    }
            }
            case ESCAPE -> {
                this.setShowPauseMenu(!this.isShowPauseMenu());
                drawPauseMenu();
                this.getGameTimer().stop();
            }
            case F1 -> {
                if (e.isAltDown() && e.isShiftDown()){

                    Stage stage = new Stage();
                    FXMLLoader root;
//                    Parent root = null;
                    try {
                        root = new FXMLLoader(getClass().getResource("/com/example/migrateFx/DebugView.fxml"));
                        stage.setScene(new Scene(root.load()));
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                    //m_debugConsole.setVisible(true);

            }
        }
    }

    public void keyReleased(KeyEvent keyEvent) {
       this.getWall().getPlayer().getController().stop();
    }


    public void mouseClicked(MouseEvent mouseEvent) {
        //Point p = mouseEvent.getPoint();
        Point2D p = new Point2D(mouseEvent.getX(), mouseEvent.getY());

        if (!this.isShowPauseMenu())
            return;
        //if (m_continueButtonRect.contains(p)) {
//        if (this.getContinueButtonRect().contains(p)) {
//            this.setShowPauseMenu(false);
//            //repaint();
//        } else if (this.getRestartButtonRect().contains(p)) {
//            this.setMessage("Restarting Game...");
//            this.getWall().ballReset();
//            this.getWall().wallReset();
//            this.setShowPauseMenu(false);
////            paint();
//        } else if (this.getExitButtonRect().contains(p)) {
//            System.exit(0);
//        }

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
//        paint();
    }

    public ImpactHandler getImpactHandler() {
        return m_impactHandler;
    }

    private void timeActionListener() {
        //m_wall.move();
        this.getWall().move();
//        m_wall.findImpacts();
//        this.getWall().findImpacts();
        this.getImpactHandler().handleImpacts();
//        m_line.setFill(Color.RED);
//        m_line.setStartX(this.getWall().getBall().getModel().getXLocationProperty().get());
//        m_line.setStartY(this.getWall().getBall().getModel().getYLocationProperty().get());
//        m_line.setEndX(this.getWall().getBall().getModel().getXLocationProperty().get());
//        m_line.setEndX(this.getWall().getBall().getModel().getYLocationProperty().get());

//        m_message = String.format("Bricks: %d Balls %d", m_wall.getBrickCount(), m_wall
//                .getBallCount());
        this.setMessage(String.format("Bricks: %d Balls %d",
                                      this.getWall().getBrickCount(),
                                      this.getWall().getBallCount()));
//        System.out.println(getMessage());
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

package code;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Wall {

    public static final int LEVELS_COUNT = 4;
    public static final int X_SPEED_BOUND = 5;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;
    public static final int BALL_COUNT = 3;
    public static final int OFFSET = 2;
    public static final int Y_SPEED_BOUND = 3;
    public static final int PADDLE_WIDTH = 150;
    public static final int PADDLE_HEIGHT = 10;

    private Brick[] m_bricks;
    private Ball m_ball;
    private Paddle m_player;
    private Random m_rnd;
    private Rectangle m_area;
    private Brick[][] m_levels;
    private int m_level;

    private Point m_startPoint;
    private int m_brickCount;
    private int m_ballCount;
    private boolean m_ballLost;

    public Random getRnd() {
        return m_rnd;
    }

    public void setRnd(Random rnd) {
        this.m_rnd = rnd;
    }

    public Rectangle getArea() {
        return m_area;
    }

    public void setArea(Rectangle area) {
        this.m_area = area;
    }

    public Brick[][] getLevels() {
        return m_levels;
    }

    public void setLevels(Brick[][] levels) {
        this.m_levels = levels;
    }

    public int getLevel() {
        return m_level;
    }

    public void setLevel(int level) {
        this.m_level = level;
    }

    public Point getStartPoint() {
        return m_startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.m_startPoint = startPoint;
    }

    public void setBrickCount(int brickCount) {
        this.m_brickCount = brickCount;
    }

    public void setBallCount(int ballCount) {
        this.m_ballCount = ballCount;
    }

    public void setBallLost(boolean ballLost) {
        this.m_ballLost = ballLost;
    }

    public int getBrickCount() {
        return m_brickCount;
    }

    public int getBallCount() {
        return m_ballCount;
    }

    public boolean isBallLost() {
        return m_ballLost;
    }

    public boolean isDone() {
        return m_brickCount == 0;
    }

    public Brick[] getBricks() {
        return m_bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.m_bricks = bricks;
    }

    public Ball getBall() {
        return m_ball;
    }

    public void setBall(Ball ball) {
        this.m_ball = ball;
    }

    public Paddle getPlayer() {
        return m_player;
    }

    public void setPlayer(Paddle player) {
        this.m_player = player;
    }

    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos) {

        //this.m_startPoint = new Point(ballPos);
        this.setStartPoint(new Point(ballPos));

//        m_levels = makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio);
//        m_level = 0;
        this.setLevels(makeLevels(drawArea, brickCount, lineCount, brickDimensionRatio));
        this.setLevel(0);

        //m_ballCount = BALL_COUNT;
//        this.setBallCount(BALL_COUNT);
        this.resetBallCount();
//        m_ballLost = false;
        this.setBallLost(false);

        //m_rnd = new Random();
        this.setRnd(new Random());

        this.makeBall(ballPos);

        int speedX, speedY;
        do {
            speedX = this.getRnd().nextInt(X_SPEED_BOUND) - OFFSET;
        } while (speedX == 0);

        do {
            speedY = - this.getRnd().nextInt(Y_SPEED_BOUND);
        } while (speedY == 0);

        this.getBall().setSpeed(speedX, speedY);

        //m_player = new Paddle((Point) ballPos.clone(), 150, 10, drawArea);
        this.setPlayer(new Paddle((Point) ballPos.clone(), PADDLE_WIDTH,
                                  PADDLE_HEIGHT, drawArea));
        //m_area = drawArea;
        this.setArea(drawArea);

    }

    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type) {
        final float HALF = 0.5f;
        // if brickCount is not divisible by line count,brickCount is adjusted to
        // the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;
        //30
        int brickOnLine = brickCnt / lineCnt; //10

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt * HALF; //31

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, type);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = new Brick2(p, brickSize);
        }
        return tmp;

    }

    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB) {
        // if brickCount is not divisible by line count, brickCount is adjusted
        // to the biggest multiple of lineCount smaller then brickCount
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen, (int) brickHgt);
        Point p = new Point();

        int i;
        for (i = 0; i < tmp.length; i++) {
            int line = i / brickOnLine;
            if (line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x = (line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x, y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ? makeBrick(p, brickSize, typeA) : makeBrick(p, brickSize, typeB);
        }

        for (double y = brickHgt; i < tmp.length; i++, y += 2 * brickHgt) {
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x, y);
            tmp[i] = makeBrick(p, brickSize, typeA);
        }
        return tmp;
    }

    public void makeBall(Point2D ballPos) {
        //m_ball = new Ball1(ballPos);
        this.setBall(new Ball1(ballPos));
    }

    public Brick[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio) {
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY);
        tmp[1] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, CEMENT);
        tmp[2] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, CLAY, STEEL);
        tmp[3] = makeChessboardLevel(drawArea, brickCount, lineCount, brickDimensionRatio, STEEL, CEMENT);
        return tmp;
    }

    public void move() {
        this.getPlayer().move();
        this.getBall().move();
    }

    public void findImpacts() {
//        if (m_player.impact(m_ball)) {
//            m_ball.reverseY();
//        } else if (impactWall()) {
//            // for efficiency reverse is done into method impactWall because for every brick program checks for horizontal and vertical impacts
//            m_brickCount--;
//        } else if (impactBorder()) {
//            m_ball.reverseX();
//        } else if (m_ball.getPosition().getY() < m_area.getY()) {
//            m_ball.reverseY();
//        } else if (m_ball.getPosition().getY() > m_area.getY() + m_area.getHeight()) {
//            m_ballCount--;
//            m_ballLost = true;
//        }
        if (this.getPlayer().impact(this.getBall())) {
            this.getBall().reverseY();
        } else if (impactWall()) {
            // for efficiency reverse is done into method impactWall because for
            // every brick program checks for horizontal and vertical impacts
            this.setBrickCount(this.getBrickCount() - 1);
        } else if (impactBorder()) {
            this.getBall().reverseX();
        } else if (this.getBall().getPosition().getY() < this.getArea().getY()){
            this.getBall().reverseY();
        } else if (this.getBall().getPosition().getY() >
                   this.getArea().getY() + this.getArea().getHeight()) {
            this.decrementBallCount();
            this.setBallLost(true);
        }
    }

    public boolean impactWall() {
//        for (Brick b : m_bricks) {
//            switch (b.findImpact(m_ball)) {
//                //Vertical Impact
//                case Brick.UP_IMPACT:
//                    m_ball.reverseY();
//                    return b.setImpact(m_ball.down, Brick.Crack.UP);
//                case Brick.DOWN_IMPACT:
//                    m_ball.reverseY();
//                    return b.setImpact(m_ball.up, Brick.Crack.DOWN);
//
//                //Horizontal Impact
//                case Brick.LEFT_IMPACT:
//                    m_ball.reverseX();
//                    return b.setImpact(m_ball.right, Brick.Crack.RIGHT);
//                case Brick.RIGHT_IMPACT:
//                    m_ball.reverseX();
//                    return b.setImpact(m_ball.left, Brick.Crack.LEFT);
//            }

        for (Brick b : this.getBricks()) {
            switch (b.findImpact(this.getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT -> {
                    this.getBall().reverseY();
                    return b.setImpact(this.getBall().down, Brick.Crack.UP);
                }
                case Brick.DOWN_IMPACT -> {
                    this.getBall().reverseY();
                    return b.setImpact(this.getBall().up, Brick.Crack.DOWN);
                }

                //Horizontal Impact
                case Brick.LEFT_IMPACT -> {
                    this.getBall().reverseX();
                    return b.setImpact(this.getBall().right, Brick.Crack.RIGHT);
                }
                case Brick.RIGHT_IMPACT -> {
                    this.getBall().reverseX();
                    return b.setImpact(this.getBall().left, Brick.Crack.LEFT);
                }
            }
        }
        return false;
    }

    public boolean impactBorder() {
        Point2D p = this.getBall().getPosition();
        return ((p.getX() < this.getArea().getX()) ||
                (p.getX() > (this.getArea().getX() + this.getArea().getWidth())));
    }

    public void ballReset() {
//        m_player.moveTo(m_startPoint);
//        m_ball.moveTo(m_startPoint);
        this.getPlayer().moveTo(this.getStartPoint());
        this.getBall().moveTo(this.getStartPoint());
        int speedX, speedY;
        do {
            speedX = this.getRnd().nextInt(5) - 2;
        } while (speedX == 0);
        do {
            speedY = -this.getRnd().nextInt(3);
        } while (speedY == 0);

        this.getBall().setSpeed(speedX, speedY);
        //m_ballLost = false;
        this.setBallLost(false);
    }

    public void wallReset() {
        for (Brick b : this.getBricks())
            b.repair();
//        m_brickCount = m_bricks.length;
//        m_ballCount = 3;
        this.setBrickCount(this.getBricks().length);
        this.resetBallCount();
    }

    public boolean ballEnd() {
        return this.getBallCount() == 0;
    }

    public void nextLevel() {
        this.setBricks(this.getLevels()[this.getLevel()]);
        this.setLevel(this.getLevel() + 1);
        this.setBrickCount(this.getBricks().length);
    }

    public boolean hasLevel() {
        return this.getLevel() < this.getLevels().length;
    }

    public void setBallXSpeed(int s) {
        this.getBall().setXSpeed(s);
    }

    public void setBallYSpeed(int s) {
        this.getBall().setYSpeed(s);
    }

    public void resetBallCount() {
        this.setBallCount(BALL_COUNT);
    }

    private Brick makeBrick(Point point, Dimension size, int type) {
        return switch (type) {
            case CLAY -> new Brick2(point, size);
            case STEEL -> new Brick3(point, size);
            case CEMENT -> new Brick1(point, size);
            default -> throw new IllegalArgumentException(String.format("Unknown Type:%d\n", type));
        };
    }

    private void decrementBallCount() {
        this.setBallCount(this.getBallCount() - 1);
    }

}

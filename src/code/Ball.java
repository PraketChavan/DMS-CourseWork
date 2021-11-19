package code;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

abstract public class Ball {

    private final static float HALF = 0.5f;

    private Shape ballFace;

    private Point2D center;

    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    public Point2D getCenter() {
        return center;
    }

    public void setCenter(Point2D center) {
        this.center = center;
    }

    public Point2D getUp() {
        return up;
    }

    public void setUp(Point2D up) {
        this.up = up;
    }

    public Point2D getDown() {
        return down;
    }

    public void setDown(Point2D down) {
        this.down = down;
    }

    public Point2D getLeft() {
        return left;
    }

    public void setLeft(Point2D left) {
        this.left = left;
    }

    public Point2D getRight() {
        return right;
    }

    public void setRight(Point2D right) {
        this.right = right;
    }

    public Color getBorder() {
        return border;
    }

    public void setBorder(Color border) {
        this.border = border;
    }

    public Color getInner() {
        return inner;
    }

    public void setInner(Color inner) {
        this.inner = inner;
    }

    public Color getBorderColor() {
        return getBorder();
    }

    public Color getInnerColor() {
        return getInner();
    }

    public Point2D getPosition() {
        return getCenter();
    }

    public Shape getBallFace() {
        return ballFace;
    }

    public void setBallFace(Shape ballFace) {
        this.ballFace = ballFace;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    protected abstract Shape makeBall(Point2D center, int radiusA, int radiusB);

    public Ball(Point2D center, int radiusA, int radiusB, Color inner, Color border) {

        this.setCenter(center);

        setUp(new Point2D.Double());
        setDown(new Point2D.Double());
        setLeft(new Point2D.Double());
        setRight(new Point2D.Double());

        getUp().setLocation(center.getX(), center.getY() - (radiusB * HALF));
        getDown().setLocation(center.getX(), center.getY() + (radiusB * HALF));

        getLeft().setLocation(center.getX() - (radiusA * HALF), center.getY());
        getRight().setLocation(center.getX() + (radiusA * HALF), center.getY());


        setBallFace(makeBall(center, radiusA, radiusB));
        this.setBorder(border);
        this.setInner(inner);
        setSpeedX(0);
        setSpeedY(0);
    }

    public void move() {
        RectangularShape tmp = (RectangularShape) getBallFace();
        getCenter().setLocation((getCenter().getX() + getSpeedX()),
                                (getCenter().getY() + getSpeedY()));

        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((getCenter().getX() - (w * HALF)),
                     (getCenter().getY() - (h * HALF)), w, h);
        setPoints(w, h);


        setBallFace(tmp);
    }

    public void setSpeed(int x, int y) {
        setSpeedX(x);
        setSpeedY(y);
    }

    public void setXSpeed(int s) {
        setSpeedX(s);
    }

    public void setYSpeed(int s) {
        setSpeedY(s);
    }

    public void reverseX() {
        setSpeedX(getSpeedX() * -1);
    }

    public void reverseY() {
        setSpeedY(getSpeedY() * -1);
    }

    public void moveTo(Point p) {
        final float HALF = 0.5f;
        getCenter().setLocation(p);

        RectangularShape tmp = (RectangularShape) getBallFace();
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((getCenter().getX() - (w * HALF)),
                     (getCenter().getY() - (h * HALF)), w, h);
        setBallFace(tmp);
    }

    public void setPoints(double width, double height) {
        getUp().setLocation(getCenter().getX(), getCenter().getY() - (height * HALF));
        getDown().setLocation(getCenter().getX(), getCenter().getY() + (height * HALF));

        getLeft().setLocation(getCenter().getX() - (width * HALF), getCenter().getY());
        getRight().setLocation(getCenter().getX() + (width * HALF), getCenter().getY());
    }
}

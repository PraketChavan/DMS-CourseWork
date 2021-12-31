package com.example.migrateFx.model.sprite;

import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.util.Movable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class BallModel extends SpriteModel implements Movable, Impactable {
    private static final int MOST_X_SPEED = 3;
    private static final int NORMAL_X_SPEED = 2;
    private final Point2D START_LOCATION;
    private SimpleObjectProperty<Point2D> m_Top;
    private SimpleObjectProperty<Point2D> m_Bottom;
    private SimpleObjectProperty<Point2D> m_Left;
    private SimpleObjectProperty<Point2D> m_Right;
    private SimpleObjectProperty<Point2D> m_Center;
    private SimpleDoubleProperty m_Radius;
    private SimpleBooleanProperty m_Collisions;
    private SimpleBooleanProperty m_lost;

    public void setSpeed(Point2D speed) {
        speed = speed.normalize().multiply(4);
        super.setSpeed(speed);
    }

    private void setBottom(Point2D bottom) {
        this.m_Bottom.set(bottom);
    }

    public Point2D getCenter() {
        return m_Center.get();
    }

    private void setCenter(Point2D center) {
        this.m_Center.set(center);
    }

    public SimpleBooleanProperty getCollisions() {
        return m_Collisions;
    }

    private void setLeft(Point2D left) {
        this.m_Left.set(left);
    }

    private double getRadius() {
        return m_Radius.get();
    }

    public void setRadius(double radius) {
        this.m_Radius.set(radius);
    }

    public void setRadius(SimpleDoubleProperty radius) {
        m_Radius = radius;
    }

    private void setRight(Point2D right) {
        this.m_Right.set(right);
    }

    private void setTop(Point2D top) {
        this.m_Top.set(top);
    }

    public boolean isCollisions() {
        return getCollisions().get();
    }

    public void setCollisions(boolean collisions) {
        this.getCollisions().set(collisions);
    }

    public void setCollisions(SimpleBooleanProperty collisions) {
        m_Collisions = collisions;
    }

    public boolean isLost() {
        return m_lost.get();
    }

    public void setLost(boolean lost) {
        this.m_lost.set(lost);
    }

    public BallModel(Point2D location) {
        super(location);
        START_LOCATION = location;
        initializeProperty();
    }

    public SimpleBooleanProperty lostProperty() {
        return m_lost;
    }

    public SimpleBooleanProperty collisionsProperty() {
        return getCollisions();
    }

    public SimpleDoubleProperty radiusProperty() {
        return m_Radius;
    }

    public SimpleObjectProperty<Point2D> topProperty() {
        return m_Top;
    }

    public SimpleObjectProperty<Point2D> bottomProperty() {
        return m_Bottom;
    }

    public SimpleObjectProperty<Point2D> leftProperty() {
        return m_Left;
    }

    public SimpleObjectProperty<Point2D> rightProperty() {
        return m_Right;
    }

    public SimpleObjectProperty<Point2D> centerProperty() {
        return m_Center;
    }

    public void initializeCenter() {
        centerProperty().set(getLocation().add(getRadius(), getRadius()));
    }

    public void updateLocations() {
        setTop(getCenter().add(0, -getRadius()));
        setBottom(getCenter().add(0, getRadius()));
        setLeft(getCenter().add(-getRadius(), 0));
        setRight(getCenter().add(getRadius(), 0));
    }

    @Override
    public int findImpact(Impactable parent) {
        Bounds bound = GAME_BOUNDS;
        if (bottomProperty().get().getY() >= bound.getMaxY()) {
            setLost(true);
        }
        if (topProperty().get().getY() <= 0) {
            onImpact(UP);
            setCollisions(true);
            return UP;
        }
        if (leftProperty().get().getX() <= 0) {
            onImpact(LEFT);
            setCollisions(true);
            return LEFT;
        }
        if (rightProperty().get().getX() >= bound.getMaxX()) {
            onImpact(RIGHT);
            setCollisions(true);
            return RIGHT;
        }
        setCollisions(false);
        return -1;
    }

    @Override
    public void onImpact(int side) {
        if (!isCollisions()) {
            switch (side) {
                case UP, DOWN -> setSpeed(new Point2D(getSpeed().getX(),
                                                      -getSpeed().getY()));
                case LEFT, RIGHT -> setSpeed(new Point2D(-getSpeed().getX(),
                                                         getSpeed().getY()));

                case LEFT_MOST_IMPACT -> setSpeed(new Point2D(-MOST_X_SPEED, -getSpeed().getY()));
                case LEFT_IMPACT -> setSpeed(new Point2D(-NORMAL_X_SPEED, -getSpeed().getY()));
                case MIDDLE_IMPACT -> setSpeed(new Point2D(0, -getSpeed().getY()));
                case RIGHT_IMPACT -> setSpeed(new Point2D(NORMAL_X_SPEED, -getSpeed().getY()));
                case RIGHT_MOST_IMPACT -> setSpeed(new Point2D(MOST_X_SPEED, -getSpeed().getY()));
                case NO_IMPACT -> {
                    return;
                }
            }
            setCollisions(true);
        }
    }

    @Override
    public void move() {
        setLocation(getLocation().add(getSpeed()));
        centerProperty().set(centerProperty().get().add(getSpeed()));
        updateLocations();
    }

    @Override
    public void reset() {
        setLocation(START_LOCATION);
        centerProperty().set(START_LOCATION.add(getRadius(), getRadius()));
        updateLocations();
    }

    private void initBottomProperty(
            SimpleObjectProperty<Point2D> bottomProperty) {
        if (bottomProperty != null) {
            m_Bottom = bottomProperty;
            setBottom(new Point2D(0, 0));
        }
    }

    private void initCenterProperty(
            SimpleObjectProperty<Point2D> centerProperty) {
        if (centerProperty != null) {
            m_Center = centerProperty;
            setCenter(new Point2D(0, 0));
        }
    }

    private void initLeftProperty(SimpleObjectProperty<Point2D> leftProperty) {
        if (leftProperty != null) {
            m_Left = leftProperty;
            setLeft(new Point2D(0, 0));
        }
    }

    private void initRightProperty(
            SimpleObjectProperty<Point2D> rightProperty) {
        if (rightProperty != null) {
            m_Right = rightProperty;
            setRight(new Point2D(0, 0));
        }
    }

    private void initTopProperty(SimpleObjectProperty<Point2D> topProperty) {
        if (topProperty != null) {
            m_Top = topProperty;
            setTop(new Point2D(0, 0));
        }
    }

    private void initializeProperty() {
        initCenterProperty(new SimpleObjectProperty<>());
        initRightProperty(new SimpleObjectProperty<>());
        initBottomProperty(new SimpleObjectProperty<>());
        initLeftProperty(new SimpleObjectProperty<>());
        initTopProperty(new SimpleObjectProperty<>());
        setRadius(new SimpleDoubleProperty(0));
        setCollisions(new SimpleBooleanProperty(false));
        setLostProperty(new SimpleBooleanProperty(false));
    }

    private void setLostProperty(SimpleBooleanProperty prop) {
        m_lost = prop;
    }

}

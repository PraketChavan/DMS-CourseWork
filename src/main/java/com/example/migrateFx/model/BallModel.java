package com.example.migrateFx.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;

public class BallModel extends SpriteModel {
    private SimpleObjectProperty<Point2D> m_Top;
    private SimpleObjectProperty<Point2D> m_Bottom;
    private SimpleObjectProperty<Point2D> m_Left;
    private SimpleObjectProperty<Point2D> m_Right;
    private SimpleObjectProperty<Point2D> m_Center;
    private SimpleDoubleProperty m_Radius;

    private Point2D getBottom() {
        return m_Bottom.get();
    }

    private void setBottom(Point2D bottom) {
        this.m_Bottom.set(bottom);
    }

    private Point2D getCenter() {
        return m_Center.get();
    }

    private void setCenter(Point2D center) {
        this.m_Center.set(center);
    }

    private Point2D getLeft() {
        return m_Left.get();
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

    private Point2D getRight() {
        return m_Right.get();
    }

    public void setRadius(SimpleDoubleProperty radius) {
        m_Radius = radius;
    }

    private void setRight(Point2D right) {
        this.m_Right.set(right);
    }

    private Point2D getTop() {
        return m_Top.get();
    }

    private void setTop(Point2D top) {
        this.m_Top.set(top);
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


    public BallModel(Point2D location) {
        super(location);
        initializeProperty();
    }

    public void initializeCenter() {
        centerProperty().set(getLocation().add(getRadius(), getRadius()));
    }

    private void initializeProperty() {
        initCenterProperty(new SimpleObjectProperty<>());
        initRightProperty(new SimpleObjectProperty<>());
        initBottomProperty(new SimpleObjectProperty<>());
        initLeftProperty(new SimpleObjectProperty<>());
        initTopProperty(new SimpleObjectProperty<>());
        setRadius(new SimpleDoubleProperty(0));
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

    public void updateLocations() {
        setTop(getCenter().add(0,  -getRadius()));
        setBottom(getCenter().add(0, getRadius()));
        setLeft(getCenter().add(-getRadius(), 0));
        setRight(getCenter().add(getRadius(), 0));
    }

}

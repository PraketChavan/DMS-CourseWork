package com.example.migrateFx.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public abstract class SpriteModel {
    private final SimpleStringProperty m_Name;
    private final SimpleObjectProperty<Point2D> m_Location;
    private final SimpleDoubleProperty m_XLocation;
    private final SimpleDoubleProperty m_YLocation;
    private final SimpleObjectProperty<Point2D> m_Speed;
    private final SimpleObjectProperty<Bounds> m_Bounds;
    private final SimpleDoubleProperty m_width;
    private final SimpleDoubleProperty m_heigth;

    public Bounds getBounds() {
        return m_Bounds.get();
    }

    public void setBounds(Bounds bounds) {
        this.m_Bounds.set(bounds);
    }

    public Point2D getLocation() {
        return m_Location.get();
    }

    public void setLocation(Point2D location) {
        this.m_Location.set(location);
        this.getXLocationProperty().set(location.getX());
        this.getYLocationProperty().set(location.getY());
    }

    public SimpleObjectProperty<Point2D> getLocationProperty() {
        return m_Location;
    }

    public String getName() {
        return m_Name.get();
    }

    public void setName(String name) {
        this.m_Name.set(name);
    }

    public SimpleStringProperty getNameProperty() {
        return m_Name;
    }


    public Point2D getSpeed() {
        return m_Speed.get();
    }

    public void setSpeed(Point2D speed) {
        this.m_Speed.set(speed);
    }

    public SimpleObjectProperty<Point2D> getSpeedProperty() {
        return m_Speed;
    }


    public SimpleDoubleProperty getXLocationProperty() {
        return m_XLocation;
    }


    public SimpleDoubleProperty getYLocationProperty() {
        return m_YLocation;
    }


    public SpriteModel(Point2D location) {
        this.m_YLocation = new SimpleDoubleProperty();
        this.m_XLocation = new SimpleDoubleProperty();
        this.m_Location = new SimpleObjectProperty<>(location);
        this.m_Name = new SimpleStringProperty();
        this.m_Speed = new SimpleObjectProperty<>();
        this.m_Bounds = new SimpleObjectProperty<>();
        this.m_width = new SimpleDoubleProperty();
        this.m_heigth = new SimpleDoubleProperty();

        getLocationProperty().addListener((observableValue, point2D, t1) -> {
            setXLocation(t1.getX());
            setYLocation(t1.getY());
        });
    }

    public SimpleObjectProperty<Bounds> boundsProperty() {
        return m_Bounds;
    }

    public double getWidth() {
        return m_width.get();
    }

    public SimpleDoubleProperty widthProperty() {
        return m_width;
    }

    public void setWidth(double width) {
        this.m_width.set(width);
    }

    public double getHeigth() {
        return m_heigth.get();
    }

    public SimpleDoubleProperty heigthProperty() {
        return m_heigth;
    }

    public void setHeigth(double heigth) {
        this.m_heigth.set(heigth);
    }

    public void setXLocation(double XLocation) {
        this.m_XLocation.set(XLocation);
    }

    public void setYLocation(double YLocation) {
        this.m_YLocation.set(YLocation);
    }

    public abstract void reset();
}

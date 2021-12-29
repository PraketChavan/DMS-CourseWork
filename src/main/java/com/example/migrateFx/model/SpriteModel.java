package com.example.migrateFx.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public abstract class SpriteModel {
    private SimpleStringProperty m_Name;
    private SimpleObjectProperty<Point2D> m_Location;
    private SimpleDoubleProperty m_XLocation;
    private SimpleDoubleProperty m_YLocation;
    private SimpleObjectProperty<Point2D> m_Speed;
    private SimpleObjectProperty<Bounds> m_Bounds;

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

    public void setLocationProperty(
            SimpleObjectProperty<Point2D> location) {
        m_Location = location;
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

    public void setNameProperty(SimpleStringProperty name) {
        m_Name = name;
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

    public void setSpeedProperty(
            SimpleObjectProperty<Point2D> speed) {
        m_Speed = speed;
    }

    public SimpleDoubleProperty getXLocationProperty() {
        return m_XLocation;
    }

    public void setXLocationProperty(SimpleDoubleProperty XLocation) {
        m_XLocation = XLocation;
    }

    public SimpleDoubleProperty getYLocationProperty() {
        return m_YLocation;
    }

    public void setYLocationProperty(SimpleDoubleProperty YLocation) {
        m_YLocation = YLocation;
    }

    public SpriteModel(Point2D location) {
        this.m_YLocation = new SimpleDoubleProperty();
        this.m_XLocation = new SimpleDoubleProperty();
        this.m_Location = new SimpleObjectProperty<>(location);
        this.m_Name = new SimpleStringProperty();
        this.m_Speed = new SimpleObjectProperty<>();
        this.m_Bounds = new SimpleObjectProperty<>();
        getLocationProperty().addListener((observableValue, point2D, t1) -> {
            setXLocation(t1.getX());
            setYLocation(t1.getY());
        });
    }

    public SimpleObjectProperty<Bounds> boundsProperty() {
        return m_Bounds;
    }

    public void setXLocation(double XLocation) {
        this.m_XLocation.set(XLocation);
    }

    public void setYLocation(double YLocation) {
        this.m_YLocation.set(YLocation);
    }

    public abstract void reset();
}

package com.example.migrateFx.model.sprite;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * The abstract Model for the Sprite MVC.
 * <br>
 * It stores the generic data that all sprites will have and provides some
 * basic functionality for controller to modify the state/property of the
 * sprite.
 * <br>
 * @author Praket Chavan
 * @see com.example.migrateFx.controller.sprite.SpriteController
 * @see com.example.migrateFx.view.SpriteView
 */
public abstract class SpriteModel {
    /**
     * String Property that stores the spites name
     * @see #getName()
     * @see #setName(String)
     * @see #getNameProperty()
     */
    private final SimpleStringProperty m_Name;

    /**
     * ObjectProperty that stores a Point2D object that represent the
     * location of the sprite.
     * @see #getLocation()
     * @see #setLocation(Point2D)
     * @see #getLocationProperty()
     */
    private final SimpleObjectProperty<Point2D> m_Location;

    /**
     * Double Property that stores the X location of the sprite
     * @see #getXLocationProperty()
     */
    private final SimpleDoubleProperty m_XLocation;

    /**
     * Double Property that stores the Y location of the sprite
     * @see #getYLocationProperty()
     */
    private final SimpleDoubleProperty m_YLocation;

    /**
     * ObjectProperty that stores a Point2D object that represent the
     * speed of the sprite.
     * @see #getSpeed()
     * @see #setSpeed(Point2D)
     * @see #getSpeedProperty()
     */
    private final SimpleObjectProperty<Point2D> m_Speed;

    /**
     * ObjectProperty that stores a Bounds object that represent the
     * bounds / bounding box of the sprite.
     * @see #getBounds()
     * @see #boundsProperty()
     */
    private final SimpleObjectProperty<Bounds> m_Bounds;

    /**
     * Double Property that stores the width of the sprite
     * @see #widthProperty() ()
     */
    private final SimpleDoubleProperty m_width;

    /**
     * Double Property that stores the height of the sprite
     * @see #heigthProperty()
     */
    private final SimpleDoubleProperty m_heigth;

    /**
     * Gets the bounding box of the sprite in the screen
     * @return Bounds object
     * @see  #boundsProperty()
     */
    public Bounds getBounds() {
        return boundsProperty().get();
    }

    /**
     * Gets the height of the sprite view as a double
     * @return height of the sprite
     * @see #setHeigth(double)
     */
    public double getHeigth() {
        return heigthProperty().get();
    }

    /**
     * Sets the height of the sprite view to the passed value
     * @param heigth the new height of the sprite
     * @see #getHeigth()
     */
    public void setHeigth(double heigth) {
        heigthProperty().set(heigth);
    }

    /**
     * Gets the location of the sprite on the screen
     * @return Point2D object
     * @see #getLocationProperty()
     * @see #setLocation(Point2D)
     */
    public Point2D getLocation() {
        return m_Location.get();
    }

    /**
     * Sets the location of the sprite on the screen and updates the X
     * location and Y location property
     * @see #getLocationProperty()
     * @see #getLocation()
     */
    public void setLocation(Point2D location) {
        this.getLocationProperty().set(location);
        this.getXLocationProperty().set(location.getX());
        this.getYLocationProperty().set(location.getY());
    }

    /**
     * Gets the location property of the sprite
     * @return
     */
    private SimpleObjectProperty<Point2D> getLocationProperty() {
        return m_Location;
    }

    /**
     * Gets the name of the sprite
     * @return
     * @see #getNameProperty()
     * @see #setName(String)
     */
    public String getName() {
        return getNameProperty().get();
    }

    /**
     * Set the name property of the sprite to the
     * @param name the new name to set
     * @see #getName()
     * @see #getNameProperty()
     */
    public void setName(String name) {
        this.getNameProperty().set(name);
    }

    /**
     * Gets the StringProperty containing the name of the sprite
     * @return String Property
     * @see #getName()
     * @see #setName(String)
     */
    private SimpleStringProperty getNameProperty() {
        return m_Name;
    }

    /**
     * Get the Point2D object representing the speed of th sprite
     * @return
     * @see #setSpeed(Point2D)
     * @see #getSpeedProperty()
     */
    public Point2D getSpeed() {
        return getSpeedProperty().get();
    }

    /**
     * Set the speed of the sprite to the new speed
     * @param speed
     * @see #getSpeedProperty()
     * @see #getSpeed()
     */
    public void setSpeed(Point2D speed) {
        this.getSpeedProperty().set(speed);
    }

    /**
     * Get the ObjectProperty representing the speed of th sprite
     * @return
     * @see #setSpeed(Point2D)
     * @see #getSpeed()
     */
    private SimpleObjectProperty<Point2D> getSpeedProperty() {
        return m_Speed;
    }

    /**
     * Gets the width of the sprite
     * @return
     * @see #setWidth(double)
     * @see #widthProperty()
     */
    public double getWidth() {
        return widthProperty().get();
    }

    /**
     * Sets the width of the sprite to the new value
     * @param width the new width
     *
     * @see #getWidth()
     * @see #widthProperty()
     */
    public void setWidth(double width) {
        this.widthProperty().set(width);
    }

    /**
     * Gets the X location property of the sprite
     * @return
     */
    public SimpleDoubleProperty getXLocationProperty() {
        return m_XLocation;
    }
    /**
     * Gets the Y location property of the sprite
     * @return
     */
    public SimpleDoubleProperty getYLocationProperty() {
        return m_YLocation;
    }

    /**
     * Constructor to initialise the properties of the model and set the
     * location of the sprite. <br>
     * Also adds listener to the location property to update the X and Y
     * location whenever the location of the sprite changed
     * @param location the starting location of the sprite
     */
    protected SpriteModel(Point2D location) {
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

    /**
     * Gets the Object Property containing Bounds of the sprite
     * @return
     */
    public SimpleObjectProperty<Bounds> boundsProperty() {
        return m_Bounds;
    }

    /**
     * Gets the DoubleProperty representing width of the sprite
     * @return
     * @see #setWidth(double)
     * @see #getWidth()
     */
    public SimpleDoubleProperty widthProperty() {
        return m_width;
    }

    /**
     * Gets the DoubleProperty representing height of the sprite
     * @return
     * @see #setHeigth(double)
     * @see #getHeigth()
     */
    public SimpleDoubleProperty heigthProperty() {
        return m_heigth;
    }

    /**
     * Sets the X location of the sprite to the value passed
     * @param XLocation the new X location of the sprite
     * @see #getXLocationProperty()
     */
    public void setXLocation(double XLocation) {
        this.getXLocationProperty().set(XLocation);
    }

    /**
     * Sets the Y location of the sprite to the value passed
     * @param YLocation the new Y location of the sprite
     * @see #getYLocationProperty()
     */
    public void setYLocation(double YLocation) {
        this.getYLocationProperty().set(YLocation);
    }

    /**
     * This method is called when the game state is reset
     */
    public abstract void reset();
}

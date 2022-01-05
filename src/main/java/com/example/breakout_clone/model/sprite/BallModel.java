package com.example.breakout_clone.model.sprite;

import com.example.breakout_clone.util.Impactable;
import com.example.breakout_clone.util.Movable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * Model class for the Ball MVC. The class implements the {@link Movable} and
 * the {@link Impactable} interfaces. This class contains the main sprite
 * logic for how to move the ball and what happens when the ball collides
 * with the something
 *
 * @author Praket Chavan
 * @see SpriteModel
 */
public class BallModel extends SpriteModel implements Movable, Impactable {
    /**
     * Constant to define the X speed of the ball when it hits the edge of
     * the paddle
     */
    private static final int MOST_X_SPEED = 3;

    /**
     * Constant that defines the X speed of the ball when it hits the paddle
     * around the middle
     */
    private static final int NORMAL_X_SPEED = 2;

    /**
     * The start location of the ball
     *
     * @see #getSTART_LOCATION()
     */
    private final Point2D START_LOCATION;

    /**
     * The Point2D that represents the point on top of the ball
     *
     * @see #setTop(Point2D)
     * @see #topProperty()
     */
    private SimpleObjectProperty<Point2D> m_Top;

    /**
     * The Point2D object that represents the point on the bottom of the ball
     *
     * @see #bottomProperty()
     * @see #setBottom(Point2D)
     */
    private SimpleObjectProperty<Point2D> m_Bottom;

    /**
     * The Point2D object that represents the point on the left edge of the ball
     *
     * @see #leftProperty()
     * @see #setLeft(Point2D)
     */
    private SimpleObjectProperty<Point2D> m_Left;

    /**
     * The Point2D object that represents the point on the right edge of the
     * ball
     *
     * @see #rightProperty()
     * @see #setRight(Point2D)
     */
    private SimpleObjectProperty<Point2D> m_Right;

    /**
     * The Point2D object that represents the center point of the ball
     *
     * @see #centerProperty()
     * @see #setCenter(Point2D)
     * @see #getCenter()
     */
    private SimpleObjectProperty<Point2D> m_Center;

    /**
     * The Double property that stores the radius of the ball
     *
     * @see #radiusProperty()
     * @see #getRadius()
     * @see #setRadius(double)
     */
    private SimpleDoubleProperty m_Radius;

    /**
     * The Boolean property that stores whether the ball is colliding with
     * something or not
     *
     * @see #collisionsProperty()
     * @see #setCollisions(boolean)
     * @see #setCollisions(SimpleBooleanProperty)
     */
    private SimpleBooleanProperty m_Collisions;

    /**
     * The Boolean property that stores whether the ball has been lost or not
     *
     * @see #lostProperty()
     * @see #isLost()
     * @see #setLost(boolean)
     * @see #setLostProperty(SimpleBooleanProperty)
     */
    private SimpleBooleanProperty m_lost;

    /**
     * Gets the location of the center point of the ball
     *
     * @return the Point2D object that represents center of the ball
     * @see #setCenter(Point2D)
     */
    public Point2D getCenter() {
        return centerProperty().get();
    }

    /**
     * Sets the center property of the ball to the new value passed
     *
     * @param center the new center location
     * @see #centerProperty()
     * @see #getCenter()
     */
    private void setCenter(Point2D center) {
        centerProperty().set(center);
    }

    /**
     * Gets the current radius of the ball
     *
     * @return the radius of the Ball
     * @see #radiusProperty()
     * @see #setRadius(double)
     */
    private double getRadius() {
        return radiusProperty().get();
    }

    /**
     * Sets the radius of the ball to the new value
     *
     * @param radius the new radius of the ball
     * @see #radiusProperty()
     * @see #getRadius()
     */
    public void setRadius(double radius) {
        this.m_Radius.set(radius);
    }

    /**
     * Sets the radius property to the object that is passed
     *
     * @param radius the new Double property that is to be set
     * @see #radiusProperty()
     * @see #setRadius(double)
     * @see #getRadius()
     */
    private void setRadius(SimpleDoubleProperty radius) {
        m_Radius = radius;
    }

    /**
     * Gets the starting location of the Ball
     *
     * @return Point2D object that stores the starting X, Y co-ordinate
     */
    private Point2D getSTART_LOCATION() {
        return START_LOCATION;
    }

    /**
     * Returns the value of the collision boolean property
     *
     * @see #setCollisions(SimpleBooleanProperty)
     * @see #setCollisions(boolean)
     * @see #collisionsProperty()
     */
    private boolean isCollisions() {
        return collisionsProperty().get();
    }

    /**
     * Sets the collision property value to the value that is passed to the
     * method
     *
     * @param collisions the new value of the collision property
     * @see #collisionsProperty()
     * @see #isCollisions()
     */
    public void setCollisions(boolean collisions) {
        collisionsProperty().set(collisions);
    }

    /**
     * Sets the object reference of the collision property to the new object
     * that is passed
     *
     * @param collisions The new object that the collision property will now
     *                   reference
     * @see #collisionsProperty()
     * @see #isCollisions()
     * @see #setCollisions(boolean)
     */
    private void setCollisions(SimpleBooleanProperty collisions) {
        m_Collisions = collisions;
    }

    /**
     * Gets the value of the lost boolean property
     */
    public boolean isLost() {
        return lostProperty().get();
    }

    /**
     * Sets the value of the lost property to the new value that is passed
     *
     * @param lost the new value of the lost property
     * @see #isLost()
     * @see #lostProperty()
     */
    public void setLost(boolean lost) {
        lostProperty().set(lost);
    }

    /**
     * Sets the bottom property of the model to the new Point2D object that
     * is passed
     *
     * @param bottom the new bottom location
     * @see #bottomProperty()
     */
    private void setBottom(Point2D bottom) {
        bottomProperty().set(bottom);
    }

    /**
     * Sets the left point of the ball to the passes value
     *
     * @param left the new location of the left point of the ball
     * @see #leftProperty()
     */
    private void setLeft(Point2D left) {
        leftProperty().set(left);
    }

    /**
     * Sets the lost property object to the new object that is passed
     *
     * @param prop the new object to be set
     */
    private void setLostProperty(SimpleBooleanProperty prop) {
        m_lost = prop;
    }

    /**
     * Sets the right point location of the ball to the new value
     *
     * @param right the new location for the right property
     * @see #rightProperty()
     */
    private void setRight(Point2D right) {
        rightProperty().set(right);
    }

    /**
     * Sets the top position of the ball to the new location that is passed
     *
     * @param top the new location of the top property
     * @see #topProperty()
     */
    private void setTop(Point2D top) {
        topProperty().set(top);
    }

    /**
     * Constructor to initialise the start location and all the other
     * properties of the model
     *
     * @param location the starting location of the ball
     * @see SpriteModel
     */
    public BallModel(Point2D location) {
        super(location);
        START_LOCATION = location;
        initializeProperty();
    }

    /**
     * Gets the lost BooleanProperty
     *
     * @see #isLost()
     * @see #setLost(boolean)
     */
    public SimpleBooleanProperty lostProperty() {
        return m_lost;
    }

    /**
     * Gets the collision BooleanProperty
     *
     * @see #isCollisions()
     * @see #setCollisions(boolean)
     */
    private SimpleBooleanProperty collisionsProperty() {
        return m_Collisions;
    }

    /**
     * Gets the radius DoubleProperty
     *
     * @see #setRadius(double)
     * @see #getRadius()
     */
    private SimpleDoubleProperty radiusProperty() {
        return m_Radius;
    }

    /**
     * Gets the top SimpleObjectProperty containing the Point2D object
     *
     * @see #setTop(Point2D)
     */
    public SimpleObjectProperty<Point2D> topProperty() {
        return m_Top;
    }

    /**
     * Gets the bottom SimpleObjectProperty containing the Point2D object
     *
     * @see #setBottom(Point2D)
     */
    public SimpleObjectProperty<Point2D> bottomProperty() {
        return m_Bottom;
    }

    /**
     * Gets the left SimpleObjectProperty containing the Point2D object
     *
     * @see #setLeft(Point2D)
     */
    public SimpleObjectProperty<Point2D> leftProperty() {
        return m_Left;
    }

    /**
     * Gets the right SimpleObjectProperty containing the Point2D object
     *
     * @see #setRadius(double)
     */
    public SimpleObjectProperty<Point2D> rightProperty() {
        return m_Right;
    }

    /**
     * Initialises the center property by getting the location of the ball
     * and offsetting the X and Y by the Radius of the ball
     */
    public void initializeCenter() {
        centerProperty().set(getLocation().add(getRadius(), getRadius()));
    }

    /**
     * {@inheritDoc}
     * The new speed is normalized and then multiplied by 4 to ensure that
     * the ball speed is consistent even if the X and Y speed have changed
     *
     * @param speed the new speed of the ball
     * @see #getSpeed()
     */
    public void setSpeed(Point2D speed) {
        speed = speed.normalize().multiply(4);
        super.setSpeed(speed);
    }

    /**
     * Resets the balls location to its original starting location and
     * updates the center and other points of the ball
     *
     * @see #updateLocations()
     */
    @Override
    public void reset() {
        setLocation(getSTART_LOCATION());
        centerProperty().set(getSTART_LOCATION().add(getRadius(), getRadius()));
        updateLocations();
    }

    /**
     * {@inheritDoc}
     * <p>
     * The method finds the impact of the ball with the game wall
     *
     * @param parent the parent in this case will null
     * @return a constant value signifying the direction of the collision
     * @see #GAME_BOUNDS
     */
    @Override
    public int findImpact(Impactable parent) {
        Bounds bound = GAME_BOUNDS;
        setCollisions(false);
        if (bottomProperty().get().getY() >= bound.getMaxY()) {
            setLost(true); // ball has been lost
        }
        if (topProperty().get().getY() <= 0) { // ball hits the ceiling
            onImpact(UP);
            setCollisions(true);
            return UP;
        }
        if (leftProperty().get().getX() <= 0) { // ball hits the left wall
            onImpact(LEFT);
            setCollisions(true);
            return LEFT;
        }
        if (rightProperty().get().getX() >= bound.getMaxX()) {
            // ball hits the right wall
            onImpact(RIGHT);
            setCollisions(true);
            return RIGHT;
        }
        //if no collision is detected set the collision property to false
        setCollisions(false);
        return NO_IMPACT;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method is handle the action that the ball takes depending on
     * which direction the ball has collided from
     *
     * @param side the direction of the collision
     */
    @Override
    public void onImpact(int side) {
        if (!isCollisions()) {
            switch (side) {
                //reverse the y speed
                case UP, DOWN -> setSpeed(new Point2D(getSpeed().getX(),
                                                      -getSpeed().getY()));
                //reverse the x speed
                case LEFT, RIGHT -> setSpeed(new Point2D(-getSpeed().getX(),
                                                         getSpeed().getY()));

                //Ball collides the left most part of the paddle
                case LEFT_MOST_IMPACT -> setSpeed(
                        new Point2D(-MOST_X_SPEED, -getSpeed().getY()));

                //Ball collides with the left part of the paddle
                case LEFT_IMPACT -> setSpeed(
                        new Point2D(-NORMAL_X_SPEED, -getSpeed().getY()));

                //Ball collides with the middle of the paddle
                case MIDDLE_IMPACT -> setSpeed(
                        new Point2D(0, -getSpeed().getY()));

                //Ball collides with the right part of the paddle
                case RIGHT_IMPACT -> setSpeed(
                        new Point2D(NORMAL_X_SPEED, -getSpeed().getY()));

                //Ball collides with the right most part of the paddle
                case RIGHT_MOST_IMPACT -> setSpeed(
                        new Point2D(MOST_X_SPEED, -getSpeed().getY()));
                //ball has no collisiona
                case NO_IMPACT -> {
                    return;
                }
            }
            setCollisions(true);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        setLocation(getLocation().add(getSpeed()));
        centerProperty().set(centerProperty().get().add(getSpeed()));
        updateLocations();
    }

    /**
     * Gets the center SimpleObjectProperty containing the Point2D object
     *
     * @see #setCenter(Point2D)
     * @see #getCenter()
     */
    private SimpleObjectProperty<Point2D> centerProperty() {
        return m_Center;
    }

    /**
     * Initialises the bottom property of the ball to its default value of 0, 0
     *
     * @param bottomProperty
     */
    private void initBottomProperty(
            SimpleObjectProperty<Point2D> bottomProperty) {
        if (bottomProperty != null) {
            m_Bottom = bottomProperty;
            setBottom(new Point2D(0, 0));
        }
    }

    /**
     * Initialises the center property of the ball to its default value of 0, 0
     *
     * @param centerProperty
     */
    private void initCenterProperty(
            SimpleObjectProperty<Point2D> centerProperty) {
        if (centerProperty != null) {
            m_Center = centerProperty;
            setCenter(new Point2D(0, 0));
        }
    }

    /**
     * Initialises the left property of the ball to its default value of 0, 0
     *
     * @param leftProperty
     */
    private void initLeftProperty(SimpleObjectProperty<Point2D> leftProperty) {
        if (leftProperty != null) {
            m_Left = leftProperty;
            setLeft(new Point2D(0, 0));
        }
    }

    /**
     * Initialises the left property of the ball to its default value of 0, 0
     *
     * @param rightProperty
     */
    private void initRightProperty(
            SimpleObjectProperty<Point2D> rightProperty) {
        if (rightProperty != null) {
            m_Right = rightProperty;
            setRight(new Point2D(0, 0));
        }
    }

    /**
     * Initialises the top property of the ball to its default value of 0, 0
     *
     * @param topProperty
     */
    private void initTopProperty(SimpleObjectProperty<Point2D> topProperty) {
        if (topProperty != null) {
            m_Top = topProperty;
            setTop(new Point2D(0, 0));
        }
    }

    /**
     * Initialises all the properties objects of the ball
     */
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

    /**
     * Updates the top, bottom, left and right point of the ball
     */
    private void updateLocations() {
        setTop(getCenter().add(0, -getRadius()));
        setBottom(getCenter().add(0, getRadius()));
        setLeft(getCenter().add(-getRadius(), 0));
        setRight(getCenter().add(getRadius(), 0));
    }

}

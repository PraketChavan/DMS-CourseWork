package com.example.breakout_clone.model.sprite;

import com.example.breakout_clone.util.Breakable;
import com.example.breakout_clone.util.Impactable;
import com.example.breakout_clone.wrappers.powerup.PowerUpFactory;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * Model class for the Brick MVC. It stores all the data of a Brick object
 * and provides the controller with functions to allow it to modify the
 * state/data of the model
 * <br>
 * This class contains the brick logic for the game, i.e. what to do when
 * the brick is broken, what to do when the ball collides with the brick,
 * etc.<br>
 * The class implements the Breakable and the Impactable interface and all
 * its method
 * @author Praket Chavan
 * @see SpriteModel
 * @see Breakable
 * @see Impactable
 */
public class BrickModel extends SpriteModel implements Breakable, Impactable {

    /**
     * The Integer property that stores the full strength of a brick<br>
     * i.e. total number of times the ball need to hit it for it to break
     * @see #getFullStrength()
     * @see #setFullStrength(int)
     * @see #fullStrengthProperty()
     */
    private final SimpleIntegerProperty m_FullStrength;

    /**
     * The Integer property that stores the current strength/life of a brick
     * @see #getStrength()
     * @see #setStrength(int)
     * @see #strengthProperty()
     */
    private final SimpleIntegerProperty m_Strength;

    /**
     * The boolean property that shows if a brick is a special brick or not
     * <br>
     * Special bricks have chances dropping power ups
     * @see #isSpecial()
     * @see #setSpecial(boolean)
     * @see #specialProperty()
     */
    private final SimpleBooleanProperty m_Special;

    /**
     * The boolean property that shows if a brick is broken or not
     * @see #isBroken()
     * @see #setBroken(boolean)
     * @see #brokenProperty()
     */
    private final SimpleBooleanProperty m_Broken;

    /**
     * Gets the bricks full strength from its properties
     * @return the full strength of the brick
     * @see #setFullStrength(int)
     * @see #fullStrengthProperty()
     */
    public int getFullStrength() {
        return fullStrengthProperty().get();
    }

    /**
     * Sets the full strength of the brick to the new value
     * @param fullStrength the new full strength
     * @see #fullStrengthProperty()
     * @see #getFullStrength()
     */
    public void setFullStrength(int fullStrength) {
        fullStrengthProperty().set(fullStrength);
    }

    /**
     * Gets the current strength of the brick
     * @return the strength of the brick
     * @see #setStrength(int)
     * @see #strengthProperty()
     */
    private int getStrength() {
        return strengthProperty().get();
    }

    /**
     * Sets the strength of the brick to the new value
     * @param strength the new strength
     * @see #strengthProperty()
     * @see #getStrength()
     */
    public void setStrength(int strength) {
        this.m_Strength.set(strength);
    }

    /**
     * Return the value of the broken property of the brick
     * @see #brokenProperty()
     * @see #setBroken(boolean)
     */
    public boolean isBroken() {
        return brokenProperty().get();
    }

    /**
     * Sets the value of the broken property of the brick to the new value
     * @param broken new value of the brick
     * @see #brokenProperty()
     * @see #isBroken()
     */
    private void setBroken(boolean broken) {
       brokenProperty().set(broken);
    }

    /**
     * Return the value of the special property of the brick
     * @see #specialProperty()
     * @see #setSpecial(boolean)
     */
    private boolean isSpecial() {
        return specialProperty().get();
    }

    /**
     * Sets the value of the special property of the brick to the new value
     * @param special new value of the special property
     * @see #specialProperty()
     * @see #isSpecial()
     */
    public void setSpecial(boolean special) {
        this.specialProperty().set(special);
    }

    /**
     * Constructor to initialise the final properties of the Brick model and
     * to call the super constructor
     * @param location the starting location of the brick objecy
     * @param isSpec is the brick special
     * @param name the type of brick
     */
    public BrickModel(Point2D location, boolean isSpec, String name) {
        super(location);
        m_Special = new SimpleBooleanProperty(isSpec);
        m_Broken = new SimpleBooleanProperty();
        m_Strength = new SimpleIntegerProperty();
        m_FullStrength = new SimpleIntegerProperty();
        setName(name);
        initializeProperties();
    }

    /**
     * Gets the special BooleanProperty object of the model
     * @see #isSpecial()
     * @see #setSpecial(boolean)
     */
    private SimpleBooleanProperty specialProperty() {
        return m_Special;
    }

    /**
     * Decrements the strength property of the brick by 1
     */
    private void decreaseStrength() {
        setStrength(getStrength() - 1);
    }

    /**
     * Gets the full strength IntegerProperty object of the model
     * @see #getFullStrength()
     * @see #setFullStrength(int)
     */
    private SimpleIntegerProperty fullStrengthProperty() {
        return m_FullStrength;
    }

    /**
     * Gets the strength IntegerProperty object of the model
     * @see #getStrength()
     * @see #setStrength(int)
     */
    public SimpleIntegerProperty strengthProperty() {
        return m_Strength;
    }

    /**
     * Gets the broken BooleanProperty object of the model
     * @see #isBroken()
     * @see #setBroken(boolean)
     */
    public SimpleBooleanProperty brokenProperty() {
        return m_Broken;
    }

    /**
     * {@inheritDoc}
     * Resets the strength of the brick to its original strength, i.e. full
     * strength
     */
    @Override
    public void reset() {
        setStrength(getFullStrength());
        setBroken(false);
    }

    /**
     * {@inheritDoc}
     *
     * Finds impacts between the ball and the brick and calls the
     * {@link #onImpact(int)} method with the correct constant value
     * @param parent the ball object as an Impactable object
     * @return the side on which the collision has occurred
     */
    @Override
    public int findImpact(Impactable parent) {
        if (!checkBroken()) {
            BallModel ball = (BallModel) parent;
            if (getBounds().contains(ball.rightProperty().get())) {
                onImpact(LEFT);
                return LEFT;
            }
            if (getBounds().contains(ball.leftProperty().get())) {
                onImpact(RIGHT);
                return RIGHT;
            }
            if (getBounds().contains(ball.topProperty().get())) {
                onImpact(DOWN);
                return DOWN;
            }
            if (getBounds().contains(ball.bottomProperty().get())) {
                onImpact(UP);
                return UP;
            }
        }
        return NO_IMPACT;
    }

    /**
     * Decreases the strength of the brick
     * @param side the direction of the impact
     */
    @Override
    public void onImpact(int side) {
        decreaseStrength();
    }

    /**
     * {@inheritDoc}
     * Checks the strength property to see if the brick is broken
     * @return true if the strength is less than 0 else false
     */
    @Override
    public boolean checkBroken() {
        return getStrength() <= 0;
    }

    /**
     * {@inheritDoc}
     * Checks to see if the brick is special. If it is, then it has a chance to
     * generate a new PowerUp
     * @return a constant defined int that signifies whether the broken brick
     * is special or not
     */
    @Override
    public int onBreak() {
        if (isSpecial() && new Random().nextBoolean()) {
            PowerUpFactory.setLocation(getLocation().add(getWidth() / 2, 0));
            setBroken(true);
            return SPECIAL_BREAK;
        }
        setBroken(true);
        return NORMAL_BREAK;
    }

    /**
     * Initialise the properties of the brick when the object is first created
     */
    private void initializeProperties() {
        setBroken(false);
        setFullStrength(1);
        setStrength(getFullStrength());
        setXLocation(getLocation().getX());
        setYLocation(getLocation().getY());
    }
}

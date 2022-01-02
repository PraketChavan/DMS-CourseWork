package com.example.breakout_clone.wrappers.powerup;

import com.example.breakout_clone.util.ResourceHandler;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * Factory class method for creating the different types of power up
 * @see PowerUp
 * @author Praket Chavan
 */
public class PowerUpFactory {

    /**
     * Constant to define the probability of creating a score powerUp
     */
    private static final int SCORE_PROBABILITY = 75;

    /**
     * Constant to define the lower bound of the probability for creating the
     *  extra life power up
     */
    private static final int EXTRA_LIFE_PROB_LOWER = 76;

    /**
     * Constant to defines the upper bounds of the probability for creating
     * the extra life power up
     */
    private static final int EXTRA_LIFE_PROB_UPPER = 95;

    /**
     * Maximum value for the random number generator
     */
    private static final int PROBABILITY_BOUNDS = 100;

    /**
     * The location where the power up will be created
     * @see #getLocation()
     */
    private static Point2D m_location;

    /**
     * Gets the location of where the sprites needs to be created
     * @return
     */
    private static Point2D getLocation() {
        return m_location;
    }

    /**
     * Set the new location for the sprite
     * @param m_location the new location
     */
    public static void setLocation(Point2D m_location) {
        PowerUpFactory.m_location = m_location;
    }

    /**
     * Factory method for creating powerUps  based on the random number
     * generator
     * @return the PowerUp object that is created
     */
    public PowerUp createPowerUP() {
        Random random = new Random();
        int probability = random.nextInt(PROBABILITY_BOUNDS);

        if (probability < SCORE_PROBABILITY)
            return new PowerUp(ResourceHandler.getResource("Points"),
                               getLocation(),
                               PowerUpType.SCORE);
        else if (probability >= EXTRA_LIFE_PROB_LOWER
                && probability < EXTRA_LIFE_PROB_UPPER)
            return new PowerUp(ResourceHandler.getResource("ExtraLife"),
                               getLocation(), PowerUpType.EXTRA_LIFE);
        else
            return new PowerUp(ResourceHandler.getResource("Nuclear"),
                               getLocation(),
                               PowerUpType.NUCLEAR);
    }
}

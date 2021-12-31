package com.example.migrateFx.wrappers.powerup;

import com.example.migrateFx.handler.ResourceHandler;
import javafx.geometry.Point2D;

import java.util.Random;

public class PowerUpFactory {
    public static final int SCORE_PROBABILITY = 75;
    public static final int EXTRA_LIFE_PROB_LOWER = 75;
    public static final int EXTRA_LIFE_PROB_UPPER = 95;
    public static final int PROBABILITY_BOUNDS = 100;
    private static Point2D m_location;

    public static Point2D getLocation() {
        return m_location;
    }

    public static void setLocation(Point2D m_location) {
        PowerUpFactory.m_location = m_location;
    }

    public PowerUp createPowerUP() {
        Random random = new Random();
        int probability = random.nextInt(PROBABILITY_BOUNDS);

        if (probability < SCORE_PROBABILITY)
            return new PowerUp(ResourceHandler.getResource("Points"),
                               getLocation(),
                               PowerUpType.SCORE);
        else if (probability > EXTRA_LIFE_PROB_LOWER
                && probability < EXTRA_LIFE_PROB_UPPER)
            return new PowerUp(ResourceHandler.getResource("ExtraLife"),
                               getLocation(), PowerUpType.EXTRA_LIFE);
        else
            return new PowerUp(ResourceHandler.getResource("Nuclear"),
                               getLocation(),
                               PowerUpType.NUCLEAR);
    }
}

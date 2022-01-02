package com.example.breakout_clone.wrappers.brick;

import com.example.breakout_clone.util.ResourceHandler;
import javafx.geometry.Point2D;

import java.util.Random;

/**
 * Factory class for creating the different types of bricks. Each type of
 * brick contains many textures, the factory allows for randomised textures
 * which gives the level more randomness.
 * @author Praket Chavan
 * @see Brick
 */
public class BrickFactory {
    //Constant defined to remove magic number
    private static final int TWENTY_FIVE = 25;

    private static final int FIFTY = 50;

    private static final int SEVENTY_FIVE = 75;

    private static final int MAX_RANDOM_NUMBER = 100;

    private static final int MAX_SPECIAL = 15;

    private static final int SPECIAL_THRESHOLD = 10;

    private final Random m_random = new Random();

    /**
     * The method that is used to create the bricks
     *
     * @param type     the type of brick that is needed
     * @param location the location of the brick
     * @return the Brick object that had been created
     */
    public Brick createBrick(BrickType type, Point2D location) {
        //the probability for generating a specific type of texture
        int probability = m_random.nextInt(MAX_RANDOM_NUMBER);
        switch (type) {
            case CLAY -> {
                // probability for generating special bricks
                return getClayBrick(location, probability);
            }
            case STEEL -> {
                return getSteelBrick(location, probability);
            }
            case UNBREAKABLE -> {
                //select from the two steel bricks based on the probability
                return getUnbreakableBrick(location, probability);
            }
        }
        return null;
    }

    private UnbreakableBrick getUnbreakableBrick(Point2D location,
                                                 int probability) {
        if (probability <= FIFTY)
            return new UnbreakableBrick(
                    ResourceHandler.getResource("unbreakable0"),
                    location);
        else
            return new UnbreakableBrick(
                    ResourceHandler.getResource("unbreakable1"),
                    location);
    }

    private SteelBrick getSteelBrick(Point2D location, int probability) {
        //select from the four steel bricks based on the probability
        if (probability <= TWENTY_FIVE)
            return new SteelBrick(ResourceHandler.getResource("steel0"),
                                  location);
        else if (probability <= FIFTY)
            return new SteelBrick(ResourceHandler.getResource("steel1"),
                                  location);

        else if (probability <= SEVENTY_FIVE)
            return new SteelBrick(ResourceHandler.getResource("steel2"),
                                  location);

        else
            return new SteelBrick(ResourceHandler.getResource("steel3"),
                                  location);
    }

    private ClayBrick getClayBrick(Point2D location, int probability) {
        int probabilitySpec = m_random.nextInt(MAX_SPECIAL);
        //select between the four clay brick based on the probability
        if (probability <= TWENTY_FIVE)
            return new ClayBrick(ResourceHandler.getResource(
                    probabilitySpec < SPECIAL_THRESHOLD ?
                            "clay0" : "claySpec0"), location,
                                 probabilitySpec >= SPECIAL_THRESHOLD);
        else if (probability <= FIFTY)
            return new ClayBrick(ResourceHandler.getResource(
                    probabilitySpec < SPECIAL_THRESHOLD ?
                            "clay1" : "claySpec1"), location,
                                 probabilitySpec >= SPECIAL_THRESHOLD);

        else if (probability <= SEVENTY_FIVE)
            return new ClayBrick(ResourceHandler.getResource(
                    probabilitySpec < SPECIAL_THRESHOLD ?
                            "clay2" : "claySpec2"), location,
                                 probabilitySpec >= SPECIAL_THRESHOLD);

        else return new ClayBrick(ResourceHandler.getResource(
                    probabilitySpec < SPECIAL_THRESHOLD ?
                            "clay3" : "claySpec3"), location,
                                  probabilitySpec >= SPECIAL_THRESHOLD);
    }
}

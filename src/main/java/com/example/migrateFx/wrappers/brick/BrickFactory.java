package com.example.migrateFx.wrappers.brick;

import com.example.migrateFx.handler.ResourceHandler;
import javafx.geometry.Point2D;

import java.util.Random;

public class BrickFactory {
    private static final int TWENTY_FIVE = 25;
    private static final int FIFTY = 50;
    private static final int SEVENTY_FIVE = 75;
    private static final int MAX_RANDOM_NUMBER = 100;
    private static final int MAX_SPECIAL = 10;
    private static final int SPECIAL_THRESHOLD = 8;
    private final Random m_random = new Random();

    public Brick createBrick(BrickType type, Point2D location) {
        int probability = m_random.nextInt(MAX_RANDOM_NUMBER);
        switch (type) {
            case CLAY -> {
                int probabilitySpec = m_random.nextInt(MAX_SPECIAL);
                if (probability <= TWENTY_FIVE)
                    return new ClayBrick(ResourceHandler.getResource(
                            probabilitySpec < SPECIAL_THRESHOLD ? "clay0" : "claySpec0"),
                                         location, probabilitySpec >= SPECIAL_THRESHOLD);
                else if (probability <= FIFTY)
                    return new ClayBrick(ResourceHandler.getResource(
                            probabilitySpec < SPECIAL_THRESHOLD ? "clay1" : "claySpec1"),
                                         location, probabilitySpec >= SPECIAL_THRESHOLD);

                else if (probability <= SEVENTY_FIVE)
                    return new ClayBrick(ResourceHandler.getResource(
                            probabilitySpec < SPECIAL_THRESHOLD ? "clay2" : "claySpec2"),
                                         location, probabilitySpec >= SPECIAL_THRESHOLD);

                else return new ClayBrick(ResourceHandler.getResource(
                            probabilitySpec < SPECIAL_THRESHOLD ? "clay3" : "claySpec3"),
                                          location, probabilitySpec >= SPECIAL_THRESHOLD);
            }
            case STEEL -> {
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
            case UNBREAKABLE -> {
                if (probability <= FIFTY)
                    return new UnbreakableBrick(
                            ResourceHandler.getResource("unbreakable0"),
                            location);
                else
                    return new UnbreakableBrick(
                            ResourceHandler.getResource("unbreakable1"),
                            location);
            }
        }
        return null;
    }
}

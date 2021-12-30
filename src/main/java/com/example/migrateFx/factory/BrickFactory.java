package com.example.migrateFx.factory;

import com.example.migrateFx.handler.ResourceHandler;
import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.ClayBrick;
import com.example.migrateFx.wrappers.SteelBrick;
import com.example.migrateFx.wrappers.UnbreakableBrick;
import javafx.geometry.Point2D;

import java.util.Random;

public class BrickFactory {
    private Random m_random = new Random();
    public Brick createBrick(int type, Point2D location) {
        switch (type) {
            case 1 -> {
                int i = m_random.nextInt(100);
                int m = m_random.nextInt(100);
                if (i <= 25)
                    return new ClayBrick(ResourceHandler.getResource(m < 8 ? "clay0" : "claySpec0"),
                                         location, m >= 8);
                else if (i <= 50)
                    return new ClayBrick(ResourceHandler.getResource(m < 8 ? "clay1" : "claySpec1"),
                                     location, m >= 8);

                else if (i <= 75)
                    return new ClayBrick(ResourceHandler.getResource(m < 8 ? "clay2" : "claySpec2"),
                                         location, m >= 8);

                else return new ClayBrick(ResourceHandler.getResource(m < 8 ? "clay3" : "claySpec3"),
                                      location, m >= 8);
            }
            case 2 -> {
                int i = m_random.nextInt(100);
                if (i <= 25)
                    return new SteelBrick(ResourceHandler.getResource("steel0"),
                                          location);
                else if (i <= 50)
                    return new SteelBrick(ResourceHandler.getResource("steel1"),
                                         location);

                else if (i <= 75)
                    return new SteelBrick(ResourceHandler.getResource("steel2"),
                                         location);

                else return new SteelBrick(ResourceHandler.getResource("steel3"),
                                       location);
            }
            case 3 -> {
                int i = m_random.nextInt(100);
                if (i <= 50)
                    return new UnbreakableBrick(ResourceHandler.getResource("unbreakable0"),
                                                location);
                else
                    return new UnbreakableBrick(ResourceHandler.getResource("unbreakable1"),
                                         location);
            }
        }
        return null;
    }
}

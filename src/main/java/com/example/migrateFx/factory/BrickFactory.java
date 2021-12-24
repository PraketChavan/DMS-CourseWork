package com.example.migrateFx.factory;

import com.example.migrateFx.wrappers.Brick;
import com.example.migrateFx.wrappers.ClayBrick;
import javafx.geometry.Point2D;

import java.util.Random;

public class BrickFactory {
    private Random m_random = new Random();
    public Brick createBrick(int type, Point2D location) {
        switch (type) {
            case 1 -> {
                int i = m_random.nextInt(100);
                if (i <= 25)
                    return new ClayBrick("D:\\Data\\Praket\\Nottingham" +
                                     "\\Y2\\DMS\\Breakout_Clone\\src\\main\\" +
                                             "resources\\com\\example\\" +
                                             "migrateFx\\sprite\\" +
                                             "blue\\tile000\\clay0.png",
                                     location);
                else if (i <= 50)
                    return new ClayBrick("D:\\Data\\Praket\\Nottingham" +
                                             "\\Y2\\DMS\\Breakout_Clone\\src\\main\\" +
                                             "resources\\com\\example\\" +
                                             "migrateFx\\sprite\\" +
                                             "blue\\tile000\\clay1.png",
                                     location);

                else if (i <= 75)
                    return new ClayBrick("D:\\Data\\Praket\\Nottingham" +
                                                 "\\Y2\\DMS\\Breakout_Clone\\src\\main\\" +
                                                 "resources\\com\\example\\" +
                                                 "migrateFx\\sprite\\" +
                                                 "blue\\tile000\\clay2.png",
                                         location);

                else if (i <= 100)
                    return new ClayBrick("D:\\Data\\Praket\\Nottingham" +
                                                 "\\Y2\\DMS\\Breakout_Clone\\src\\main\\" +
                                                 "resources\\com\\example\\" +
                                                 "migrateFx\\sprite\\" +
                                                 "blue\\tile000\\clay3.png",
                                         location);
            }
        }
        return null;
    }
}

package com.example.migrateFx.factory;

import com.example.migrateFx.handler.ResourceHandler;
import com.example.migrateFx.wrappers.PowerUp;
import javafx.geometry.Point2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Random;

public class PowerUpFactory {
    public static Point2D getLocation() {
        return m_location;
    }

    public static void setLocation(Point2D m_location) {
        PowerUpFactory.m_location = m_location;
    }

    private static Point2D m_location;
    public PowerUp createPowerUP() {
        Random random = new Random();
        int i = random.nextInt(1000);

        if (i < 75)
            return new PowerUp(ResourceHandler.getResource("Points"), getLocation(),
                               "Score");
        else if (i > 75 && i < 95)
            return new PowerUp(ResourceHandler.getResource("ExtraLife"),
                               getLocation(), "ExtraLife");
        else
            return new PowerUp(ResourceHandler.getResource("Nuclear"), getLocation(),
                               "Nuclear");
    }
}

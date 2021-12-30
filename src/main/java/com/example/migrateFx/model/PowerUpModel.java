package com.example.migrateFx.model;

import com.example.breakout_clone.Paddle;
import com.example.migrateFx.Breakable;
import com.example.migrateFx.Impactable;
import com.example.migrateFx.Movable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class PowerUpModel extends SpriteModel implements Impactable, Movable {

    public PowerUpModel(Point2D location) {
        super(location);
    }

    @Override
    public void move() {
        setLocation(getLocation().add(getSpeed()));
    }

    @Override
    public int findImpact(Impactable parent) {
        Bounds bounds = ((PaddleModel)parent).getBounds();
        if (getBounds().intersects(bounds)) {
            onImpact(UP);
            return UP;
        }
        else return NO_IMPACT;
    }

    @Override
    public void onImpact(int side) {
        switch (getName()) {
            case "Score" -> GameModel.getGameInstance().addScore(100);
            case "Nuclear" -> GameModel.getGameInstance().clearLevel();
            case "ExtraLife" ->GameModel.getGameInstance().addLife();
        }
    }

    @Override
    public void reset() {

    }

}

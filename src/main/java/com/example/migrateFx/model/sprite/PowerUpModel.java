package com.example.migrateFx.model.sprite;

import com.example.migrateFx.model.game.GameModel;
import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.util.Movable;
import com.example.migrateFx.wrappers.powerup.PowerUpType;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class PowerUpModel extends SpriteModel implements Impactable, Movable {
    private PowerUpType m_type;

    public PowerUpType getType() {
        return m_type;
    }

    public void setType(PowerUpType type) {
        m_type = type;
    }

    public PowerUpModel(Point2D location, PowerUpType type) {
        super(location);
        setType(type);
    }

    @Override
    public void move() {
        setLocation(getLocation().add(getSpeed()));
    }

    @Override
    public int findImpact(Impactable parent) {
        Bounds bounds = ((PaddleModel) parent).getBounds();
        if (getBounds().intersects(bounds)) {
            onImpact(UP);
            return UP;
        } else return NO_IMPACT;
    }

    @Override
    public void onImpact(int side) {
        switch (getType()) {
            case SCORE -> GameModel.getGameInstance().addScore(100);
            case NUCLEAR -> GameModel.getGameInstance().clearLevel();
            case EXTRA_LIFE -> GameModel.getGameInstance().addLife();
        }
    }

    @Override
    public void reset() {

    }

}

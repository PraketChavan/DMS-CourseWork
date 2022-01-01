package com.example.migrateFx.model.sprite;

import com.example.migrateFx.model.game.GameModel;
import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.util.Movable;
import com.example.migrateFx.wrappers.powerup.PowerUpType;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

/**
 * The PowerUp class for the PowerUp MVC. It stores all the data of a PowerUp
 * object
 * and provides the controller with functions to allow it to modify the
 * state/data of the model
 * <br><br>
 * The class implements the Movable and the Impactable interface and all
 * its method
 * @author Praket Chavan
 * @see SpriteModel
 * @see Impactable
 * @see Movable
 */
public class PowerUpModel extends SpriteModel implements Impactable, Movable {
    /**
     * The PowerUp enum that defines the type of power up
     */
    private PowerUpType m_type;

    /**
     * Returns an enum value that defines the type of PowerUp
     * @return the type of powerup
     */
    private PowerUpType getType() {
        return m_type;
    }

    /**
     * Set the type of the power up
     * @param type the new type to set
     */
    private void setType(PowerUpType type) {
        m_type = type;
    }

    /**
     * Initialises the model's properties to the constructor parameter
     * @param location the starting location
     * @param type the type of Powerup
     */
    public PowerUpModel(Point2D location, PowerUpType type) {
        super(location);
        setType(type);
    }

    /**
     * Update the location of poweup based on the speed of the PowerUp
     */
    @Override
    public void move() {
        setLocation(getLocation().add(getSpeed()));
    }

    /**
     * Find the impact of the PowerUp and the Paddle
     * @param parent the Paddle object passed as an Impactable object
     * @return  return {@link #UP} if there is an impact else returns
     * {@link #NO_IMPACT}
     */
    @Override
    public int findImpact(Impactable parent) {
        Bounds bounds = ((PaddleModel) parent).getBounds();
        if (getBounds().intersects(bounds)) {
            onImpact(UP);
            return UP;
        } else return NO_IMPACT;
    }

    /**
     * Depending on the type of the powerUp the effect on the GameModel will
     * be different
     * @param side
     */
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

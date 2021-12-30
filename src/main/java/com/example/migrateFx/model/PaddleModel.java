package com.example.migrateFx.model;

import com.example.migrateFx.Impactable;
import com.example.migrateFx.Movable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class PaddleModel extends SpriteModel implements Impactable, Movable {
    private final Point2D START_LOCATION;

    public Bounds getGAME_BOUNDS() {
        return GAME_BOUNDS.get();
    }

    public SimpleObjectProperty<Bounds> GAME_BOUNDSProperty() {
        return GAME_BOUNDS;
    }

    public void setGAME_BOUNDS(Bounds GAME_BOUNDS) {
        this.GAME_BOUNDS.set(GAME_BOUNDS);
    }

    private final SimpleObjectProperty<Bounds> GAME_BOUNDS;
    public PaddleModel(Point2D location, SimpleObjectProperty<Bounds> gameBounds) {
        super(location);
        START_LOCATION = location;
        GAME_BOUNDS = gameBounds;
        setSpeed(new Point2D(0, 0));
    }

    @Override
    public void reset() {
        setLocation(START_LOCATION.subtract(getWidth()/2, 0));
    }

    @Override
    public int findImpact(Impactable parent) {
        if (getBounds().contains(((BallModel)parent).bottomProperty().get()))
            parent.onImpact(UP);
        return UP;
    }

    @Override
    public void onImpact(int side) {
        stop();
    }

    @Override
    public void move() {
        Point2D newLocation = getLocation().add(getSpeed());
        if (newLocation.getX() <= getGAME_BOUNDS().getMinX()
                || newLocation.getX() + getWidth() >= getGAME_BOUNDS().getMaxX())
            return;
        setLocation(newLocation);
    }

    public void stop() {
        setSpeed(new Point2D(0, 0));
    }
}

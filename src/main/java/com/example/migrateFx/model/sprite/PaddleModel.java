package com.example.migrateFx.model.sprite;

import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.util.Movable;
import javafx.geometry.Point2D;

public class PaddleModel extends SpriteModel implements Impactable, Movable {
    private final Point2D START_LOCATION;


    public PaddleModel(Point2D location) {
        super(location);
        START_LOCATION = location;
        setSpeed(new Point2D(0, 0));
    }


    public void stop() {
        setSpeed(new Point2D(0, 0));
    }

    @Override
    public void reset() {
        setLocation(START_LOCATION.subtract(getWidth() / 2, 0));
    }

    @Override
    public int findImpact(Impactable parent) {

        if (getBounds().contains(((BallModel)parent).bottomProperty().get())){
            double centerBounds = getBounds().getCenterX();
            double rightBounds = getBounds().getCenterX() + getWidth() / 4;
            double leftBounds = getBounds().getCenterX() - getWidth() / 4;
            double ballX = ((BallModel)parent).bottomProperty().get().getX();
            if (ballX > centerBounds) {
                if (ballX > rightBounds) {
                    return RIGHT_MOST_IMPACT;
                } else {
                    return RIGHT_IMPACT;
                }
            } else if (ballX < centerBounds) {
                if (ballX < leftBounds) {
                    return LEFT_MOST_IMPACT;
                } else {
                    return LEFT_IMPACT;
                }
            } else
                return MIDDLE_IMPACT;
        }
        return NO_IMPACT;
    }

    @Override
    public void onImpact(int side) {
        stop();
    }

    @Override
    public void move() {
        Point2D newLocation = getLocation().add(getSpeed());
        if (newLocation.getX() <= GAME_BOUNDS.getMinX()
                || newLocation.getX() + getWidth() >= GAME_BOUNDS.getMaxX())
            return;
        setLocation(newLocation);
    }
}

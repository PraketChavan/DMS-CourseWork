package com.example.breakout_clone_javafx;

import com.example.util.Sprite;
import javafx.geometry.Bounds;

import java.util.ArrayList;
import java.util.List;

//Class to handle all the impact for the game
public class ImpactHandler {
    private List<? extends Impactable> m_balls;
    private List<? extends Impactable> m_bricks;
    private Impactable m_player;

    public ImpactHandler(
            List<? extends Impactable> balls,
            List<? extends Impactable> bricks,
            Impactable player) {
        m_balls = balls;
        m_bricks = bricks;
        m_player = player;
    }

    public void handleImpacts() {
        ballImpacts();
        playerImpacts();
    }

    private void ballImpacts() {
        for(Impactable ball: m_balls) {
            Sprite ballSprite = (Sprite) ball;
            for (Impactable brick : m_bricks) {
                if (ball.findImpact((Sprite) brick) != Impactable.NO_IMPACT)
                    break;
            }
            Bounds bounds = ballSprite.getParent().getLayoutBounds();
            //System.out.println(bounds);
            if (ballSprite.getPosition().getX() >= (bounds.getMaxX() - ballSprite.getRadius()))
                ball.onImpact(Impactable.RIGHT);
            if (ballSprite.getPosition().getX() <= (bounds.getMinX() + ballSprite.getRadius()))
                ball.onImpact(Impactable.LEFT);
            if (ballSprite.getPosition().getY() >= (bounds.getMaxY() - ballSprite.getRadius()))
                ball.onImpact(Impactable.DOWN);
            if (ballSprite.getPosition().getY() <= (bounds.getMinY() + ballSprite.getRadius()))
                ball.onImpact(Impactable.UP);

        }
        
        
    }

    private void playerImpacts() {

    }
}

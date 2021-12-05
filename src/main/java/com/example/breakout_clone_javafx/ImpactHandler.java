package com.example.breakout_clone_javafx;

import com.example.util.Sprite;
import javafx.geometry.Bounds;

import java.util.ArrayList;

//Class to handle all the impact for the game
public class ImpactHandler {
    private ArrayList<Impactable> m_balls = new ArrayList<>();
    private ArrayList<Impactable> m_bricks = new ArrayList<>();
    private Impactable m_player;

    private ImpactHandler(
            ArrayList<Impactable> balls,
            ArrayList<Impactable> bricks,
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

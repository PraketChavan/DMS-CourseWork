package com.example.migrateFx.handler;

import com.example.migrateFx.model.sprite.BallModel;
import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.util.Breakable;
import com.example.migrateFx.util.Impactable;
import com.example.migrateFx.wrappers.powerup.PowerUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ImpactHandler {
    public static final int SPECIAL_BRICK_BROKEN = -2;
    public static final int NORMAL_BRICK_BROKEN = -1;
    public static final int NO_IMPACT = 0;
    public static final int NORMAL_WALL_IMPACT = 1;
    private final ObservableList<Impactable> m_Bricks;
    private final ObservableList<Impactable> m_Balls;
    private final ObservableList<Impactable> m_PowerUp;
    private Impactable m_Paddle;

    public ObservableList<Impactable> getBalls() {
        return m_Balls;
    }

    public ObservableList<Impactable> getBricks() {
        return m_Bricks;
    }

    public Impactable getPaddle() {
        return m_Paddle;
    }

    public ObservableList<Impactable> getPowerUp() {
        return m_PowerUp;
    }

    public ImpactHandler(Impactable border) {
        m_Bricks = FXCollections.observableArrayList();
        m_Balls = FXCollections.observableArrayList();
        m_PowerUp = FXCollections.observableArrayList();
    }

    public void addPower(PowerUp power) {
        getPowerUp().add(power.getModel());
    }

    public void addBrick(Impactable brick) {
        getBricks().add(brick);
    }

    public void addPaddle(Impactable paddle) {
        m_Paddle = paddle;
    }

    public void addBall(Impactable ball) {
        getBalls().add(ball);
    }

    public int handleImpacts() {
        handleBallPaddleImpacts();
        handlePowerUpImpact();
        handleWallImpacts();
        int impact = handleBrickImpact();
        if (impact == SPECIAL_BRICK_BROKEN)
            return SPECIAL_BRICK_BROKEN;
        else if (impact == NORMAL_BRICK_BROKEN)
            return NORMAL_BRICK_BROKEN;
        return NO_IMPACT;
    }

    private boolean handlePowerUpImpact() {
        List<Impactable> toRemove = new LinkedList<>();
        for (Impactable powerUp: getPowerUp()) {
            if (powerUp.findImpact(getPaddle()) == Impactable.UP)
                toRemove.add(powerUp);
        }
        getPowerUp().removeAll(toRemove);
        return false;
    }

    private boolean handleBallPaddleImpacts() {
        for (Impactable ball : getBalls())
            ball.onImpact(getPaddle().findImpact(ball));
        return false;
    }

    private int handleBrickImpact() {
        ArrayList<Impactable> broken = new ArrayList<>();
        boolean specialBreak = false;
        boolean impact = false;
        for (Impactable ball : getBalls()) {
            for (Impactable brick : getBricks()) {
                switch (brick.findImpact(ball)) {
                    //Vertical Impact
                    case Impactable.UP -> {
                        ball.onImpact(Impactable.UP);
                        ((BallModel) ball).setCollisions(true);
                        impact = true;
                    }
                    case Impactable.DOWN -> {
                        ball.onImpact(Impactable.DOWN);
                        ((BallModel) ball).setCollisions(true);
                        impact = true;
                    }

                    //Horizontal Impact
                    case Impactable.LEFT -> {
                        ball.onImpact(Impactable.LEFT);
                        ((BallModel) ball).setCollisions(true);
                        impact = true;
                    }
                    case Impactable.RIGHT -> {
                        ball.onImpact(Impactable.RIGHT);
                        ((BallModel) ball).setCollisions(true);
                        impact = true;
                    }
                }
                if (((Breakable) brick).checkBroken()) {
                    specialBreak = ((Breakable) brick).onBreak() == 1;
                    broken.add(brick);
                }
                if (impact) {
                    AudioClip impactSound = null;
                    switch (((SpriteModel) brick).getName()) {
                        case "Clay" -> impactSound = new AudioClip(
                                ResourceHandler.getSoundResource(
                                        "ClayBrick.mp3"));
                        case "Steel" -> impactSound = new AudioClip(
                                ResourceHandler.getSoundResource(
                                        "Steel.mp3"));
                        case "Unbreakable" -> impactSound = new AudioClip(
                                ResourceHandler.getSoundResource(
                                        "Unbreakable.mp3"));
                    }
                    impactSound.play();
                }
            }
            getBricks().removeAll(broken);
            ((BallModel) ball).setCollisions(false);
        }

        if (specialBreak)
            return SPECIAL_BRICK_BROKEN;
        else if (impact)
            return NORMAL_BRICK_BROKEN;
        else
            return NO_IMPACT;
    }


    private void handleWallImpacts() {
        for (Impactable ball : getBalls())
            ball.findImpact(null);
    }


}

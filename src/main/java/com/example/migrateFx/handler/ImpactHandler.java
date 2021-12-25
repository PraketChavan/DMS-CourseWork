package com.example.migrateFx.handler;

import com.example.migrateFx.Ball;
import com.example.migrateFx.Breakable;
import com.example.migrateFx.Impactable;
import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.model.BallModel;

import java.util.LinkedList;
import java.util.List;

public class ImpactHandler {
    private final List<Impactable> m_Bricks;
    private final List<Impactable> m_Balls;
    private final List<Impactable> m_Paddle;
    private final Impactable m_Border;

    public List<Impactable> getBalls() {
        return m_Balls;
    }

    public List<Impactable> getBricks() {
        return m_Bricks;
    }

    public List<Impactable> getPaddle() {
        return m_Paddle;
    }

    public Impactable getBorder() {
        return m_Border;
    }

    public ImpactHandler(Impactable border) {
        m_Bricks = new LinkedList<>();
        m_Balls = new LinkedList<>();
        m_Paddle = new LinkedList<>();
        m_Border = border;
    }

    public void addBrick(Impactable brick) {
        getBricks().add(brick);
    }

    public void addPaddle(Impactable paddle) {
        getPaddle().add(paddle);
    }

    public void addBall(Impactable ball) {
        getBalls().add(ball);
    }

    public void handleImpacts() {
        if (!handleBrickImpact())
            if (!handleBallPaddleImpacts())
                if(!handleWallImpacts())
                    handlePaddleWallImpacts();
    }

    private boolean handleBrickImpact() {
        for (Impactable ball : getBalls()) {
            for (Impactable brick : getBricks()) {
                switch (brick.findImpact(ball)) {
                    //Vertical Impact
                    case Impactable.UP -> {
                        if (!((BallModel)ball).isCollisions()) {
                            ball.onImpact(Impactable.UP);
                            ((BallModel)ball).setCollisions(true);
                            return true;
                        }
                    }
                    case Impactable.DOWN -> {
                        if (!((BallModel)ball).isCollisions()) {
                            ball.onImpact(Impactable.DOWN);
                            ((BallModel)ball).setCollisions(true);
                            return true;
                        }
                    }

                    //Horizontal Impact
                    case Impactable.LEFT -> {
                        if (!((BallModel)ball).isCollisions()) {
                            ball.onImpact(Impactable.LEFT);
                            ((BallModel)ball).setCollisions(true);
                            return true;
                        }
                    }
                    case Impactable.RIGHT -> {
                        if (!((BallModel)ball).isCollisions()) {
                            ball.onImpact(Impactable.RIGHT);
                            ((BallModel)ball).setCollisions(true);
                            return true;
                        }
                    }
                }
                if (((Breakable)brick).checkBroken())
                    ((Breakable)brick).onBreak();
            }
            ((BallModel)ball).setCollisions(false);
            return false;
        }
        return false;
    }

    private boolean handleWallImpacts() {
        for (Impactable ball: getBalls())
             ball.findImpact(getBorder());
        return false;
    }

    private boolean handlePaddleWallImpacts() {
        return false;
    }

    private boolean handleBallPaddleImpacts() {
        for (Impactable ball: getBalls())
            getPaddle().get(0).findImpact(ball);
        return false;
    }


}

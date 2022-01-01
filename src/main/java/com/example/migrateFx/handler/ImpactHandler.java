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

/**
 * Class responsible for handling all the impacts that are occurring in the
 * game. The objects need to implement the {@link Impactable} interface
 * inorder for this class to handle its impacts.
 *
 * The main code for this class was extracted from the original Wall class
 * and thus reduced the Wall class's responsibility
 *
 * This class abstracts away from the individual class types and only deals
 * with Impactable objects
 *
 * @author Praket Chavan
 */
public class ImpactHandler {
    /**
     * Constant the defines the return value of the function when a special
     * brick is broken
     */
    public static final int SPECIAL_BRICK_BROKEN = -2;

    /**
     * Constant defines the return value of functions when a normal brick is
     * broken
     */
    public static final int NORMAL_BRICK_BROKEN = -1;

    /**
     * Constant defines the functions return value when the handle detects no
     * impacts in the game interation
     */
    public static final int NO_BRICK_BROKEN = 0;

    /**
     * Constant defines the functions return value there is a normal wall
     * impact
     */
    public static final int NORMAL_WALL_IMPACT = 1;

    /**
     * List containing all the bricks that are currently present in the
     * GameFrame view
     * @see #getBricks()
     * @see #addBrick(Impactable)
     */
    private final ObservableList<Impactable> m_Bricks;

    /**
     * List containing all the ball that are currently present in the
     * GameFrameView
     * @see #getBalls()
     * @see #addBall(Impactable)
     */
    private final ObservableList<Impactable> m_Balls;

    /**
     * List containing all the power ups that are currently present in the
     * GameFrameView
     * @see #getPowerUp()
     * @see #addPower(PowerUp)
     */
    private final ObservableList<Impactable> m_PowerUp;

    /**
     * The paddle object that is in the GameFrame view
     * @see #getPaddle()
     * @see #addPaddle(Impactable)
     */
    private Impactable m_Paddle;

    /**
     * Gets the Observable list of balls present on the screen
     * @return Observable list object
     */
    public ObservableList<Impactable> getBalls() {
        return m_Balls;
    }

    /**
     * Gets the Observable list of bricks present on the screen
     * @return Observable list object
     */
    public ObservableList<Impactable> getBricks() {
        return m_Bricks;
    }

    /**
     * Gets the paddle object present on the screen
     * @return the current paddle on the screen as an Impactable object
     */
    public Impactable getPaddle() {
        return m_Paddle;
    }

    /**
     * Gets the Observable list of power ups present on the screen
     * @return Observable list object
     */
    public ObservableList<Impactable> getPowerUp() {
        return m_PowerUp;
    }

    /**
     * Constructor to initialise the final Observable list member variables
     * @see FXCollections
     * @see FXCollections#observableArrayList()
     */
    public ImpactHandler() {
        m_Bricks = FXCollections.observableArrayList();
        m_Balls = FXCollections.observableArrayList();
        m_PowerUp = FXCollections.observableArrayList();
    }

    /**
     * Add a PowerUp object as an Impactable object to the list
     * @param power object that is to be added
     * @see #getPowerUp()
     */
    public void addPower(PowerUp power) {
        getPowerUp().add(power.getModel());
    }

    /**
     * Add a Brick object as an Impactable object to the list
     * @param brick object that is to be added
     * @see #getBricks()
     */
    public void addBrick(Impactable brick) {
        getBricks().add(brick);
    }

    /**
     * Sets the m_Paddle member to the  Paddle object as an Impactable object
     * @param paddle object that is to be set to
     * @see #getPaddle()
     */
    public void addPaddle(Impactable paddle) {
        m_Paddle = paddle;
    }

    /**
     * Add a Ball object as an Impactable object to the list
     * @param ball object that is to be added
     * @see #getBalls()
     */
    public void addBall(Impactable ball) {
        getBalls().add(ball);
    }

    /**
     * Handles all the impacts in the game by calling the respective functions
     * @return an integer constant depending on the impact that is detected
     * @see #handleBallPaddleImpacts()
     * @see #handlePowerUpImpact()
     * @see #handleBrickImpact()
     * @see #handleWallImpacts()
     */
    public int handleImpacts() {
        handleBallPaddleImpacts();
        handlePowerUpImpact();
        handleWallImpacts();
        int impact = handleBrickImpact();
        if (impact == SPECIAL_BRICK_BROKEN)
            return SPECIAL_BRICK_BROKEN;
        else if (impact == NORMAL_BRICK_BROKEN)
            return NORMAL_BRICK_BROKEN;
        return NO_BRICK_BROKEN;
    }

    /**
     * Handles all the impact related to the PowerUp objects.
     * If an impact is detected with the paddle (player) then the PowerUp is
     * removed from the list as it is no longer present on the screen
     * @return true if any impact is detected,<br>
     *         false if no impact is detected
     */
    private boolean handlePowerUpImpact() {
        List<Impactable> toRemove = new LinkedList<>();
        for (Impactable powerUp: getPowerUp()) {
            if (powerUp.findImpact(getPaddle()) == Impactable.UP)
                toRemove.add(powerUp);
        }
        getPowerUp().removeAll(toRemove);
        return toRemove.size() != 0;
    }

    /**
     * Handles the impact between the paddle and the ball objects and call
     * the {@link Impactable#onImpact(int)} method on the ball object
     */
    private boolean handleBallPaddleImpacts() {
        for (Impactable ball : getBalls())
            //find the impact for each ball present on the screen
            ball.onImpact(getPaddle().findImpact(ball));
        return false;
    }

    /**
     * Handles the impacts between the bricks and the ball objects on the screen
     * The method iterates through each brick and ball object in the lists
     * and find and handles the impacts between the two objects
     *
     * @return the constant value depending on the type of brick impact
     * @see #NORMAL_BRICK_BROKEN
     * @see #SPECIAL_BRICK_BROKEN
     * @see #NO_BRICK_BROKEN
     */
    private int handleBrickImpact() {
        //list of bricks that will be removed from the Obsevable list since
        // they are broken
        ArrayList<Impactable> broken = new ArrayList<>();
        boolean specialBreak = false;
        boolean impact = false;
        int impactSide;
        for (Impactable ball : getBalls()) {
            for (Impactable brick : getBricks()) {
                impact  = false; // reset the impact boolean
                impactSide = brick.findImpact(ball);
                switch (impactSide) {
                    //If any impact is detected
                    case Impactable.UP, Impactable.RIGHT,
                         Impactable.LEFT, Impactable.DOWN -> {
                        //Do the impact effect on the ball object
                        ball.onImpact(impactSide);
                        ((BallModel) ball).setCollisions(true);
                        impact = true;
                    }
                }
                if (((Breakable) brick).checkBroken()) {
                    //check to see if a special brick has been broken
                    specialBreak = ((Breakable) brick).onBreak() == 1;
                    broken.add(brick); // add the brick to the broken list
                }
                //Fetch the correct sound track depending on the brick type
                // and play it
                if (impact) {
                    AudioClip impactSound = null;
                    switch (((SpriteModel) brick).getName()) {
                        case "Clay" -> impactSound = new AudioClip(
                                ResourceHandler.getSoundResource(
                                        "ClayBrick.mp3"));
                        case "Steel" -> impactSound = new AudioClip(
                                ResourceHandler.getSoundResource(
                                        "SteelBrick.mp3"));
                        case "Unbreakable" -> impactSound = new AudioClip(
                                ResourceHandler.getSoundResource(
                                        "Unbreakable.mp3"));
                    }
                    impactSound.play();
                }
            }
            //Remove all the broken bricks from the observable list
            getBricks().removeAll(broken);
//            ((BallModel) ball).setCollisions(false);
        }

        //Return a specific constant value depending on the type of broken
        // brick
        if (specialBreak)
            return SPECIAL_BRICK_BROKEN;
        else if (impact)
            return NORMAL_BRICK_BROKEN;
        else
            return NO_BRICK_BROKEN;
    }


    /**
     * Handles the ball and the wall impact
     */
    private void handleWallImpacts() {
        for (Impactable ball : getBalls())
            ball.findImpact(null);
    }


}

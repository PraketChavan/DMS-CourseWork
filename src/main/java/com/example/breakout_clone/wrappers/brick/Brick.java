package com.example.breakout_clone.wrappers.brick;

import com.example.breakout_clone.controller.sprite.BrickController;
import com.example.breakout_clone.model.sprite.BrickModel;
import com.example.breakout_clone.view.BrickView;
import com.example.breakout_clone.wrappers.Sprite;
import javafx.geometry.Point2D;

/**
 * Wrapper class for creating Brick MVC classes.<br>
 * The class has been migrated from the original code and has been broken into
 *  the three different classes namely the {@link BrickController},
 *  {@link BrickView}, {@link BrickModel}. This was done to achieve single
 *  responsibility for each classes
 * @author Praket Chavan - modified
 * @see Sprite
 */
public abstract class Brick extends Sprite {
    /**
     * Constant that defines the width of the brick
     */
    private static final int BRICK_WIDTH = 80;

    /**
     * Constant that defines the height of the brick
     */
    private static final int BRICK_HEIGHT = 16;

    /**
     * Constructor for creating the BrickModel, BrickController, BrickModel
     * @param url the path of the image resource
     * @param location the starting position of the sprite
     * @param isSpec the boolean specifying if the brick is special or not
     * @param name the type of brick
     */
    Brick(String url, Point2D location, boolean isSpec, String name) {
        setModel(new BrickModel(location, isSpec, name));
        setView(new BrickView(url, BRICK_WIDTH, BRICK_HEIGHT));
        setController(new BrickController(getModel(), getView()));
    }

    /**
     * Overloaded getter method that returns the BrickController instead of
     * SpriteController
     * @return
     */
    public BrickController getController() {
        return (BrickController) super.getController();//m_Controller;
    }

    /**
     * Overloaded getter method that returns the BrickModel instead of
     * SpriteController
     * @return
     */
    public BrickModel getModel() {
        return (BrickModel) super.getModel();//m_Model;
    }

    /**
     * Overloaded getter method that returns the BrickView instead of
     * SpriteController
     * @return
     */
    public BrickView getView() {
        return (BrickView) super.getView();//m_View;
    }
}

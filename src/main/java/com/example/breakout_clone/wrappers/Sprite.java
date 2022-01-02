package com.example.breakout_clone.wrappers;

import com.example.breakout_clone.controller.sprite.SpriteController;
import com.example.breakout_clone.model.sprite.SpriteModel;
import com.example.breakout_clone.view.SpriteView;

/**
 * Abstract wrapper class for storing the SpriteModel, SpriteController and
 * the SpriteView. It reduces the clutter in code for when creating MVC for
 * different sprite object
 *
 * @author Praket Chavan
 *
 */
public abstract class Sprite {
    /**
     * Controller of the Sprite MVC
     */
    private SpriteController m_Controller;

    /**
     * Model of the Sprite MVC
     */
    private SpriteModel m_Model;

    /**
     * View of the Sprite MVC
     */
    private SpriteView m_View;

    /**
     * Gets the controller of the sprite
     * @return
     */
    public SpriteController getController() {
        return m_Controller;
    }

    /**
     * Sets the controller of the sprite to the new controller
     * @param controller the new sprite Controller
     */
    protected void setController(
            SpriteController controller) {
        m_Controller = controller;
    }

    /**
     * Gets the model of the sprite
     * @return
     */
    public SpriteModel getModel() {
        return m_Model;
    }

    /**
     * Sets the model of the sprite to the new model
     * @param model the new SpriteModel
     */
    protected void setModel(SpriteModel model) {
        m_Model = model;
    }

    /**
     * Gets the view of the sprite
     * @return
     */
    public SpriteView getView() {
        return m_View;
    }

    /**
     * Sets the view of the sprite to new view
     * @param view the SpriteView object
     */
    protected void setView(SpriteView view) {
        m_View = view;
    }
}

package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.view.SpriteView;

/**
 * Abstract class for defining the controller class for the Sprite MVC.
 * Subclasses of this class need to implement the {@link #initialize()} method
 * @see SpriteModel
 * @see SpriteView
 * @author Praket Chavan
 */
public abstract class SpriteController {
    /**
     * Stores the SpriteModel object that is linked with the controller
     * @see #setModel(SpriteModel)
     * @see #getModel()
     */
    private SpriteModel m_Model;

    /**
     * Stores the SpriteView object that is linked with the controller
     * @see #setView(SpriteView)
     * @see #getView()
     */
    private SpriteView m_View;

    /**
     * Gets the SpriteModel object linked to the controller
     * @return SpriteModel object
     * @see #setModel(SpriteModel)
     */
    public SpriteModel getModel() {
        return m_Model;
    }

    /**
     * Sets the model that is connected to the controller to the passed
     * parameter
     * @param model The model to be linked to the controller
     * @see #getModel()
     */
    public void setModel(SpriteModel model) {
        m_Model = model;
    }


    /**
     * Gets the SpriteView object linked to the controller
     * @return SpriteView object
     * @see #setView(SpriteView)
     */
    public SpriteView getView() {
        return m_View;
    }

    /**
     * Sets the model that is connected to the controller to the passed
     * parameter
     * @param view The model to be linked to the controller
     * @see #getView()
     */
    public void setView(SpriteView view) {
        m_View = view;
    }

    /**
     * Constructor to initialise the SpriteModel and SpriteView connected to
     * the controller. It also binds the common properties of the view and
     * model such as the sprite's size and bounds. Calls the
     * {@link #initialize()} method once the model and view has been set to
     * initialise the connections
     *
     * @param model The SpriteModel that is to be connected
     * @param view The SpriteView that is to be connected
     * @see #initialize()
     */
    public SpriteController(SpriteModel model, SpriteView view) {
        this.setModel(model);
        this.setView(view);
        getModel().boundsProperty()
                  .bind(getView().getView().boundsInParentProperty());
        getModel().widthProperty()
                  .bind(getView().getView().getImage().widthProperty());
        getModel().heigthProperty()
                  .bind(getView().getView().getImage().heightProperty());
        this.initialize();
    }

    /**
     * Initialises the connection between the model and view by binding the
     * view and model properties.
     */
    public abstract void initialize();
}

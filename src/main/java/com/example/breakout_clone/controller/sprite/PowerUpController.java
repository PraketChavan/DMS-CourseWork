package com.example.breakout_clone.controller.sprite;

import com.example.breakout_clone.model.sprite.PowerUpModel;
import com.example.breakout_clone.model.sprite.SpriteModel;
import com.example.breakout_clone.view.SpriteView;

/**
 * Controller class for the PowerUp MVC. It sets up the connection
 * and binds PowerUpModel attributes to the PowerUpView. It handles the
 * requests to change the PowerUpModel attributes.
 * @author Praket Chavan
 * @see com.example.breakout_clone.view.PowerUpView
 * @see PowerUpModel
 * @see SpriteController
 */
public class PowerUpController extends SpriteController {

    /**
     * Constructor to create the controller object using the super constructor
     * @param model PowerUpModel object that will be connected to the MVC
     * @param view PowerUpView object that will be connected to the MVC
     */
    public PowerUpController(SpriteModel model,
                             SpriteView view) {
        super(model, view);
    }

    /**
     * Handle the move request for the PowerUp sprite by calling the
     * {@link PowerUpModel#move()} method
     */
    public void move() {
        ((PowerUpModel)getModel()).move();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
    }
}

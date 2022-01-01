package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.BrickModel;
import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import javafx.scene.effect.ColorAdjust;

/**
 * Controller class for the Brick MVC. It sets up the connection
 * and binds BrickModel attributes to the BrickView. It handles the
 * requests to change the BrickModel attributes.
 * @author Praket Chavan
 * @see SpriteController
 */
public class BrickController extends SpriteController {

    private static final double CONTRAST_VALUE = 0.3;
    private static final double HUE_VALUE = 0.05;
    private static final double BRIGHTNESS_VALUE = 0.5;
    private static final double SATURATION_VALUE = 0.7;

    /**
     * Checks to see if the brick is broken by calling the
     * {@link BrickModel#isBroken()} method
     * @return true if the {@link BrickModel#isBroken()} returns true else false
     */
    public boolean isBroken() {
        return ((BrickModel) getModel()).isBroken();
    }

    /**
     * Constructor to create the controller object using the super constructor
     * @param model BrickModel object that will be connected to the MVC
     * @param view BrickView object that will be connected to the MVC
     */
    public BrickController(SpriteModel model,
                           SpriteView view) {
        super(model, view);
    }

    /**
     * {@inheritDoc}
     * <br>
     * Binds the views layout X and Y properties to the model X and Y
     * Location properties.
     * Binds the views visible property to the model broken property
     * Initialises a listener to the strength property of steel brick which
     * will allow the user to create cracks in the bricks
     * @see #createCrack()
     * @see BrickModel
     */
    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getView().getView().visibleProperty()
                 .bind(((BrickModel) getModel()).brokenProperty().not());
        getModel().boundsProperty()
                  .bind(getView().getView().boundsInParentProperty());
        ((BrickModel) getModel()).strengthProperty().addListener(
                (observableValue, number, t1) -> {
                    if (t1.intValue() != ((BrickModel) getModel())
                            .getFullStrength()
                            && getModel().getName().compareTo("Steel") == 0) {
                        createCrack();
                    }
                });
        getView().getView().visibleProperty().addListener(
                (observableValue, aBoolean, t1) -> {

                });
    }

    /**
     * Creates a breaking effect that will be set on the in the
     * steel brick
     * when it is weakened.
     */
    private void createCrack() {
        ColorAdjust colorAdjust = new ColorAdjust();
        // Setting the contrast value
        colorAdjust.setContrast(CONTRAST_VALUE);
        // Setting the hue value
//        colorAdjust.setHue(-HUE_VALUE);
        // Setting the brightness value
        colorAdjust.setBrightness(BRIGHTNESS_VALUE);
        // Setting the saturation value
        colorAdjust.setSaturation(SATURATION_VALUE);
        // Applying ColorAdjust effect to the ImageView node
        getView().getView().setEffect(colorAdjust);
    }
}

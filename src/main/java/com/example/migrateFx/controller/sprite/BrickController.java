package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.BrickModel;
import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.view.SpriteView;

public class BrickController extends SpriteController {

    public boolean isBroken() {
        return ((BrickModel) getModel()).isBroken();
    }


    public BrickController(SpriteModel model,
                           SpriteView view) {
        super(model, view);
    }

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
                    if (t1.intValue() > 0 && getModel().getName().compareTo(
                            "Steel") == 0) {
                        createCrack();
                    }
                });
        getView().getView().visibleProperty().addListener(
                (observableValue, aBoolean, t1) -> {

                });
    }

    private void createCrack() {
    }
}

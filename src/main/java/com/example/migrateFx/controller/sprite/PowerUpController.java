package com.example.migrateFx.controller.sprite;

import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.view.SpriteView;

public class PowerUpController extends SpriteController {

    public PowerUpController(SpriteModel model,
                             SpriteView view) {
        super(model, view);
    }

    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
    }
}

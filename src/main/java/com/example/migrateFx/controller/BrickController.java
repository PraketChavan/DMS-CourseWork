package com.example.migrateFx.controller;

import com.example.migrateFx.Breakable;
import com.example.migrateFx.Impactable;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import javafx.scene.layout.Pane;

public class BrickController extends SpriteController {

    public BrickController(SpriteModel model,
                           SpriteView view) {
        super(model, view);
    }

    public boolean checkBroken() {
        return ((BrickModel)getModel()).getStrength() <= 0;
    }

    public void onBreak() {
        ((BrickModel)getModel()).onBreak();
        if (getView().getView().getParent() != null)
            ((Pane)getView().getView().getParent()).getChildren()
                 .remove(getView().getView());
    }

    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getView().getView().visibleProperty().bind(((BrickModel)getModel()).brokenProperty().not());
        getModel().boundsProperty().bind(getView().getView()
                                                  .boundsInParentProperty());
    }

    public int findImpact(Impactable parent) {
        return ((Impactable)getModel()).findImpact(parent);
    }


    public void onImpact(int side) {
    }
}

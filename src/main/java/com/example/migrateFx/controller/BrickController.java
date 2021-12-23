package com.example.migrateFx.controller;

import com.example.migrateFx.Breakable;
import com.example.migrateFx.Impactable;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;
import javafx.scene.layout.Pane;

public class BrickController extends SpriteController implements Breakable, Impactable {

    public BrickController(SpriteModel model,
                           SpriteView view) {
        super(model, view);
    }

    @Override
    public boolean isBroken() {
        return ((BrickModel)getModel()).getStrength() <= 0;
    }

    @Override
    public void onBreak() {
        ((BrickModel)getModel()).setBroken(true);
        if (getView().getView().getParent() != null)
            ((Pane)getView().getView().getParent()).getChildren()
                 .remove(getView().getView());
    }

    @Override
    public void initialize() {
        getView().getView().xProperty().bind(getModel().getXLocationProperty());
        getView().getView().yProperty().bind(getModel().getYLocationProperty());
        getView().getView().visibleProperty().bind(((BrickModel)getModel()).brokenProperty().not());
    }

    @Override
    public int findImpact(SpriteModel parent) {
        if (!((BrickModel)getModel()).isBroken()) {
            BallModel ball = (BallModel) parent;
            if (this.getView().getView().contains(ball.rightProperty().get())) {
                onImpact(LEFT);
                ((BrickModel)getModel()).decreaseStrength();
                return LEFT;
            }
            if (this.getView().getView().contains(ball.leftProperty().get())) {
                onImpact(RIGHT);
                ((BrickModel)getModel()).decreaseStrength();
                return RIGHT;
            }
            if (this.getView().getView().contains(ball.topProperty().get())) {
                onImpact(DOWN);
                ((BrickModel)getModel()).decreaseStrength();
                return DOWN;
            }
            if (this.getView().getView()
                    .contains(ball.bottomProperty().get())) {
                onImpact(UP);
                ((BrickModel)getModel()).decreaseStrength();
                return UP;
            }
        }
        return -1;
    }

    @Override
    public void onImpact(int side) {
        BrickModel model = (BrickModel) getModel();
        model.setStrength(model.getStrength() - 1);
    }
}

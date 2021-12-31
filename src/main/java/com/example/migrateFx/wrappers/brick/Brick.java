package com.example.migrateFx.wrappers.brick;

import com.example.migrateFx.controller.sprite.BrickController;
import com.example.migrateFx.model.sprite.BrickModel;
import com.example.migrateFx.view.BrickView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

public abstract class Brick extends Sprite {

    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 16;

    public Brick(String url, Point2D location, boolean isSpec) {
        setModel(new BrickModel(location, isSpec));
        setView(new BrickView(url, BRICK_WIDTH, BRICK_HEIGHT));
        setController(new BrickController(getModel(), getView()));
    }

    public BrickController getController() {
        return (BrickController) super.getController();//m_Controller;
    }

    public BrickModel getModel() {
        return (BrickModel) super.getModel();//m_Model;
    }

    public BrickView getView() {
        return (BrickView) super.getView();//m_View;
    }
}

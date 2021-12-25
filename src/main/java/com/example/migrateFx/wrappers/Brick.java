package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BrickController;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.view.BrickView;
import javafx.geometry.Point2D;

public abstract class Brick extends Sprite{

    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 16;
//    private final BrickModel m_Model;
//    private final BrickController m_Controller;
//    private final BrickView m_View;

    public BrickController getController() {
        return (BrickController) super.getController();//m_Controller;
    }

    public BrickModel getModel() {
        return (BrickModel) super.getModel();//m_Model;
    }

    public BrickView getView() {
        return (BrickView) super.getView();//m_View;
    }

    public Brick(String url, Point2D location) {
        setModel(new BrickModel(location));
        setView(new BrickView(url, BRICK_WIDTH, BRICK_HEIGHT));
        setController(new BrickController(getModel(), getView()));
    }
}

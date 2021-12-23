package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BrickController;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.view.BrickView;
import javafx.geometry.Point2D;

public abstract class Brick {

    public static final int BRICK_WIDTH = 80;
    public static final int BRICK_HEIGHT = 16;
    private final BrickModel m_Model;
    private final BrickController m_Controller;
    private final BrickView m_View;

    public BrickController getController() {
        return m_Controller;
    }

    public BrickModel getModel() {
        return m_Model;
    }

    public BrickView getView() {
        return m_View;
    }

    public Brick(String url, Point2D location) {
        m_Model = new BrickModel(location);
        m_View = new BrickView(url, BRICK_WIDTH, BRICK_HEIGHT);
        m_Controller = new BrickController(getModel(), getView());
        getController().initialize();
    }
}

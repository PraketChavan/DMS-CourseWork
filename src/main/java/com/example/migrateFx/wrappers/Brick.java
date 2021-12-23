package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BrickController;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.view.BrickView;
import javafx.geometry.Point2D;

public abstract class Brick {
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

    public Brick(String url, Point2D location, double width, double height) {
        m_Model = new BrickModel(location);
        m_View = new BrickView(url, width, height);
        m_Controller = new BrickController(getModel(), getView());
        getController().initialize();
    }
}

package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.view.BallView;
import javafx.geometry.Point2D;

public abstract class Ball {
    protected final BallModel m_Model;
    protected final BallController m_Controller;
    protected final BallView m_View;

    public BallController getController() {
        return m_Controller;
    }

    public BallModel getModel() {
        return m_Model;
    }

    public BallView getView() {
        return m_View;
    }

    public Ball(String url, Point2D location, double size) {
        m_Model = new BallModel(location);
        m_View = new BallView(url, size, size);
        m_Controller = new BallController(getModel(), getView());
        getController().initialize();

    }
}

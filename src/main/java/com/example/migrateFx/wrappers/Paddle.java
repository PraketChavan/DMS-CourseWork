package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.PaddleController;
import com.example.migrateFx.model.PaddleModel;
import com.example.migrateFx.view.PaddleView;
import javafx.geometry.Point2D;

public class Paddle {
    protected final PaddleModel m_Model;
    protected final PaddleController m_Controller;
    protected final PaddleView m_View;

    public PaddleController getController() {
        return m_Controller;
    }

    public PaddleModel getModel() {
        return m_Model;
    }

    public PaddleView getView() {
        return m_View;
    }

    public Paddle(String url, Point2D location) {
        m_Model = new PaddleModel(location);
        m_View = new PaddleView(url);
        m_Controller = new PaddleController(getModel(), getView());
//        getController().initialize();
    }
}

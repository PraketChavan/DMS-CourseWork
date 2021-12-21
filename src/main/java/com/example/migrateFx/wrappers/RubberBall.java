package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.view.BallView;
import javafx.geometry.Point2D;


public class RubberBall {
    private static final double BALL_SIZE = 20;
    private final BallModel m_Model;
    private final BallController m_Controller;
    private final BallView m_View;

    public BallController getController() {
        return m_Controller;
    }

    public BallModel getModel() {
        return m_Model;
    }

    public BallView getView() {
        return m_View;
    }

    public RubberBall(String url, Point2D location) {
        m_Model = new BallModel(location);
        m_View = new BallView(url, BALL_SIZE, BALL_SIZE);
        m_Controller = new BallController(getModel(), getView());
        getController().initialize();
    }



}

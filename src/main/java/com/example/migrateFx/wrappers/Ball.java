package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.view.BallView;
import javafx.geometry.Point2D;

public abstract class Ball extends Sprite{
//    protected final BallModel m_Model;
//    protected final BallController m_Controller;
//    protected final BallView m_View;

    public BallController getController() {
        return (BallController) super.getController();//m_Controller;
    }

    public BallModel getModel() {
        return (BallModel) super.getModel();//m_Model;
    }

    public BallView getView() {
        return (BallView) super.getView();//m_View;
    }

    public Ball(String url, Point2D location, double size) {
        setModel(new BallModel(location));
        setView(new BallView(url, size, size));
        setController(new BallController(getModel(), getView()));

    }
}

package com.example.migrateFx.wrappers.ball;

import com.example.migrateFx.controller.sprite.BallController;
import com.example.migrateFx.model.sprite.BallModel;
import com.example.migrateFx.view.BallView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

public abstract class Ball extends Sprite {

    public Ball(String url, Point2D location, double size) {
        setModel(new BallModel(location));
        setView(new BallView(url, size, size));
        setController(new BallController(getModel(), getView()));

    }

    public BallController getController() {
        return (BallController) super.getController();//m_Controller;
    }

    public BallModel getModel() {
        return (BallModel) super.getModel();//m_Model;
    }

    public BallView getView() {
        return (BallView) super.getView();//m_View;
    }
}

package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.BallController;
import com.example.migrateFx.controller.BrickController;
import com.example.migrateFx.model.BallModel;
import com.example.migrateFx.model.BrickModel;
import com.example.migrateFx.view.BallView;
import com.example.migrateFx.view.BrickView;
import javafx.geometry.Point2D;

import javax.swing.text.View;

public class ClayBrick {

    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 12;
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

    public ClayBrick(String url, Point2D location) {
        m_Model = new BrickModel(location);
        m_View = new BrickView(url, BRICK_WIDTH, BRICK_HEIGHT);
        m_Controller = new BrickController(getModel(), getView());
        getController().initialize();
    }
}

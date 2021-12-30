package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.PaddleController;
import com.example.migrateFx.model.PaddleModel;
import com.example.migrateFx.view.PaddleView;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;

public class Paddle extends  Sprite{

    public PaddleController getController() {
        return (PaddleController) super.getController();
    }

    public PaddleModel getModel() {
        return (PaddleModel) super.getModel();
    }

    public PaddleView getView() {
        return (PaddleView) super.getView();
    }

    public Paddle(String url, Point2D location, SimpleObjectProperty<Bounds> bounds) {
        setModel(new PaddleModel(location, bounds));
        setView(new PaddleView(url));
        setController(new PaddleController(getModel(), getView()));
//        getController().initialize();
    }
}

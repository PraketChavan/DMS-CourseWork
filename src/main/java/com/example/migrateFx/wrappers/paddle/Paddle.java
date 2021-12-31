package com.example.migrateFx.wrappers.paddle;

import com.example.migrateFx.controller.sprite.PaddleController;
import com.example.migrateFx.model.sprite.PaddleModel;
import com.example.migrateFx.view.PaddleView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

public class Paddle extends Sprite {

    public Paddle(String url, Point2D location) {
        setModel(new PaddleModel(location));
        setView(new PaddleView(url));
        setController(new PaddleController(getModel(), getView()));
//        getController().initialize();
    }

    public PaddleController getController() {
        return (PaddleController) super.getController();
    }

    public PaddleModel getModel() {
        return (PaddleModel) super.getModel();
    }

    public PaddleView getView() {
        return (PaddleView) super.getView();
    }
}

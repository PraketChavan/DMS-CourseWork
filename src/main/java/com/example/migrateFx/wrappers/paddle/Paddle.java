package com.example.migrateFx.wrappers.paddle;

import com.example.migrateFx.controller.sprite.BallController;
import com.example.migrateFx.controller.sprite.PaddleController;
import com.example.migrateFx.model.sprite.BallModel;
import com.example.migrateFx.model.sprite.PaddleModel;
import com.example.migrateFx.view.BallView;
import com.example.migrateFx.view.PaddleView;
import com.example.migrateFx.wrappers.Sprite;
import javafx.geometry.Point2D;

/**
 * Wrapper class for the Paddle MVC it is responsible for creating the
 * PaddleModel, PaddleView, PaddleController and sets up the necessary
 * connections
 * <br>
 * The class has been migrated from the original code and has been broken into
 * the three different classes namely the {@link PaddleController},
 * {@link PaddleView}, {@link PaddleModel}. This was done to achieve single
 * responsibility for each classes
 * @author Praket Chavan - modified
 * @see Sprite
 */
public class Paddle extends Sprite {

    public Paddle(String url, Point2D location) {
        setModel(new PaddleModel(location));
        setView(new PaddleView(url));
        setController(new PaddleController(getModel(), getView()));
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

package com.example.migrateFx.view;

import javafx.scene.layout.Pane;

public class BallView extends SpriteView {


    public BallView(String url) {
        super(url);
    }

    public BallView(String url, double width, double height) {
        super(url, width, height);
    }

    @Override
    public void createView(Pane parent) {
        parent.getChildren().add(getView());
    }

}

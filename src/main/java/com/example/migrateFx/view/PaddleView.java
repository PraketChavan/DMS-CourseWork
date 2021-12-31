package com.example.migrateFx.view;

import javafx.scene.layout.Pane;

public class PaddleView extends SpriteView {
    public PaddleView(String url) {
        super(url);
    }

    public PaddleView(String url, double width, double height) {
        super(url, width, height);
    }

    @Override
    public void createView(Pane parent) {
        parent.getChildren().add(this.getView());
    }
}

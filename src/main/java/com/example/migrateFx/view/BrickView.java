package com.example.migrateFx.view;

import javafx.scene.layout.Pane;

public class BrickView extends SpriteView {


    public BrickView(String url) {
        super(url);
    }

    public BrickView(String url, double width, double height) {
        super(url, width, height);
    }

    @Override
    public void createView(Pane parent) {
        parent.getChildren().add(getView());
    }
}

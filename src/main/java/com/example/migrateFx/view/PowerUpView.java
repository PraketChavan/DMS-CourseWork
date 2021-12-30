package com.example.migrateFx.view;

import javafx.scene.layout.Pane;

public class PowerUpView extends SpriteView{
    public PowerUpView(String url) {
        super(url);
    }

    public PowerUpView(String url, double width, double height) {
        super(url, width, height);
    }

    @Override
    public void createView(Pane parent) {
        parent.getChildren().add(getView());

    }
}

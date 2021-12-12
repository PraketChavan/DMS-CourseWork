package com.example.migrateFx;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpriteView {
    private ImageView m_View;

    public ImageView getView() {
        return m_View;
    }

    public SpriteView(String url) {
        setView(new ImageView(url));
    }

    public SpriteView(String url, double width, double height) {
        setView(new ImageView(new Image(url, width, height, false, false)));
    }

    public void addView(Pane parent) {
        parent.getChildren().add(getView());
    }

    public void setView(ImageView view) {
        m_View = view;
    }
}

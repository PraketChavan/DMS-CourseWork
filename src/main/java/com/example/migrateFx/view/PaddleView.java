package com.example.migrateFx.view;

import javafx.scene.layout.Pane;

/**
 * View class for the PaddleView for the MVC.
 * @see SpriteView
 */
public class PaddleView extends SpriteView {

    /**
     * Constructor for the View class where the size of the sprite is not
     * specified
     * @param url the path of the sprites image resource
     */
    public PaddleView(String url) {
        super(url);
    }

    /**
     * Constructor for the view class where the size of the sprite has been
     * specified
     * @param url the path of the sprites image resource
     * @param width the width of the sprite
     * @param height the height of the sprite
     */
    public PaddleView(String url, double width, double height) {
        super(url, width, height);
    }

    /**
     * Adds the ImageView object from the super class to the parent class
     * @param parent the Pane on which the node need to be added
     */
    @Override
    public void createView(Pane parent) {
        parent.getChildren().add(this.getView());
    }
}

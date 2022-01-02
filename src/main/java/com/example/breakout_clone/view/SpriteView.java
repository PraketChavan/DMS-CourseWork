package com.example.breakout_clone.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The abstract View class for the Sprite MVC. It stores the image resource
 * for the sprite object.
 * @author Praket Chavan
 */
public abstract class SpriteView {
    /**
     * The Image view object that stores the image resource of the Sprite
     * @see #getView()
     * @see #setView(ImageView)
     */
    private ImageView m_View;

    /**
     * Gets the imageView object of the Sprite view
     * @return
     * @see #getView()
     */
    public ImageView getView() {
        return m_View;
    }

    /**
     * Sets the image view object to the new value that is passed
     * @param view
     * @see #getView()
     */
    private void setView(ImageView view) {
        m_View = view;
    }

    /**
     * Constructor for creating the image view when the size of the sprite is
     * not specified
     * @param url the path of the image resource
     */
    public SpriteView(String url) {
        setView(new ImageView(url));
    }

    /**
     * Constructor for created the image view with the desired width and height
     * @param url the path of the image resource
     * @param width the desired width of the image
     * @param height the desired height of the image
     */
    public SpriteView(String url, double width, double height) {
        setView(new ImageView(new Image(url, width, height, false, false)));
    }

    public abstract void createView(Pane parent);
}

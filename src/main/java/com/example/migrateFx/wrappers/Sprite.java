package com.example.migrateFx.wrappers;

import com.example.migrateFx.controller.sprite.SpriteController;
import com.example.migrateFx.model.sprite.SpriteModel;
import com.example.migrateFx.view.SpriteView;

public abstract class Sprite {
    private SpriteController m_Controller;
    private SpriteModel m_Model;
    private SpriteView m_View;

    public SpriteController getController() {
        return m_Controller;
    }

    protected void setController(
            SpriteController controller) {
        m_Controller = controller;
    }

    public SpriteModel getModel() {
        return m_Model;
    }

    protected void setModel(SpriteModel model) {
        m_Model = model;
    }

    public SpriteView getView() {
        return m_View;
    }

    protected void setView(SpriteView view) {
        m_View = view;
    }
}

package com.example.migrateFx.controller;

import com.example.migrateFx.model.SpriteModel;
import com.example.migrateFx.view.SpriteView;

public abstract class SpriteController {
    private SpriteModel m_Model;
    private SpriteView m_View;

    public SpriteModel getModel() {
        return m_Model;
    }

    public void setModel(SpriteModel model) {
        m_Model = model;
    }

    public SpriteView getView() {
        return m_View;
    }

    public void setView(SpriteView view) {
        m_View = view;
    }

    public SpriteController(SpriteModel model, SpriteView view) {
        this.setModel(model);
        this.setView(view);
        this.initialize();
    }

    public abstract void initialize();
}

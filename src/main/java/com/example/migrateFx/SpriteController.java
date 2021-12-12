package com.example.migrateFx;

public class SpriteController {
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
    }

    public void initalize() {
        m_View.getView().xProperty().bind(m_Model.getXLocationProperty());
        m_View.getView().yProperty().bind(m_Model.getYLocationProperty());
    }
}

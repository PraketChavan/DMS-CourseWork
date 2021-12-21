package com.example.migrateFx.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class BrickModel extends SpriteModel {
    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 12;
    private final SimpleObjectProperty<Dimension2D> m_Size;
    private final SimpleIntegerProperty m_FullStrength;
    private final SimpleIntegerProperty m_Strength;
    private final SimpleBooleanProperty m_Broken;

    public SimpleObjectProperty<Dimension2D> sizeProperty() {
        return m_Size;
    }


    public int getFullStrength() {
        return m_FullStrength.get();
    }

    public void setFullStrength(int fullStrength) {
        this.m_FullStrength.set(fullStrength);
    }

    private Dimension2D getSize() {
        return m_Size.get();
    }

    private void setSize(Dimension2D size) {
        this.m_Size.set(size);
    }

    public SimpleObjectProperty<Dimension2D> getSizeProperty() {
        return m_Size;
    }

    public int getStrength() {
        return m_Strength.get();
    }

    public void setStrength(int strength) {
        this.m_Strength.set(strength);
    }

    public boolean isBroken() {
        return m_Broken.get();
    }

    public void setBroken(boolean broken) {
        this.m_Broken.set(broken);
    }

    public BrickModel(Point2D location) {
        super(location);
        m_Broken = new SimpleBooleanProperty();
        m_Size = new SimpleObjectProperty<>();
        m_Strength = new SimpleIntegerProperty();
        m_FullStrength = new SimpleIntegerProperty();
        initializeProperties();
    }

    private void initializeProperties() {
        setBroken(false);
        setSize(new Dimension2D(BRICK_WIDTH, BRICK_HEIGHT));
        setFullStrength(1);
        setStrength(getFullStrength());
        setXLocation(getLocation().getX());
        setYLocation(getLocation().getY());
    }

    public SimpleIntegerProperty fullStrengthProperty() {
        return m_FullStrength;
    }

    public SimpleIntegerProperty strengthProperty() {
        return m_Strength;
    }

    public SimpleBooleanProperty brokenProperty() {
        return m_Broken;
    }
}

package com.example.migrateFx.model;

import com.example.migrateFx.Breakable;
import com.example.migrateFx.Impactable;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

import java.util.Random;

public class BrickModel extends SpriteModel implements Breakable, Impactable {
    private static final int BRICK_WIDTH = 60;
    private static final int BRICK_HEIGHT = 12;
    private final SimpleObjectProperty<Dimension2D> m_Size;
    private final SimpleIntegerProperty m_FullStrength;
    private final SimpleIntegerProperty m_Strength;
    private final SimpleBooleanProperty m_Special;

    public boolean isSpecial() {
        return m_Special.get();
    }

    public SimpleBooleanProperty specialProperty() {
        return m_Special;
    }

    public void setSpecial(boolean special) {
        this.m_Special.set(special);
    }

    public boolean isBroken() {
        return m_Broken.get();
    }

    private final SimpleBooleanProperty m_Broken;

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

    public BrickModel(Point2D location, boolean isSpec) {
        super(location);
        m_Special = new SimpleBooleanProperty(isSpec);
        m_Broken = new SimpleBooleanProperty();
        m_Size = new SimpleObjectProperty<>();
        m_Strength = new SimpleIntegerProperty();
        m_FullStrength = new SimpleIntegerProperty();
        initializeProperties();
    }

    @Override
    public void reset() {
        setStrength(getFullStrength());
        setBroken(false);
    }

    public SimpleObjectProperty<Dimension2D> sizeProperty() {
        return m_Size;
    }

    public void decreaseStrength() {
        setStrength(getStrength() - 1);
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

    @Override
    public int findImpact(Impactable parent) {
        if (!checkBroken()) {
            BallModel ball = (BallModel) parent;
            if (getBounds().contains(ball.rightProperty().get())) {
                onImpact(LEFT);
                decreaseStrength();
                return LEFT;
            }
            if (getBounds().contains(ball.leftProperty().get())) {
                onImpact(RIGHT);
                decreaseStrength();
                return RIGHT;
            }
            if (getBounds().contains(ball.topProperty().get())) {
                onImpact(DOWN);
                decreaseStrength();
                return DOWN;
            }
            if (getBounds().contains(ball.bottomProperty().get())) {
                onImpact(UP);
                decreaseStrength();
                return UP;
            }
        }
        return NO_IMPACT;
    }

    @Override
    public void onImpact(int side) {
        setStrength(getStrength() - 1);
    }

    @Override
    public boolean checkBroken() {
        return getStrength() <= 0;
    }

    @Override
    public void onBreak() {
        if (isSpecial() && new Random().nextBoolean())
            System.out.println("power");
        setBroken(true);
    }

    public void setBroken(boolean broken) {
        this.m_Broken.set(broken);
    }

    private void initializeProperties() {
        setBroken(false);
        setSize(new Dimension2D(BRICK_WIDTH, BRICK_HEIGHT));
        setFullStrength(1);
        setStrength(getFullStrength());
        setXLocation(getLocation().getX());
        setYLocation(getLocation().getY());
    }
}

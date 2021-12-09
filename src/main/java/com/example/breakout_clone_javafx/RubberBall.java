package com.example.breakout_clone_javafx;

import com.example.util.Sprite;
import com.example.util.Vector2D;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import javax.swing.plaf.basic.BasicSplitPaneUI;

public class RubberBall extends Ball{

    public RubberBall(Vector2D position) {
        super(new SimpleObjectProperty<>(position));
    }

    @Override
    public int findImpact(Sprite sprite) {
        Point2D top = new Point2D(getPosition().getX() , getPosition().getY() - getRadius());
        Point2D bottom = new Point2D(getPosition().getX(), getPosition().getY() + getRadius());
        Point2D left = new Point2D(getPosition().getX() - getRadius(), getPosition().getY());
        Point2D right = new Point2D(getPosition().getX() + getRadius(), getPosition().getY());

        if (sprite.contains(top)) {
            sprite.onImpact(DOWN);
            return UP;
        }
        if (sprite.contains(bottom)) {
            sprite.onImpact(UP);
            return DOWN;
        }
        if (sprite.contains(left)) {
            sprite.onImpact(RIGHT);
            return LEFT;
        }
        if (sprite.contains(right)){
            sprite.onImpact(LEFT);
            return RIGHT;
        }

        return NO_IMPACT;

//        Bounds bounds = this.getParent().getLayoutBounds();
//        //System.out.println(bounds);
//        if (this.getPosition().getX() >= (bounds.getMaxX() - this.getRadius()))
//            return RIGHT;
//        if (this.getPosition().getX() <= (bounds.getMinX() + this.getRadius()))
//            return LEFT;
//        if (this.getPosition().getY() >= (bounds.getMaxY() - this.getRadius()))
//            return DOWN;
//        if (this.getPosition().getY() <= (bounds.getMinY() + this.getRadius()))
//            return UP;
//
//        return NO_IMPACT;
    }

    @Override
    public void onImpact(int side) {
        switch (side){
            case LEFT, RIGHT -> getSpeed().invertX();
            case DOWN, UP -> getSpeed().invertY();
        }
    }

    @Override
    public Node createView() {

        Circle circle = new Circle();
        circle.setRadius(getRadius());
        circle.setCenterX(getRadius());
        circle.setCenterY(getRadius());
        circle.setStroke(Color.GREEN);
        circle.setFill(Color.GREEN.deriveColor(1, 1, 1, 0.3));
        return circle;
    }
}

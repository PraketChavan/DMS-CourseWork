package com.example.breakout_clone_javafx;

import com.example.util.Vector2D;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClayBrick extends Brick{


    public ClayBrick(
            Vector2D position) {
        super(new SimpleObjectProperty<>(position));
    }

    @Override
    public void onImpact(int side) {

    }

    @Override
    public Node createView() {
        Rectangle brick = new Rectangle();
        brick.setHeight(this.getmHeight());
        brick.setWidth(this.getmWidth());
        brick.setFill(Color.GRAY);
        brick.setStroke(Color.GRAY.deriveColor(1, 1, 1 , 0.5));

        return brick;
    }
}

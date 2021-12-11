package com.example.migrateFx;

import java.awt.geom.Ellipse2D;
import javafx.scene.paint.Color;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Ball1 extends Ball {


    private static final String NAME = "Rubber Ball";
    private static final int DEF_RADIUS = 5;
    private static final Color DEF_INNER_COLOR = Color.rgb(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();
    private static final float HALF = 0.5f;

    public Ball1(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX();
        double y = center.getY();

        return new Circle(x, y, radiusA);
    }
}

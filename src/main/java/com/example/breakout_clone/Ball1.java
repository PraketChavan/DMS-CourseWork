package com.example.breakout_clone;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Ball1 extends Ball {


    private static final String NAME = "Rubber Ball";
    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();
    private static final float HALF = 0.5f;

    public Ball1(Point2D center){
        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
    }


    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {

        double x = center.getX() - (radiusA * HALF);
        double y = center.getY() - (radiusB * HALF);

        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}

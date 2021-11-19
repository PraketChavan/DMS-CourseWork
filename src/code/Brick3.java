package code;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


public class Brick3 extends Brick {

    private static final String NAME = "Steel Brick";
    private static final Color DEF_INNER = new Color(203, 203, 201);
    private static final Color DEF_BORDER = Color.BLACK;
    private static final int STEEL_STRENGTH = 1;
    private static final double STEEL_PROBABILITY = 0.4;

    private final Random m_ChildRnd;

    public Random getChildRnd() {
        return m_ChildRnd;
    }

    public Shape getM_ChildBrickFace() {
        return m_ChildBrickFace;
    }

    private final Shape m_ChildBrickFace;

    public Brick3(Point point, Dimension size) {
        super(NAME,point,size,DEF_BORDER,DEF_INNER,STEEL_STRENGTH);
        m_ChildRnd = new Random();
        m_ChildBrickFace = super.getBrickFace();
    }


    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    @Override
    public Shape getBrick() {
        return m_ChildBrickFace;
    }

    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    public void impact(){
        if(this.getChildRnd().nextDouble() < STEEL_PROBABILITY){
            super.impact();
        }
    }

}

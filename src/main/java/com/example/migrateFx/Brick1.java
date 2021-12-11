package com.example.migrateFx;


import java.awt.geom.GeneralPath;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


public class Brick1 extends Brick {


    private static final String NAME = "Cement Brick";
    private static final Color DEF_INNER = Color.rgb(147, 147, 147);
    private static final Color DEF_BORDER = Color.rgb(217, 199, 175);
    private static final int CEMENT_STRENGTH = 2;

    private final Crack crack;
    private Shape m_ChildBrickFace;

    public Crack getCrack() {
        return crack;
    }

    public Shape getChildBrickFace() {
        return m_ChildBrickFace;
    }

    public void setChildBrickFace(Shape m_brickFace) {
        this.m_ChildBrickFace = m_brickFace;
    }

    public Brick1(Point2D point, Dimension2D size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CEMENT_STRENGTH);
        crack = new Crack(DEF_CRACK_DEPTH,DEF_STEPS);
        this.setChildBrickFace(super.getBrickFace());
    }

    @Override
    protected Shape makeBrickFace(Point2D pos, Dimension2D size) {
        return new Rectangle(pos.getX(), pos.getY(), size.getWidth(), size.getHeight());
    }

    @Override
    public boolean setImpact(Point2D point, int dir) {
        if(super.isBroken())
            return false;
        super.impact();
        if(!super.isBroken()){
//            this.getCrack().makeCrack(point,dir);
            updateBrick();
            return false;
        }
        return true;
    }


    @Override
    public Shape getBrick() {
        return m_ChildBrickFace;
    }

    private void updateBrick(){
//        if(!super.isBroken()){
//            GeneralPath gp = this.getCrack().draw();
//            gp.append(super.getBrickFace(),false);
////            m_brickFace = gp;
//            this.setChildBrickFace(gp);
//        }
    }

    public void repair(){
        super.repair();
        this.getCrack().reset();
//        m_brickFace = super.getBrickFace();
        this.setChildBrickFace(super.getBrickFace());
    }
}

package com.example.migrateFx;



import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Paddle {


    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    public static final int DEF_MOVE_AMOUNT = 5;

    private Rectangle m_paddleFace;
    private Point2D m_ballPoint;
    private int m_moveAmount;
    private int m_min;
    private int m_max;

    public Point2D getBallPoint() {
        return m_ballPoint;
    }

    public int getMax() {
        return m_max;
    }

    public int getMin() {
        return m_min;
    }

    public int getMoveAmount() {
        return m_moveAmount;
    }


    public Paddle(Point2D ballPoint, int width, int height, Rectangle container) {
        this.setBallPoint(ballPoint);
        setMoveAmount(0);
        setPaddleFace(makeRectangle(width, height));
        setMin(container.xProperty().intValue() + (width / 2));
        setMax(getMin() + container.widthProperty().intValue() - width);

    }

    public Rectangle makeRectangle(int width,int height){
        Point2D p = new Point2D((int)(getBallPoint().getX() - (width / 2)), (int) getBallPoint().getY());
        return  new Rectangle(p.getX(), p.getY(), width, height);
    }

    public boolean impact(Ball b){
        if (b.getPosition().getY() == 429)
            System.out.println("Below");
        return getPaddleFace().intersects(b.getBallFace().getBoundsInParent()) && getPaddleFace().contains(b.getDown()) ;
    }

    public void move(){
        double x = getBallPoint().getX() + getMoveAmount();
        if(x < getMin() || x > getMax())
            return;
        setBallPoint(new Point2D(x, getBallPoint().getY()));
        System.out.println(getBallPoint().getX() + "\t" + getBallPoint().getY());

//        ballPoint.setLocation(x,ballPoint.getY());
        getPaddleFace().relocate(getBallPoint().getX() -  ((Rectangle)getPaddleFace()).getWidth()/2, getBallPoint().getY());
        setPaddleFace(new Rectangle(getBallPoint().getX() -  ((Rectangle)getPaddleFace()).getWidth()/2, getBallPoint().getY(), ((Rectangle) getPaddleFace()).getWidth(), ((Rectangle) getPaddleFace()).getHeight()));
    }

    public void moveLeft(){
        setMoveAmount(-DEF_MOVE_AMOUNT);
    }

    public void movRight(){
        setMoveAmount(DEF_MOVE_AMOUNT);
    }

    public void stop(){
        setMoveAmount(0);
    }

    public Shape getPaddleFace(){
        return m_paddleFace;
    }


    public void moveTo(Point2D p){
        setBallPoint(p);

        getPaddleFace().relocate(getBallPoint().getX() - ((Rectangle)getPaddleFace()).getWidth()/2, getBallPoint().getY());
    }

    public void setPaddleFace(Rectangle paddleFace) {
        m_paddleFace = paddleFace;
    }

    public void setBallPoint(Point2D ballPoint) {
        m_ballPoint = ballPoint;
    }

    public void setMoveAmount(int moveAmount) {
        m_moveAmount = moveAmount;
    }

    public void setMin(int min) {
        m_min = min;
    }

    public void setMax(int max) {
        m_max = max;
    }
}

package com.example.breakout_clone;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

abstract public class Brick {

    public static final int MIN_CRACK = 1;
    public static final int MAX_CRACK = 3;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random m_rnd;

    private Shape m_brickFace;
    private String m_name;
    private Color m_BorderColor;
    private Color m_InnerColor;
    private int m_FullStrength;
    private int m_Strength;
    private boolean m_Broken;

    public static Random getRnd() {
        return m_rnd;
    }

    public static void setRnd(Random rnd) {
        Brick.m_rnd = rnd;
    }

    public Shape getBrickFace() {
        return m_brickFace;
    }

    public void setBrickFace(Shape brickFace) {
        this.m_brickFace = brickFace;
    }

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }

    public Color getBorderColor() {
        return m_BorderColor;
    }

    public void setBorderColor(Color border) {
        this.m_BorderColor = border;
    }

    public Color getInnerColor() {
        return m_InnerColor;
    }

    public void setInnerColor(Color inner) {
        this.m_InnerColor = inner;
    }

    public int getFullStrength() {
        return m_FullStrength;
    }

    public void setFullStrength(int fullStrength) {
        this.m_FullStrength = fullStrength;
    }

    public int getStrength() {
        return m_Strength;
    }

    public void setStrength(int strength) {
        this.m_Strength = strength;
    }

    public final boolean isBroken() {
        return m_Broken;
    }

    public void setBroken(boolean broken) {
        this.m_Broken = broken;
    }

    public abstract Shape getBrick();




    protected abstract Shape makeBrickFace(Point pos, Dimension size);

    public Brick(String name, Point pos, Dimension size, Color border, Color inner, int strength) {
        setRnd(new Random());
        //broken = false;
        this.setBroken(false);
        //this.name = name;
        this.setName(name);
        //brickFace = makeBrickFace(pos, size);
        this.setBrickFace(makeBrickFace(pos, size));

//        this.border = border;
//        this.inner = inner;
//        this.fullStrength = this.strength = strength;

        this.setBorderColor(border);
        this.setInnerColor(inner);
        this.setFullStrength(strength);
        this.setStrength(strength);

    }

    public boolean setImpact(Point2D point, int dir) {
        if (this.isBroken())
            return false;
        impact();
        return this.isBroken();
    }

    public final int findImpact(Ball b) {
        if (this.isBroken())
            return 0;
        int out = 0;
        if (this.getBrickFace().contains(b.getRight()))
            out = LEFT_IMPACT;
        else if (this.getBrickFace().contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if (this.getBrickFace().contains(b.getUp()))
            out = DOWN_IMPACT;
        else if (this.getBrickFace().contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    public void repair() {
//        m_Broken = false;
//        m_Strength = m_FullStrength;
        this.setBroken(false);
        this.setStrength(this.getFullStrength());
    }

    public void impact() {
//        m_Strength--;
//        m_Broken = (m_Strength == 0);
        this.setStrength(this.getStrength() - 1);
        this.setBroken(this.getStrength() == 0);
    }

    public class Crack {

        public static final int LEFT = 10;
        public static final int RIGHT = 20;
        public static final int UP = 30;
        public static final int DOWN = 40;
        public static final int VERTICAL = 100;
        public static final int HORIZONTAL = 200;
        private static final int CRACK_SECTIONS = 3;
        private static final double JUMP_PROBABILITY = 0.7;
        private GeneralPath m_crack;

        private int m_crackDepth;
        private int m_steps;

        public GeneralPath getCrack() {
            return m_crack;
        }

        public void setCrack(GeneralPath crack) {
            this.m_crack = crack;
        }

        public int getCrackDepth() {
            return m_crackDepth;
        }

        public void setCrackDepth(int crackDepth) {
            this.m_crackDepth = crackDepth;
        }

        public int getSteps() {
            return m_steps;
        }

        public void setSteps(int steps) {
            this.m_steps = steps;
        }

        public Crack(int crackDepth, int steps) {

            //m_crack = new GeneralPath();
            this.setCrack(new GeneralPath());
//            this.m_crackDepth = crackDepth;
//            this.m_steps = steps;
            this.setCrackDepth(crackDepth);
            this.setSteps(steps);
        }

        protected GeneralPath draw() {
            return this.getCrack();
        }

        protected void reset() {
            this.getCrack().reset();
        }

        protected void makeCrack(Point2D point, int direction) {
            Rectangle bounds = Brick.this.getBrickFace().getBounds();

            Point impact = new Point((int) point.getX(), (int) point.getY());
            Point start = new Point();
            Point end = new Point();

            //Changes all direct accesses to the x y width and height to
            // accessor methods
            switch (direction) {
                case LEFT:
                    start.setLocation(bounds.getX() + bounds.getWidth(), bounds.getY());
                    end.setLocation(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds
                            .getHeight());
                    Point tmp = makeRandomPoint(start, end, VERTICAL);
                    makeCrack(impact, tmp);
                    break;

                case RIGHT:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.getX(), bounds.getY() + bounds.getHeight());
                    tmp = makeRandomPoint(start, end, VERTICAL);
                    makeCrack(impact, tmp);
                    break;

                case UP:
                    start.setLocation(bounds.getX(), bounds.getY() + bounds.getHeight());
                    end.setLocation(bounds.getX() + bounds.getWidth(), bounds.getY() + bounds
                            .getHeight());
                    tmp = makeRandomPoint(start, end, HORIZONTAL);
                    makeCrack(impact, tmp);
                    break;

                case DOWN:
                    start.setLocation(bounds.getLocation());
                    end.setLocation(bounds.getX() + bounds.getWidth(), bounds.getY());
                    tmp = makeRandomPoint(start, end, HORIZONTAL);
                    makeCrack(impact, tmp);
                    break;
            }
        }

        protected void makeCrack(Point start, Point end) {
            final int FIVE = 5;
            GeneralPath path = new GeneralPath();


            path.moveTo(start.getX(), start.getY());

            double w = (end.getX() - start.getX()) / (double) this.getSteps();
            double h = (end.getY() - start.getY()) / (double) this.getSteps();

            int bound = this.getCrackDepth();
            int jump = bound * FIVE;

            double x, y;

            for (int i = 1; i < this.getSteps(); i++) {

                x = (i * w) + start.getX();
                y = (i * h) + start.getY() + randomInBounds(bound);

                if (inMiddle(i, CRACK_SECTIONS, this.getSteps()))
                    y += jumps(jump, JUMP_PROBABILITY);

                path.lineTo(x, y);

            }

            path.lineTo(end.getX(), end.getY());
            this.getCrack().append(path, true);
        }

        private int randomInBounds(int bound) {
            final int DOUBLE = 2;

            int n = (bound * DOUBLE) + 1;
            return getRnd().nextInt(n) - bound;
        }

        private boolean inMiddle(int i, int steps, int divisions) {
            int low = (steps / divisions);
            int up = low * (divisions - 1);

            return (i > low) && (i < up);
        }

        private int jumps(int bound, double probability) {

            if (getRnd().nextDouble() > probability)
                return randomInBounds(bound);
            return 0;

        }

        private Point makeRandomPoint(Point from, Point to, int direction) {

            Point out = new Point();
            int pos;

            switch (direction) {
                case HORIZONTAL -> {
                    pos = (int) (getRnd().nextInt((int) (to.getX() - from.getX())) + from
                            .getX());
                    out.setLocation(pos, to.getY());
                }
                case VERTICAL -> {
                    pos = (int) (getRnd().nextInt((int) (to.getY() - from.getY())) + from
                            .getY());
                    out.setLocation(to.getX(), pos);
                }
            }
            return out;
        }

    }


}






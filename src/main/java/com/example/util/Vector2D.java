package com.example.util;

public class Vector2D {
    private double m_X;
    private double m_Y;

    public double getX() {
        return m_X;
    }

    public void setX(double x) {
        m_X = x;
    }

    public double getY() {
        return m_Y;
    }

    public void setY(double y) {
        m_Y = y;
    }

    public Vector2D(double x, double y) {
        setX(x);
        setY(y);
    }

    public void setMagnitude (double x, double y) {
        setX(x);
        setY(y);
    }

    public double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY());
    }

    public void addVector(Vector2D v2) {
        setY(getY() + v2.getY());
        setX(getX() + v2.getX());
    }

    public void multiply(double num) {
        setX(getX() * num);
        setY(getY() * num);
    }

    public void subVector(Vector2D v2) {
        setY(getY() - v2.getY());
        setX(getX() - v2.getX());
    }

    public void invertX() {
        setX(getX() * -1);
    }

    public void invertY() {
        setY(getY() * -1);
    }
}

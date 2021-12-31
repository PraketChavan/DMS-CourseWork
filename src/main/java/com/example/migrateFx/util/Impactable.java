package com.example.migrateFx.util;


import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

public interface Impactable {
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int NO_IMPACT = -1;
    int LEFT_MOST_IMPACT = 4;
    int LEFT_IMPACT = 5;
    int MIDDLE_IMPACT = 6;
    int RIGHT_IMPACT = 7;
    int RIGHT_MOST_IMPACT = 8;

    Bounds GAME_BOUNDS = new BoundingBox(0, 0, 640, 500);

    int findImpact(Impactable parent);

    void onImpact(int side);
}

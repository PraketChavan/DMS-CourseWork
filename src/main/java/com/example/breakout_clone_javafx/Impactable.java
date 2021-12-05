package com.example.breakout_clone_javafx;


import com.example.util.Sprite;

public interface Impactable {
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int NO_IMPACT = -1;
    int findImpact(Sprite sprite);
    void onImpact(int side);
}

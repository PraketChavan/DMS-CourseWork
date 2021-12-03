package com.example.breakout_clone_javafx;


public interface Impactable {
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;

    void onImpact(int side);
}

package com.example.migrateFx;


import com.example.migrateFx.model.SpriteModel;

public interface Impactable {
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int NO_IMPACT = -1;
    int findImpact(SpriteModel parent);
    void onImpact(int side);
}

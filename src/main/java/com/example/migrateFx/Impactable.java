package com.example.migrateFx;


import com.example.util.Sprite;
import javafx.scene.layout.Pane;

public interface Impactable {
    int UP = 0;
    int RIGHT = 1;
    int DOWN = 2;
    int LEFT = 3;
    int NO_IMPACT = -1;
    void findImpact(Pane parent);
    void onImpact(int side);
}

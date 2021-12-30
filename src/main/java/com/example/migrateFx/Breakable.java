package com.example.migrateFx;

public interface Breakable {
    int SPECIAL_BREAK = 1;
    int NORMAL_BREAK = 2;
    boolean checkBroken();
    int onBreak();
}

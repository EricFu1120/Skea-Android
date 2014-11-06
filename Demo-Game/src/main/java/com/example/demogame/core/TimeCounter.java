package com.example.demogame.core;


import static com.example.demogame.core.BarConst.TYPE.*;
import static com.example.demogame.core.BarConst.LEVEL.*;

/**
 * Created by Ervin on 14/11/1.
 */
public class TimeCounter {

    private int totalTime;

    private int remainingTime;

    public TimeCounter(int level) {
        int num = BarConst.LEVEL.NUM[level];
        totalTime = (TIME[SHORT] + TIME[MEDIUM] + TIME[LONG]) * num + 3 * num - 1;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

}

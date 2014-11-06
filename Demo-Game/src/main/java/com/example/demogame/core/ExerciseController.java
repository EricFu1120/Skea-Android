package com.example.demogame.core;

import android.content.Context;

import static com.example.demogame.core.BarConst.TYPE.*;
import static com.example.demogame.core.BarConst.LEVEL.*;

/**
 * Created by Ervin on 14/11/2.
 */
public class ExerciseController {

    private Context context;

    private TimeCounter timeCounter;

    private ScoreCounter scoreCounter;

    private int speed;

    private int level;

    private int slot;

    public ExerciseController() {
        //TODO get user's level

    }

    private void initBarsNum(int level) {
        int num = 0;
        switch (level) {
            case 1:
                num = 15;
                break;
            case 2:
                num = 20;
                break;
            case 3:
                num = 30;
                break;
            case 4:
                num = 40;
            default:
                break;
        }
    }


}

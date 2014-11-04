package com.example.demogame.core;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup;

import com.example.demogame.view.BarViewWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin on 14/11/2.
 */
public class ExerciseManager {

    private Context context;

    private TimeCounter timeCounter;

    private ScoreCounter scoreCounter;

    private int level;

    private int barLongNum, barMediumNum, barShortNum;

    private ViewGroup front, behind;

    private int slot;

    private BarViewWrapper wrapper1, wrapper2;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (slot > 0) {
                slot--;
            } else {
                wrapper2.animate();
            }
        }
    };

//    public static ExerciseManager instance;

//    public static ExerciseManager getInstance() {
//        if (instance != null)
//            instance = new ExerciseManager();
//        return instance;
//    }

    public ExerciseManager(Context context, ViewGroup front, ViewGroup behind) {
        //TODO 读取用户Level级别
        //TODO 初始化个数
        initBarsNum(1);
        this.context = context;
        this.front = front;
        this.behind = behind;
        wrapper1 = new BarViewWrapper(context, 1, front, behind);
        wrapper2 = new BarViewWrapper(context, 0, front, behind);
//        shortList = new ArrayList<BarViewWrapper>();
//        mediumList = new ArrayList<BarViewWrapper>();
//        longList = new ArrayList<BarViewWrapper>();
    }

    public void start() {
        timeCounter = new TimeCounter(handler);
        timeCounter.startTimingTask();

//        BarViewWrapper wrapper1 = getBarViewWrapper(0);

        wrapper1.animate();
        wrapper2.animate();
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
        barLongNum = barMediumNum = barShortNum = num;
    }

    private List<BarViewWrapper> shortList, mediumList, longList;

    private BarViewWrapper getBarViewWrapper(int type) {
        BarViewWrapper wrapper = null;
        switch (type) {
            case BarViewWrapper.BAR_VIEW_LONG:
                barLongNum = barLongNum - 1;
                break;
            case BarViewWrapper.BAR_VIEW_MEDIUM:
                barMediumNum = barMediumNum - 1;
                break;
            case BarViewWrapper.BAR_VIEW_SHORT:
                barShortNum = barShortNum - 1;
                break;
            default:
                break;
        }
        wrapper = new BarViewWrapper(context, type, front, behind);
        slot = 2 + 7;
        return wrapper;
    }

}

package com.example.demogame.core;

import android.content.Context;
import android.util.Log;

import com.example.demogame.DensityUtils;
import com.example.demogame.view.BarGenerator;
import com.example.demogame.view.BarGroupManager;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ervin on 14/11/2.
 */
public class ExerciseController {

    private Timer timer;

    private TimerTask timerTask;

    private final int speed;

    private final int UNIT_TIME = 100;

    private int offset;

    private int activePosition = -1;

    private int nextPosition;

    private List<Bar> list;

    public ExerciseController(Context context) {
        //TODO get user's level
        speed = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_HEIGHT);
        list = BarGenerator.getInstance().getBars();
        nextPosition = list.size() - 1;
        Log.d("The speed of bar is ", "" + speed);

    }

    /**
     * 开始计算分数
     */
    public void shrink() {

    }

    public void relax() {

    }


    public void startTimerTask() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                BarGroupManager.getInstance().scrollBy(-speed);
                offset = offset + speed;
                checkActivePosition();
            }
        }, 0, UNIT_TIME);
    }

    public void checkActivePosition() {
        Bar bar = list.get(nextPosition);
        //确定激活的Bar
        if (nextPosition >= 0) {
            if (bar.getBeginActiveOffset() <= offset && bar.getEndActiviteOffset() >= offset && activePosition == -1) {
                activePosition = nextPosition;
                nextPosition = activePosition - 2;
                Log.d("checkActivePosition", "" + "activePosition = " + activePosition);
                Log.d("checkActivePosition", "" + "nextPosition = " + nextPosition);
            } else {
                activePosition = -1;
            }
        } else {
            //TODO 所有Bar都落完了
        }
    }

    public void endTimerTask() {

    }

    public void pasue() {

    }

    public void start() {

    }


    public interface OnExcerciseStateChangedListener {
        void onStart();

        void onPause();

        void onEnd();
    }


}

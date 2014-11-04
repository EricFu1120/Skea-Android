package com.example.demogame.core;

import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Ervin on 14/11/1.
 */
public class TimeCounter {

    private int totalTime;

    private Timer timer;

    private TimerTask timerTask;

    private Handler handler;

    public TimeCounter(final Handler handler) {
        this.handler = handler;
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                if (totalTime > 0) {
                    totalTime--;
                    handler.sendEmptyMessage(0);
                }
            }
        };
    }

    public void startTimingTask() {
        if (timer != null) {
            timer.schedule(timerTask, 1000, 1000);
        } else {
            timer = new Timer();
            startTimingTask();
        }
    }

    public void cancelTimingTask() {
        if (timer != null)
            timer.cancel();
    }

}

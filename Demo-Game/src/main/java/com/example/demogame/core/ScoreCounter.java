package com.example.demogame.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin on 14/11/1.
 */
public class ScoreCounter {

    private static ScoreCounter instance;

    private int count = 0;

    private Bar bar;

    private List<Integer> segments;

    private boolean lock;

    public static ScoreCounter getInstance() {
        if (instance == null)
            instance = new ScoreCounter();
        return instance;
    }

    private ScoreCounter() {
        segments = new ArrayList<Integer>();
    }

    public void startScore(Bar bar) {
        this.bar = bar;
        lock = true;
    }

    public void tickScore() {
        segments.add(getScore());
        count = 0;
    }

    public void stopScore() {
        bar.setScore(count);
        segments.clear();
        count = 0;
        lock = false;
    }

    public void receiveSignal() {
        if (!lock)
            count++;
    }

    private int getScore() {
        return 0;
    }

}

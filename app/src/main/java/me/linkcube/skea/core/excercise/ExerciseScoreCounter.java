package me.linkcube.skea.core.excercise;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import custom.android.util.Timber;

/**
 * Created by Ervin on 14/11/1.
 */
public class ExerciseScoreCounter {

    private static ExerciseScoreCounter instance;

    private int count = 0;

    private Bar bar;

    private List<Segment> segments;

    private boolean lock;

    private int totalScore;

    public static ExerciseScoreCounter getInstance() {
        if (instance == null)
            instance = new ExerciseScoreCounter();
        return instance;
    }

    private ExerciseScoreCounter() {
        segments = new ArrayList<Segment>();
    }

    public void startScore(Bar bar) {
        this.bar = bar;
        lock = true;
    }

    public void tickScore() {
        if (lock) {
            //TODO 可能出现锁问题
            Segment segment = new Segment(count);
            segments.add(segment);
            count = 0;
        }
    }

    public int stopScore() {

        if (lock) {
            float score = getScore();
            totalScore += getScore();
            Log.d("stopScore", "totalScore=" + totalScore);
            bar.setScore(score);
            segments.clear();
            count = 0;
            lock = false;
        }
        return totalScore;
    }

    public void receiveSignal() {
        if (lock) {
            count++;
        }
    }

    private float getScore() {
        float segmentScore = 0;
        float duration = 0;
        int counter = 0;
        Log.d("getScore", "segments size = " + segments.size());
        for (int i = 0; i < segments.size(); i++) {
            if (segments.get(i).isAvailable()) {
                counter = counter + 1;
            } else {
                duration = 0.5f * counter;
                segmentScore += 2.17 * duration * duration + 9.73 * duration;
                counter = 0;
            }
            if (i == segments.size() - 1) {
                duration = 0.5f * counter;
                segmentScore += 2.17f * duration * duration + 9.73f * duration;
            }
        }

        return segmentScore;
    }

    private class Segment {

        boolean available;

        int counter;

        public Segment(int counter) {
            this.counter = counter;
            if (counter < 15) {
                available = false;
            } else {
                available = true;
            }
        }

        public boolean isAvailable() {
            return available;
        }

    }


}

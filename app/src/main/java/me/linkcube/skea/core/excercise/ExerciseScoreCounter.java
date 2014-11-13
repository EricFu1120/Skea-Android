package me.linkcube.skea.core.excercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin on 14/11/1.
 */
public class ExerciseScoreCounter {

    private static ExerciseScoreCounter instance;

    private int count = 0;

    private Bar bar;

    private List<Segment> segments;

    private boolean lock;

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
        Segment segment = new Segment();
        segment.setCounter(count);
        segments.add(segment);
        count = 0;
    }

    public int stopScore() {
        int score = getScore();
        bar.setScore(score);
        segments.clear();
        count = 0;
        lock = false;
        return score;
    }

    public void receiveSignal() {
        if (!lock)
            count++;
    }

    private int getScore() {
        return 0;
    }

    private void calculate() {
        int segmentScore = 0;
        float duration = 0;
        int counter = 0;
        for (int i = 0; i < segments.size(); i++) {
            if (segments.get(i).isAvailable()) {
                counter++;
            } else {
                duration = 0.5f * counter;
                //TODO 计计算得分
                counter = 1;
            }
        }
        duration = 0.5f * counter;
        //TODO 计计算得分
        //segmentScore +=
    }

    private class Segment {

        int counter;

        float score;

        boolean available;

        public void setCounter(int counter) {
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

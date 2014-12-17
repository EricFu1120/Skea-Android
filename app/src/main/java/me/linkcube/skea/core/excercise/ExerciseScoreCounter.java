package me.linkcube.skea.core.excercise;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin on 14/11/1.
 */
public class ExerciseScoreCounter {

    private static ExerciseScoreCounter instance;

    private int game_count = 0;
    private int cool_count = 0;
    private int perfect_count = 0;

    private Bar bar;

    private List<Segment> segments;

    private boolean lock = false;
    private boolean cool_lock = false;
    private boolean perfect_lock = false;

    private int totalScore;

    private ExerciseScoreCounter() {
        segments = new ArrayList<Segment>();
    }

    public static ExerciseScoreCounter getInstance() {
        if (instance == null)
            instance = new ExerciseScoreCounter();
        return instance;
    }

    public void startScore(Bar bar) {
//        this.bar = bar;
        lock = true;
    }

    public void startCoolScore(Bar bar) {
        this.bar = bar;
        cool_lock = true;

    }

    public void startPerfectScore(Bar bar) {
//        this.bar = bar;
        perfect_lock = true;
    }

    public void tickScore() {
        if (lock) {
            //TODO 可能出现锁问题
            Segment segment = new Segment(game_count);
            segments.add(segment);
            game_count = 0;
        }
    }

    public void tickCoolScore() {
        if (cool_lock) {
            Log.i("CXC", "----Cool ++++");

        }
    }

    public void tickPerfectScore() {
        if (perfect_lock) {
            Log.i("CXC", "----perfect ++++");
        }
    }

    /**
     * 计算当前游戏得分
     */
    public int stopScore() {

        if (lock) {
            float score = getScore();
            totalScore += getScore();
            bar.setScore(score);
            Log.i("CXC", "score:---" + score);
            segments.clear();
            lock = false;
        }
        game_count = 0;
        return totalScore;
    }

    public int stopCoolScore() {

        cool_lock = false;

        return 0;

    }

    public int stopPerfectScore() {
        if (cool_count > 0) {
            totalScore += 30;
//                cool_count = 0;
            Log.i("CXC", "Cool ++++30");

        } else if (perfect_count > 0) {
            totalScore += 50;

//                perfect_count = 0;
            Log.i("CXC", "perfect +++50");


        } else {

            totalScore += 0;
        }
        perfect_count = 0;
        cool_count = 0;
        perfect_lock = false;

        return totalScore;
    }

    public void receiveSignal() {

        if (perfect_lock) {
            perfect_count++;
        }
        if (cool_lock) {
            cool_count++;
        }
        if (lock) {
            game_count++;
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

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
    private int perfect_cool_score=0;

    private ExerciseScoreCounter() {
        segments = new ArrayList<Segment>();
    }

    public static ExerciseScoreCounter getInstance() {
        if (instance == null)
            instance = new ExerciseScoreCounter();
        return instance;
    }

    public void startScore(Bar bar) {
        this.bar = bar;
        lock = true;
    }

    public void startCoolScore(Bar bar) {
        this.bar = bar;
        cool_lock = true;

    }

    public void startPerfectScore(Bar bar) {
        this.bar = bar;
        perfect_lock = true;
    }

    public boolean tickScore() {
        if (lock) {
            //TODO 可能出现锁问题
            Segment segment = new Segment(game_count);
            segments.add(segment);
            game_count=0;
            return segment.isAvailable();
        }
        else {
            game_count = 0;
            return false;
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
            totalScore+=score;
            bar.setScore(score+perfect_cool_score);
        }
        //归“0”
        perfect_lock=false;
        cool_lock=false;
        lock = false;

        game_count = 0;
        perfect_cool_score=0;
        return totalScore;
    }

    public int stopCoolScore() {

        if (cool_count > 0) {
            perfect_cool_score=BarConst.SCORE.COOL_SCORE;
            totalScore += perfect_cool_score;
            Log.i("CXC", "Cool ++++30");

        }
        cool_lock = false;

        return totalScore;

    }

    public int stopPerfectScore() {
        if (cool_count<=0 && perfect_count > 0) {
            perfect_cool_score=BarConst.SCORE.PERFECT_SCORE;
            totalScore += perfect_cool_score;
            Log.i("CXC", "perfect +++50");
        }
        perfect_count = 0;
        cool_count = 0;
        perfect_lock = false;

        return totalScore;
    }

    public void receiveSignal() {

        if (cool_lock) {
            cool_count++;
        }

        if (perfect_lock) {
            perfect_count++;
        }

        if (lock) {
            game_count++;
        }
    }

    private float getScore() {
        float segmentScore = 0.0f;
        float duration = 0.0f;
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
        //清空
        segments.clear();

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

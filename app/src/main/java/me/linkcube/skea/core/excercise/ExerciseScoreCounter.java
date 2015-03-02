package me.linkcube.skea.core.excercise;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.core.KeyConst;
import me.linkcube.skea.db.BarScore;
import me.linkcube.skea.db.DayRecord;
import me.linkcube.skea.db.SegmentScore;
import me.linkcube.skea.ui.evaluation.ExerciseLevelSettingActivity;

/**
 * Created by Ervin on 14/11/1.
 */
public class ExerciseScoreCounter {

    private static ExerciseScoreCounter instance;

    private int game_count = 0;
    private int cool_count = 0;
    private int perfect_count = 0;
    private int miss_count=0;

    private Bar bar;

    private List<Segment> segments;

    private boolean game_lock = false;
    private boolean cool_lock = false;
    private boolean perfect_lock = false;
    private boolean miss_lock=false;

    private int totalScore;
    private int perfect_cool_score=0;

    //DB
    private DayRecord dayRecord;
    private BarScore barScore;
    private List<BarScore> record;


    private ExerciseScoreCounter(Context context) {
        segments = new ArrayList<Segment>();
        dayRecord=new DayRecord(PreferenceUtils.getInt(context, KeyConst.SKEA_EXERCISE_LEVEL_KEY, 3)+1);//本地持久化时，level 为4 时，实际存储为3

//        record=new ArrayList<BarScore>();
    }

    public static ExerciseScoreCounter getInstance(Context context) {
        if (instance == null){
            instance = new ExerciseScoreCounter(context);
        }

        return instance;
    }


    public void startMissScore(){
        miss_lock=true;
    }

    public void startCoolScore(Bar bar) {
        this.bar = bar;
        cool_lock = true;
        barScore=new BarScore(bar.getType());

    }

    public void startPerfectScore(Bar bar) {
        this.bar = bar;
        perfect_lock = true;
    }

    public void startGameScore(Bar bar) {
        this.bar = bar;
        game_lock = true;
    }


    public boolean tickMissScore(){
        if (miss_lock && miss_count>0){
            barScore.setPerfectCool(0);
            miss_count=0;
            return true;
        }else {
            miss_count = 0;
            return false;
        }
    }

    public void tickCoolScore() {
        if (cool_lock) {
//            Log.i("CXC", "----Cool ++++");

        }
    }

    public void tickPerfectScore() {
        if (perfect_lock) {
//            Log.i("CXC", "----perfect ++++");
        }
    }

    public boolean tickGameScore() {
        if (game_lock) {
            //TODO 可能出现锁问题
            Segment segment = new Segment(game_count);
            //DB
            SegmentScore segmentScore=new SegmentScore(game_count);
            segmentScore.save();
//            db_bar.add(segmentScore);
            segments.add(segment);
            barScore.getBar().add(segmentScore);
            game_count=0;
            return segment.isAvailable();
        }
        else {
            game_count = 0;
            return false;
        }
    }

    /**
     * 计算当前游戏得分
     */
    public int stopGameScore() {

        if (game_lock) {
            float score = getGameScore();
            totalScore+=score;
            bar.setScore(score+perfect_cool_score);
            barScore.setScore(score);

//            dayRecord.getRecord().add(barScore);

            BarScore temp_bar=new BarScore(barScore.getType());
            temp_bar.setScore(barScore.getScore());
            temp_bar.setType(barScore.getType());
            temp_bar.setPerfectCool(barScore.getPerfectCool());
            temp_bar.setBar(barScore.getBar());

            temp_bar.save();

//            record.add(temp_bar);
            dayRecord.getRecord().add(temp_bar);
        }
        //归“0”
        perfect_lock=false;
        cool_lock=false;
        game_lock = false;

        game_count = 0;
        perfect_cool_score=0;
        return totalScore;
    }

    public int stopCoolScore() {


        if (cool_count > 0) {
            perfect_cool_score=BarConst.SCORE.COOL_SCORE;
            totalScore += perfect_cool_score;
            Log.i("CXC", "Cool ++++30");
            barScore.setPerfectCool(1);//miss 0,cool 1,perfect 2;

        }
        cool_lock = false;

        return totalScore;

    }

    public int stopPerfectScore() {
        if (cool_count<=0 && perfect_count > 0) {
            perfect_cool_score=BarConst.SCORE.PERFECT_SCORE;
            totalScore += perfect_cool_score;
//            Log.i("CXC", "perfect +++50");
            barScore.setPerfectCool(2);
        }
        perfect_count = 0;
        cool_count = 0;
        perfect_lock = false;

        return totalScore;
    }

    public void stopMissScore(){
        miss_lock=false;
        miss_count=0;
        segments.clear();
    }

    public void stopScoreCounter(){
        //this.totalScore=0;
//        dayRecord.setRecord(record);
        dayRecord.save();
        instance=null;
    }
    public void receiveSignal() {

        if (cool_lock) {
            cool_count++;
        }

        if (perfect_lock) {
            perfect_count++;
        }

        if (game_lock) {
            game_count++;
        }
        if(miss_lock){
            miss_count++;
        }
    }

    private float getGameScore() {
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

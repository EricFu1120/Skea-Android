package me.linkcube.skea.core.excercise;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import custom.android.util.PreferenceUtils;
import me.linkcube.skea.core.KeyConst;
import me.linkcube.skea.db.DayRecord;

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

    private int totalScore=0;
    private int perfect_cool_score=0;
    private int game_duration=0;

    //DB
    private DayRecord dayRecord;
    private JSONObject jsonBarInfos;
    private JSONArray jsonBarArray;
//    private BarScore barScore;
//    private List<BarScore> record;


    private ExerciseScoreCounter(Context context) {
        segments = new ArrayList<Segment>();
        int level=PreferenceUtils.getInt(context, KeyConst.SKEA_EXERCISE_LEVEL_KEY, 3);
        dayRecord=new DayRecord(level+1);//本地持久化时，level 为4 时，实际存储为3
//        dayRecord.setmLevel(level+1);

        //init
        jsonBarInfos=new JSONObject();
        jsonBarArray =new JSONArray();

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
//        barScore=new BarScore(bar.getType());

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
            segments.add(segment);

            game_count=0;
            return segment.isAvailable();
        }
        else {
            game_count = 0;
            return false;
        }
    }

    public void tickSecond(){
        game_duration++;
    }

    /**
     * 计算当前游戏得分
     */
    public int stopGameScore() {

        if (game_lock) {
            float score = getGameScore();
            Log.i("CXC","##################"+score+"@@@@@@@"+totalScore);
            totalScore+=score;
            bar.setScore(score+perfect_cool_score);

            try{
                JSONObject tempObj=new JSONObject();
                tempObj.put(BarConst.JSONConst.KEY_TYPE,bar.getType());
                tempObj.put(BarConst.JSONConst.KEY_SCORE,bar.getScore());
                jsonBarArray.put(tempObj);


            }catch (JSONException e){
                Log.i("CXC","org.json.JSONException -----");
            }


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
//            barScore.setPerfectCool(1);//miss 0,cool 1,perfect 2;
        }
        cool_lock = false;

        return totalScore;

    }

    public int stopPerfectScore() {
        if (cool_count<=0 && perfect_count > 0) {
            perfect_cool_score=BarConst.SCORE.PERFECT_SCORE;
            totalScore += perfect_cool_score;
//            Log.i("CXC", "perfect +++50");

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
        try{
            jsonBarInfos.put(BarConst.JSONConst.KEY_INFO,jsonBarArray);
        }catch (JSONException e){
            Log.i("CXC","org.json.JSONException ++++");
        }
        dayRecord.setmBarsJSONInfo(jsonBarInfos.toString());
        //Duration
        dayRecord.setmDuration(game_duration);
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
//        Log.d("getScore", "segments size = " + segments.size());
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

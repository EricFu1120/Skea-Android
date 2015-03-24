package me.linkcube.skea.db;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import me.linkcube.skea.core.excercise.BarConst;

/**
 * Created by CXC on 15-2-11.
 */
public class BarScore extends SugarRecord<BarScore> {
    private int type;
    private int perfectCool;
    private float score;
//    private SegmentScore [] bar;//用Array比List要方便一些，不用再一个一个的add 了。
    private List<SegmentScore> bar;
    //Note: Please retain the default constructor.
    public BarScore(){

    }
    public BarScore(int type){
        this.type=type;
        int count=0;
        switch (type){
            case BarConst.TYPE.SHORT:
                count=4;//   2/0.5
                break;
            case BarConst.TYPE.MEDIUM:
                count=14;//  7/0.5
                break;
            case BarConst.TYPE.LONG:
                count=24;// 12/0.5
                break;
            default:
                break;
        }
//        bar=new SegmentScore[count];
        bar=new ArrayList<SegmentScore>(count);

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPerfectCool() {
        return perfectCool;
    }

    public void setPerfectCool(int perfectCool) {
        this.perfectCool = perfectCool;
    }

    public List<SegmentScore> getBar() {
        return bar;
    }

    public void setBar(List<SegmentScore> bar) {
        this.bar = bar;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}

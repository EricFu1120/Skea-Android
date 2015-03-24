package me.linkcube.skea.db;

import android.util.Log;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.linkcube.skea.core.excercise.BarConst;

/**
 * Created by CXC on 15-2-11.
 */
public class DayRecord extends SugarRecord<DayRecord>{
    //当前日期“yyyy-MM-dd”
    private String today;
    //本次锻炼Level
    private int mLevel;
    //time
    private int mDuration;
    //Bars info ---Json format
    private String mBarsJSONInfo;
    //Note: Please retain the default constructor.
    public DayRecord(){

    }
    public DayRecord(int level){
        this.mLevel=level;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        this.today =sdf.format(new Date()).toString();//记录当前日期－－得到特定日期格式的日期格式yyyy-MM-dd
//        int count=0;
//
//        switch (level){
//            case BarConst.LEVEL.LEVEL_ONE:
//                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_ONE];
//                break;
//            case BarConst.LEVEL.LEVEL_TWO:
//                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_TWO];
//                break;
//            case BarConst.LEVEL.LEVEL_THREE:
//                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_THREE];
//                break;
//            case BarConst.LEVEL.LEVEL_FOUR:
//                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_FOUR];
//                break;
//            case BarConst.LEVEL.LEVEL_FIVE:
//                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_FIVE];
//                break;
//            default://level 4
//                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_FOUR];
//                break;
//        }

    }

    public int getmLevel() {
        return mLevel;
    }

    public void setmLevel(int mLevel) {
        this.mLevel = mLevel;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getmBarsJSONInfo() {
        return mBarsJSONInfo;
    }

    public void setmBarsJSONInfo(String mBarsJSONInfo) {
        this.mBarsJSONInfo = mBarsJSONInfo;
    }
}

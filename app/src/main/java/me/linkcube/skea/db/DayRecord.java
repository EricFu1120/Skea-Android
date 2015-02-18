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
    private String today;
    private int level;
//    private BarScore[] record;
    private List<BarScore> record;
    //Note: Please retain the default constructor.
    public DayRecord(){

    }
    public DayRecord(int level){
        this.level=level;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        this.today =sdf.format(new Date()).toString();//记录当前日期－－得到特定日期格式的日期格式yyyy-MM-dd


        int count=0;

        switch (level){
            case BarConst.LEVEL.LEVEL_ONE:
                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_ONE];
                break;
            case BarConst.LEVEL.LEVEL_TWO:
                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_TWO];
                break;
            case BarConst.LEVEL.LEVEL_THREE:
                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_THREE];
                break;
            case BarConst.LEVEL.LEVEL_FOUR:
                count=BarConst.LEVEL.BAR_UNIT_NUM[BarConst.LEVEL.LEVEL_FOUR];
                break;
            default:
                break;
        }
//        record=new BarScore[count*BarConst.TYPE.BAR_TIME.length/2];//每组3种Bar
        record=new ArrayList<BarScore>(count * BarConst.TYPE.BAR_TIME.length/2);
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<BarScore> getRecord() {
        return record;
    }

    public void setRecord(List<BarScore> record) {
        this.record = record;
    }
}

package me.linkcube.skea.db;

/**
 * Created by CXC on 15-2-12.
 */
public class DBOperation {
    public static void saveSegment(SegmentScore segmentScore){
        segmentScore.save();
    }
    public static void saveBar(BarScore barScore){
        barScore.save();
    }
    public static void saveRecord(DayRecord dayRecord){
        dayRecord.save();
    }
}

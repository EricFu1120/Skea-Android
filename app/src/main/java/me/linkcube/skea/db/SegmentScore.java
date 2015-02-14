package me.linkcube.skea.db;

import com.orm.SugarRecord;

/**
 * Created by CXC on 15-2-12.
 */
public class SegmentScore extends SugarRecord<SegmentScore> {
    /**
     * Segment分段时间，这里统一为0.5S
     * */
    private float time=0.5f;//0.5S
    /**
     * 为这0.5S内，蓝牙接收到的挤压次数。－－最原始的数据，以便将来更改各个“指标”计算方法时，进行重新计算
     * */
    private int count;
    //Note: Please retain the default constructor.
    public SegmentScore(){

    }
    public SegmentScore(int count){
        this.count=count;
        this.time=0.5f;//0.5S
    }
}

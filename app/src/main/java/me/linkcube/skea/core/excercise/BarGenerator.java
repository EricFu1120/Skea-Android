package me.linkcube.skea.core.excercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarGenerator {

    private static BarGenerator instance;
    private int[] barArray;
    private int barUnitNum;
    private int barNum;
    private int level = 1;
    private List<Bar> bars;

    private BarGenerator() {
        //TODO 获取level值
        initBarNum(level);
        initBarArray();
        bars = new ArrayList<Bar>();
        create();
    }

    public static BarGenerator getInstance() {
        if (instance == null)
            instance = new BarGenerator();
        return instance;
    }

    private void create() {
        for (int i = 0; i < barNum; i++) {
            Bar temp=getRandomBarType(i);
            bars.add(temp);
            //不是最后一个Bar时要添加间隔，此处注意间隔不是固定长度的
            if (i != barNum - 1){
                switch (temp.getType()){
                    case BarConst.TYPE.LONG:
                        bars.add(new Bar(BarConst.TYPE.SLOT_LONG));
                        break;
                    case BarConst.TYPE.MEDIUM:
                        bars.add(new Bar(BarConst.TYPE.SLOT_MEDIUM));
                        break;
                    case BarConst.TYPE.SHORT:
                        bars.add(new Bar(BarConst.TYPE.SLOT_SHORT));
                        break;
                    default:
                        break;
                }
            }

        }
    }

    public List<Bar> getBars() {
        return bars;
    }

    private void initBarArray() {
        barNum = barUnitNum * 3;
        barArray = new int[barNum];
        for (int i = 0; i < barUnitNum; i++) {
            barArray[i] = BarConst.TYPE.LONG;
        }
        for (int i = barUnitNum; i < 2 * barUnitNum; i++) {
            barArray[i] = BarConst.TYPE.MEDIUM;
        }
        for (int i = 2 * barUnitNum; i < 3 * barUnitNum; i++) {
            barArray[i] = BarConst.TYPE.SHORT;
        }
    }

    private void initBarNum(int level) {
        switch (level) {
            case 1:
                barUnitNum = 15;
                break;
            case 2:
                barUnitNum = 20;
                break;
            case 3:
                barUnitNum = 30;
                break;
            case 4:
                barUnitNum = 40;
            default:
                break;
        }
    }

    private Bar getRandomBarType(int boundary) {
        int index = (int) (Math.random() * (barNum - boundary)) + boundary;
        int type = barArray[index];
        Bar bar = new Bar(type);
        if (boundary != index)
            barArray[index] = barArray[boundary];
        return bar;
    }

}

package com.example.demogame.view;

import com.example.demogame.core.Bar;
import com.example.demogame.core.BarConst;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarGenerator {

    private int[] barArray;

    private int barUnitNum;

    private int barNum;

    private int level = 1;

    private List<Bar> bars;

    private static BarGenerator instance;

    public static BarGenerator getInstance() {
        if (instance == null)
            instance = new BarGenerator();
        return instance;
    }

    private BarGenerator() {
        //TODO 获取level值
        initBarNum(level);
        initBarArray();
        bars = new ArrayList<Bar>();
        create();
    }

    private void create() {
        for (int i = 0; i < barNum; i++) {
            bars.add(getRandomBarType(i));
            if (i != barNum - 1)
                bars.add(new Bar(BarConst.TYPE.SLOT));
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

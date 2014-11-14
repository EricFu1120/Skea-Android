package me.linkcube.skea.core.excercise;

import android.content.Context;

import custom.android.util.DensityUtils;

/**
 * Created by Ervin on 14/11/4.
 */
public class Bar {

    private int type;

    private float score;

    private int height;

    private int beginActiveOffset;

    private int endActiveOffset;

    public Bar(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getHeight(Context context) {
        return BarConst.LEVEL.TIME[type] * BarConst.VIEW.SPEED;
    }

    public int getBeginActiveOffset() {
        return beginActiveOffset;
    }

    public void setBeginActiveOffset(int beginActiveOffset) {
        this.beginActiveOffset = beginActiveOffset;
    }

    public int getEndActiveOffset() {
        return endActiveOffset;
    }

    public void setEndActiveOffset(int endActiveOffset) {
        this.endActiveOffset = endActiveOffset;
    }
}

package me.linkcube.skea.core.excercise;

import android.content.Context;

/**
 * Created by Ervin on 14/11/4.
 */
public class Bar {

    private int type;

    private float score;

    private int height;

    private int beginActiveOffset;
    /**真正的开始触发点*/
    private int realBeginActiveOffset;

    private int endActiveOffset;
    /**真正的结束触发点*/
    private int realEndActiveOffset;

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
        this.realBeginActiveOffset=this.beginActiveOffset+BarConst.ACTIVE_BEGIN_OFFSET_MARGIN;
    }

    public int getEndActiveOffset() {
        return endActiveOffset;
    }

    public void setEndActiveOffset(int endActiveOffset) {
        this.endActiveOffset = endActiveOffset;
        this.realEndActiveOffset=this.endActiveOffset-BarConst.ACTIVE_END_OFFSET_MARGIN;
    }

    public int getRealBeginActiveOffset() {
        return realBeginActiveOffset;
    }

//    public void setRealBeginActiveOffset(int realBeginActiveOffset) {
//        this.realBeginActiveOffset = realBeginActiveOffset;
//    }

    public int getRealEndActiveOffset() {
        return realEndActiveOffset;
    }

//    public void setRealEndActiveOffset(int realEndActiveOffset) {
//        this.realEndActiveOffset = realEndActiveOffset;
//    }
}

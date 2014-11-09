package com.example.demogame.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.demogame.DensityUtils;
import com.example.demogame.R;
import com.example.demogame.core.Bar;
import com.example.demogame.core.BarConst;


import static com.example.demogame.core.BarConst.TYPE.*;
import static com.example.demogame.core.BarConst.LEVEL.*;

import java.util.List;

/**
 * Created by Ervin on 14/11/5.
 */
public class BarGroupManager {

    private List<Bar> list;

    private int frontBlankHeight;

    private int behindBlankHeight;

    private ScrollView frontScrollView;

    private ScrollView behindScrollView;

    private static BarGroupManager instance;

    public static BarGroupManager getInstance() {
        if (instance == null)
            instance = new BarGroupManager();
        return instance;
    }

    private BarGroupManager() {
        //TODO 获取level
    }

    public int getBarGroupHeight(Context context, boolean front) {
        //TODO 获取level
//        switch (level)
//        {
//
//        }
        int level = 1;

        int exercise = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_HEIGHT * (5 + 7 + 12) * 15);
        int blank = DensityUtils.dip2px(context, getBlankHeight(front) * 2);
        int slot = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_HEIGHT * 2) * 44;
        return exercise + blank + slot;

    }

    /**
     * 滑动到指定位置开始游戏
     *
     * @param frontScrollView
     * @param behindScrollView
     */
    public void prepare(Context context, ScrollView frontScrollView, ScrollView behindScrollView) {
        this.frontScrollView = frontScrollView;
        this.behindScrollView = behindScrollView;
        frontScrollView.scrollBy(0, getBarGroupHeight(context, true));
        behindScrollView.scrollBy(0, getBarGroupHeight(context, false));
    }

    public void scrollBy(int y) {
        frontScrollView.scrollBy(0, y);
        behindScrollView.scrollBy(0, y);
    }

    /**
     * 这个方法只执行一次
     *
     * @param context
     * @param frontGroup
     * @param behindGroup
     */
    public void initBarGroup(Context context, LinearLayout frontGroup, LinearLayout behindGroup) {
        ViewGroup frontParent = (ViewGroup) frontGroup.getParent();
        ViewGroup behindParent = (ViewGroup) behindGroup.getParent();

        list = BarGenerator.getInstance().getBars();
        frontGroup.addView(getHeaderOrFooterView(frontParent, true));
        behindGroup.addView(getHeaderOrFooterView(behindParent, false));
        for (int i = 0; i < list.size() - 1; i++) {
            frontGroup.addView(new BarViewWrapper().getImageView(context, list.get(i).getType(), false));
            behindGroup.addView(new BarViewWrapper().getImageView(context, list.get(i).getType(), true));
        }
        frontGroup.addView(getHeaderOrFooterView(frontParent, true));
        behindGroup.addView(getHeaderOrFooterView(behindParent, false));
        for (int i = list.size() - 1; i >= 0; i--) {
            Bar bar = list.get(i);
            if (i == list.size() - 1) {
                bar.setBeginActiveOffset(DensityUtils.dip2px(context, getBlankHeight(true)));
                bar.setEndActiviteOffset(bar.getBeginActiveOffset() + bar.getHeight(context));
            } else {
                bar.setBeginActiveOffset(list.get(i + 1).getEndActiviteOffset());
                bar.setEndActiviteOffset(bar.getBeginActiveOffset() + bar.getHeight(context));
            }
        }

    }

    private int getBlankHeight(boolean front) {
        if (front)
            return frontBlankHeight;
        else
            return behindBlankHeight;
    }

    private View getHeaderOrFooterView(ViewGroup parent, boolean front) {
        View view = new View(parent.getContext());
        int width = DensityUtils.dip2px(parent.getContext(), 60);
        int height = parent.getHeight();
        if (front) {
            frontBlankHeight = height;
            Log.d("getHeaderOrFooterView", "front height=" + height);
        } else {
            behindBlankHeight = height;
            Log.d("getHeaderOrFooterView", "behind height=" + height);
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(lp);
        view.setBackgroundColor(parent.getResources().getColor(R.color.transparent));
        return view;
    }

}

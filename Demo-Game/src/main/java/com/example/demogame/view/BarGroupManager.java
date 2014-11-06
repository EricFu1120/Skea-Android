package com.example.demogame.view;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.demogame.DensityUtils;
import com.example.demogame.R;
import com.example.demogame.core.Bar;
import com.example.demogame.core.BarConst;

import java.util.List;

/**
 * Created by Ervin on 14/11/5.
 */
public class BarGroupManager {

    private List<Bar> list;

    private static BarGroupManager instance;

    private int progress;

    public static BarGroupManager getInstance() {
        if (instance == null)
            instance = new BarGroupManager();
        return instance;
    }

    private BarGroupManager() {

    }

    public void test() {

    }

    private ViewGroup parent;

    public int getBarGroupHeight(Context context) {
        //TODO 获取level
//        switch (level)
//        {
//
//        }
        int exercise = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_HEIGHT * (5 + 7 + 12) * 15);
        int blank = DensityUtils.dip2px(context, parent.getHeight()) * 2;
        int slot = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_HEIGHT * 2) * 44;
        return exercise + blank + slot;

    }

    public void getBarGroup(LinearLayout group, ViewGroup parent, boolean glow) {
        this.parent = parent;
        list = BarGenerator.getInstance().getBars();
        group.addView(getHeaderOrFooterView(parent));
        for (int i = 0; i < list.size() - 1; i++) {
            group.addView(new BarViewWrapper().getImageView(group.getContext(), list.get(i).getType(), glow));
        }
        group.addView(getHeaderOrFooterView(parent));
    }

    private View getHeaderOrFooterView(ViewGroup parent) {
        View view = new View(parent.getContext());
        int width = DensityUtils.dip2px(parent.getContext(), 60);
        int height = parent.getHeight();
        Log.d("getHeaderOrFooterView", " height=" + height);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        view.setLayoutParams(lp);
        view.setBackgroundColor(parent.getResources().getColor(R.color.transparent));
        return view;
    }


}

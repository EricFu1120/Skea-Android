package com.example.demogame.view;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.demogame.DensityUtils;
import com.example.demogame.R;
import com.example.demogame.core.Bar;
import com.example.demogame.core.BarConst;

/**
 * Created by Ervin on 14/11/4.
 */
public class BarViewWrapper {

    private Context mContext;

    private int height;

    private int width;

    private boolean glow;

    private int resId;

    public BarViewWrapper() {

    }

    public ImageView getImageView(Context context, int type, boolean glow) {
        this.mContext = context;
        this.width = DensityUtils.dip2px(context, BarConst.BAR_WIDTH);
        initVarViewAttrs(type, glow);
        ImageView imageView = new ImageView(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, height);
        imageView.setLayoutParams(lp);
        if (resId != -1)
            imageView.setImageResource(resId);
        else
            imageView.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }


    private void initVarViewAttrs(int type, boolean glow) {
        switch (type) {
            case BarConst.BAR_LONG:
                height = DensityUtils.dip2px(mContext, 12 * BarConst.BAR_UNIT_HEIGHT);
                resId = glow ? R.drawable.bar_long_glow : R.drawable.bar_long;
                break;
            case BarConst.BAR_MEDIUM:
                height = DensityUtils.dip2px(mContext, 7 * BarConst.BAR_UNIT_HEIGHT);
                resId = glow ? R.drawable.bar_medium_glow : R.drawable.bar_medium;
                break;
            case BarConst.BAR_SHORT:
                height = DensityUtils.dip2px(mContext, 5 * BarConst.BAR_UNIT_HEIGHT);
                resId = glow ? R.drawable.bar_short_glow : R.drawable.bar_short;
                break;
            case BarConst.BAR_SLOT:
                height = DensityUtils.dip2px(mContext, 2 * BarConst.BAR_UNIT_HEIGHT);
                resId = -1;
                break;
            default:
                break;
        }
    }

}

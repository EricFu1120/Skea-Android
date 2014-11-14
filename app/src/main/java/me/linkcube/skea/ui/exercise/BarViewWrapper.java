package me.linkcube.skea.ui.exercise;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;

import custom.android.util.DensityUtils;
import me.linkcube.skea.R;
import me.linkcube.skea.core.excercise.BarConst;

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
        this.width = DensityUtils.dip2px(context, BarConst.VIEW.UNIT_WIDTH);
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
            case BarConst.TYPE.LONG:
                height = 12 * BarConst.VIEW.SPEED;
                resId = glow ? R.drawable.bar_long_glow : R.drawable.bar_long;
                break;
            case BarConst.TYPE.MEDIUM:
                height = 7 * BarConst.VIEW.SPEED;
                resId = glow ? R.drawable.bar_medium_glow : R.drawable.bar_medium;
                break;
            case BarConst.TYPE.SHORT:
                height = 5 * BarConst.VIEW.SPEED;
                resId = glow ? R.drawable.bar_short_glow : R.drawable.bar_short;
                break;
            case BarConst.TYPE.SLOT:
                height = 2 * BarConst.VIEW.SPEED;
                resId = -1;
                break;
            default:
                break;
        }
    }

    public int getHeight() {
        return height;
    }

}

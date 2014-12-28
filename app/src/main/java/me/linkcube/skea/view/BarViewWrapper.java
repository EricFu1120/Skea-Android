package me.linkcube.skea.view;

import android.content.Context;
import android.util.Log;
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
        Log.i("CXC", "type:" + type);
        switch (type) {
            case BarConst.TYPE.LONG:
                height = BarConst.LEVEL.TIME[BarConst.TYPE.LONG] * BarConst.VIEW.SPEED;
                resId = glow ? R.drawable.bar_long_glow : R.drawable.bar_long;
                break;
            case BarConst.TYPE.MEDIUM:
                height = BarConst.LEVEL.TIME[BarConst.TYPE.MEDIUM] * BarConst.VIEW.SPEED;
                resId = glow ? R.drawable.bar_medium_glow : R.drawable.bar_medium;
                break;
            case BarConst.TYPE.SHORT:
                height = BarConst.LEVEL.TIME[BarConst.TYPE.SHORT] * BarConst.VIEW.SPEED;
                resId = glow ? R.drawable.bar_short_glow : R.drawable.bar_short;
                break;
            case BarConst.TYPE.SLOT_SHORT:
                height = BarConst.LEVEL.TIME[BarConst.TYPE.SLOT_SHORT] * BarConst.VIEW.SPEED;
                resId = -1;
                break;
            case BarConst.TYPE.SLOT_MEDIUM:
                height = BarConst.LEVEL.TIME[BarConst.TYPE.SLOT_MEDIUM] * BarConst.VIEW.SPEED;
                resId = -1;
                break;
            case BarConst.TYPE.SLOT_LONG:
                height = BarConst.LEVEL.TIME[BarConst.TYPE.SLOT_LONG] * BarConst.VIEW.SPEED;
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

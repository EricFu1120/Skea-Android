package com.example.demogame.view;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.demogame.DensityUtils;
import com.example.demogame.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.view.ViewHelper;

/**
 * Created by Ervin on 14/11/3.
 * 用来同步前后两个BarView动画
 */
public class BarViewGroup {

    public final static int BAR_VIEW_LONG = 0;

    public final static int BAR_VIEW_MEDIUM = 1;

    public final static int BAR_VIEW_SHORT = 2;

    private Context mContext;

    private ViewGroup mFrontParent;

    private ViewGroup mBehindParent;

    private BarViewFrontAnimator mFrontAnimator;

    private BarViewBehindAnimator mBehindAnimator;

    private ImageView mFrontView;

    private ImageView mBehindView;

    private int mType;

    private boolean shown = false;

    public BarViewGroup(Context context, int type, ViewGroup frontParent, ViewGroup behindParent) {
        this.mFrontParent = frontParent;
        this.mBehindParent = behindParent;
        this.mContext = context;
        this.mType = type;
        initViews();
    }

    public void initViews() {
        mFrontView = getImageView(mFrontParent, false);
        mBehindView = getImageView(mBehindParent, true);
        mFrontAnimator = new BarViewFrontAnimator();
        mBehindAnimator = new BarViewBehindAnimator();
    }

    private ImageView getImageView(ViewGroup parent, boolean glow) {
        ImageView imageView = new ImageView(mContext);
        int unitHeight = 40, height = 70;
        int width = DensityUtils.dip2px(mContext, 200);
        switch (mType) {
            case BAR_VIEW_LONG:
                height = DensityUtils.dip2px(mContext, 12 * unitHeight);
                if (glow)
                    imageView.setImageResource(R.drawable.bar_long_glow);
                else
                    imageView.setImageResource(R.drawable.bar_long);
                break;
            case BAR_VIEW_MEDIUM:
                height = DensityUtils.dip2px(mContext, 7 * unitHeight);
                if (glow)
                    imageView.setImageResource(R.drawable.bar_medium_glow);
                else
                    imageView.setImageResource(R.drawable.bar_medium);
                break;
            case BAR_VIEW_SHORT:
                height = DensityUtils.dip2px(mContext, 2 * unitHeight);
                if (glow)
                    imageView.setImageResource(R.drawable.bar_short_glow);
                else
                    imageView.setImageResource(R.drawable.bar_short);
                break;
            default:
                break;
        }

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        parent.addView(imageView, lp);
        imageView.setVisibility(View.INVISIBLE);
        return imageView;
    }

    public void animate() {
        mFrontAnimator.addAnimatorListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.d("onAnimationStart", "bar view front");
                mFrontView.setVisibility(View.VISIBLE);
                ViewHelper.setAlpha(mFrontView, 0f);
                mBehindView.setVisibility(View.VISIBLE);
                ViewHelper.setAlpha(mBehindView, 0f);
                shown = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                shown = false;

            }
        });
        int duration = getDuration();
        mFrontAnimator.setDuration(duration).animate(mFrontView);
        mBehindAnimator.setDuration(duration).animate(mBehindView);
    }

    private int getDuration() {
        return mBehindParent.getHeight() / mBehindView.getHeight() * 7 * 1000;
    }

    public void pause() {

    }

    public void cancel() {

    }

    public void resume() {

    }

    public boolean isActive() {
        return true;
    }

    public boolean isShown() {
        return shown;
    }

    public void invalidate()
    {
        mFrontParent.invalidate();
        mBehindParent.invalidate();
    }



}

package com.example.demogame.view;

import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Ervin on 14/11/2.
 */
public class BarViewGlowAnimator extends BaseViewAnimator {

    private View target;

    @Override
    protected void prepare(View target) {
        this.target = target;
        int distance = getMainParent().getHeight();
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0, 1), ObjectAnimator.ofFloat(target, "translationY", -target.getHeight(), distance));

    }

    @Override
    protected ViewGroup getFrontParent() {
        return (ViewGroup) getMainParent().getChildAt(0);
    }

    @Override
    protected ViewGroup getBehindParent() {
        return (ViewGroup) getMainParent().getChildAt(1);
    }

    @Override
    protected ViewGroup getMainParent() {
        return (ViewGroup) target.getParent().getParent();
    }
}

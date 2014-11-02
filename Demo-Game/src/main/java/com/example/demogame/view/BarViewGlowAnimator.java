package com.example.demogame.view;

import android.view.View;
import android.view.ViewGroup;

import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by Ervin on 14/11/2.
 */
public class BarViewGlowAnimator extends BaseViewAnimator {

    @Override
    protected void prepare(View target) {
        ViewGroup parent = (ViewGroup) target.getParent().getParent();
        int distance = parent.getHeight();
        getAnimatorAgent().playTogether(
                ObjectAnimator.ofFloat(target, "alpha", 0, 1), ObjectAnimator.ofFloat(target, "translationY", -target.getHeight() + 100, distance - 100));

    }
}

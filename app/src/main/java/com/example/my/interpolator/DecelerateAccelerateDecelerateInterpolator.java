package com.example.my.interpolator;

import android.view.animation.BaseInterpolator;

/**
 * Created by  wsl
 * on 2019/10/22 15:44
 */
public class DecelerateAccelerateDecelerateInterpolator extends BaseInterpolator {
    @Override
    public float getInterpolation(float input) {
        float result = 0;
        if (input <= 0.5) {
            result = (float) (Math.sin(input * Math.PI) / 2);
        } else {
            result = (float) ((2 - Math.sin(input * Math.PI)) / 2);
        }
        return result;
    }
}

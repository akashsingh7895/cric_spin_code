package com.avssolution.fancylivecricketscore.CricketUtility;

import android.view.animation.Interpolator;

public class BounceAnimation implements Interpolator {
    private double mAmplitude;
    private double mFrequency;

    public BounceAnimation(double d, double d2) {
        this.mAmplitude = d;
        this.mFrequency = d2;
    }

    public float getInterpolation(float f) {
        double d = (double) (-f);
        double d2 = this.mAmplitude;
        Double.isNaN(d);
        double d3 = this.mFrequency;
        double d4 = (double) f;
        Double.isNaN(d4);
        return (float) ((Math.pow(2.718281828459045d, d / d2) * -1.0d * Math.cos(d3 * d4)) + 1.0d);
    }
}

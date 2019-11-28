package com.shg.keyebang.builder;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;

public class DisplayAdapter {

    private static float nonCompatDensity;
    private static float nonCompatScaleDensity;

    public static void setCustomDensity(@NonNull Activity activity, @NonNull Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

        if(nonCompatDensity == 0){
            nonCompatDensity = appDisplayMetrics.density;
            nonCompatScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration configuration) {
                    if(configuration != null && configuration.fontScale > 0){
                        nonCompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }
                @Override
                public void onLowMemory() {
                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels/360;
        final float targetScaledDensity = targetDensity * (nonCompatScaleDensity / nonCompatDensity);
        final int targetDensityDpi = (int)(160 * targetDensity);

        appDisplayMetrics.density =  targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }
}

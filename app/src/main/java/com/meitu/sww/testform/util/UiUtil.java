package com.meitu.sww.testform.util;

import android.content.Context;

/**
 * @author ShaoWenWen
 * @date 2019/5/31
 */
public class UiUtil {

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

}

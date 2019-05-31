package com.meitu.sww.testform.util;

import android.content.res.Resources;

/**
 * @author ShaoWenWen
 * @date 2019/5/30
 */
public class DeviceUtil {

    public static int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    public static int dip2px(float dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int getScreenWidth() {
        return screenWidth;
    }

}

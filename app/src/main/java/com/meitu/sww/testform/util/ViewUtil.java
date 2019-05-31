package com.meitu.sww.testform.util;

import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author ShaoWenWen
 * @date 2019/5/30
 */
public class ViewUtil {

    public static void addCorner(View view, int radius, @ColorInt int backgroundColor, int strokeWidth, int strokeColor) {
        addTopAndBottomCorner(view, radius, radius, backgroundColor, strokeWidth, strokeColor);
    }

    public static void addTopAndBottomCorner(View view, int topRadius, int bottomRadius, @ColorInt int backgroundColor, int strokeWidth, int strokeColor) {

        float top = DeviceUtil.dip2px(topRadius);
        float bottom = DeviceUtil.dip2px(bottomRadius);

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadii(new float[]{top, top, top, top, bottom, bottom, bottom, bottom});
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setSize(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        shape.setColor(backgroundColor);
        if (strokeWidth > 0) {
            shape.setStroke(strokeWidth, strokeColor);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            view.setClipToOutline(true);
        }
        view.setBackground(shape);
    }

}

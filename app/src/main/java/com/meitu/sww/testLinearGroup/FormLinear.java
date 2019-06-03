package com.meitu.sww.testLinearGroup;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.meitu.sww.testform.R;

/**
 * @author ShaoWenWen
 * @date 2019/6/3
 */
public class FormLinear extends FrameLayout {

    private LinearLayout linearGroup;

    public FormLinear(Context context) {
        this(context, null);
    }

    public FormLinear(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        inflate(context, R.layout.layout_linear_group, this);
        linearGroup = findViewById(R.id.linear_group);
    }

    public void updateFormStyle(int top, int bottom, int bg) {
        linearGroup.setPadding(0, top, 0, bottom);
        // TODO: 暂时使用yellow作为背景色 by ShaoWenWen 2019/6/3
        linearGroup.setBackgroundColor(bg);
    }

    public void updateChildView() {

    }

}

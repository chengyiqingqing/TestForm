package com.meitu.sww.testEditText;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.meitu.sww.testform.R;
import com.meitu.sww.testform.util.DeviceUtil;
import com.meitu.sww.testform.util.ViewUtil;

/**
 * @author ShaoWenWen
 * @date 2019/6/3
 */
public class TestEditText extends AppCompatEditText {

    public TestEditText(Context context) {
        this(context, null);
    }

    public TestEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        setHintTextColor(getResources().getColor(R.color.color_cccccc));
        setTextColor(getResources().getColor(R.color.color_333333));
        // 设置左右的padding，不让文本贴边儿
        setPadding(DeviceUtil.dip2px(10), 0, DeviceUtil.dip2px(10), 0);
        setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
//        setBackgroundResource(R.drawable.edit_text_border_style);
        ViewUtil.addCorner(this, DeviceUtil.dip2px(4), getResources().getColor(R.color.colorWhite), 0
                , 0);
    }

    public void updateView(String hint, int inputType) {
        setHint(hint);
        setInputType(inputType);
    }

}

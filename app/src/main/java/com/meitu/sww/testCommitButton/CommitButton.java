package com.meitu.sww.testCommitButton;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
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
public class CommitButton extends AppCompatTextView {

    public CommitButton(Context context) {
        this(context, null);
    }

    public CommitButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateView();
    }

    private void updateView() {
        setText("立即提交");
        setTextColor(Color.WHITE);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        setGravity(Gravity.CENTER);
        setClickable(true);
        setFocusable(true);
        setPadding(0, 0, 0, 0);
        ViewUtil.addCorner(this, DeviceUtil.dip2px(4), getResources().getColor(R.color.colorBlack), 0
                , 0);
    }

}

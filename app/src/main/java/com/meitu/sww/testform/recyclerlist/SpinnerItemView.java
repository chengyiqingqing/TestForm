package com.meitu.sww.testform.recyclerlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.meitu.sww.testform.R;
import com.meitu.sww.testform.util.DeviceUtil;

/**
 * @author ShaoWenWen
 * @date 2019/5/29
 */
public class SpinnerItemView extends FrameLayout {

    private View rootView;
    private TextView textItem;

    public SpinnerItemView(@NonNull Context context) {
        this(context, null);
    }

    public SpinnerItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        rootView = inflate(getContext(), R.layout.layout_item_text, this);
        textItem = rootView.findViewById(R.id.text_use);
    }

    public void updateViewByData(String text) {
        textItem.setText(text);
//        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
//                DeviceUtil.dip2px(30));
//        layoutParams.gravity = Gravity.CENTER;
        setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                DeviceUtil.dip2px(30)));

    }

}

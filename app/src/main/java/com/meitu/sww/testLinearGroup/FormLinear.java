package com.meitu.sww.testLinearGroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meitu.sww.testCommitButton.CommitButton;
import com.meitu.sww.testEditText.TestEditText;
import com.meitu.sww.testRunText.MarqueeView;
import com.meitu.sww.testform.R;
import com.meitu.sww.testform.spinner.SpinnerViewGroup;
import com.meitu.sww.testform.util.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShaoWenWen
 * @date 2019/6/3
 */
public class FormLinear extends FrameLayout {

    private List<Integer> list = new ArrayList<>();
    private LinearLayout linearGroup;
    private float ratio = 0.78f;
    private int width = (int) (DeviceUtil.getScreenWidth() * ratio);
    private int height = DeviceUtil.dip2px(22);

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

    /**
     * 更新View;
     */
    public void updateChildView() {
        ArrayList<Integer> listInt = new ArrayList<>();
        listInt.add(0);
        listInt.add(1);
        listInt.add(2);
        listInt.add(3);
        listInt.add(4);
        listInt.add(5);
        listInt.add(6);
        this.list = listInt;
        for (int index = 0; index < list.size(); index++) {
            if (list.get(index) == 1) {
                generateNumberEditText();
            } else if (list.get(index) == 2) {
                generateCharEditText();
            } else if (list.get(index) == 3) {
                generateSpinnerView();
            } else if (list.get(index) == 4) {
                generateCommitButton();
            } else if (list.get(index) == 5) {
                generateCountView();
            } else if (list.get(index) == 6) {
                generateMarqueeView();
            }
        }
    }

    /**
     * 1.生成吊起数字类型键盘的编辑框
     */
    public void generateNumberEditText() {
        TestEditText testEditText = new TestEditText(getContext());
        testEditText.updateView("请输入姓名", InputType.TYPE_CLASS_TEXT);
        linearGroup.addView(testEditText);
        setCenterHorizontal(testEditText, width, height, DeviceUtil.dip2px(18), 0);
    }

    /**
     * 2.生成吊起文本类型的键盘编辑框
     */
    public void generateCharEditText() {
        TestEditText testEditText = new TestEditText(getContext());
        testEditText.updateView("请输入手机号", InputType.TYPE_CLASS_NUMBER | InputType.TYPE_CLASS_PHONE);
        linearGroup.addView(testEditText);
        setCenterHorizontal(testEditText, width, height, DeviceUtil.dip2px(10), 0);
    }

    /**
     * 3.生成下拉选择框
     */
    public void generateSpinnerView() {
        SpinnerViewGroup spinnerViewGroup = new SpinnerViewGroup(getContext());
        spinnerViewGroup.testRootNode(3, width, height);
        linearGroup.addView(spinnerViewGroup);
        setCenterHorizontal(spinnerViewGroup, width, ViewGroup.LayoutParams.WRAP_CONTENT, 0, 0);
    }

    /**
     * 4.生成button提交按钮
     */
    public void generateCommitButton() {
        CommitButton commitButton = new CommitButton(getContext());
        commitButton.setText("立即提交");
        linearGroup.addView(commitButton);
        setCenterHorizontal(commitButton, width, DeviceUtil.dip2px(25), DeviceUtil.dip2px(25), 0);
    }

    /**
     * 5.生成轮播视图
     */
    public void generateMarqueeView() {
        MarqueeView marqueeView = new MarqueeView(getContext());
        linearGroup.addView(marqueeView);
        marqueeView.startWithList(null);
        setCenterHorizontal(marqueeView, width, ViewGroup.LayoutParams.WRAP_CONTENT, DeviceUtil.dip2px(10), 0);
    }

    /**
     * 生成计数器
     */
    public void generateCountView() {
        TextView textView = new TextView(getContext());
        textView.setText("你好第200位提交者");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,6);
        linearGroup.addView(textView);
        setCenterHorizontal(textView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                DeviceUtil.dip2px(8), 0);
    }

    public void setCenterHorizontal(View view, int width, int height, int top, int bottom) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        layoutParams.topMargin = top;
        layoutParams.bottomMargin = bottom;
        view.setLayoutParams(layoutParams);
    }

}

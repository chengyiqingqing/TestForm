package com.meitu.sww.testform.spinner;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.meitu.sww.testform.R;
import com.meitu.sww.testform.model.SpinnerNode;
import com.meitu.sww.testform.recyclerlist.SpinnerAdapter;
import com.meitu.sww.testform.util.DeviceUtil;
import com.meitu.sww.testform.util.ViewUtil;

/**
 * 下拉选择框
 * @author ShaoWenWen
 * @date 2019/5/29
 */
public class SpinnerView extends AppCompatTextView {

    private RecyclerView recyclerView;
    private SpinnerAdapter spinnerAdapter;
    private PopupWindow popupWindow;

    private SpinnerNode node = new SpinnerNode();
    private float ratio = 0.7f;
    private int width = (int) (DeviceUtil.getScreenWidth() * ratio);
    private int height = DeviceUtil.dip2px(30);
    private SpinnerAdapter.OnSpinnerItemClickListener onSpinnerItemClickListener;

    public SpinnerView(Context context) {
        this(context, null);
    }

    public SpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
        initListener();
    }

    public void setOnSpinnerItemClickListener(SpinnerAdapter.OnSpinnerItemClickListener onSpinnerItemClickListener) {
        this.onSpinnerItemClickListener = onSpinnerItemClickListener;
    }

    private void initView() {
        recyclerView = new RecyclerView(getContext());
        spinnerAdapter = new SpinnerAdapter(node.getNodes());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(spinnerAdapter);
        ViewUtil.addCorner(recyclerView, DeviceUtil.dip2px(1), getResources().getColor(R.color.colorWhite), DeviceUtil
                .dip2px(1), getResources().getColor(R.color.colorBlack));
        popupWindow = new PopupWindow(getContext());
        popupWindow.setContentView(recyclerView);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setWidth(width);
        popupWindow.setHeight(DeviceUtil.dip2px(200));
        ViewUtil.addCorner(this, DeviceUtil.dip2px(1), getResources().getColor(R.color.colorWhite), DeviceUtil
                .dip2px(1), getResources().getColor(R.color.colorBlack));
        setPadding(DeviceUtil.dip2px(5), 0, 0, 0);
        setText("请选择");
    }

    private void initListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.showAsDropDown(SpinnerView.this, DeviceUtil.dip2px(1), DeviceUtil.dip2px(3));
            }
        });
        spinnerAdapter.setOnItemClickListener(new SpinnerAdapter.OnSpinnerItemClickListener() {
            @Override
            public void onItemClick(SpinnerNode node, int positon) {
                setText(node.getValue());
                // 把这样一个数据传递到外面；
                onSpinnerItemClickListener.onItemClick(node, positon);
                if (popupWindow != null) popupWindow.dismiss();
            }
        });
    }

    public void layout(){
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams(); // 这样才不会清除掉
        layoutParams.width = width;
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        setGravity(Gravity.CENTER_VERTICAL);
    }

    public void updateViewByData(SpinnerNode node) {
        this.node = node;
//        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams(); // 这样才不会清除掉
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams(); // 这样才不会清除掉
        layoutParams.width = width;
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        setGravity(Gravity.CENTER_VERTICAL);
        spinnerAdapter.setArrayList(this.node.getNodes());
    }

    public void resetStatus(SpinnerNode spinnerNode, boolean clickable) {
        if (spinnerNode == null || spinnerNode.getNodes() == null || spinnerNode.getNodes().size() == 0) {
            setClickable(false);
        }else {
            updateViewByData(spinnerNode);
            setClickable(clickable);
            setText("请选择");
        }
    }

    public void updateViewByData(SpinnerNode node, int viewHeight, float ratio) {
        this.node = node;
        //setLayoutParams(new FrameLayout.LayoutParams(width, height)); //将之前的居中之类的，layout设置全部清除了，因为你是重新new了一个layoutParams
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams(); // 这样才不会清除掉
        layoutParams.width = (int) (DeviceUtil.getScreenWidth() * ratio);
        layoutParams.height = viewHeight;
        setLayoutParams(layoutParams);
        spinnerAdapter.setArrayList(this.node.getNodes());
    }

}

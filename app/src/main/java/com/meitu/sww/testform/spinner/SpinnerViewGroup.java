package com.meitu.sww.testform.spinner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.meitu.sww.testform.R;
import com.meitu.sww.testform.model.SpinnerNode;
import com.meitu.sww.testform.recyclerlist.SpinnerAdapter;

import java.util.ArrayList;

/**
 * 下拉选择框，最多支持三级联动
 * @author ShaoWenWen
 * @date 2019/5/31
 */
public class SpinnerViewGroup extends FrameLayout {

    private static final int DEFAULT_POSITION = -1;

    private SpinnerView firstSpinnerView;
    private SpinnerView secondSpinnerView;
    private SpinnerView thirdSpinnerView;

    private SpinnerNode rootNode;
    private int allLevel = 1;
    private ArrayList<SpinnerNode> arrayList = new ArrayList<>();
    private int[] indexArray = new int[]{DEFAULT_POSITION, DEFAULT_POSITION, DEFAULT_POSITION};

    public SpinnerViewGroup(@NonNull Context context) {
        this(context, null);
    }

    public SpinnerViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initListener();
    }

    public void setRootNode(SpinnerNode rootNode, int allLevel, int width,int height) {
        this.rootNode = rootNode;
        this.allLevel = allLevel;
        if (rootNode == null || rootNode.getNodes() == null || rootNode.getNodes().size() == 0) {
            setVisibility(View.GONE);
        } else {
            firstSpinnerView.updateViewByData(rootNode);
            initChildView(width, height);
        }
    }

    private void initView(Context context) {
        inflate(context, R.layout.layout_spinner_view_group, this);
        firstSpinnerView = findViewById(R.id.first_spinner_view);
        secondSpinnerView = findViewById(R.id.second_spinner_view);
        thirdSpinnerView = findViewById(R.id.third_spinner_view);
        firstSpinnerView.setClickable(false);
        secondSpinnerView.setClickable(false);
        thirdSpinnerView.setClickable(false);
    }

    public void initChildView(int width, int height) {
        // TODO: 从rootNode中获取三节点的初始化显示 by ShaoWenWen 2019/6/4
        if (allLevel == 1){
            firstSpinnerView.setHint("请选择");
            firstSpinnerView.layout(width, height);
        } else if (allLevel == 2){
            firstSpinnerView.setHint("请选择");
            secondSpinnerView.setHint("请选择");
            firstSpinnerView.layout(width, height);
            secondSpinnerView.layout(width, height);
        } else if (allLevel == 3){
            firstSpinnerView.setHint("请选择");
            secondSpinnerView.setHint("请选择");
            thirdSpinnerView.setHint("请选择");
            firstSpinnerView.layout(width, height);
            secondSpinnerView.layout(width, height);
            thirdSpinnerView.layout(width, height);
        }
        firstSpinnerView.setClickable(true);
    }

    private void initListener() {
        firstSpinnerView.setOnSpinnerItemClickListener(new SpinnerAdapter.OnSpinnerItemClickListener() {
            @Override
            public void onItemClick(SpinnerNode node, int positon) {
                if (indexArray[0] != positon) {// 不同的选择
                    indexArray[0] = positon;
                    arrayList.clear();
                    arrayList.add(0, node);
                    removeRemainData(0);
                    secondSpinnerView.resetStatus(node, true);
                    thirdSpinnerView.resetStatus(node, false);
                }
            }
        });
        secondSpinnerView.setOnSpinnerItemClickListener(new SpinnerAdapter.OnSpinnerItemClickListener() {
            @Override
            public void onItemClick(SpinnerNode node, int positon) {
                if (indexArray[1] != positon) {// 不同的选择
                    arrayList.add(1, node);
                    removeRemainData(1);
                    thirdSpinnerView.resetStatus(node, true);
                }
            }
        });
        thirdSpinnerView.setOnSpinnerItemClickListener(new SpinnerAdapter.OnSpinnerItemClickListener() {
            @Override
            public void onItemClick(SpinnerNode node, int positon) {
                if (indexArray[2] != positon) {// 不同的选择
                    arrayList.add(2, node);
                    removeRemainData(2);
                }
            }
        });
    }

    private void removeRemainData(int from) {
        for (int index = arrayList.size() - 1; index > from; index--) {
            arrayList.remove(index);
        }
    }

    public void testRootNode(int level,int width,int height) {
        SpinnerNode rootNode = new SpinnerNode();
        ArrayList<SpinnerNode> firstList = new ArrayList<>();
        for (int index = 0; index < 12; index++) {
            SpinnerNode firstNode = new SpinnerNode();
            firstNode.setValue("数据" + index);
            ArrayList<SpinnerNode> secondList = new ArrayList<>();
            for (int position = 0; position < 12; position++) {
                SpinnerNode secondNode = new SpinnerNode();
                secondNode.setValue("数据" + position);
                ArrayList<SpinnerNode> thirdList = new ArrayList<>();
                for (int i = 0; i < 12; i++) {
                    SpinnerNode thirdNode = new SpinnerNode();
                    thirdNode.setValue("数据" + i);
                    thirdList.add(thirdNode);
                }
                secondNode.setNodes(thirdList);
                secondList.add(secondNode);
            }
            firstNode.setNodes(secondList);
            firstList.add(firstNode);
        }
        rootNode.setNodes(firstList);
        this.setRootNode(rootNode, level, width, height);
    }

}

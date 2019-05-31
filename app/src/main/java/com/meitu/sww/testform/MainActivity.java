package com.meitu.sww.testform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;

import com.meitu.sww.testRunText.MarqueeView;
import com.meitu.sww.testform.model.SpinnerNode;
import com.meitu.sww.testform.spinner.SpinnerView;
import com.meitu.sww.testform.spinner.SpinnerViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SpinnerView spinnerView;
    private SpinnerViewGroup spinnerViewGroup;
    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
        spinnerView = findViewById(R.id.spinner_show);
        spinnerViewGroup = findViewById(R.id.spinner_view_group);
        marqueeView = findViewById(R.id.text_marquee);
//        Log.e(TAG, "onCreate: "+createJSONObject() );
//        Log.e(TAG, "onCreate: " + createSpinnerObject());
    }

    // 创建JSONObject对象
    private static JSONObject createJSONObject() {
        try{
            JSONObject result = new JSONObject();
            result.put("success", true);
            result.put("totalCount", "30");

            JSONObject user1 = new JSONObject();
            user1.put("id", "12");
            user1.put("name", "张三");
            user1.put("createTime", "2017-11-16 12:12:12");

            JSONObject user2 = new JSONObject();
            user2.put("id", "13");
            user2.put("name", "李四");
            user2.put("createTime", "2017-11-16 12:12:15");

            JSONObject department = new JSONObject();
            department.put("id", 1);
            department.put("name","技术部");

            user1.put("department", department);
            user2.put("department", department);

            // 返回一个JSONArray对象
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(0, user1);
            jsonArray.put(1, user2);
            result.put("data", jsonArray);
            return result;
        }catch (Exception e){

        }
        return null;
    }

    private static JSONObject createSpinnerObject(){
        try{
            JSONObject rootNode = new JSONObject();
                        JSONArray jsonArray = new JSONArray();
            ArrayList<SpinnerNode> firstNodeList = new ArrayList<>();
            rootNode.put("name", "rootNode");
//            rootNode.put("nodes", firstNodeList);


//            JSONObject node1 = new JSONObject();
//            node1.put("name", "node1");
//            node1.put("nodes",new ArrayList<>());
            SpinnerNode node1 = new SpinnerNode();
            node1.setValue("name1");
            node1.setNodes(new ArrayList<SpinnerNode>());
            firstNodeList.add(node1);

            SpinnerNode node2 = new SpinnerNode();
            node2.setValue("name2");
            node2.setNodes(new ArrayList<SpinnerNode>());
            firstNodeList.add(node2);

            for (int index = 0; index < firstNodeList.size(); index++) {
                SpinnerNode node = firstNodeList.get(index);
                JSONObject nodeObject = new JSONObject();
                nodeObject.put("name", node.getValue());
                nodeObject.put("nodes", node.getNodes());
                jsonArray.put(index,nodeObject);
            }
            rootNode.put("nodes", jsonArray);

            /*JSONObject node2 = new JSONObject();
            node1.put("name", "node2");
            node1.put("nodes",new ArrayList<>());

            JSONObject department = new JSONObject();
            department.put("id", 1);
            department.put("name","技术部");*/

//            user1.put("department", department);
//            user2.put("department", department);
//
//            // 返回一个JSONArray对象
//            JSONArray jsonArray = new JSONArray();
//            jsonArray.put(0, user1);
//            jsonArray.put(1, user2);

//            rootNode.put("data", jsonArray);
            return rootNode;
        }catch (Exception e){

        }
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
//        spinnerView.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                setNode();
        setRootNode();
        List<CharSequence> list = new ArrayList<>();
        SpannableString ss1 = new SpannableString("1、MarqueeView开源项目");
        ss1.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red)), 2, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss1);
        SpannableString ss2 = new SpannableString("2、GitHub：sunfusheng");
        ss2.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.blue)), 9, 19, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss2);
        SpannableString ss3 = new SpannableString("3、个人博客：sunfusheng.com");
        ss3.setSpan(new URLSpan("http://sunfusheng.com/"), 7, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss3);
        list.add("4、新浪微博：@孙福生微博");
        marqueeView.startWithList(list);
//            }
//        },1000);
    }

    private void setRootNode() {
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
        spinnerViewGroup.setRootNode(rootNode, 3);
    }

    private void setNode() {
        SpinnerNode rootNode = new SpinnerNode();
        ArrayList<SpinnerNode> arrayList = new ArrayList<>();
        for (int index = 0; index < 20; index++) {
            SpinnerNode spinnerNode = new SpinnerNode();
            spinnerNode.setValue("数据" + index);
            spinnerNode.setNodes(new ArrayList<SpinnerNode>());
            arrayList.add(spinnerNode);
        }
        rootNode.setNodes(arrayList);
        spinnerView.updateViewByData(rootNode);
    }

}

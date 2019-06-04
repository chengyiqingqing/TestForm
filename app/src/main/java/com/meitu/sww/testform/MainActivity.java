package com.meitu.sww.testform;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.LinearLayout;

import com.meitu.sww.testEditText.TestEditText;
import com.meitu.sww.testRunText.MarqueeView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TestEditText edit_input;
    private LinearLayout linear_add;
    private MarqueeView marqueeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recycler);
        linear_add = findViewById(R.id.linear_add);
        edit_input = new TestEditText(MainActivity.this);
        edit_input.updateView("请输入姓名", InputType.TYPE_CLASS_PHONE | InputType.TYPE_CLASS_NUMBER);
//        addContentView(edit_input,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
//        marqueeView = findViewById(R.id.text_marquee);
        linear_add.addView(edit_input);
        marqueeView = new MarqueeView(MainActivity.this);
        linear_add.addView(marqueeView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<CharSequence> list = new ArrayList<>();
        list.add("1、nihao ");
        list.add("2、不错哦 ");
        list.add("3、可以了 ");
        list.add("4、哈哈");
        marqueeView.startWithList(list);
    }
}

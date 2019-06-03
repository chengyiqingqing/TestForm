package com.meitu.sww;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.meitu.sww.testLinearGroup.FormLinear;
import com.meitu.sww.testform.R;
import com.meitu.sww.testform.util.DeviceUtil;

/**
 * @author ShaoWenWen
 * @date 2019/6/3
 */
public class FormActivity extends AppCompatActivity {

    private FormLinear formLinear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_form);
        initView();
    }

    private void initView() {
        formLinear = findViewById(R.id.form_linear);
        formLinear.updateFormStyle(DeviceUtil.dip2px(10),DeviceUtil.dip2px(10), Color.YELLOW);
    }

}

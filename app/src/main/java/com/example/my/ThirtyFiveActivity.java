package com.example.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.example.my.base.BaseActivity;

/**
 * Created by  wsl
 * on 2019/6/18 12:52
 */
public class ThirtyFiveActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置是否显示标题栏
        setShowTitle(true);
        //设置是否显示状态栏
        setShowStatusBar(true);
        //是否允许屏幕旋转
        setAllowScreenRoate(true);
        //以上设置一定要在 super.onCreate(savedInstanceState) 方法之前设置
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        //初始化布局
        return 0;
    }

    @Override
    protected void initView() {
        //初始化控件
        Button button = new Button(context);
        button.setText("click");
        //每秒只响应一次点击事件
        button.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {

            }
        });
        //每秒可以重复响应点击事件
        button.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {

            }
        });
    }

    @Override
    protected void initData() {

        //初始化、绑定数据
    }


}

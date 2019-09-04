package com.example.my;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.my.base.BaseFragment;

/**
 * Created by  wsl
 * on 2019/6/18 16:03
 */
public class TestFragment extends BaseFragment {
    @Override
    protected int initLayout() {
        //初始化布局
        return 0;
    }

    @Override
    protected void initView(View view) {

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
    protected void initData(Context mContext) {

        //初始化、绑定数据
    }
}

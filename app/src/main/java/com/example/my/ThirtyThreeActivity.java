package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.example.my.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by  wsl
 * on 2019/6/18 12:52
 */
public class ThirtyThreeActivity extends BaseActivity {
    private MyHandler myHandler;
    private EditText editText;
       private String[] hintArray = {"第一", "第一次", "第一次写代码", "第一次领工资", "第二", "第二个"};

    /**
     * 启动activity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirtyThreeActivity.class);
        context.startActivity(intent);
    }

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
        return R.layout.activity_thirty_three_layout;
    }

    @Override
    protected void initView() {
        editText = findViewById(R.id.editText);

    }

    @Override
    protected void initData() {
    }

    /**
     * 点击事件
     */
    public OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            switch (view.getId()) {
                default:
                    break;
            }
        }
    };

    public static class MyHandler extends Handler {
        private WeakReference<ThirtyThreeActivity> activity;

        public MyHandler(ThirtyThreeActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ThirtyThreeActivity ezActivity = activity.get();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

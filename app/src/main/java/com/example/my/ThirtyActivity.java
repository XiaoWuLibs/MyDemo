package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.litepal.util.LogUtil;

import cn.addapp.pickers.util.LogUtils;

/**
 * Created by  wsl
 * on 2019/4/15 14:34
 */
public class ThirtyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirty_layout);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirtyActivity.class);
        context.startActivity(intent);
    }
}

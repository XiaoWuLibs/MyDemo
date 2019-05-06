package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.my.util.Log.LogUtils;

/**
 * Created by  wsl
 * on 2019/4/15 14:34
 * 异常捕获工具类
 */
public class ThirtyOneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirtyOneActivity.class);
        context.startActivity(intent);
    }
}

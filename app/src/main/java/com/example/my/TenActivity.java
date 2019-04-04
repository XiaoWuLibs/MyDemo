package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/5/16.
 */

public class TenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ten_layout);

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TenActivity.class);
        context.startActivity(intent);
    }
}

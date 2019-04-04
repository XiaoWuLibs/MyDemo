package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.my.thread.MyThread;

/**
 * Created by Administrator on 2018/4/2.
 */

public class SevenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("HD TEST");
        toolbar.setNavigationIcon(android.R.drawable.btn_plus);
        setSupportActionBar(toolbar);
        MyThread myThread_1 = new MyThread();
        new Thread(myThread_1).start();
        new Thread(myThread_1).start();
        new Thread(myThread_1).start();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SevenActivity.class);
        context.startActivity(intent);
    }
}

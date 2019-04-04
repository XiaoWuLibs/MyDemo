package com.example.my;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TwentyOneActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_one);


    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyOneActivity.class);
        context.startActivity(intent);
    }
}

package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/2/23.
 */

public class FourActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_layout);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FourActivity.class);
        context.startActivity(intent);
    }
}

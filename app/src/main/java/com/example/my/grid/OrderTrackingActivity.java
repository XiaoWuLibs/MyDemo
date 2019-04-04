package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.my.R;

/**
 * Created by Administrator on 2018/1/22.
 * 订单跟踪
 */

public class OrderTrackingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_tracking);

    }

    /**
     * 启动OrderTrackingActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrderTrackingActivity.class);
        context.startActivity(intent);
    }
}

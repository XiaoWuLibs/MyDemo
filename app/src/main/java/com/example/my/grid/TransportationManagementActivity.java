package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.my.R;

/**
 * Created by Administrator on 2018/1/22.
 * 运输管理
 */

public class TransportationManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transportation_management);

    }

    /**
     * 启动TransportationManagementActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TransportationManagementActivity.class);
        context.startActivity(intent);
    }
}

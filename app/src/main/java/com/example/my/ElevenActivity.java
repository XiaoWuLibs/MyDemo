package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.my.util.TaiJi;

/**
 * Created by Administrator on 2018/5/16.
 */

public class ElevenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleven_layout);
        final TaiJi taiji = findViewById(R.id.taiji);
        Handler handler = new Handler() {
            private float degrees = 0;

            @Override
            public void handleMessage(Message msg) {
                taiji.setRotate(degrees += 2);
                this.sendEmptyMessageDelayed(0, 10);
            }
        };
        handler.sendEmptyMessageDelayed(0, 10);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ElevenActivity.class);
        context.startActivity(intent);
    }
}

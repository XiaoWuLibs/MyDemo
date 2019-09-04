package com.example.my.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2018/9/26.
 * 萤石设备receive 广播接收器
 */

public class EZReceiver extends BroadcastReceiver {
    public static final String TAG = "EZReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "-----------");
    }
}

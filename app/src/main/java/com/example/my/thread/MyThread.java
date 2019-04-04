package com.example.my.thread;

import android.util.Log;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyThread implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (ticket > 0) {
                Log.i("----:", "卖票:" + ticket--);
            }
        }
    }
}

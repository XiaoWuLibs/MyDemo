package com.example.my.grid;

import com.example.my.interfaces.ToastListener;

/**
 * 接口使用
 * Created by Administrator on 2018/8/17.
 */

public class ToastListenerUser {
    private ToastListener toastListener;

    public void setToastListener(ToastListener listener) {
        this.toastListener = listener;
    }

    public void useToastListener() {
        toastListener.showToast();
    }
}

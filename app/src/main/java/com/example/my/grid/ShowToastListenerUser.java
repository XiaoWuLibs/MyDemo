package com.example.my.grid;

import com.example.my.interfaces.ShowToastListener;

/**
 * Created by Administrator on 2018/8/17.
 * ShowToastListener使用者
 */

public class ShowToastListenerUser {
    private ShowToastListener listener;

    public void setShowToastListener(ShowToastListener showToastListener) {
        this.listener = showToastListener;
    }

    public void showToast(String str) {
        listener.show("000");
    }
}

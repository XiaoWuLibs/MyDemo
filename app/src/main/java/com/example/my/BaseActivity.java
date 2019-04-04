package com.example.my;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/1/25.
 * 父类
 */

public class BaseActivity extends AppCompatActivity {
    public ProgressDialog dataLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataLoadingDialog = new ProgressDialog(this);
        dataLoadingDialog.setTitle("加载数据");
        dataLoadingDialog.setMessage("玩命加载中...");
    }

    /**
     * 开启加载框
     */
    public void showLoadingDialog() {
        if (dataLoadingDialog != null && !dataLoadingDialog.isShowing()) {
            dataLoadingDialog.show();
        }
    }

    /**
     * 关闭加载框
     */
    public void closeLoadingDialog() {
        if (dataLoadingDialog != null && dataLoadingDialog.isShowing()) {
            dataLoadingDialog.dismiss();
        }
    }
}

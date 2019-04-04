package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.my.BaseActivity;
import com.example.my.R;
import com.example.my.adapter.AddressAdapter;
import com.example.my.db.Address;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2018/1/22.
 * 订单管理/->尚未接单
 */

public class OrderManagementActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);
        initElement();
        setElement();
    }

    /**
     * 初始化控件
     */
    private void initElement() {
        queryOrder();
    }

    /**
     * 配置控件
     */
    private void setElement() {
        //关闭加载框
        closeLoadingDialog();

    }

    /**
     * 查询数据库中地址簿
     */
    private void queryOrder() {
        //显示加载框
        showLoadingDialog();
    }

    /**
     * 启动OrderManagementActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, OrderManagementActivity.class);
        context.startActivity(intent);
    }
}

package com.example.my;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TwentyEightActivity extends AppCompatActivity {
    private static final String TAG = "TwentyEightActivity";

    private CheckBox checkbox;
    private Button btn_click;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_eight);

        initView();
        setData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mContext = this;
        checkbox = findViewById(R.id.checkbox);
        btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isSelected()) {

                    checkbox.setSelected(false);
                } else {
                    checkbox.setSelected(true);
                }

            }
        });
    }

    /**
     * 配置控件
     */
    private void setData() {
        clearDefaultLauncher();
    }

    /**
     * 清除默认桌面（采用先设置一个空的桌面为默认然后在将该空桌面禁用的方式来实现）
     *
     * @param cont
     */
    @SuppressLint("WrongConstant")
    public void clearDefaultLauncher() {
        PackageManager pm = mContext.getPackageManager();
        String pn = mContext.getPackageName();
        String hn = TwentyEightActivity.class.getName();
        ComponentName mhCN = new ComponentName(pn, hn);
        Intent homeIntent = new Intent("android.intent.action.MAIN");
        homeIntent.addCategory("android.intent.category.HOME");
        homeIntent.addCategory("android.intent.category.DEFAULT");
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        pm.setComponentEnabledSetting(mhCN, 1, 1);
        mContext.startActivity(homeIntent);
        pm.setComponentEnabledSetting(mhCN, 0, 1);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyEightActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.my.bean.IbeaconBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TwentySevenActivity extends AppCompatActivity {
    private static final String TAG = "TwentySevenActivity";
    private Button btn_click;
    private LinearLayout ll_layout;
    public static final String[] ids = {"1", "1", "1", "2", "2", "2", "1", "2"};
    public static final String[] macs = {"aa", "aa", "aa", "bb", "bb", "aa", "cc", "cc"};
    public static final String[] rssi = {"80", "80", "60", "50", "40", "30", "50", "10"};
    private List<IbeaconBean> ibeaconBeans = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_seven);

        initView();
        setData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        for (int i = 0; i < ids.length; i++) {
            IbeaconBean bean = new IbeaconBean(ids[i], macs[i], rssi[i]);
            if (!ibeaconBeans.contains(bean))
                ibeaconBeans.add(bean);
        }
        btn_click = findViewById(R.id.btn_click);
        ll_layout = findViewById(R.id.ll_layout);

    }

    /**
     * 配置控件
     */
    private void setData() {
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (IbeaconBean b : ibeaconBeans) {
                    Log.i("Log", b.key + "++" + b.mac + "++" + b.rssi);
                }
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentySevenActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

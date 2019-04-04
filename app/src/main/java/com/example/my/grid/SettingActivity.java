package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.adapter.AddressAdapter;

/**
 * Created by Administrator on 2018/1/22.
 * 系统设置
 */

public class SettingActivity extends AppCompatActivity {
    private LinearLayout ll_aboutApp, ll_appText;
    private Button btn_exit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initElement();
        setElement();
    }

    /**
     * 初始化控件
     */
    private void initElement() {
        ll_aboutApp = findViewById(R.id.ll_aboutApp);
        ll_appText = findViewById(R.id.ll_appText);
        btn_exit = findViewById(R.id.btn_exit);
        ll_aboutApp.setOnClickListener(onClickListener);
        ll_appText.setOnClickListener(onClickListener);
        btn_exit.setOnClickListener(onClickListener);
    }

    /**
     * 配置控件
     */
    private void setElement() {

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_aboutApp:
                    break;
                case R.id.ll_appText:
                    AppTextActivity.startActivity(SettingActivity.this);
                    break;
                case R.id.btn_exit:
//                    Toast.makeText(SettingActivity.this, "退出登录", Toast.LENGTH_SHORT).show();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 启动SettingActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        context.startActivity(intent);
    }
}

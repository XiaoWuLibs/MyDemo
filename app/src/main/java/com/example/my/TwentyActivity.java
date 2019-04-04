package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.my.util.MyProgressDialog;
import com.example.my.util.ProgressView;

/**
 * Created by Administrator on 2018/9/8.
 */

public class TwentyActivity extends AppCompatActivity {
    private static final String TAG = "TwentyActivity";
    ProgressView progressView;
    MyProgressDialog progressDialog;
    private Button btn_click;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty);
        initElement();
    }

    private void initElement() {
        progressView = findViewById(R.id.progressView);
//        progressView.setValue("30");
//        progressView.setCurrentPercent(0.6f);
        progressDialog = new MyProgressDialog(this, null);


        btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.showLoading(TwentyActivity.this, null);
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyActivity.class);
        context.startActivity(intent);
    }
}

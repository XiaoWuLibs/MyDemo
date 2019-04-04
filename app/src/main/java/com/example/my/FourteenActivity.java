package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.my.grid.ChenLieDingFragment;
import com.example.my.grid.DaPeiDingFragment;
import com.example.my.grid.DingDanFenXiFragment;
import com.example.my.grid.GouWuCheFragment;
import com.example.my.grid.TuiYanDingFragment;
import com.example.my.grid.ZiYouDingFragment;

/**
 * Created by Administrator on 2018/5/16.
 */

public class FourteenActivity extends AppCompatActivity {

    private TextView tv_ziYouDing, tv_daPeiDing, tv_chenLieDing, tv_tuiYanDing, tv_gouWuChe, tv_dingDanFenXi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_code);
        tv_ziYouDing = findViewById(R.id.tv_ziYouDing);
        tv_daPeiDing = findViewById(R.id.tv_daPeiDing);
        tv_chenLieDing = findViewById(R.id.tv_chenLieDing);
        tv_tuiYanDing = findViewById(R.id.tv_tuiYanDing);
        tv_gouWuChe = findViewById(R.id.tv_gouWuChe);
        tv_dingDanFenXi = findViewById(R.id.tv_dingDanFenXi);
        tv_ziYouDing.setOnClickListener(onClickListener);
        tv_daPeiDing.setOnClickListener(onClickListener);
        tv_chenLieDing.setOnClickListener(onClickListener);
        tv_tuiYanDing.setOnClickListener(onClickListener);
        tv_gouWuChe.setOnClickListener(onClickListener);
        tv_dingDanFenXi.setOnClickListener(onClickListener);
        tv_ziYouDing.setTextColor(Color.RED);
        tv_daPeiDing.setTextColor(Color.BLACK);
        tv_chenLieDing.setTextColor(Color.BLACK);
        tv_tuiYanDing.setTextColor(Color.BLACK);
        tv_gouWuChe.setTextColor(Color.BLACK);
        tv_dingDanFenXi.setTextColor(Color.BLACK);
        replace(new ZiYouDingFragment());
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_ziYouDing:
                    tv_ziYouDing.setTextColor(Color.RED);
                    tv_daPeiDing.setTextColor(Color.BLACK);
                    tv_chenLieDing.setTextColor(Color.BLACK);
                    tv_tuiYanDing.setTextColor(Color.BLACK);
                    tv_gouWuChe.setTextColor(Color.BLACK);
                    tv_dingDanFenXi.setTextColor(Color.BLACK);
                    replace(new ZiYouDingFragment());
                    break;
                case R.id.tv_daPeiDing:
                    tv_ziYouDing.setTextColor(Color.BLACK);
                    tv_daPeiDing.setTextColor(Color.RED);
                    tv_chenLieDing.setTextColor(Color.BLACK);
                    tv_tuiYanDing.setTextColor(Color.BLACK);
                    tv_gouWuChe.setTextColor(Color.BLACK);
                    tv_dingDanFenXi.setTextColor(Color.BLACK);
                    replace(new DaPeiDingFragment());
                    break;
                case R.id.tv_chenLieDing:
                    tv_ziYouDing.setTextColor(Color.BLACK);
                    tv_daPeiDing.setTextColor(Color.BLACK);
                    tv_chenLieDing.setTextColor(Color.RED);
                    tv_tuiYanDing.setTextColor(Color.BLACK);
                    tv_gouWuChe.setTextColor(Color.BLACK);
                    tv_dingDanFenXi.setTextColor(Color.BLACK);
                    replace(new ChenLieDingFragment());
                    break;
                case R.id.tv_tuiYanDing:
                    tv_ziYouDing.setTextColor(Color.BLACK);
                    tv_daPeiDing.setTextColor(Color.BLACK);
                    tv_chenLieDing.setTextColor(Color.BLACK);
                    tv_tuiYanDing.setTextColor(Color.RED);
                    tv_gouWuChe.setTextColor(Color.BLACK);
                    tv_dingDanFenXi.setTextColor(Color.BLACK);
                    replace(new TuiYanDingFragment());
                    break;
                case R.id.tv_gouWuChe:
                    tv_ziYouDing.setTextColor(Color.BLACK);
                    tv_daPeiDing.setTextColor(Color.BLACK);
                    tv_chenLieDing.setTextColor(Color.BLACK);
                    tv_tuiYanDing.setTextColor(Color.BLACK);
                    tv_gouWuChe.setTextColor(Color.RED);
                    tv_dingDanFenXi.setTextColor(Color.BLACK);
                    replace(new GouWuCheFragment());
                    break;
                case R.id.tv_dingDanFenXi:
                    tv_ziYouDing.setTextColor(Color.BLACK);
                    tv_daPeiDing.setTextColor(Color.BLACK);
                    tv_chenLieDing.setTextColor(Color.BLACK);
                    tv_tuiYanDing.setTextColor(Color.BLACK);
                    tv_gouWuChe.setTextColor(Color.BLACK);
                    tv_dingDanFenXi.setTextColor(Color.RED);
                    replace(new DingDanFenXiFragment());
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 切换Fragment
     *
     * @param fragment Fragment
     */
    private void replace(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.commit();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FourteenActivity.class);
        context.startActivity(intent);
    }
}

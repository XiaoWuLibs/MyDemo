package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.my.R;

/**
 * Created by Administrator on 2018/1/22.
 * 主页index
 */

public class SecondActivity extends AppCompatActivity {
    /**
     * FragmentTabhost
     */
    private FragmentTabHost mTabHost;

    /**
     * 布局填充器
     */
    private LayoutInflater mLayoutInflater;
    private ImageView iv_shopCar;
    private ImageView iv_main;
    private LinearLayout ll_search;
    /**
     * Fragment数组界面
     */
    private Class mFragmentArray[] = {Fragment1.class, Fragment2.class,
            Fragment3.class, Fragment4.class};
    /**
     * 存放图片数组
     */
    private int mImageArray[] = {R.drawable.icon_about,
            R.drawable.icon_category_48,
            R.drawable.icon_about,
            R.drawable.icon_about};

    /**
     * 选修卡文字
     */
    private String mTextArray[] = {"首页", "分类", "购物车", "个人中心"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_main);
        //秒杀倒计时
//        CountdownView countdownView = findViewById(R.id.countdownView);
//        countdownView.start(9550000); // Millisecond
//        for (int time = 0; time < 1000; time++) {
//            countdownView.updateShow(time);
//        }

        initView();
    }

    /**
     * 初始化组件
     */
    private void initView() {
        mLayoutInflater = LayoutInflater.from(this);

        // 找到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        // 得到fragment的个数
        int count = mFragmentArray.length;
        for (int i = 0; i < count; i++) {
            // 给每个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
                    .setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, mFragmentArray[i], null);
            // 设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i)
                    .setBackgroundColor(getResources().getColor(R.color.white_gray));
        }
        final View tab1 = mTabHost.getTabWidget().getChildTabViewAt(0);
        final View tab2 = mTabHost.getTabWidget().getChildTabViewAt(1);
        final View tab3 = mTabHost.getTabWidget().getChildTabViewAt(2);
        final View tab4 = mTabHost.getTabWidget().getChildTabViewAt(3);
        final TextView tv_1 = tab1.findViewById(R.id.textview);
        final TextView tv_2 = tab2.findViewById(R.id.textview);
        final TextView tv_3 = tab3.findViewById(R.id.textview);
        final TextView tv_4 = tab4.findViewById(R.id.textview);
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ll_search.setBackgroundColor(getResources().getColor(R.color.white_gray));
                ll_search.setVisibility(View.VISIBLE);
                tv_1.setTextColor(getResources().getColor(R.color.red));
                tv_2.setTextColor(getResources().getColor(R.color.black));
                tv_3.setTextColor(getResources().getColor(R.color.black));
                tv_4.setTextColor(getResources().getColor(R.color.black));
                tab1.setBackgroundColor(getResources().getColor(R.color.light_gray));
                tab2.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab3.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab4.setBackgroundColor(getResources().getColor(R.color.white_gray));
                mTabHost.setCurrentTab(0);

            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ll_search.setBackgroundColor(getResources().getColor(R.color.white_gray));
                ll_search.setVisibility(View.VISIBLE);
                tv_1.setTextColor(getResources().getColor(R.color.black));
                tv_2.setTextColor(getResources().getColor(R.color.red));
                tv_3.setTextColor(getResources().getColor(R.color.black));
                tv_4.setTextColor(getResources().getColor(R.color.black));
                tab1.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab2.setBackgroundColor(getResources().getColor(R.color.light_gray));
                tab3.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab4.setBackgroundColor(getResources().getColor(R.color.white_gray));
                mTabHost.setCurrentTab(1);
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ll_search.setBackgroundColor(getResources().getColor(R.color.white_gray));
                ll_search.setVisibility(View.VISIBLE);
                tv_1.setTextColor(getResources().getColor(R.color.black));
                tv_2.setTextColor(getResources().getColor(R.color.black));
                tv_3.setTextColor(getResources().getColor(R.color.red));
                tv_4.setTextColor(getResources().getColor(R.color.black));
                tab1.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab2.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab3.setBackgroundColor(getResources().getColor(R.color.light_gray));
                tab4.setBackgroundColor(getResources().getColor(R.color.white_gray));
                mTabHost.setCurrentTab(2);
            }
        });
        tab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ll_search.setBackgroundColor(getResources().getColor(R.color.red));
                ll_search.setVisibility(View.GONE);
                tv_1.setTextColor(getResources().getColor(R.color.black));
                tv_2.setTextColor(getResources().getColor(R.color.black));
                tv_3.setTextColor(getResources().getColor(R.color.black));
                tv_4.setTextColor(getResources().getColor(R.color.red));
                tab1.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab2.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab3.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab4.setBackgroundColor(getResources().getColor(R.color.light_gray));
                mTabHost.setCurrentTab(3);

            }
        });
        tv_1.setTextColor(getResources().getColor(R.color.red));
        tv_2.setTextColor(getResources().getColor(R.color.black));
        tv_3.setTextColor(getResources().getColor(R.color.black));
        tv_4.setTextColor(getResources().getColor(R.color.black));
        tab1.setBackgroundColor(getResources().getColor(R.color.light_gray));
        tab2.setBackgroundColor(getResources().getColor(R.color.white_gray));
        tab3.setBackgroundColor(getResources().getColor(R.color.white_gray));
        tab4.setBackgroundColor(getResources().getColor(R.color.white_gray));

        iv_shopCar = findViewById(R.id.iv_shopCar);
        iv_main = findViewById(R.id.iv_main);
        ll_search = findViewById(R.id.ll_search);

        iv_shopCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ll_search.setBackgroundColor(getResources().getColor(R.color.white_gray));
                ll_search.setVisibility(View.VISIBLE);
                tv_1.setTextColor(getResources().getColor(R.color.black));
                tv_2.setTextColor(getResources().getColor(R.color.black));
                tv_3.setTextColor(getResources().getColor(R.color.red));
                tv_4.setTextColor(getResources().getColor(R.color.black));
                tab1.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab2.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab3.setBackgroundColor(getResources().getColor(R.color.light_gray));
                tab4.setBackgroundColor(getResources().getColor(R.color.white_gray));
                mTabHost.setCurrentTab(2);
            }
        });
        iv_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ll_search.setBackgroundColor(getResources().getColor(R.color.red));
                ll_search.setVisibility(View.GONE);
                tv_1.setTextColor(getResources().getColor(R.color.black));
                tv_2.setTextColor(getResources().getColor(R.color.black));
                tv_3.setTextColor(getResources().getColor(R.color.black));
                tv_4.setTextColor(getResources().getColor(R.color.red));
                tab1.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab2.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab3.setBackgroundColor(getResources().getColor(R.color.white_gray));
                tab4.setBackgroundColor(getResources().getColor(R.color.light_gray));
                mTabHost.setCurrentTab(3);
            }
        });
    }

    /**
     * 给每个Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageArray[index]);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(mTextArray[index]);

        return view;
    }

    /**
     * 启动SecondActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SecondActivity.class);
        context.startActivity(intent);
    }
}

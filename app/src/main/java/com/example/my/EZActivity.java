package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.my.adapter.GuideAdapter;
import com.example.my.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  wsl
 * on 2019/6/18 12:52
 */
public class EZActivity extends BaseActivity {
    private MyHandler myHandler;
    private ViewPager viewpager;
    private Button btnSubmit;
    private ImageView ivPoint1, ivPoint2, ivPoint3, ivPoint4;
    private List<ImageView> guidePointList;

    /**
     * 启动activity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, EZActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置是否显示标题栏
        setShowTitle(true);
        //设置是否显示状态栏
        setShowStatusBar(true);
        //是否允许屏幕旋转
        setAllowScreenRoate(true);
        //以上设置一定要在 super.onCreate(savedInstanceState) 方法之前设置
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int initLayout() {
        //初始化布局
        return R.layout.activity_ez_layout;
    }

    @Override
    protected void initView() {
        guidePointList = new ArrayList<>();
        viewpager = findViewById(R.id.viewpager);
        btnSubmit = findViewById(R.id.btnSubmit);
        ivPoint1 = findViewById(R.id.ivPoint1);
        ivPoint2 = findViewById(R.id.ivPoint2);
        ivPoint3 = findViewById(R.id.ivPoint3);
        ivPoint4 = findViewById(R.id.ivPoint4);

        addGuidePointToList();
    }

    /**
     * 添加引导点 到 list
     */
    private void addGuidePointToList() {
        guidePointList.add(ivPoint1);
        guidePointList.add(ivPoint2);
        guidePointList.add(ivPoint3);
        guidePointList.add(ivPoint4);
    }

    @Override
    protected void initData() {
        final List<Integer> list = new ArrayList<>();
        list.add(R.drawable.book_1);
        list.add(R.drawable.book_2);
        list.add(R.drawable.book_3);
        list.add(R.drawable.book_4);
        final GuideAdapter guideAdapter = new GuideAdapter(context, list);
        viewpager.setAdapter(guideAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == list.size() - 1) {
                    btnSubmit.setVisibility(View.VISIBLE);
                } else {
                    btnSubmit.setVisibility(View.GONE);
                }

                setGuidePoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        viewpager.setPageTransformer(false, new DepthPageTransformer());
    }

    /**
     * 设置引导点  的状态 （选中、未选中）
     *
     * @param position 当前滑动到的位置
     */
    private void setGuidePoint(int position) {
        for (int i = 0; i < guidePointList.size(); i++) {
            if (position == i) {
                guidePointList.get(i).setImageResource(R.drawable.shape_guide_point_big);
            } else {
                guidePointList.get(i).setImageResource(R.drawable.shape_guide_point_small);
            }
        }
    }

    /**
     * 点击事件
     */
    public OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            switch (view.getId()) {
                default:
                    break;
            }
        }
    };


    public static class MyHandler extends Handler {
        private WeakReference<EZActivity> activity;

        public MyHandler(EZActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EZActivity ezActivity = activity.get();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

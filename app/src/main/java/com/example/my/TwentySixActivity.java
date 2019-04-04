package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/9/12.
 * 控件随手指拖动
 */

public class TwentySixActivity extends AppCompatActivity {
    private static final String TAG = "TwentySixActivity";
    private Button btn_start, btn_stop, btn_pull;
    private LinearLayout ll_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_six);

        initView();
        setData();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        btn_start = findViewById(R.id.btn_start);
        btn_stop = findViewById(R.id.btn_stop);
        btn_pull = findViewById(R.id.btn_pull);
        ll_layout = findViewById(R.id.ll_layout);

    }

    /**
     * 配置控件
     */
    private void setData() {
        btn_pull.setOnTouchListener(onTouchListener);
    }


    /**
     * 是否移动过
     */
    private boolean isMove;

    private float mLastX;
    private float mLastY;
    private float mStartX;
    private float mStartY;
    private long mLastTime;
    private long mCurrentTime;

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        int lastX;
        int lastY;
        int left;
        int top;
        int right;
        int bottom;
        int screenWidth;
        int screenHeight;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    isMove = false;
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    mStartX = event.getRawX();
                    mStartY = event.getRawY();
                    mLastTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int dx = (int) event.getRawX() - lastX;
                    int dy = (int) event.getRawY() - lastY;
                    screenWidth = ll_layout.getWidth();
                    screenHeight = ll_layout.getHeight();

                    if (dx != 0 || dy != 0) {
                        isMove = true;
                    }

                    left = v.getLeft() + dx;
                    top = v.getTop() + dy;
                    right = v.getRight() + dx;
                    bottom = v.getBottom() + dy;
                    if (left < 0) {
                        left = 0;
                        right = left + v.getWidth();
                    }
                    if (right > screenWidth) {
                        right = screenWidth;
                        left = right - v.getWidth();
                    }
                    if (top < 0) {
                        top = 0;
                        bottom = top + v.getHeight();
                    }
                    if (bottom > screenHeight) {
                        bottom = screenHeight;
                        top = bottom - v.getHeight();
                    }
                    v.layout(left, top, right, bottom);
                    lastX = (int) event.getRawX();
                    lastY = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_UP:
                    if (left <= (screenWidth / 2)) {
                        left = 0;
                    } else {
                        left = screenWidth - btn_pull.getWidth();
                    }
                    v.layout(left, top, right, bottom);
                    Rect vRect = new Rect();
                    v.getHitRect(vRect);
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) v
                            .getLayoutParams();
                    lp.leftMargin = vRect.left;
                    lp.topMargin = vRect.top;
                    v.setLayoutParams(lp);

                    mLastX = event.getRawX();
                    mLastY = event.getRawY();
                    mCurrentTime = System.currentTimeMillis();
                    if (mCurrentTime - mLastTime < 800) {//长按不起作用
                        Log.d("kitchee", "开始Y=" + mStartY);
                        Log.d("kitchee", "最后Y=" + mLastY);
                        Log.d("kitchee", "移动Y=" + Math.abs(mStartY - mLastY));
                        if (Math.abs(mStartX - mLastX) < 10.0 && Math.abs(mStartY - mLastY) < 10.0) {//判断是否属于点击
                            Toast.makeText(TwentySixActivity.this, "可以执行点击任务", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                default:
                    break;
            }
            return true;
        }
    };

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentySixActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.example.my.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/6/19.
 */

public class TaiJi extends View {
    private Paint whitePaint;//白色画笔
    private Paint blackPaint;//黑色画笔
    private float degrees = 0;//旋转角度

    // 用户直接New一个View时会被调用
    public TaiJi(Context context) {
        super(context);
        initPaint();//初始化画笔
    }

    //用户在layout布局文件中使用这个View时会被调用
    public TaiJi(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();//初始化画笔
    }

    public TaiJi(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();//初始化画笔
    }

    /**
     * 初始化画笔函数
     */
    private void initPaint() {
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);

        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        Point centerPoint = new Point(width / 2, height / 2);
        canvas.translate(centerPoint.x, centerPoint.y);
        canvas.drawColor(Color.GRAY);
        canvas.rotate(degrees);
        //绘制两个半圆
        int radius = Math.min(width, height) / 2 - 100;

        RectF rect = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rect, 90, 180, false, blackPaint);
        canvas.drawArc(rect, -90, 180, false, whitePaint);

        //绘制两个小圆
        int smallRadius = radius / 2;
        canvas.drawCircle(0, -smallRadius, smallRadius, blackPaint);
        canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);


        //绘制鱼眼，两个更小的圆
        canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
        canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaint);
    }

    //旋转函数
    public void setRotate(float degrees) {
        this.degrees = degrees;
        invalidate();//重绘界面
    }
}

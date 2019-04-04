package com.example.my.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.my.R;

/**
 * Created by Administrator on 2018/9/14.
 */

public class StopWatch extends View {
    private Paint outerCirclePaint,
            middleBackgroundCirclePaint,
            innerCirclePaint,
            rectPaint;
    private int width;
    private int height;
    private static final int M_S_DEGREES_UNIT = 360 / 60; //分、秒针每个数字走的角度
    private static final int SCALE_LINE_LENGTH = 50; //刻度线长
    private static final int SCALE_LINE_WIDTH = 6; //刻度线宽

    public StopWatch(Context context) {
        super(context);
        init(context, null);
    }

    public StopWatch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StopWatch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        outerCirclePaint = new Paint();
        outerCirclePaint.setColor(getResources().getColor(R.color.gray));
        outerCirclePaint.setAntiAlias(true);
        outerCirclePaint.setStyle(Paint.Style.STROKE);
        outerCirclePaint.setStrokeWidth(1f);

        middleBackgroundCirclePaint = new Paint();
        middleBackgroundCirclePaint.setAntiAlias(true);
        middleBackgroundCirclePaint.setColor(getResources().getColor(R.color.gray_red));
        middleBackgroundCirclePaint.setStyle(Paint.Style.STROKE);
        middleBackgroundCirclePaint.setStrokeWidth(5f);

        innerCirclePaint = new Paint();
        innerCirclePaint.setAntiAlias(true);
        innerCirclePaint.setColor(getResources().getColor(R.color.light_blue));
        innerCirclePaint.setStyle(Paint.Style.STROKE);
        innerCirclePaint.setStrokeWidth(20f);

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setColor(getResources().getColor(R.color.black));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            width = height = 400;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = 400;
            width = height = Math.min(width, height);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = 400;
            width = height = Math.min(width, height);
        } else {
            width = height = Math.min(width, height);
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radiusX = width / 2;
        float radiusY = height / 2;
        float radius = width / 2 - 10;
        int mR = (int) radius - 40;
        canvas.drawCircle(radiusX, radiusY, radius, outerCirclePaint);

        canvas.drawRect(width - 10, radiusY - 10, width, radiusY + 10, rectPaint);

        canvas.drawCircle(radiusX, radiusY, radius - 20, middleBackgroundCirclePaint);

        canvas.drawCircle(radiusX, radiusY, radius - 50, innerCirclePaint);

         /*
        绘制刻度
         */
        for (int i = 0; i < 60; i++) {
            canvas.save();
            canvas.rotate(M_S_DEGREES_UNIT * i, radiusX, radiusY);
            rectPaint.setStrokeWidth(SCALE_LINE_WIDTH);
            canvas.drawLine(radiusX, radiusY - mR, radiusX, radiusY - mR + SCALE_LINE_LENGTH, rectPaint);
            if (i % 5 == 0) {
                rectPaint.setStrokeWidth(SCALE_LINE_WIDTH + 5);
                canvas.drawLine(radiusX, radiusY - mR, radiusX, radiusY - mR + SCALE_LINE_LENGTH + 10, rectPaint);

                String num = i == 0 ? 12 + "" : i / 5 + "";
                float x = radiusX - rectPaint.measureText(num) / 2 - 10;
                float y = radiusX - mR + SCALE_LINE_LENGTH + 20;
                rectPaint.setTextSize(30);
                canvas.drawText(num, x, y + Math.abs(rectPaint.ascent()), rectPaint);
            }
            canvas.restore();

        }
    }
}

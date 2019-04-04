package com.example.my.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.example.my.R;

/**
 * Created by Administrator on 2018/9/13.
 * 项目地址：
 * https://www.jianshu.com/p/bfe74a8620b3
 * https://github.com/SolveBugs/BlogPracticeDems
 */

public class ProgressView extends View {
    private Context context;

    private String title = "标题";
    private String num = "000";
    private String unit = "单位";

    private float titleTextSize;
    private float numTextSize;
    private float unitTextSize;

    private int titleTextColor;
    private int numTextColor;
    private int unitTextColor;

    private float backCircleWidth;
    private float outerCircleWidth;

    private int backCircleColor;
    private int outerCircleColor;

    private float endCircleWidth;
    private int endCircleColor;

    private Paint backCirclePaint,//画背景圆
            outerCirclePaint,//画进度圆弧
            endCirclePaint,//画终点实心大圆
            endCirclePaint2,//画终点实心小圆
            titlePaint,//画第一行文字
            numPaint,//画第二行文字
            unitPaint;//画第三行文字
    private int width, height;

    private float currentPercent = 0.3f;


    public ProgressView(Context context) {
        super(context);
        init(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);

            title = TextUtils.isEmpty(array.getString(R.styleable.ProgressView_progress_title))
                    ? "标题" : array.getString(R.styleable.ProgressView_progress_title);
            num = TextUtils.isEmpty(array.getString(R.styleable.ProgressView_progress_num))
                    ? "000" : array.getString(R.styleable.ProgressView_progress_num);
            unit = TextUtils.isEmpty(array.getString(R.styleable.ProgressView_progress_unit))
                    ? "单位" : array.getString(R.styleable.ProgressView_progress_unit);


            titleTextSize = array.getDimension(R.styleable.ProgressView_titleTextSize, getResources().getDimension(R.dimen.px_to_dip_24));
            numTextSize = array.getDimension(R.styleable.ProgressView_numTextSize, getResources().getDimension(R.dimen.px_to_dip_48));
            unitTextSize = array.getDimension(R.styleable.ProgressView_unitTextSize, getResources().getDimension(R.dimen.px_to_dip_24));

            titleTextColor = array.getColor(R.styleable.ProgressView_titleTextColor, Color.parseColor("#656d78"));
            numTextColor = array.getColor(R.styleable.ProgressView_numTextColor, Color.parseColor("#4fc1e9"));
            unitTextColor = array.getColor(R.styleable.ProgressView_unitTextColor, Color.parseColor("#4fc1e9"));

            backCircleWidth = array.getDimension(R.styleable.ProgressView_backCircleWidth, getResources().getDimension(R.dimen.px_to_dip_12));
            outerCircleWidth = array.getDimension(R.styleable.ProgressView_outerCircleWidth, getResources().getDimension(R.dimen.px_to_dip_20));

            backCircleColor = array.getColor(R.styleable.ProgressView_backCircleColor, Color.parseColor("#e6e9ed"));
            outerCircleColor = array.getColor(R.styleable.ProgressView_outerCircleColor, Color.parseColor("#4fc1e9"));

            endCircleWidth = array.getDimension(R.styleable.ProgressView_endCircleWidth, getResources().getDimension(R.dimen.px_to_dip_24));
            endCircleColor = array.getColor(R.styleable.ProgressView_endCircleColor, Color.parseColor("#4fc1e9"));

            backCirclePaint = new Paint();
            backCirclePaint.setAntiAlias(true);
            backCirclePaint.setStrokeWidth(backCircleWidth);
            backCirclePaint.setColor(backCircleColor);
            backCirclePaint.setStyle(Paint.Style.STROKE);

            outerCirclePaint = new Paint();
            outerCirclePaint.setAntiAlias(true);
            outerCirclePaint.setStrokeWidth(outerCircleWidth);
            outerCirclePaint.setColor(outerCircleColor);
            outerCirclePaint.setStyle(Paint.Style.STROKE);
            outerCirclePaint.setStrokeCap(Paint.Cap.ROUND);

            endCirclePaint = new Paint();
            endCirclePaint.setAntiAlias(true);
            endCirclePaint.setColor(endCircleColor);
            endCirclePaint.setStyle(Paint.Style.FILL);

            endCirclePaint2 = new Paint();
            endCirclePaint2.setAntiAlias(true);
            endCirclePaint2.setColor(Color.WHITE);
            endCirclePaint2.setStyle(Paint.Style.FILL);

            titlePaint = new Paint();
            titlePaint.setAntiAlias(true);
            titlePaint.setColor(titleTextColor);
            titlePaint.setTextSize(titleTextSize);

            numPaint = new Paint();
            numPaint.setAntiAlias(true);
            numPaint.setColor(numTextColor);
            numPaint.setTextSize(numTextSize);

            unitPaint = new Paint();
            unitPaint.setAntiAlias(true);
            unitPaint.setColor(unitTextColor);
            unitPaint.setTextSize(unitTextSize);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = height = Math.min(width, height);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = width / 2;
        int centerY = height / 2;

        //计算半径
        float radius = (width / 2) - outerCircleWidth + ((outerCircleWidth - backCircleWidth) / 2);

        //画背景圆
        canvas.drawCircle(centerX, centerY, radius, backCirclePaint);

        //根据进度画扫过一定角度的圆弧
        RectF rectF = new RectF(outerCircleWidth / 2 + backCircleWidth / 2,
                outerCircleWidth / 2 + backCircleWidth / 2,
                width - outerCircleWidth / 2 - backCircleWidth / 2,
                height - outerCircleWidth / 2 - backCircleWidth / 2);
//        RectF rectF = new RectF(outerCircleWidth, outerCircleWidth, width - outerCircleWidth, height - outerCircleWidth);
        canvas.drawArc(rectF, -90, 360 * currentPercent, false, outerCirclePaint);

        //画矩形
//        canvas.drawRect(rectF, rectPaint);

//画三行文字
        Rect textRect = new Rect();
        titlePaint.getTextBounds(title, 0, title.length(), textRect);
        canvas.drawText(title, width / 2 - textRect.width() / 2, height / 4 + textRect.height() / 2, titlePaint);

        numPaint.getTextBounds(num, 0, num.length(), textRect);
        canvas.drawText(num, width / 2 - textRect.width() / 2, height / 2 + textRect.height() / 2, numPaint);

        unitPaint.getTextBounds(unit, 0, unit.length(), textRect);
        canvas.drawText(unit, width / 2 - textRect.width() / 2, height * 2 / 3 + textRect.height() / 2, unitPaint);


        if (currentPercent < 1 && currentPercent > 0) {
            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180),
                    endCircleWidth / 2, endCirclePaint);

            canvas.drawCircle(centerX + rectF.width() / 2 * (float) Math.sin(360 * currentPercent * Math.PI / 180),
                    centerY - rectF.width() / 2 * (float) Math.cos(360 * currentPercent * Math.PI / 180), endCircleWidth / 4, endCirclePaint2);

        }
    }

    public void setValue(String value) {
        this.num = value;
        invalidate();
    }

    public void setCurrentPercent(float currentPercent) {
        this.currentPercent = currentPercent;
        invalidate();
    }
}

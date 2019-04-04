package com.example.my.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.my.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/25.
 * 滚动的文字
 */

public class RunText extends View implements Runnable {
    private static final String TAG = "RunText";
    private String string;//最终绘制的文本
    private float speed = 1;//移动速度
    private int textColor = Color.BLACK;//文字颜色，默认黑色
    private float textSize = 12;//文字大小，默认12sp
    private int textdistance;
    private int textDistance1 = 10;//item间距，单位：dp
    private String black_count = "";//间距转化成空格距离

    private int repetType = REPET_INTERVAL;//滚动模式
    public static final int REPET_ONCETIME = 0;//一次结束
    public static final int REPET_INTERVAL = 1;//一次结束以后，再继续第二次
    public static final int REPET_CONTINUOUS = 2;//紧接着滚动第二次
    private float startLocationDistance = 1.0f;//开始的位置选取，百分比来的，距离左边，0-1,0代表不间距，1的话代表，从右面，1/2代表中间。
    private boolean isClickStop = false;//点击是否暂停
    private boolean isResetLocation = true;//默认为true
    private float xLocation = 0;//文本的x坐标
    private int contentWidth;//内容的宽度
    private boolean isRoll = false;//是否继续滚动
    private float oneBlack_width;//空格的宽度
    private TextPaint paint;//画笔
    private Rect rect;
    private int repetCount = 0;
    private boolean resetInit = true;
    private Thread thread;
    private String content = "";
    private float textHeight;

    public RunText(Context context) {
        super(context);
        initpaint();
        initClick();
    }

    public RunText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initattrs(attrs);
        initpaint();
        initClick();
    }

    public RunText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initattrs(attrs);
        initpaint();
        initClick();
    }

    public void initClick() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isClickStop) {
                    if (isRoll) {
                        stopRoll();
                    } else {
                        continueRoll();
                    }
                }
            }
        });
    }

    @SuppressLint("RestrictedApi")
    private void initattrs(AttributeSet attrs) {
        TintTypedArray tta = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.RunText);
        textColor = tta.getColor(R.styleable.RunText_marqueeview_text_color, textColor);
        isClickStop = tta.getBoolean(R.styleable.RunText_marqueeview_isclickalbe_stop, isClickStop);
        isResetLocation = tta.getBoolean(R.styleable.RunText_marqueeview_is_resetLocation, isResetLocation);
        speed = tta.getFloat(R.styleable.RunText_marqueeview_text_speed, speed);
        textSize = tta.getFloat(R.styleable.RunText_marqueeview_text_size, textSize);
        textDistance1 = tta.getInteger(R.styleable.RunText_marqueeview_text_distance, textDistance1);
        startLocationDistance = tta.getFloat(R.styleable.RunText_marqueeview_text_startlocationdistance, startLocationDistance);
        repetType = tta.getInt(R.styleable.RunText_marqueeview_repet_type, repetType);
        tta.recycle();
    }

    /**
     * 刻字机修改
     */
    private void initpaint() {
        rect = new Rect();
        paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);//anti_alias_flag
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(textColor);
        paint.setTextSize(dp2px(textSize));
    }

    private int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (resetInit) {
            setTextDistance(textDistance1);
            if (startLocationDistance < 0) {
                startLocationDistance = 0;
            } else if (startLocationDistance > 1) {
                startLocationDistance = 1;
            }
            xLocation = getWidth() * startLocationDistance;
            resetInit = false;
        }

        //需要判断滚动模式的
        switch (repetType) {
            case REPET_ONCETIME:
                if (contentWidth < (-xLocation)) {
                    //也就是说文字已经到头了
//                    此时停止线程就可以了
                    stopRoll();
                }
                break;
            case REPET_INTERVAL:
                if (contentWidth <= (-xLocation)) {
                    //也就是说文字已经到头了
                    xLocation = getWidth();
                }
                break;
            case REPET_CONTINUOUS:
                if (xLocation < 0) {
                    int beAppend = (int) ((-xLocation) / contentWidth);
                    if (beAppend >= repetCount) {
                        repetCount++;
                        //也就是说文字已经到头了
//                    xLocation = speed;//这个方法有问题，所以采取了追加字符串的 方法
                        string = string + content;
                    }
                }
                break;
            default:
                //默认一次到头好了
                if (contentWidth < (-xLocation)) {
                    //也就是说文字已经到头了
//                    此时停止线程就可以了
                    stopRoll();
                }
                break;
        }
        //把文字画出来
        if (string != null) {
            canvas.drawText(string, xLocation, getHeight() / 2 + textHeight / 2, paint);
            Log.i(TAG, "onDraw: " + string + "----size:" + string.length());
        }
    }

    /**
     * 设置文字间距  不过如果内容是List形式的，该方法不适用 ,list的数据源，必须在设置setContent之前调用此方法。
     *
     * @param textdistance2
     */
    private void setTextDistance(int textdistance2) {

        //设置之后就需要初始化了
        String black = " ";
        oneBlack_width = getBlacktWidth();//空格的宽度
        textdistance2 = dp2px(textdistance2);

        int count = (int) (textdistance2 / oneBlack_width);//空格个数，有点粗略，有兴趣的朋友可以精细
        if (count == 0) {
            count = 1;
        }

        textdistance = (int) (oneBlack_width * count);
        black_count = "";
        for (int i = 0; i <= count; i++) {
            black_count = black_count + black;//间隔字符串
        }
        setContent(content);//设置间距以后要重新刷新内容距离，不过如果内容是List形式的，该方法不适用
    }

    /**
     * 计算出一个空格的宽度
     *
     * @return
     */
    private float getBlacktWidth() {
        String text1 = "en en";
        String text2 = "enen";
        return getContentWidth(text1) - getContentWidth(text2);
    }

    private float getContentWidth(String black) {
        if (TextUtils.isEmpty(black)) {
            return 0;
        }
        if (rect == null) {
            rect = new Rect();
        }
        paint.getTextBounds(black, 0, black.length(), rect);
        textHeight = getContentHeight();
        return rect.width();
    }

    /**
     * http://blog.csdn.net/u014702653/article/details/51985821
     * 详细解说了
     * @return float
     */
    private float getContentHeight() {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return Math.abs((fontMetrics.bottom - fontMetrics.top)) / 2;
    }

    /**
     * |设置滚动的条目内容 ， 集合形式的
     *
     * @param strings
     */
    public void setContent(List<String> strings) {
        setTextDistance(textDistance1);
        String temString = "";
        if (strings != null && strings.size() != 0) {
            for (int i = 0; i < strings.size(); i++) {
                temString = temString + strings.get(i) + black_count;
            }
        }
        setContent(temString);
    }

    /**
     * 设置滚动的条目内容  字符串形式的
     *
     * @parambt_control00
     */
    public void setContent(String content2) {
        if (TextUtils.isEmpty(content2)) {
            return;
        }
        if (isResetLocation) {//控制重新设置文本内容的时候，是否初始化xLocation。
            xLocation = getWidth() * startLocationDistance;
        }
        if (!content2.endsWith(black_count)) {
            content2 = content2 + black_count;//避免没有后缀
        }
        this.content = content2;
        //这里需要计算宽度啦，当然要根据模式来搞
        if (repetType == REPET_CONTINUOUS) {//紧接着滚动第二次
            //如果说是循环的话，则需要计算 文本的宽度 ，然后再根据屏幕宽度 ， 看能一个屏幕能盛得下几个文本
            contentWidth = (int) (getContentWidth(content) + textdistance);//可以理解为一个单元内容的长度
            //从0 开始计算重复次数了， 否则到最后 会跨不过这个坎而消失。
            repetCount = 0;
            int contentCount = (getWidth() / contentWidth) + 2;
            this.string = "";
            for (int i = 0; i <= contentCount; i++) {
                this.string = this.string + this.content;//根据重复次数去叠加
            }
        } else {
            if (xLocation < 0 && repetType == REPET_ONCETIME) {//一次结束
                if (-xLocation > contentWidth) {
                    xLocation = getWidth() * startLocationDistance;
                }
            }
            contentWidth = (int) getContentWidth(content);
            this.string = content2;
        }

        if (!isRoll) {//如果没有在滚动的话，重新开启线程滚动
            continueRoll();
        }
    }

    /**
     * 继续滚动
     */
    public void continueRoll() {
        if (!isRoll) {
            if (thread != null) {
                thread.interrupt();
                thread = null;
            }
            isRoll = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * 停止滚动
     */
    public void stopRoll() {
        isRoll = false;
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    @Override
    public void run() {
        while (isRoll && !TextUtils.isEmpty(content)) {
            try {
                Thread.sleep(10);
                xLocation = xLocation - speed;
                postInvalidate();//每隔10毫秒重绘视图
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

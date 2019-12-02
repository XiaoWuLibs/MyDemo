package com.example.my.widget;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by  wsl
 * on 2019/10/16 10:52
 */
public class CountButton extends AppCompatButton {
    private int countNum = 60;
    private CountDownTimer countDownTimer;
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {
            countNum--;
            setText(countNum + "");
            if (countNum <= 0) {
                timer.cancel();
                setText("获取验证码");
                setEnabled(true);
            }
        }
    };


    public CountButton(Context context) {
        super(context);
    }

    public CountButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CountButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(60 * 000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    setText(millisUntilFinished / 1000 + "");
                    setEnabled(false);

                }

                @Override
                public void onFinish() {
                    setText("获取验证码");
                    setEnabled(true);
                }
            };
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        super.setOnClickListener(l);

    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (isEnabled()) {
//                setEnabled(false);
//                countNum = 60;
//                if (timer == null) {
//                    timer = new Timer();
//                }
//                if (task != null) {
//                    timer.schedule(task, 1000);
//                }
//            }
            if (isEnabled() && countDownTimer != null) {
                countDownTimer.start();
            }
        }
        return super.onTouchEvent(event);
    }
}

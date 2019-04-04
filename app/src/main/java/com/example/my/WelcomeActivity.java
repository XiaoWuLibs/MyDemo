package com.example.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2018/4/13.
 */

public class WelcomeActivity extends BaseActivity {
    private LinearLayout welcom;
    Animation anim1 = null;
    Animation anim2 = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(this, R.layout.activity_welcome_layout, null);
        setContentView(R.layout.activity_welcome_layout);


        welcom = findViewById(R.id.welcome);
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim_in);
        anim2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_out);
        welcom.startAnimation(anim1);
        anim1.setFillEnabled(true);//启动保持
        anim1.setFillAfter(true);//最后一帧保持，否则会跳到原始
        anim1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                welcom.startAnimation(anim2);
                anim2.setFillAfter(true);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        anim2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        final Intent intent = new Intent(this, MainActivity.class); //你要转向的Activity
//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                startActivity(intent); //执行
//                finish();
//            }
//        };
//        timer.schedule(task, 1000 * 3); //3秒后
    }

    /**
     * 跳转到...
     */
    private void redirectTo() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

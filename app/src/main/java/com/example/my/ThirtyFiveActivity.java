package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import com.example.my.base.BaseActivity;
import com.example.my.interpolator.DecelerateAccelerateDecelerateInterpolator;

import java.lang.ref.WeakReference;

/**
 * Created by  wsl
 * on 2019/6/18 12:52
 */
public class ThirtyFiveActivity extends BaseActivity {
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private MyHandler myHandler;
    private TranslateAnimation animation;

    private int repeat;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirtyFiveActivity.class);
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
        return R.layout.activity_thirty_five_layout;
    }

    @Override
    protected void initView() {
        myHandler = new MyHandler(this);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        //初始化控件
        Button button = new Button(context);
        button.setText("click");
        //每秒只响应一次点击事件
        button.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View view) {

            }
        });
        //每秒可以重复响应点击事件
        button.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {

            }
        });
    }

    @Override
    protected void initData() {

        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int x = point.x;
        int y = point.y;
        animation = new TranslateAnimation(0, 0, y - 800, y + 10);
        animation.setDuration(2 * 1000);
        animation.setInterpolator(new DecelerateAccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!ThirtyFiveActivity.this.isFinishing()) {
                    myHandler.sendEmptyMessage(1000);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(1000);
            }
        }, 500);


        //初始化、绑定数据
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_up_to_dowm);
//        animation.setInterpolator(new CycleInterpolator(5));
//        animation.setDuration(5 * 1000);
//        imageView3.setAnimation(animation);
//
//        animation.start();
    }

    /**
     * 加载webview
     *
     * @param url url地址 "http://www.taotie.666.com/Upload/insurance/保险条款.html"
     */
    private void loadWebView(final String url, WebView webview) {
        if (TextUtils.isEmpty(url)) {
            return;
        }
        //支持javascript
        webview.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        webview.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        webview.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        webview.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        webview.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webview.getSettings().setLoadWithOverviewMode(true);


        //如果不设置WebViewClient，请求会跳转系统浏览器
        webview.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

//                if (url.toString().contains("http")) {
//                    view.loadUrl(url);
//                    return true;
//                }

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    if (request.getUrl().toString().contains("http")) {
//                        view.loadUrl(url);
//                        return true;
//                    }
//                }

                return false;
            }

        });
        webview.loadUrl(url);
    }

    public static class MyHandler extends Handler {
        static private WeakReference<ThirtyFiveActivity> weakReference;

        public MyHandler(ThirtyFiveActivity thirtyFiveActivity) {
            weakReference = new WeakReference<ThirtyFiveActivity>(thirtyFiveActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            ThirtyFiveActivity activity = weakReference.get();
            activity.repeat++;
            if (activity.repeat <= 5) {
                activity.imageView1.startAnimation(activity.animation);


                activity.imageView2.startAnimation(activity.animation);


                activity.imageView3.startAnimation(activity.animation);
            } else {
                activity.animation.cancel();
                activity.repeat = 0;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (animation != null) {
            animation.cancel();
            animation = null;
        }

        if (myHandler != null) {
            myHandler = null;
        }
        super.onDestroy();

    }
}

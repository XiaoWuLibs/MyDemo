package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TwentyEightActivity extends AppCompatActivity {
    private static final String TAG = "TwentyEightActivity";
    private AdView adView;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_eight);

        initView();
        setData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mContext = this;
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        adView = findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        adView.loadAd(adRequest);
    }

    /**
     * 配置控件
     */
    private void setData() {

    }



    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyEightActivity.class);
        context.startActivity(intent);
    }

    /** Called when leaving the activity */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /** Called when returning to the activity */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /** Called before the activity is destroyed */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}

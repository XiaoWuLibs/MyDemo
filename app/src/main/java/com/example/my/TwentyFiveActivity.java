package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.rtmp.ITXLivePlayListener;
import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * Created by Administrator on 2018/9/12.
 * 腾讯直播demo
 */

public class TwentyFiveActivity extends AppCompatActivity implements ITXLivePushListener {
    private static final String TAG = "TwentyFourActivity";
    private static final String rtmpUrl = "rtmp://29380.livepush.myqcloud.com/live/empl1022cc9ffe0a4649?txSecret=0583f4a441beb4163a883dde86beeae6&txTime=5C8A7A7F";
    private static final String flvUrl = "http://live.119xiehui.com/live/empl1022cc9ffe0a4649.flv";
    private Button btn_start, btn_stop, btn_pull;
    private TXLivePusher mLivePusher;
    private TXLivePushConfig mLivePushConfig;
    private TXCloudVideoView mCaptureView;
    private TXLivePlayer mLivePlayer;
    private boolean mVideoPublish;
    private int mVideoQuality = TXLiveConstants.VIDEO_QUALITY_STANDARD_DEFINITION;
    private int mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_360_640;
    private boolean mHWVideoEncode = true;
    private boolean mIsRealTime = false;
    private boolean mIsPlaying;
    private int mCurrentRenderMode;
    private int mCurrentRenderRotation;
    private boolean mHWDecode = false;
    private TXLivePlayConfig mPlayConfig;
    private static final float CACHE_TIME_FAST = 1.0f;
    private static final float CACHE_TIME_SMOOTH = 5.0f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_five);
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
        mCaptureView = (TXCloudVideoView) findViewById(R.id.video_view);
        if (mLivePlayer == null) {
            mLivePlayer = new TXLivePlayer(this);
        }
        mCurrentRenderMode = TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION;
        mCurrentRenderRotation = TXLiveConstants.RENDER_ROTATION_PORTRAIT;
        mPlayConfig = new TXLivePlayConfig();
        mLivePusher = new TXLivePusher(this);
        mLivePushConfig = new TXLivePushConfig();
    }

    /**
     * 配置控件
     */
    private void setData() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mVideoPublish) {
                    stopRtmpPublish();
                } else {

                    FixOrAdjustBitrate();  //根据设置确定是“固定”还是“自动”码率
                    mVideoPublish = startPublishRtmp();
                }
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRtmpPublish();
            }
        });
        btn_pull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsPlaying) {
                    stopPlay();
                } else {
                    mIsPlaying = startPlay();
                }
            }
        });


    }

    public void FixOrAdjustBitrate() {
        if (mLivePushConfig == null || mLivePusher == null) {
            return;
        }
        mLivePushConfig.setVideoEncodeGop(5);

        mVideoQuality = TXLiveConstants.VIDEO_QUALITY_REALTIEM_VIDEOCHAT;
        mLivePusher.setVideoQuality(mVideoQuality, false, false);
        mCurrentVideoResolution = TXLiveConstants.VIDEO_RESOLUTION_TYPE_360_640;
        //超清默认开启硬件加速
        if (Build.VERSION.SDK_INT >= 18) {
            mHWVideoEncode = true;
        }
        mIsRealTime = true;

    }

    private boolean startPublishRtmp() {

        if (TextUtils.isEmpty(rtmpUrl) || (!rtmpUrl.trim().toLowerCase().startsWith("rtmp://"))) {
            Toast.makeText(getApplicationContext(), "推流地址不合法，目前支持rtmp推流!", Toast.LENGTH_SHORT).show();
//            Bundle params = new Bundle();
//            params.putString(TXLiveConstants.EVT_DESCRIPTION, "检查地址合法性");
//            mPushVisibleLogView.setLogText(null, params, TXPushVisibleLogView.CHECK_RTMP_URL_FAIL);
            return false;
        }
//        Bundle params = new Bundle();
//        params.putString(TXLiveConstants.EVT_DESCRIPTION, "检查地址合法性");
//        mPushVisibleLogView.setLogText(null, params, TXPushVisibleLogView.CHECK_RTMP_URL_OK);

        // demo默认不加水印
//        mLivePushConfig.setWatermark(mBitmap, 0.02f, 0.05f, 0.2f);

        int customModeType = 0;

//        if (isActivityCanRotation()) {
//            onActivityRotation();
//        }
        mLivePushConfig.setCustomModeType(customModeType);
        mLivePusher.setPushListener(this);
        mLivePushConfig.setPauseImg(300, 5);
//        Bitmap bitmap = decodeResource(getResources(), R.drawable.pause_publish);
//        mLivePushConfig.setPauseImg(bitmap);
//        mLivePushConfig.setPauseFlag(TXLiveConstants.PAUSE_FLAG_PAUSE_VIDEO | TXLiveConstants.PAUSE_FLAG_PAUSE_AUDIO);

        mLivePushConfig.setFrontCamera(false);
        mLivePushConfig.setBeautyFilter(5, 3, 2);
        mLivePusher.setConfig(mLivePushConfig);
        mLivePusher.startCameraPreview(mCaptureView);


        mLivePusher.startPusher(rtmpUrl.trim());

        return true;
    }

    //结束推流并进行清理
    public void stopRtmpPublish() {
        mLivePusher.stopCameraPreview(true); //停止摄像头预览
        mLivePusher.stopPusher();            //停止推流
        mLivePusher.setPushListener(null);   //解绑 listener
    }

    private boolean startPlay() {
        if (!checkPlayUrl(flvUrl)) {
            Bundle params = new Bundle();
            params.putString(TXLiveConstants.EVT_DESCRIPTION, "检查地址合法性");
            return false;
        }

        mLivePlayer.setPlayerView(mCaptureView);

        mLivePlayer.setPlayListener(new ITXLivePlayListener() {
            @Override
            public void onPlayEvent(int i, Bundle bundle) {

            }

            @Override
            public void onNetStatus(Bundle bundle) {

            }
        });
        // 硬件加速在1080p解码场景下效果显著，但细节之处并不如想象的那么美好：
        // (1) 只有 4.3 以上android系统才支持
        // (2) 兼容性我们目前还仅过了小米华为等常见机型，故这里的返回值您先不要太当真
        mLivePlayer.enableHardwareDecode(mHWDecode);
        mLivePlayer.setRenderRotation(mCurrentRenderRotation);
        mLivePlayer.setRenderMode(mCurrentRenderMode);
        //设置播放器缓存策略
        //这里将播放器的策略设置为自动调整，调整的范围设定为1到4s，您也可以通过setCacheTime将播放器策略设置为采用
        //固定缓存时间。如果您什么都不调用，播放器将采用默认的策略（默认策略为自动调整，调整范围为1到4s）
        //mLivePlayer.setCacheTime(5);
        // HashMap<String, String> headers = new HashMap<>();
        // headers.put("Referer", "qcloud.com");
        // mPlayConfig.setHeaders(headers);
        mPlayConfig.setAutoAdjustCacheTime(true);
        mPlayConfig.setMaxAutoAdjustCacheTime(CACHE_TIME_SMOOTH);
        mPlayConfig.setMinAutoAdjustCacheTime(CACHE_TIME_FAST);
        mLivePlayer.setConfig(mPlayConfig);
        int result = mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); // result返回值：0 success;  -1 empty url; -2 invalid url; -3 invalid playType;
        if (result != 0) {
            return false;
        }

        Log.w("video render", "timetrack start play");

        return true;
    }

    private void stopPlay() {
        if (mLivePlayer != null) {
            mLivePlayer.stopRecord();
            mLivePlayer.setPlayListener(null);
            mLivePlayer.stopPlay(true);
        }
        mIsPlaying = false;
    }

    private boolean checkPlayUrl(final String playUrl) {
        if (TextUtils.isEmpty(playUrl) || (!playUrl.startsWith("http://") && !playUrl.startsWith("https://") && !playUrl.startsWith("rtmp://") && !playUrl.startsWith("/"))) {
            Toast.makeText(getApplicationContext(), "播放地址不合法，直播目前仅支持rtmp,flv播放方式!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyFiveActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        mCaptureView.onDestroy();
    }

    @Override
    public void onPushEvent(int event, Bundle param) {
        String msg = param.getString(TXLiveConstants.EVT_DESCRIPTION);
        String pushEventLog = "receive event: " + event + ", " + msg;
        Log.d(TAG, pushEventLog);
//        mPushVisibleLogView.setLogText(null, param, event);

        //错误还是要明确的报一下
        if (event < 0) {
            Toast.makeText(getApplicationContext(), param.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show();
            if (event == TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL || event == TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL) {
                stopRtmpPublish();
            }
        }

        if (event == TXLiveConstants.PUSH_ERR_NET_DISCONNECT || event == TXLiveConstants.PUSH_ERR_INVALID_ADDRESS) {
            stopRtmpPublish();
        }
    }

    @Override
    public void onNetStatus(Bundle bundle) {

    }
}

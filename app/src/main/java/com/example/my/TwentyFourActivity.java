package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tencent.rtmp.ITXLivePushListener;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayConfig;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.TXLivePushConfig;
import com.tencent.rtmp.TXLivePusher;
import com.tencent.rtmp.ui.TXCloudVideoView;

import static com.tencent.rtmp.TXLiveConstants.VIDEO_QUALITY_HIGH_DEFINITION;

/**
 * Created by Administrator on 2018/9/12.
 * 腾讯直播demo
 */

public class TwentyFourActivity extends AppCompatActivity {
    private static final String TAG = "TwentyFourActivity";
    private static final String rtmpUrl = "rtmp://29380.livepush.myqcloud.com/live/empl1022cc9ffe0a4649?txSecret=0583f4a441beb4163a883dde86beeae6&txTime=5C8A7A7F";
    private Button btn_start, btn_stop, btn_pull;
    private TXLivePusher mLivePusher;
    private TXLivePushConfig mLivePushConfig;
    private TXLivePlayConfig mLivePlayConfig;
    private TXCloudVideoView mCaptureView;
    private TXLivePlayer mLivePlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_four);

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

        //push
        mLivePusher = new TXLivePusher(this);
        mLivePushConfig = new TXLivePushConfig();
        mLivePlayConfig = new TXLivePlayConfig();

        //pull
        //创建 player 对象
        mLivePlayer = new TXLivePlayer(this);

//关键 player 对象与界面 view
        mLivePlayer.setPlayerView(mCaptureView);


    }

    /**
     * 配置控件
     */
    private void setData() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLivePusher.setPushListener(new ITXLivePushListener() {
                    @Override
                    public void onPushEvent(int event, Bundle param) {
                        //错误还是要明确的报一下
                        if (event < 0) {
                            Toast.makeText(getApplicationContext(), param.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show();
                            if (event == TXLiveConstants.PUSH_ERR_OPEN_CAMERA_FAIL
                                    || event == TXLiveConstants.PUSH_ERR_OPEN_MIC_FAIL) {
                                stopRtmpPublish();
                            }
                        }


                        if (event == TXLiveConstants.PUSH_ERR_NET_DISCONNECT || event == TXLiveConstants.PUSH_ERR_INVALID_ADDRESS) {
                            stopRtmpPublish();
                        } else if (event == TXLiveConstants.PUSH_WARNING_HW_ACCELERATION_FAIL) {
                            Toast.makeText(getApplicationContext(), param.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show();
                            mLivePushConfig.setHardwareAcceleration(TXLiveConstants.ENCODE_VIDEO_SOFTWARE);
                            mLivePusher.setConfig(mLivePushConfig);
                        } else if (event == TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_UNSURPORT) {
                            stopRtmpPublish();
                        } else if (event == TXLiveConstants.PUSH_ERR_SCREEN_CAPTURE_START_FAILED) {
                            stopRtmpPublish();
                        } else if (event == TXLiveConstants.PUSH_EVT_CHANGE_RESOLUTION) {
                            Log.d(TAG, "change resolution to " + param.getInt(TXLiveConstants.EVT_PARAM2) + ", bitrate to" + param.getInt(TXLiveConstants.EVT_PARAM1));
                        } else if (event == TXLiveConstants.PUSH_EVT_CHANGE_BITRATE) {
                            Log.d(TAG, "change bitrate to" + param.getInt(TXLiveConstants.EVT_PARAM1));
                        } else if (event == TXLiveConstants.PUSH_WARNING_NET_BUSY) {
                            Toast.makeText(getApplicationContext(), param.getString(TXLiveConstants.EVT_DESCRIPTION), Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void onNetStatus(Bundle bundle) {

                    }
                });
                mLivePushConfig.setFrontCamera(false);
                mLivePusher.setConfig(mLivePushConfig);
                mLivePusher.startCameraPreview(mCaptureView);
                mLivePusher.setVideoQuality(VIDEO_QUALITY_HIGH_DEFINITION, false, false);
                mLivePusher.startPusher(rtmpUrl);
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

                mLivePlayConfig.setAutoAdjustCacheTime(false);
                mLivePlayConfig.setMaxAutoAdjustCacheTime(5);
                mLivePlayConfig.setMinAutoAdjustCacheTime(5);
                mLivePlayer.setConfig(mLivePlayConfig);
                mLivePlayer.setRenderRotation(180);
                String flvUrl = "http://live.119xiehui.com/live/empl4371b871c3a30218.flv";
                mLivePlayer.startPlay(flvUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV); //推荐 FLV
            }
        });


        // 如果您不清楚要何时开启硬件加速, 建议设置为 ENCODE_VIDEO_AUTO
// 默认是启用软件编码, 但手机 CPU 使用率超过 80% 或者帧率 <= 10, SDK 内部会自动切换为硬件编码
        mLivePushConfig.setHardwareAcceleration(TXLiveConstants.ENCODE_VIDEO_AUTO);
        mLivePusher.setConfig(mLivePushConfig);


    }

    //结束推流并进行清理
    public void stopRtmpPublish() {
        mLivePusher.stopCameraPreview(true); //停止摄像头预览
        mLivePusher.stopPusher();            //停止推流
        mLivePusher.setPushListener(null);   //解绑 listener
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyFourActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLivePlayer.stopPlay(true); // true 代表清除最后一帧画面
        mCaptureView.onDestroy();
    }
}

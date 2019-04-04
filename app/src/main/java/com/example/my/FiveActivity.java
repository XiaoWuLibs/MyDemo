package com.example.my;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.my.db.LatLngDB;
import com.example.my.util.BikingRouteOverlay;
import com.example.my.util.DragFloatActionButton;

import org.litepal.crud.DataSupport;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import me.bakumon.library.adapter.SimpleBulletinAdapter;
import me.bakumon.library.view.BulletinView;

/**
 * Created by Administrator on 2018/2/6.
 */

public class FiveActivity extends AppCompatActivity {
    private RoutePlanSearch mSearch;
    private MapView mMapView = null;
    BikingRouteOverlay overlay;
    DragFloatActionButton btn_click, btn_refresh, btn_searchDB;
    BulletinView bulletin_view;
    Button btn_start, btn_end;
    private long timeusedinsec;
    private boolean isstop = false;
    TextView tv_time;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private MyHandler myHandler;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_five_layout);
        initElement();
        boolean isAllGranted = checkPermissionAllGranted(new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        });
        if (isAllGranted) {
            btn_start.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myHandler.removeMessages(1);
                    myHandler.sendEmptyMessage(1);
                    isstop = false;
                    mLocationClient.start();
                    Toast.makeText(FiveActivity.this, "开始采集坐标", Toast.LENGTH_SHORT).show();
                }
            });
            btn_end.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isstop = true;
                    mLocationClient.stop();
                    Toast.makeText(FiveActivity.this, "停止采集坐标", Toast.LENGTH_SHORT).show();
                }
            });
        }

        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                },
                0);
    }

    private void initElement() {
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(listener);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.bmapView);
        btn_click = (DragFloatActionButton) findViewById(R.id.btn_click);
        btn_refresh = (DragFloatActionButton) findViewById(R.id.btn_refresh);
        btn_searchDB = (DragFloatActionButton) findViewById(R.id.btn_searchDB);

        btn_start = findViewById(R.id.btn_start);
        btn_end = findViewById(R.id.btn_end);
        tv_time = findViewById(R.id.tv_time);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        initLocationClientOption();
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setElement();
            }
        });
        btn_searchDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomwindow(v);
            }
        });
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FiveActivity.this, "正在清除数据", Toast.LENGTH_SHORT).show();
                List<LatLngDB> latLngDBList = DataSupport.findAll(LatLngDB.class);
                for (LatLngDB latLngDB : latLngDBList) {
                    latLngDB.delete();
                }
                int dbSize = DataSupport.findAll(LatLngDB.class).size();
                List<String> list = new ArrayList<>();
                list.add("坐标库当前数据：" + dbSize + " 条");
                list.add("坐标库当前数据：" + dbSize + " 条");
                bulletin_view.setAdapter(new SimpleBulletinAdapter(FiveActivity.this, list));
                Toast.makeText(FiveActivity.this, "清除数据完成", Toast.LENGTH_SHORT).show();
            }
        });

        myHandler = new MyHandler(this);

        int dbSize = DataSupport.findAll(LatLngDB.class).size();
        bulletin_view = findViewById(R.id.bulletin_view);
        List<String> list = new ArrayList<>();
        list.add("坐标库当前数据：" + dbSize + " 条");
        list.add("坐标库当前数据：" + dbSize + " 条");
        bulletin_view.setAdapter(new SimpleBulletinAdapter(this, list));
    }

    private void initLocationClientOption() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效
        option.setScanSpan(1000);
        option.setLocationNotify(true);
        option.setOpenGps(true);
        mLocationClient.setLocOption(option);
    }

    private void setElement() {
        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");
        mSearch.bikingSearch((new BikingRoutePlanOption())
                .from(stNode)
                .to(enNode));
        overlay = new BikingRouteOverlay(mMapView.getMap());
    }

    OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
            Toast.makeText(FiveActivity.this, "未定位到数据3", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {
            Toast.makeText(FiveActivity.this, "未定位到数据4", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {
            Toast.makeText(FiveActivity.this, "未定位到数据5", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            Toast.makeText(FiveActivity.this, "未定位到数据6", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
            Toast.makeText(FiveActivity.this, "未定位到数据7", Toast.LENGTH_SHORT).show();
        }

        public void onGetBikingRouteResult(BikingRouteResult result) {
            if (result != null) {
                List<BikingRouteLine> routeLineList = result.getRouteLines();
                if (routeLineList != null && !routeLineList.isEmpty() && routeLineList.size() != 0) {
//                    Toast.makeText(FiveActivity.this, routeLineList.get(0).getDistance() + "", Toast.LENGTH_SHORT).show();
                    overlay.removeFromMap();
                    overlay.setData(routeLineList.get(0));
                    overlay.addToMap();
                    overlay.zoomToSpan();
                    String instruction = routeLineList.get(0).getAllStep().get(0).getInstructions();
                    Toast.makeText(FiveActivity.this, routeLineList.get(0).getAllStep().size()
                            + "-------:" + instruction, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FiveActivity.this, "未定位到数据2", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(FiveActivity.this, "未定位到数据1", Toast.LENGTH_SHORT).show();
            }
        }
    };


    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation != null) {
                double latitude = bdLocation.getLatitude();    //获取纬度信息
                double longitude = bdLocation.getLongitude();    //获取经度信息
                LatLngDB latLngDB = new LatLngDB();
                latLngDB.setLat(latitude);
                latLngDB.setLng(longitude);
                latLngDB.save();
                Log.i("-----", "(" + latitude + "," + longitude + ")");

            }
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FiveActivity.class);
        context.startActivity(intent);
    }

    private void updateView() {
        timeusedinsec += 1;
        int minute = (int) (timeusedinsec / 60) % 60;
        int second = (int) (timeusedinsec % 60);

        if (minute < 10 && second < 10) {
            tv_time.setText("0" + minute + ":0" + second);
        } else if (minute < 10 && second >= 10) {
            tv_time.setText("0" + minute + ":" + second);
        } else if (minute >= 10 && second < 10) {
            tv_time.setText(minute + ":0" + second);
        } else if (minute >= 10 && second >= 10) {
            tv_time.setText(minute + ":" + second);
        }
    }

    private static class MyHandler extends Handler {
        private WeakReference<FiveActivity> activity;

        public MyHandler(FiveActivity fiveActivity) {
            this.activity = new WeakReference<FiveActivity>(fiveActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FiveActivity fiveActivity = activity.get();
            switch (msg.what) {
                case 1:
                    // 添加更新ui的代码
                    if (!fiveActivity.isstop) {
                        fiveActivity.updateView();
                        fiveActivity.myHandler.sendEmptyMessageDelayed(1, 1000);
                    }
                    break;
                case 0:
                    break;
                case 10:

                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 检查是否有指定的权限
     *
     * @param permissions 权限列表
     * @return 是否有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getApplicationContext().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            boolean isAllGranted = true;
            for (int grant : grantResults) {
                if (grant != PackageManager.PERMISSION_GRANTED) {
                    isAllGranted = false;
                    break;
                }
            }

            if (isAllGranted) {
                btn_start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        myHandler.removeMessages(1);
                        myHandler.sendEmptyMessage(1);
                        isstop = false;
                        mLocationClient.start();
                        Toast.makeText(FiveActivity.this, "开始采集坐标", Toast.LENGTH_SHORT).show();
                    }
                });
                btn_end.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isstop = true;
                        mLocationClient.stop();
                        Toast.makeText(FiveActivity.this, "停止采集坐标", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(this, "未授权 ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 创建popupWindow
     *
     * @param view View
     */
    private void bottomwindow(View view) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.pop_five_activity_bottom_layout, null);
        popupWindow = new PopupWindow(layout,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //添加弹出、弹入的动画
//        popupWindow.setAnimationStyle(R.style.Popupwindow);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
        //添加按键事件监听
        setButtonListeners(layout);
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
//        popupWindow.setOnDismissListener(new poponDismissListener());
//        backgroundAlpha(1f);
    }

    private void setButtonListeners(LinearLayout layout) {
        Button btn_cancel = (Button) layout.findViewById(R.id.btn_cancel);
        Button btn_ok = (Button) layout.findViewById(R.id.btn_ok);
        final EditText et_lat = layout.findViewById(R.id.et_lat);
        final EditText et_lng = layout.findViewById(R.id.et_lng);
        final TextView tv_showResult = layout.findViewById(R.id.tv_showResult);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    popupWindow.dismiss();
                }
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    String lat = et_lat.getText().toString().trim();
                    String lng = et_lng.getText().toString().trim();
                    if (TextUtils.isEmpty(lat) || TextUtils.isEmpty(lng)) {
                        Toast.makeText(FiveActivity.this, "请填写经纬度", Toast.LENGTH_SHORT).show();
                    } else {
                        long startTime = System.currentTimeMillis();
                        Log.i("startTime:", startTime + "");
                        Double resultDistance = 0.0;
                        Double distance;
                        LatLng startPoint = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
                        List<LatLngDB> latLngDBList = DataSupport.findAll(LatLngDB.class);
                        int i = 0;
                        for (LatLngDB latLngDB : latLngDBList) {
                            Log.i("次数:", "" + i);
                            i++;
                            //计算p1、p2两点之间的直线距离，单位：米
                            LatLng endPoint = new LatLng(latLngDB.getLat(), latLngDB.getLng());
                            distance = DistanceUtil.getDistance(startPoint, endPoint);
                            Log.i("距离:", "" + distance);
                            if (resultDistance < distance) {
                                resultDistance = distance;
                            }
                        }
                        long endTime = System.currentTimeMillis();
                        Log.i("endTime:", endTime + "");
                        long timeCha = endTime - startTime;
                        Toast.makeText(FiveActivity.this, "---" + timeCha + "ms", Toast.LENGTH_SHORT).show();
                        int minute = (int) (timeCha / 60) % 60;
                        int second = (int) (timeCha % 60);
                        if (minute < 10 && second < 10) {
                            tv_showResult.setText("用时 0" + minute + ":0" + second + ";最远距离:" + resultDistance);
                        } else if (minute < 10 && second >= 10) {
                            tv_showResult.setText("用时 0" + minute + ":" + second + ";最远距离:" + resultDistance);
                        } else if (minute >= 10 && second < 10) {
                            tv_showResult.setText("用时 " + minute + ":0" + second + ";最远距离:" + resultDistance);
                        } else if (minute >= 10 && second >= 10) {
                            tv_showResult.setText("用时 " + minute + ":" + second + ";最远距离:" + resultDistance);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearch.destroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
        mLocationClient.unRegisterLocationListener(myListener);
    }
}

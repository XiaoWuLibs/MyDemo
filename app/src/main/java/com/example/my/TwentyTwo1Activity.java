package com.example.my;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.my.util.MyProgressDialog;
import com.example.my.util.StringUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TwentyTwo1Activity extends AppCompatActivity {
    private static final String TAG = "TwentyTwo1Activity";
    //打开扫描界面请求码
    private int REQUEST_CODE = 0x01;
    //扫描成功返回码
    private int RESULT_OK = 0xA1;


    EditText ibcon_code;
    Button btn_unbind;
    ListView foundList;
    private boolean is_bind;
    private int pos, type;
    // 消息处理器使用的常量
    private static final int FOUND_DEVICE = 1; // 发现设备
    private static final int START_DISCOVERY = 2; // 开始查找设备
    private static final int FINISH_DISCOVERY = 3; // 结束查找设备
    private static final int CONNECT_FAIL = 4; // 连接失败
    private static final int CONNECT_SUCCEED_P = 5; // 主动连接成功
    private static final int CONNECT_SUCCEED_N = 6; // 收到连接成功
    private static final int RECEIVE_MSG = 7; // 收到消息
    private static final int SEND_MSG = 8; // 发送消息
    public static final int CONNECT_INTERRUPT = 101; //连接中断
    private String[] permissions = {Manifest.permission.BLUETOOTH, Manifest.permission.ACCESS_COARSE_LOCATION};
    private final int REQUEST_OPEN_BT = 101;
    public static boolean connFlag = false;
    private List<BluetoothDevice> foundDevices;
    private BluetoothAdapter btAdapter;
    private BroadcastReceiver mReceiver;
    private Context context;
    private MyHandler myHandler;
    private boolean isAllGranted;
    MyProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twenty_two1);
        context = this;
        isAllGranted = checkPermissionAllGranted(new String[]{
                Manifest.permission.BLUETOOTH
        });
        initData();
        setData();

    }

    private void initData() {
        ibcon_code = findViewById(R.id.ibcon_code);
        btn_unbind = findViewById(R.id.btn_unbind);
        foundList = findViewById(R.id.foundList);
        myHandler = new MyHandler(TwentyTwo1Activity.this);
        progressDialog = new MyProgressDialog(this, null);
        btn_unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(ibcon_code.getText().toString())) {
                    TwentyTwoActivity.startActivity(TwentyTwo1Activity.this, ibcon_code.getText().toString().trim());
                } else {
                    Toast.makeText(context, "请输入内容", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData() {
        if (isAllGranted) {
            initBluetooth();
            getBluetoothList();
        } else {
            ActivityCompat.requestPermissions(TwentyTwo1Activity.this, new String[]{
                            Manifest.permission.BLUETOOTH
                    },
                    0);
        }
    }

    /**
     * 初始化蓝牙模块
     */
    private void initBluetooth() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter == null) {
            // 设备不支持蓝牙
            Toast.makeText(context, "此设备不支持蓝牙", Toast.LENGTH_SHORT).show();
        }
        // 检查蓝牙是否可用
        if (!btAdapter.isEnabled()) {
            Intent enableBt = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBt, REQUEST_OPEN_BT);
        }
    }

    private void getBluetoothList() {
        progressDialog.showLoading(context, null);
        btAdapter.cancelDiscovery();
        btAdapter.startDiscovery();
        // 将已配对的蓝牙设备显示到第一个ListView中
//        Set<BluetoothDevice> deviceSet = btAdapter.getBondedDevices();
//        final List<BluetoothDevice> bondedDevices = new ArrayList<BluetoothDevice>();
//        if (deviceSet.size() > 0) {
//            for (Iterator<BluetoothDevice> it = deviceSet.iterator();
//                 it.hasNext(); ) {
//                BluetoothDevice device = (BluetoothDevice) it.next();
//                bondedDevices.add(device);
//            }
//        }

        // 将找到的蓝牙设备显示到第二个ListView中
        foundDevices = new ArrayList<BluetoothDevice>();
        foundList.setAdapter(new com.example.my.adapter.BluetoothAdapter(
                context, foundDevices));
        foundList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BluetoothDevice bean = (BluetoothDevice) parent.getItemAtPosition(position);
                ibcon_code.setText(StringUtils.removeAllSign(bean.getAddress(), ":"));
            }
        });
        registerBroadReceiver();
    }

    /**
     * 注册广播接收
     */
    private void registerBroadReceiver() {
        // 注册广播接收器
        mReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context arg0, Intent arg1) {
                // TODO Auto-generated method stub
                String actionStr = arg1.getAction();
                Log.e("actionStr", actionStr);
                if (actionStr.equals(BluetoothDevice.ACTION_FOUND)) {
                    BluetoothDevice device = arg1
                            .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    if (!isFoundDevices(device)) {
                        foundDevices.add(device);
                    }
                    myHandler.sendEmptyMessage(FOUND_DEVICE);
                } else if (actionStr
                        .equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED)) {
                    myHandler.sendEmptyMessage(START_DISCOVERY);
                } else if (actionStr
                        .equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED)) {
                    myHandler.sendEmptyMessage(FINISH_DISCOVERY);
                }
            }

        };
        IntentFilter filter1 = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        IntentFilter filter2 = new IntentFilter(
                BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        IntentFilter filter3 = new IntentFilter(
                BluetoothAdapter.ACTION_DISCOVERY_FINISHED);

        registerReceiver(mReceiver, filter1);
        registerReceiver(mReceiver, filter2);
        registerReceiver(mReceiver, filter3);
    }

    /**
     * 是否是同一台设备
     *
     * @param device
     * @return
     * @author tom
     * @Date 2016-10-23
     */
    private boolean isFoundDevices(BluetoothDevice device) {

        if (foundDevices != null && !foundDevices.isEmpty()) {
            for (BluetoothDevice devices : foundDevices) {
                if (device.getAddress().equals(devices.getAddress())) {
                    return true;
                }
            }
        }
        return false;
    }

    static class MyHandler extends Handler {
        WeakReference<TwentyTwo1Activity> mWeakReference;

        public MyHandler(TwentyTwo1Activity activity) {
            mWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            TwentyTwo1Activity activity = mWeakReference.get();
            switch (msg.what) {
                case FOUND_DEVICE:
                    activity.foundList.setAdapter(new com.example.my.adapter.BluetoothAdapter(
                            activity, activity.foundDevices));
                    activity.progressDialog.stopLoading();
                    break;
                case START_DISCOVERY:
                    //discoveryPro.setVisibility(View.VISIBLE);
                    break;
                case FINISH_DISCOVERY:
                    //discoveryPro.setVisibility(View.GONE);
                    break;
                case CONNECT_FAIL:
                    activity.connFlag = false;
                    Toast.makeText(activity, "连接失败", Toast.LENGTH_SHORT).show();
                    break;
                case CONNECT_SUCCEED_P:
                case CONNECT_SUCCEED_N:
                    break;
                case CONNECT_INTERRUPT:
                    Toast.makeText(activity, "连接已断开,请重新连接", Toast.LENGTH_SHORT).show();
                    activity.connFlag = false;
                    break;
            }
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyTwo1Activity.class);
        context.startActivity(intent);
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
                getBluetoothList();
            } else {
                Toast.makeText(this, "未授权 ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
    }
}

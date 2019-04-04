package com.example.my;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/9/12.
 */

public class TwentyTwoActivity extends AppCompatActivity {
    private static final String TAG = "TwentyTwoActivity";
    private static String IpAddress = "114.115.172.251";//正式ip
    //    private static String IpAddress = "117.78.41.183";//正式ip
    private static String ip = "192.168.1.103";//测试ip
    private static int Port = 12022;
    private EditText editText;
    Socket socket = null;
    Timer timer;
    TimerTask task;
    BufferedReader mReader;
    BufferedWriter mWriter;
    boolean isStartRecieveMsg;
    Socket socket1;
    private OutputStream outputStream;
    //                    原始数据
    String message = "3120334130313230303030303032303130313030303231303030333230303043303031323342364131423345383034463030303030303236303031323342364131423346334433443030303030303236303031323342364131423345383034373030303030303236303031323342364131423346334534383030303030303236303031323342364131423345464134333030303030303236303031323342364131423346334534353030303030303236303031323342364131423345383034453030303030303236303031323342364131423345383034373030303030303237303031323342364131423346334433383030303030303237303031323342364131423345464134353030303030303237303031323342364131423346334534393030303030303237303031323342364131423345464134383030303030303237384332410D0A\n";
    String mac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final boolean isAllGranted = checkPermissionAllGranted(new String[]{
                Manifest.permission.INTERNET
        });
        setContentView(R.layout.activity_twenty_two);
        mac = getIntent().getStringExtra("mac");
        Button button = findViewById(R.id.btn_click);
        editText = findViewById(R.id.editText);
        editText.setText(mac);
        initSocket();
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                send();
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllGranted) {
                    //发送消息

                    timer.schedule(task, 1000, 2000);
                } else {
                    ActivityCompat.requestPermissions(TwentyTwoActivity.this, new String[]{
                                    Manifest.permission.INTERNET
                            },
                            0);
                }
            }
        });

    }

    /**
     * 发送数据
     */
    private void send() {
        try {
//            outputStream.write(StringtoBytes(message));
            outputStream.write(mac.getBytes());
            outputStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化socket
     */
    private void initSocket() {
        new Thread() {
            @Override
            public void run() {
                try {
                    if (socket1 == null) {

                        socket1 = new Socket(IpAddress, Port);
                        socket1.setSoTimeout(10 * 1000);
                    }

                    // 建立连接后获得输出流
                    outputStream = socket1.getOutputStream();
//                    String message = "3A012000000201010002100032000C00123B6A1B3E804F0000002600123B6A1B3F3D3D0000002600123B6A1B3E80470000002600123B6A1B3F3E480000002600123B6A1B3EFA430000002600123B6A1B3F3E450000002600123B6A1B3E804E0000002600123B6A1B3E80470000002700123B6A1B3F3D380000002700123B6A1B3EFA450000002700123B6A1B3F3E490000002700123B6A1B3EFA48000000278C2A\n";
//                    String message = "3120334130313230303030303032303130313030303231303030333230303043303031323342364131423345383034463030303030303236303031323342364131423346334433443030303030303236303031323342364131423345383034373030303030303236303031323342364131423346334534383030303030303236303031323342364131423345464134333030303030303236303031323342364131423346334534353030303030303236303031323342364131423345383034453030303030303236303031323342364131423345383034373030303030303237303031323342364131423346334433383030303030303237303031323342364131423345464134353030303030303237303031323342364131423346334534393030303030303237303031323342364131423345464134383030303030303237384332410D0A\n";
//                    String message = "www\n";

//                    mWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
//                    mWriter.write(message);
//                    mWriter.flush();
//                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
//                    dataOutputStream.writeUTF(message);

//                    String str = new String(, "utf-8");
//                    Log.i("---", str2HexStr(message));
//                    socket1.getOutputStream().write(1);
//                    socket1.getOutputStream().flush();
                    //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
//                    socket1.shutdownOutput();


                    InputStream inputStream = socket1.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len;
                    StringBuilder sb = new StringBuilder();
                    while ((len = inputStream.read(bytes)) != -1) {
                        //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                        sb.append(new String(bytes, 0, len, "UTF-8"));
                    }
                    System.out.println("get message from server: " + sb);
//
//                    inputStream.close();
//                    outputStream.close();
//                    socket1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 四舍五入算法
     *
     * @param str 输入值
     * @return 返回值
     */
    private String roundHalfUp(String str) {
        BigDecimal decimal = new BigDecimal(str);
        double num = decimal.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
        DecimalFormat format = new DecimalFormat("##0");
        return format.format(num);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwentyTwoActivity.class);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, String mac) {
        Intent intent = new Intent();
        intent.putExtra("mac", mac);
        intent.setClass(context, TwentyTwoActivity.class);
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
                timer.schedule(task, 0, 2000);
            } else {
                Toast.makeText(this, "未授权 ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 字符串转换成十六进制字符串
     *
     * @param str 待转换的ASCII字符串
     * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
     */
    public static String str2HexStr(String str) {

        char[] chars = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;

        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public byte[] StringtoBytes(String data) {

        if (data == null || data.equals("")) {

            return null;

        }

        data = data.toUpperCase();

        char[] datachar = data.toCharArray();

        byte[] getbytes = new byte[data.length() / 2];

        for (int i = 0; i < data.length() / 2; i++) {
            int pos = i * 2;

            getbytes[i] = (byte) (charToByte(datachar[pos]) << 4 | charToByte(datachar[pos + 1]));

        }

        return getbytes;

    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

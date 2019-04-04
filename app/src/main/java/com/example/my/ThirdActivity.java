package com.example.my;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.util.OtherUtil;
import com.example.my.util.RunText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/2/6.
 */

public class ThirdActivity extends AppCompatActivity {
    BluetoothAdapter bluetoothAdapter;
    private static final String TAG = "TEST";
    private EditText et_content;
    private Button btn_to;
    private Button btn;
    private AutoCompleteTextView search_content;
    private String[] str = {"111", "123", "124", "233", "214", "225", "344", "367", "599", "633", "588", "589", "598", "634", "645"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_layout);
        TextView textView = new TextView(this);
        Button button = new Button(this);
        EditText editText = new EditText(this);
        Spinner spinner = new Spinner(this);
        et_content = findViewById(R.id.et_content);
        btn_to = findViewById(R.id.btn_to);
        btn = findViewById(R.id.btn);
        search_content = findViewById(R.id.search_content);

        String content = download();
        et_content.setText(content);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "设备无蓝牙功能", Toast.LENGTH_SHORT).show();
            return;
        }

        Button btn_click = findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bluetoothAdapter.isEnabled()) {
                    Toast.makeText(ThirdActivity.this, "已连接蓝牙", Toast.LENGTH_SHORT).show();
                } else {
//                    boolean rd = bluetoothAdapter.enable();
                    Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(intent, 0);
//                    Toast.makeText(ThirdActivity.this, "连接蓝牙：" + rd, Toast.LENGTH_SHORT).show();
                }


            }
        });
        btn_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OtherUtil.showToast(ThirdActivity.this, "showToast");
            }
        });

        RunText runtext = findViewById(R.id.runtext);
        List<String> list = new ArrayList<>();
        list.add("北京");
        list.add("故宫");
        list.add("中南海");
        runtext.setContent(list);
        runtext.initClick();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Log.i("本月第一天：", format.format(calendar.getTime()));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.getActualMaximum(Calendar.DAY_OF_MONTH));
        Log.i("本月最后一天：", format.format(calendar2.getTime()));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ThirdActivity.this
                , android.R.layout.simple_list_item_1, str);
        search_content.setAdapter(adapter);
    }

    /**
     * 存储信息
     *
     * @return boolean
     */
    private boolean save(String content) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            fos = openFileOutput("data", Context.MODE_PRIVATE);
            osw = new OutputStreamWriter(fos);
            bw = new BufferedWriter(osw);

            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (osw != null) {
                    osw.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return true;
        }
    }

    /**
     * 获取信息
     *
     * @return String
     */
    private String download() {
        StringBuffer sb = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            sb = new StringBuffer();
            fis = openFileInput("data");
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (isr != null) {
                    isr.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb != null ? sb.toString() : "";
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "请求失败", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "蓝牙已打开", Toast.LENGTH_SHORT).show();


            Log.i(TAG, "onActivityResult: " + bluetoothAdapter.getName() + "/" + bluetoothAdapter.getAddress());
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (save(et_content.getText().toString().trim())) {
            Toast.makeText(this, "已缓存", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "未缓存", Toast.LENGTH_SHORT).show();
        }
    }
}

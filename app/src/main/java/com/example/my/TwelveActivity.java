package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Administrator on 2018/5/16.
 */

public class TwelveActivity extends AppCompatActivity {
    AutoCompleteTextView et_auto;
    private Button btn_camera;
    private String path;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twelve_layout);
        btn_camera = findViewById(R.id.btn_camera);
        String[] strings = {"111", "112", "114", "221", "222", "223"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        et_auto = findViewById(R.id.et_auto);
        et_auto.setAdapter(adapter);

        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            path = Environment.getExternalStorageDirectory() + "/" + getAppName();
            File file = new File(path);
            if (!file.exists()) {
                if (file.mkdirs()) {
                    Toast.makeText(this, "创建目录失败", Toast.LENGTH_SHORT).show();
                }
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            File file1 = new File(path, "/图片.jpg");
            Uri uri = Uri.fromFile(file1);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, 1);
        } else {
            Toast.makeText(this, "未找到SD卡", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * 获取应用名
     *
     * @return 应用名
     */
    private String getAppName() {
        PackageManager packageManager = getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageManager.getApplicationLabel(applicationInfo).toString();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            File file = new File(path + "/图片.jpg");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            Bitmap bmp = BitmapFactory.decodeFile(file.getAbsolutePath(), options);

        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, TwelveActivity.class);
        context.startActivity(intent);
    }
}

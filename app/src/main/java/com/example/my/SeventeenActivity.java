package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.my.bean.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class SeventeenActivity extends AppCompatActivity {
    private static final String TAG = "SeventeenActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventeen_layout);
        initElement();
    }

    /**
     * 初始化控件
     */
    private void initElement() {
        setElement();
    }

    /**
     * 配置控件
     */
    private void setElement() {
//        List<PhoneInfo> phoneInfoList = getPhoneNumberFromMobile();
//        for (PhoneInfo phoneInfo : phoneInfoList) {
//            Log.i(TAG, phoneInfo.getName() + ":" + phoneInfo.getNumber());
//        }
    }

    private List<PhoneInfo> getPhoneNumberFromMobile() {
        List<PhoneInfo> phoneInfos = new ArrayList<>();

        Cursor cursor = getContentResolver()
                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                        , new String[]{"display_name", "sort_key", "contact_id", "data1"}
                        , null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            int id = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            String sortKey = getSortKey(cursor.getString(1));
            PhoneInfo phoneInfo = new PhoneInfo(name, number, sortKey, id);
            phoneInfos.add(phoneInfo);
        }
        cursor.close();
        return phoneInfos;
    }

    private static String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        } else {
            return "#";
        }
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SeventeenActivity.class);
        context.startActivity(intent);
    }
}

package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.my.db.BigData;
import com.example.my.db.LatLngDB;

import org.litepal.crud.DataSupport;

import java.util.List;

import static com.example.my.util.DBUtils.selectBigData;
import static com.example.my.util.DBUtils.updateHotSearchDB;

/**
 * Created by Administrator on 2018/3/26.
 */

public class SixActivity extends AppCompatActivity {
    private com.example.my.util.DragFloatActionButton btn_refresh;
    private Spinner sp_spinner;
    private String item;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_six_layout);
        btn_refresh = findViewById(R.id.btn_refresh);
        sp_spinner = findViewById(R.id.sp_spinner);
        String[] strings = {"张三", "李四", "王五", "赵六", "钱七"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this
                , android.R.layout.simple_expandable_list_item_1, strings);
        sp_spinner.setAdapter(adapter);
        sp_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                item = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //搜索热搜词
        List<BigData> bigDataList = selectBigData();

        if (bigDataList != null && !bigDataList.isEmpty() && bigDataList.size() != 0) {
            BigData bigData = bigDataList.get(0);
            String content = bigData.getContent();

            if (!TextUtils.isEmpty(content)) {
                int count = sp_spinner.getAdapter().getCount();
                for (int i = 0; i < count; i++) {
                    if (content.equals(sp_spinner.getItemAtPosition(i))) {
                        sp_spinner.setSelection(i);
                    }
                }
            }
        }

        //按钮点击事件
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //更新热搜数据库
                updateHotSearchDB(item);
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SixActivity.class);
        context.startActivity(intent);
    }
}

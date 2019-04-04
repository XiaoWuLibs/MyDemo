package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.db.Address;

/**
 * Created by Administrator on 2018/1/22.
 * 添加地址
 */

public class AddAddressActivity extends AppCompatActivity {
    private Button btn_submit;
    private EditText et_name, et_tel, et_city, et_detailAddress;
    private CheckBox checkbox;
    private String whichIntent;
    private boolean saveAddressToLocal = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        whichIntent = getIntent().getStringExtra("whichIntent");
        initElement();
        setElement();
    }

    /**
     * 初始化控件
     */
    private void initElement() {
        btn_submit = findViewById(R.id.btn_submit);
        et_name = findViewById(R.id.et_name);
        et_tel = findViewById(R.id.et_tel);
        et_city = findViewById(R.id.et_city);
        et_detailAddress = findViewById(R.id.et_detailAddress);
        checkbox = findViewById(R.id.checkbox);
    }

    /**
     * 配置控件
     */
    private void setElement() {
        btn_submit.setOnClickListener(onClickListener);
        checkbox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    /**
     * 按钮点击事件
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = et_name.getText().toString().trim();
            String tel = et_tel.getText().toString().trim();
            String city = et_city.getText().toString().trim();
            String detailAddress = et_detailAddress.getText().toString().trim();
            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(tel)
                    || TextUtils.isEmpty(city) || TextUtils.isEmpty(detailAddress)) {//如果四项有一项是空的
                Toast.makeText(AddAddressActivity.this, "将内容填写完整", Toast.LENGTH_SHORT).show();
            } else {
                if (saveAddressToLocal) {
                    //保存至地址簿数据库
                    Address address = new Address();
                    address.setName(name);
                    address.setTel(tel);
                    address.setCity(city);
                    address.setDetailAddress(detailAddress);
                    address.save();
                }

                Intent intent = new Intent();
                intent.putExtra("whichIntent", whichIntent);
                intent.putExtra("name", name);
                intent.putExtra("tel", tel);
                intent.putExtra("city", city);
                intent.putExtra("detailAddress", detailAddress);
                setResult(RESULT_OK, intent);
                Toast.makeText(AddAddressActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };
    /**
     * checkBox选择事件
     */
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {//如果勾选上保存至地址簿
                saveAddressToLocal = true;
            } else {//如果未勾选上不保存至地址簿
                saveAddressToLocal = false;
            }
        }
    };

    /**
     * 启动AddAddressActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddAddressActivity.class);
        context.startActivity(intent);
    }
}

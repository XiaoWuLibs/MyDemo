package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.my.BaseActivity;
import com.example.my.R;
import com.example.my.adapter.AddressAdapter;
import com.example.my.db.Address;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/1/22.
 * 地址簿列表页
 */

public class AddressBooksActivity extends BaseActivity {
    private ListView lv_addressBooks;
    private Button btn_addAddress;
    private TextView tv_showResult;
    private List<Address> addressList;
    private String fromWhich;
    public static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_books);
        fromWhich = getIntent().getStringExtra("fromWhich");
        initElement();
        setElement();
    }

    /**
     * 初始化控件
     */
    private void initElement() {
        lv_addressBooks = findViewById(R.id.lv_addressBooks);
        btn_addAddress = findViewById(R.id.btn_addAddress);
        tv_showResult = findViewById(R.id.tv_showResult);
        btn_addAddress.setOnClickListener(onClickListener);
        //查询数据库中地址簿
        queryAddressBooks();
    }

    /**
     * 配置控件
     */
    private void setElement() {
        //关闭加载框
        closeLoadingDialog();
        if (addressList != null && addressList.size() != 0) {
            tv_showResult.setVisibility(View.GONE);
            lv_addressBooks.setVisibility(View.VISIBLE);
            AddressAdapter addressAdapter = new AddressAdapter(this, addressList);
            lv_addressBooks.setAdapter(addressAdapter);
            lv_addressBooks.setOnItemClickListener(onItemClickListener);
        } else {
            tv_showResult.setVisibility(View.VISIBLE);
            lv_addressBooks.setVisibility(View.GONE);
        }
    }

    /**
     * 查询数据库中地址簿
     */
    private void queryAddressBooks() {
        //显示加载框
        showLoadingDialog();
        addressList = DataSupport.findAll(Address.class);
    }

    /**
     * 按钮点击事件
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_addAddress:
                    Intent intent = new Intent();
                    intent.setClass(AddressBooksActivity.this, AddAddressActivity.class);
                    startActivityForResult(intent, REQUEST_CODE);
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * listview item点击事件
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Address address = (Address) parent.getItemAtPosition(position);
            Intent intent = new Intent();
            if (TextUtils.isEmpty(fromWhich)) {
                intent.putExtra("whichIntent", "");
                intent.putExtra("name", "");
                intent.putExtra("tel", "");
                intent.putExtra("city", "");
                intent.putExtra("detailAddress", "");
            } else {
                intent.putExtra("whichIntent", fromWhich);
                intent.putExtra("name", address.getName());
                intent.putExtra("tel", address.getTel());
                intent.putExtra("city", address.getCity());
                intent.putExtra("detailAddress", address.getDetailAddress());
            }
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            //查询数据库中地址簿
            queryAddressBooks();
            setElement();
        }
    }


    /**
     * 启动AddressBooksActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddressBooksActivity.class);
        context.startActivity(intent);
    }
}

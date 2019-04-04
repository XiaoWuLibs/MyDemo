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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.db.Books;

import java.util.Arrays;
import java.util.List;

import cn.addapp.pickers.listeners.OnItemPickListener;
import cn.addapp.pickers.listeners.OnSingleWheelListener;
import cn.addapp.pickers.picker.SinglePicker;

/**
 * Created by Administrator on 2018/1/22.
 * 我要下单
 */

public class AddOrderActivity extends AppCompatActivity {
    private TextView tv_serviceText;
    private LinearLayout ll_goodName;
    private TextView tv_goodName;
    private EditText et_bookName, et_bookAuther, et_bookDecreption, et_bookPrice;
    private Button btn_submit;
    private CheckBox checkbox;
    public static final int REQUEST_CODE = 10;
    Books books;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order_parent);
        initElement();
        setElement();
    }

    /**
     * 初始化控件
     */
    private void initElement() {
        btn_submit = findViewById(R.id.btn_submit);
        ll_goodName = findViewById(R.id.ll_goodName);
        tv_goodName = findViewById(R.id.tv_goodName);
        checkbox = findViewById(R.id.checkbox);
        tv_serviceText = findViewById(R.id.tv_serviceText);
        et_bookName = findViewById(R.id.et_bookName);
        et_bookAuther = findViewById(R.id.et_bookAuther);
        et_bookPrice = findViewById(R.id.et_bookPrice);
        et_bookDecreption = findViewById(R.id.et_bookDecreption);
    }

    /**
     * 配置控件
     */
    private void setElement() {
        books = new Books();
        btn_submit.setOnClickListener(onClickListener);
        ll_goodName.setOnClickListener(onClickListener);
        tv_serviceText.setOnClickListener(onClickListener);
        checkbox.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    /**
     * 控件点击事件
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_goodName:
                    showPickerOfBottom();
                    break;
                case R.id.ll_fromWhere:
                    Intent fromWhereIntent = new Intent();
                    fromWhereIntent.putExtra("whichIntent", "fromWhereIntent");
                    fromWhereIntent.setClass(AddOrderActivity.this, AddAddressActivity.class);
                    startActivityForResult(fromWhereIntent, REQUEST_CODE);
                    break;
                case R.id.ll_toWhere:
                    Intent toWhereIntent = new Intent();
                    toWhereIntent.putExtra("whichIntent", "toWhereIntent");
                    toWhereIntent.setClass(AddOrderActivity.this, AddAddressActivity.class);
                    startActivityForResult(toWhereIntent, REQUEST_CODE);
                    break;
                case R.id.btn_submit:
                    if (addBook()) {
                        Toast.makeText(AddOrderActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddOrderActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.tv_fromWhereAddressBook:
                    Intent fromWhereAddressBookIntent = new Intent();
                    fromWhereAddressBookIntent.putExtra("fromWhich", "fromWhereIntent");
                    fromWhereAddressBookIntent.setClass(AddOrderActivity.this, AddressBooksActivity.class);
                    startActivityForResult(fromWhereAddressBookIntent, REQUEST_CODE);
                    break;
                case R.id.tv_toWhereAddressBook:
                    Intent toWhereAddressBookIntent = new Intent();
                    toWhereAddressBookIntent.putExtra("fromWhich", "toWhereIntent");
                    toWhereAddressBookIntent.setClass(AddOrderActivity.this, AddressBooksActivity.class);
                    startActivityForResult(toWhereAddressBookIntent, REQUEST_CODE);
                    break;
                case R.id.tv_serviceText:
                    AppTextActivity.startActivity(AddOrderActivity.this);
                    break;
                default:
                    break;
            }
        }
    };
    /**
     * checkBox选择事件
     */
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        btn_submit.setBackgroundColor(getResources().getColor(R.color.light_blue));
                        btn_submit.setClickable(true);
                    } else {
                        btn_submit.setBackgroundColor(getResources().getColor(R.color.gray));
                        btn_submit.setClickable(false);
                    }
                }
            };

    /**
     * 显示选择物品类型控件
     */
    public void showPickerOfBottom() {
        String[] stringArray = {"文学类", "言情类", "动漫类", "学习类"};
        List<String> list = Arrays.asList(stringArray);
        SinglePicker<String> picker = new SinglePicker<>(this, list);
        picker.setTitleText("选择图书类型");
        picker.setTitleTextColor(getResources().getColor(R.color.black));
        picker.setCanLoop(false);//不禁用循环
        picker.setLineVisible(true);
        picker.setShadowVisible(true);
        picker.setTextSize(18);
        picker.setSelectedIndex(0);
        picker.setWheelModeEnable(true);
        //启用权重 setWeightWidth 才起作用
//        picker.setLabel("分");
        picker.setWeightEnable(true);
        picker.setWeightWidth(1);
        picker.setSelectedTextColor(0xFF279BAA);//前四位值是透明度
        picker.setUnSelectedTextColor(0xFF999999);
        picker.setOnSingleWheelListener(new OnSingleWheelListener() {
            //            滑动实时toast
            @Override
            public void onWheeled(int index, String item) {

            }
        });
        picker.setOnItemPickListener(new OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int position, String item) {
                tv_goodName.setText(item);
                books.setBookType(item);
            }
        });
        picker.show();
    }

    /**
     * 添加图书
     */
    private boolean addBook() {
        try {
            books.setBookName(et_bookName.getText().toString().trim());
            books.setBookAuther(et_bookAuther.getText().toString().trim());
            books.setBookPrice(et_bookPrice.getText().toString().trim());
            books.setBookDecreption(et_bookDecreption.getText().toString().trim());
            books.save();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            String whichIntent = data.getStringExtra("whichIntent");
            String name = data.getStringExtra("name");
            String tel = data.getStringExtra("tel");
            String city = data.getStringExtra("city");
            String detailAddress = data.getStringExtra("detailAddress");
            if (!TextUtils.isEmpty(whichIntent) && whichIntent.equals("fromWhereIntent")) {//寄件人信息

            } else if (!TextUtils.isEmpty(whichIntent) && whichIntent.equals("toWhereIntent")) {//收寄件人信息

            }
        }
    }

    /**
     * 启动AddOrderActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, AddOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

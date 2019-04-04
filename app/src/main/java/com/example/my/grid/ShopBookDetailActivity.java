package com.example.my.grid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.db.Books;
import com.example.my.db.ShopCar;

/**
 * Created by Administrator on 2018/1/22.
 * 购物车图书详情
 */

public class ShopBookDetailActivity extends AppCompatActivity {
    private TextView tv_ziying, tv_bookName, tv_bookDetail, tv_bookPrice, tv_baoyou, tv_author, tv_type;
    private Button btn_buy, btn_addToShopCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_book_detail);
        ShopCar shopCar = getIntent().getParcelableExtra("ShopCar");
        initElement();
        if (shopCar != null) {
            setElement(shopCar);
        }
    }

    private void initElement() {
        tv_ziying = findViewById(R.id.tv_ziying);
        tv_bookName = findViewById(R.id.tv_bookName);
        tv_bookDetail = findViewById(R.id.tv_bookDetail);
        tv_bookPrice = findViewById(R.id.tv_bookPrice);
        tv_baoyou = findViewById(R.id.tv_baoyou);
        tv_author = findViewById(R.id.tv_author);
        tv_type = findViewById(R.id.tv_type);
        btn_buy = findViewById(R.id.btn_buy);
        btn_addToShopCar = findViewById(R.id.btn_addToShopCar);
    }

    private void setElement(ShopCar shopCar) {
        tv_bookName.setText(shopCar.getBookName());
        tv_bookDetail.setText(shopCar.getBookDetail());
        tv_bookPrice.setText(String.format("￥%s", shopCar.getBookPrice()));
        tv_author.setText(shopCar.getBookAuthor());
        tv_type.setText(shopCar.getBookType());
    }

    /**
     * 启动ShopBookDetailActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context, ShopCar shopCar) {
        Intent intent = new Intent();
        intent.putExtra("ShopCar", shopCar);
        intent.setClass(context, ShopBookDetailActivity.class);
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

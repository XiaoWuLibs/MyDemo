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

/**
 * Created by Administrator on 2018/1/22.
 * 图书详情
 */

public class BookDetailActivity extends AppCompatActivity {
    private TextView tv_ziying, tv_bookName, tv_bookDetail, tv_bookPrice, tv_baoyou, tv_author, tv_type;
    private Button btn_buy, btn_addToShopCar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        Books books = getIntent().getParcelableExtra("Books");
        initElement();
        if (books != null) {
            setElement(books);
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

    private void setElement(Books books) {
        tv_bookName.setText(books.getBookName());
        tv_bookDetail.setText(books.getBookDecreption());
        tv_bookPrice.setText(String.format("￥%s", books.getBookPrice()));
        tv_author.setText(books.getBookAuther());
        tv_type.setText(books.getBookType());
    }

    /**
     * 启动BookDetailActivity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context, Books books) {
        Intent intent = new Intent();
        intent.putExtra("Books", books);
        intent.setClass(context, BookDetailActivity.class);
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

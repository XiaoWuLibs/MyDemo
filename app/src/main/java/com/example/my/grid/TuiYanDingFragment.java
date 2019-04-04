package com.example.my.grid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.R;

/**
 * Created by Administrator on 2018/2/1.
 */

public class TuiYanDingFragment extends Fragment {
    private TextView tv_showAll, tv_show90, tv_show7090, tv_show070, tv_kuanLiang, tv_shuLiang, tv_price;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tuiyanding, null);
        tv_showAll = view.findViewById(R.id.tv_showAll);
        tv_show90 = view.findViewById(R.id.tv_show90);
        tv_show7090 = view.findViewById(R.id.tv_show7090);
        tv_show070 = view.findViewById(R.id.tv_show070);
        tv_kuanLiang = view.findViewById(R.id.tv_kuanLiang);
        tv_shuLiang = view.findViewById(R.id.tv_shuLiang);
        tv_price = view.findViewById(R.id.tv_price);

        tv_showAll.setOnClickListener(onClickListener);
        tv_show90.setOnClickListener(onClickListener);
        tv_show7090.setOnClickListener(onClickListener);
        tv_show070.setOnClickListener(onClickListener);
        tv_kuanLiang.setOnClickListener(onClickListener);
        tv_shuLiang.setOnClickListener(onClickListener);
        tv_price.setOnClickListener(onClickListener);
        tv_showAll.setTextColor(Color.RED);
        tv_show90.setTextColor(Color.BLACK);
        tv_show7090.setTextColor(Color.BLACK);
        tv_show070.setTextColor(Color.BLACK);
        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_showAll:
                    tv_showAll.setTextColor(Color.RED);
                    tv_show90.setTextColor(Color.BLACK);
                    tv_show7090.setTextColor(Color.BLACK);
                    tv_show070.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_show90:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_show90.setTextColor(Color.RED);
                    tv_show7090.setTextColor(Color.BLACK);
                    tv_show070.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_show7090:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_show90.setTextColor(Color.BLACK);
                    tv_show7090.setTextColor(Color.RED);
                    tv_show070.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_show070:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_show90.setTextColor(Color.BLACK);
                    tv_show7090.setTextColor(Color.BLACK);
                    tv_show070.setTextColor(Color.RED);
                    break;

                case R.id.tv_kuanLiang:

                    break;
                case R.id.tv_shuLiang:

                    break;
                case R.id.tv_price:

                    break;
                default:
                    break;
            }
        }
    };
}

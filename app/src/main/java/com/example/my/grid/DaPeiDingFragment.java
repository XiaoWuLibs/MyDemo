package com.example.my.grid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;

/**
 * Created by Administrator on 2018/2/1.
 */

public class DaPeiDingFragment extends Fragment {
    private TextView  tv_showAll, tv_showHas, tv_showNo, tv_weiWanQuan, tv_kuanLiang, tv_shuLiang, tv_price;
    private ImageView iv_paiLieLine, iv_paiLieTian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dapeiding, null);
        tv_showAll = view.findViewById(R.id.tv_showAll);
        tv_showHas = view.findViewById(R.id.tv_showHas);
        tv_showNo = view.findViewById(R.id.tv_showNo);
        tv_weiWanQuan = view.findViewById(R.id.tv_weiWanQuan);
        tv_kuanLiang = view.findViewById(R.id.tv_kuanLiang);
        tv_shuLiang = view.findViewById(R.id.tv_shuLiang);
        tv_price = view.findViewById(R.id.tv_price);
        iv_paiLieLine = view.findViewById(R.id.iv_paiLieLine);
        iv_paiLieTian = view.findViewById(R.id.iv_paiLieTian);

        tv_showAll.setOnClickListener(onClickListener);
        tv_showHas.setOnClickListener(onClickListener);
        tv_showNo.setOnClickListener(onClickListener);
        tv_weiWanQuan.setOnClickListener(onClickListener);
        tv_kuanLiang.setOnClickListener(onClickListener);
        tv_shuLiang.setOnClickListener(onClickListener);
        tv_price.setOnClickListener(onClickListener);
        iv_paiLieLine.setOnClickListener(onClickListener);
        iv_paiLieTian.setOnClickListener(onClickListener);
        tv_showAll.setTextColor(Color.RED);
        tv_showHas.setTextColor(Color.BLACK);
        tv_showNo.setTextColor(Color.BLACK);
        tv_weiWanQuan.setTextColor(Color.BLACK);
        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_showAll:
                    tv_showAll.setTextColor(Color.RED);
                    tv_showHas.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_weiWanQuan.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_showHas:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.RED);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_weiWanQuan.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_showNo:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.RED);
                    tv_weiWanQuan.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_weiWanQuan:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_weiWanQuan.setTextColor(Color.RED);
                    break;

                case R.id.tv_kuanLiang:

                    break;
                case R.id.tv_shuLiang:

                    break;
                case R.id.tv_price:

                    break;
                case R.id.iv_paiLieLine:

                    break;
                case R.id.iv_paiLieTian:

                    break;
                default:
                    break;
            }
        }
    };
}

package com.example.my.grid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.my.R;

/**
 * Created by Administrator on 2018/2/1.
 */

public class GouWuCheFragment extends Fragment {
    private TextView tv_showAll, tv_showNo, tv_showHas;
    private CheckBox cb_3, cb_5, cb_7, cb_10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopcar, null);
        tv_showAll = view.findViewById(R.id.tv_showAll);
        tv_showNo = view.findViewById(R.id.tv_showNo);
        tv_showHas = view.findViewById(R.id.tv_showHas);
        cb_3 = view.findViewById(R.id.cb_3);
        cb_5 = view.findViewById(R.id.cb_5);
        cb_7 = view.findViewById(R.id.cb_7);
        cb_10 = view.findViewById(R.id.cb_10);

        tv_showAll.setOnClickListener(onClickListener);
        tv_showNo.setOnClickListener(onClickListener);
        tv_showHas.setOnClickListener(onClickListener);
        tv_showAll.setTextColor(Color.RED);
        tv_showNo.setTextColor(Color.BLACK);
        tv_showHas.setTextColor(Color.BLACK);
        return view;
    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_showAll:
                    tv_showAll.setTextColor(Color.RED);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_showNo:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.RED);
                    tv_showHas.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_showHas:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.RED);
                    break;
                default:
                    break;
            }
        }
    };
}

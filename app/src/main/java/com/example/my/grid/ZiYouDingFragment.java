package com.example.my.grid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ZiYouDingFragment extends Fragment {
    private TextView tv_showAll, tv_showHas, tv_showNo, tv_yiChangChiMa, tv_sort, tv_kuanLiang, tv_shuLiang, tv_price;
    private ImageView iv_paiLieLine, iv_paiLieTian;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ziyouding, null);
        tv_showAll = view.findViewById(R.id.tv_showAll);
        tv_showHas = view.findViewById(R.id.tv_showHas);
        tv_showNo = view.findViewById(R.id.tv_showNo);
        tv_yiChangChiMa = view.findViewById(R.id.tv_yiChangChiMa);
        tv_sort = view.findViewById(R.id.tv_sort);
        tv_kuanLiang = view.findViewById(R.id.tv_kuanLiang);
        tv_shuLiang = view.findViewById(R.id.tv_shuLiang);
        tv_price = view.findViewById(R.id.tv_price);
        iv_paiLieLine = view.findViewById(R.id.iv_paiLieLine);
        iv_paiLieTian = view.findViewById(R.id.iv_paiLieTian);

        tv_showAll.setOnClickListener(onClickListener);
        tv_showHas.setOnClickListener(onClickListener);
        tv_showNo.setOnClickListener(onClickListener);
        tv_yiChangChiMa.setOnClickListener(onClickListener);
        tv_sort.setOnClickListener(onClickListener);
        tv_kuanLiang.setOnClickListener(onClickListener);
        tv_shuLiang.setOnClickListener(onClickListener);
        tv_price.setOnClickListener(onClickListener);
        iv_paiLieLine.setOnClickListener(onClickListener);
        iv_paiLieTian.setOnClickListener(onClickListener);
        tv_showAll.setTextColor(Color.RED);
        tv_showHas.setTextColor(Color.BLACK);
        tv_showNo.setTextColor(Color.BLACK);
        tv_yiChangChiMa.setTextColor(Color.BLACK);
        tv_sort.setTextColor(Color.BLACK);
        addFragment(new ZiYouDingFragmentChild_1());
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
                    tv_yiChangChiMa.setTextColor(Color.BLACK);
                    tv_sort.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_showHas:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.RED);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_yiChangChiMa.setTextColor(Color.BLACK);
                    tv_sort.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_showNo:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.RED);
                    tv_yiChangChiMa.setTextColor(Color.BLACK);
                    tv_sort.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_yiChangChiMa:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_yiChangChiMa.setTextColor(Color.RED);
                    tv_sort.setTextColor(Color.BLACK);
                    break;
                case R.id.tv_sort:
                    tv_showAll.setTextColor(Color.BLACK);
                    tv_showHas.setTextColor(Color.BLACK);
                    tv_showNo.setTextColor(Color.BLACK);
                    tv_yiChangChiMa.setTextColor(Color.BLACK);
                    tv_sort.setTextColor(Color.RED);
                    break;

                case R.id.tv_kuanLiang:

                    break;
                case R.id.tv_shuLiang:

                    break;
                case R.id.tv_price:

                    break;
                case R.id.iv_paiLieLine:
                    replace(new ZiYouDingFragmentChild_1());
                    break;
                case R.id.iv_paiLieTian:
                    replace(new ZiYouDingFragmentChild_2());
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 添加Fragment
     *
     * @param fragment Fragment
     */
    private void addFragment(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(R.id.frameContent, fragment);
            transaction.commit();
        }
    }

    /**
     * 切换Fragment
     *
     * @param fragment Fragment
     */
    private void replace(Fragment fragment) {
        FragmentManager fm = getChildFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.frameContent, fragment);
        transaction.commit();
    }

}

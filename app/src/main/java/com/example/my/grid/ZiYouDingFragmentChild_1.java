package com.example.my.grid;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.interfaces.ShowToastListener;
import com.example.my.interfaces.ToastListener;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ZiYouDingFragmentChild_1 extends Fragment {
    private Button btn_click;
    private View view;
    private ToastListenerUser listenerUser;
    private ShowToastListenerUser showToastListenerUser;
    private int i = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ziyouding_child_1, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_click = view.findViewById(R.id.btn_click);
//        listenerUser = new ToastListenerUser();
//        listenerUser.setToastListener(ZiYouDingFragmentChild_1.this);
        showToastListenerUser = new ShowToastListenerUser();
        showToastListenerUser.setShowToastListener(new ShowToastListener() {
            @Override
            public void show(String str) {
                Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
            }
        });
//        listenerUser.setToastListener(new ToastListener() {
//            @Override
//            public void showToast() {
//                Toast.makeText(getActivity(), "回调成功" + i, Toast.LENGTH_SHORT).show();
//                i++;
//            }
//        });
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showToastListenerUser.showToast("方法被回调" + i);
                i++;
            }
        });


    }

//    @Override
//    public void showToast() {
//        Toast.makeText(getActivity(), "回调成功" + i, Toast.LENGTH_SHORT).show();
//        i++;
//    }
}

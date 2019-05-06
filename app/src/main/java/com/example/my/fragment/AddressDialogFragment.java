package com.example.my.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.adapter.FragmentAddressAdapter;

/**
 * Created by  wsl
 * on 2019/4/23 10:43
 */
public class AddressDialogFragment extends AppCompatDialogFragment {
    private RecyclerView recycle;
    private OnItemClick onItemClick;

    public static AddressDialogFragment newInstance(OnItemClick onItemClick) {
        AddressDialogFragment fragment = new AddressDialogFragment();
        Bundle bundle = new Bundle();
        fragment.onItemClick = onItemClick;
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.dialog_adress, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        recycle = root.findViewById(R.id.recycle);
        recycle.setLayoutManager(new LinearLayoutManager(getContext()));
        final String[] str = {"111", "222", "333", "444"};
        final FragmentAddressAdapter adapter = new FragmentAddressAdapter(getContext(), str);
        recycle.setAdapter(adapter);
        adapter.addOnItemClickListener(new FragmentAddressAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                onItemClick.onItemClick(str[position]);
                Toast.makeText(getContext(), "点击了：" + position, Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.BOTTOM;
        attributes.width = metrics.widthPixels;
        attributes.height = (int) (metrics.heightPixels * 0.6);
        window.setAttributes(attributes);
    }

    public interface OnItemClick {
        void onItemClick(String item);
    }
}

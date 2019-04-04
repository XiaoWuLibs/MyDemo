package com.example.my.grid;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my.R;
import com.example.my.adapter.ShopCarListAdapter;
import com.example.my.db.Books;
import com.example.my.db.ShopCar;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by Administrator on 2018/2/1.
 */

public class Fragment3 extends Fragment {
    private boolean hasChecked = false;
    private ShopCar book;
    private TextView tv_price;
    private ListView lv_shopCar;
    private TextView tv_showResult;
    private PopupWindow popupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, null);
        lv_shopCar = view.findViewById(R.id.lv_shopCar);
        tv_showResult = view.findViewById(R.id.tv_showResult);
        tv_price = view.findViewById(R.id.tv_price);
        downloadData();

        return view;
    }

    private void downloadData() {
        List<ShopCar> shopCars = queryData();
        if (shopCars != null && !shopCars.isEmpty() && shopCars.size() != 0) {
            tv_showResult.setVisibility(View.GONE);
            lv_shopCar.setVisibility(View.VISIBLE);
            ShopCarListAdapter adapter = new ShopCarListAdapter(getActivity(), shopCars
                    , onCheckedChangeListener, onClickListener);
            lv_shopCar.setAdapter(adapter);
            lv_shopCar.setOnItemClickListener(onItemClickListener);
        } else {
            tv_showResult.setVisibility(View.VISIBLE);
            lv_shopCar.setVisibility(View.GONE);
        }
    }

    /**
     * 选择框
     */
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                if (hasChecked) {
                    Toast.makeText(getActivity(), "每次只能选择一个商品", Toast.LENGTH_SHORT).show();
                    buttonView.setChecked(false);
                } else {
                    book = (ShopCar) buttonView.getTag();
                    hasChecked = true;
                    String bookNum = book.getBookNum();
                    String bookPrice = book.getBookPrice();
                    if (!TextUtils.isEmpty(bookNum) && !TextUtils.isEmpty(bookNum)) {
                        float num = Float.parseFloat(bookNum);
                        float price = Float.parseFloat(bookPrice);
                        tv_price.setText(String.format("￥%s", num * price));
                    } else {
                        Toast.makeText(getActivity(), "商品出现问题，请联系维护人员", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                hasChecked = false;
                book = null;
                tv_price.setText("总价钱");
            }
        }
    };
    /**
     * 编辑
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.tv_edit:
                    ShopCar shopCar = (ShopCar) view.getTag();
                    if (shopCar != null) {
                        bottomwindow(view, shopCar);
                    } else {
                        Toast.makeText(getActivity(), "系统错误，请联系维护人员", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 创建popupWindow
     *
     * @param view View
     */
    private void bottomwindow(View view, ShopCar shopCar) {
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        LinearLayout layout = (LinearLayout) getLayoutInflater().inflate(R.layout.pop_add_sub_good_layout, null);
        popupWindow = new PopupWindow(layout,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        //点击空白处时，隐藏掉pop窗口
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //添加弹出、弹入的动画
//        popupWindow.setAnimationStyle(R.style.Popupwindow);
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        popupWindow.showAtLocation(view, Gravity.LEFT | Gravity.BOTTOM, 0, -location[1]);
        //添加按键事件监听
        setButtonListeners(layout, shopCar);
        //添加pop窗口关闭事件，主要是实现关闭时改变背景的透明度
//        popupWindow.setOnDismissListener(new poponDismissListener());
//        backgroundAlpha(1f);
    }

    private void setButtonListeners(LinearLayout layout, final ShopCar shopCar) {
        Button btn_cancel = (Button) layout.findViewById(R.id.btn_cancel);
        Button btn_ok = (Button) layout.findViewById(R.id.btn_ok);
        Button btn_sub = (Button) layout.findViewById(R.id.btn_sub);
        Button btn_add = (Button) layout.findViewById(R.id.btn_add);
        TextView tv_bookName = (TextView) layout.findViewById(R.id.tv_bookName);
        final TextView tv_showNum = (TextView) layout.findViewById(R.id.tv_showNum);
        tv_bookName.setText(shopCar.getBookName());
        tv_showNum.setText(shopCar.getBookNum());

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    popupWindow.dismiss();
                }
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    popupWindow.dismiss();
                    ShopCar shopCar1 = new ShopCar();
                    shopCar1.setBookNum(tv_showNum.getText().toString().trim());
                    shopCar1.update(shopCar.getId());//修改4号id的数据
                    downloadData();
                }
            }
        });
        btn_sub.setOnClickListener(new View.OnClickListener() {//减
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    String showNum = tv_showNum.getText().toString().trim();
                    if (!TextUtils.isEmpty(showNum)) {
                        int num = Integer.parseInt(showNum);
                        if (num >= 2) {
                            num = num - 1;
                        } else {
                            Toast.makeText(getActivity(), "至少选择一本", Toast.LENGTH_SHORT).show();
                        }
                        tv_showNum.setText(String.valueOf(num));
                    } else {
                        showNum = "1";
                        tv_showNum.setText(showNum);
                    }
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {//加
            @Override
            public void onClick(View view) {
                if (popupWindow != null && popupWindow.isShowing()) {
                    //在此处添加你的按键处理 xxx
                    String showNum = tv_showNum.getText().toString().trim();
                    if (!TextUtils.isEmpty(showNum)) {
                        int num = Integer.parseInt(showNum);
                        num = num + 1;
                        tv_showNum.setText(String.valueOf(num));
                    } else {
                        showNum = "1";
                        tv_showNum.setText(showNum);
                    }
                }
            }
        });
    }

    /**
     * 查询购物车数据
     *
     * @return List<ShopCar>
     */
    private List<ShopCar> queryData() {
        return DataSupport.findAll(ShopCar.class);
    }

    /**
     * listView中Item点击事件
     */
    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ShopCar shopCar = (ShopCar) parent.getItemAtPosition(position);
            ShopBookDetailActivity.startActivity(getActivity(), shopCar);
        }
    };

}

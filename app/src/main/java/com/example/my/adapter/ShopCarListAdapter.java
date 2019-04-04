package com.example.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.db.Books;
import com.example.my.db.ShopCar;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 * 购物车适配器
 */

public class ShopCarListAdapter extends BaseAdapter {
    private Context context;
    private List<ShopCar> list;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private View.OnClickListener onClickListener;

    public ShopCarListAdapter(Context context, List<ShopCar> shopCars
            , CompoundButton.OnCheckedChangeListener checkedChangeListener
            , View.OnClickListener clickListener) {
        super();
        this.context = context;
        this.list = shopCars;
        this.onCheckedChangeListener = checkedChangeListener;
        this.onClickListener = clickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ShopCar book = list.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_content_shop_car_list, null);
            viewHolder = new ViewHolder();

            viewHolder.name = convertView.findViewById(R.id.tv_name);
            viewHolder.price = convertView.findViewById(R.id.tv_price);
            viewHolder.description = convertView.findViewById(R.id.tv_description);
            viewHolder.bookNum = convertView.findViewById(R.id.tv_goodNum);
            viewHolder.edit = convertView.findViewById(R.id.tv_edit);
            viewHolder.checkBox = convertView.findViewById(R.id.checkbox);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(book.getBookName());
        viewHolder.price.setText(String.format("￥%s", book.getBookPrice()));
        viewHolder.description.setText(String.format("#%s#%s", book.getBookType(), book.getBookDetail()));
        viewHolder.bookNum.setText(String.format("X %s", book.getBookNum()));
        viewHolder.edit.setTag(book);
        viewHolder.edit.setOnClickListener(onClickListener);
        viewHolder.checkBox.setTag(book);
        viewHolder.checkBox.setOnCheckedChangeListener(onCheckedChangeListener);
        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView description;
        TextView price;
        TextView edit;
        TextView bookNum;
        CheckBox checkBox;
    }
}

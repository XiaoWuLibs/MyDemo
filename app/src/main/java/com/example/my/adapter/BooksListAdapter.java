package com.example.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.db.Address;
import com.example.my.db.Books;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 * 图书列表适配器
 */

public class BooksListAdapter extends BaseAdapter {
    private Context context;
    private List<Books> list;
    private View.OnClickListener onClickListener;

    public BooksListAdapter(Context context, List<Books> list, View.OnClickListener clickListener) {
        super();
        this.context = context;
        this.list = list;
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
        Books book = list.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_content_book_list, null);
            viewHolder = new ViewHolder();

            viewHolder.name = convertView.findViewById(R.id.tv_name);
            viewHolder.price = convertView.findViewById(R.id.tv_price);
            viewHolder.description = convertView.findViewById(R.id.tv_description);
            viewHolder.iv_shopCar = convertView.findViewById(R.id.iv_shopCar);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(book.getBookName());
        viewHolder.price.setText(String.format("￥%s", book.getBookPrice()));
        viewHolder.description.setText(String.format("#%s#%s", book.getBookType(), book.getBookDecreption()));
        viewHolder.iv_shopCar.setTag(book);
        viewHolder.iv_shopCar.setOnClickListener(onClickListener);

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView description;
        TextView price;
        ImageView iv_shopCar;
    }
}

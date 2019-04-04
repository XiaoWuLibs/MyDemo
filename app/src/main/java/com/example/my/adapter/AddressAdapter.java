package com.example.my.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.db.Address;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/11.
 * 地址簿列表适配器
 */

public class AddressAdapter extends BaseAdapter {
    private Context context;
    private List<Address> list;

    public AddressAdapter(Context context, List<Address> list) {
        super();
        this.context = context;
        this.list = list;
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
        Address address = list.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_content_address, null);
            viewHolder = new ViewHolder();

            viewHolder.name = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.tv_tel);
            viewHolder.address = (TextView) convertView.findViewById(R.id.tv_address);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.name.setText(address.getName());
        viewHolder.tel.setText(address.getTel());
        viewHolder.address.setText(String.format("%s%s", address.getCity(), address.getDetailAddress()));

        return convertView;
    }

    static class ViewHolder {
        TextView name;
        TextView tel;
        TextView address;
    }
}

package com.example.my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/11.
 */

public class TimelineAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> list;
    private LayoutInflater inflater;

    public TimelineAdapter(Context context, List<Map<String, Object>> list) {
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
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.content_time_line, null);
            viewHolder = new ViewHolder();

//            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
//            viewHolder.view1 = convertView.findViewById(R.id.view_1);
//            viewHolder.view2 = convertView.findViewById(R.id.view_2);
//            viewHolder.image_point = convertView.findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

//        String titleStr = list.get(position).get("title").toString();
//        if (position == 0) {
//            viewHolder.view1.setVisibility(View.INVISIBLE);
//        } else {
//            viewHolder.view1.setVisibility(View.VISIBLE);
//        }
//        if (position == list.size() - 1) {
//            viewHolder.view2.setVisibility(View.INVISIBLE);
//            viewHolder.image_point.setImageResource(R.drawable.timeline_green);
//        } else {
//            viewHolder.view2.setVisibility(View.VISIBLE);
//            viewHolder.image_point.setImageResource(R.drawable.timeline_gray);
//        }
//
//
//        viewHolder.title.setText(titleStr);

        return convertView;
    }

    static class ViewHolder {
        public TextView year;
        public TextView month;
        public TextView title;
        private View view1;
        private View view2;
        private ImageView image_point;
    }
}

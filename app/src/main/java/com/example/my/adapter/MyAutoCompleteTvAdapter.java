//package com.example.my.adapter;
//
//import android.content.Context;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.TextView;
//
//import com.example.my.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class MyAutoCompleteTvAdapter extends BaseAdapter implements Filterable {
//
//    private Context context;
//    //该list存放的是最终弹出列表的数据
//    private List<String> list = new ArrayList<>();
//
//    private MyAutoCompleteTvAdapter(Context context, List<String> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            /**
//             * 在后台线程执行，定义过滤算法
//             * @param constraint ：就是你在输入框输入的字符串
//             * @return 符合条件的数据结果，会在下面的publishResults方法中将数据传给list
//             */
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//
//                FilterResults results = new FilterResults();
//
//                if (constraint == null || constraint.length() == 0) {
//                    //
//                    results.values = mSearchDataBaseList;
//                    results.count = mSearchDataBaseList.size();
//                } else {
//
//                    //这个newList是实际搜索出的结果集合，实际上是将该newList的数据赋给了list
//                    List<String> newList = new ArrayList<>();
//                    for (String s : mSearchDataBaseList) {
//                        //包含就添加到newList中
//                        if (s.contains(constraint.toString().trim()
//                        )) {
//                            newList.add(s);
//                        }
//                    }
//
//                    //将newList传给results
//                    results.values = newList;
//                    results.count = newList.size();
//                    newList = null;
//                }
//
//                return results;
//            }
//
//            /**
//             * 本方法在UI线程执行，用于更新自动完成列表
//             * @param constraint
//             * @param results
//             */
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                if (results != null && results.count > 0) {//有符合过滤规则的数据
//                    list.clear();
//                    list.addAll((List<String>) results.values);
//                    notifyDataSetChanged();
//                } else {//没有符合过滤规则的数据
//                    notifyDataSetInvalidated();
//                }
//            }
//
//            /**
//             * 将符合条件的数据转换为你想要的方式，一般无需实现
//             * 控制用户点击提示时要填充至输入框的文本内容。
//             */
//            @Override
//            public CharSequence convertResultToString(Object resultValue) {
//
//                return super.convertResultToString(resultValue);
//                //假如这里写 return "啊哈哈";
//                //那么，无论你点击哪个条目，出现在输入框的永远是"啊哈哈"这几个字。
//            }
//        };
//
//        return filter;
//    }
//
//    @Override
//    public int getCount() {
//        return list != null && list.size() > 0 ? list.size() : 0;
//    }
//
//    /**
//     * 这里必须返回list.get(position)，否则点击条目后输入框显示的是position，而非该position的数据
//     *
//     * @param position
//     * @return
//     */
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//
//        TvViewHolder holder;
//        if (convertView == null) {
//            convertView = View.inflate(context, R.layout.item_autocompletetv_simple_dropdown, null);
//            holder = new TvViewHolder();
//            holder.tv = (TextView) convertView.findViewById(R.id.text1);
//            convertView.setTag(holder);
//
//        } else {
//            holder = (TvViewHolder) convertView.getTag();
//        }
//
//        //注意这里不要为convertView添加点击事件，默认是点击后：①下拉窗收起；
//        //②点击的条目数据会显示在搜索框中；③光标定位到字符串末位。
//        //如果自己添加点击事件，就要首先实现上面的①、②、③。
//        holder.tv.setText(list.get(position));
//        return convertView;
//    }
//
//    class TvViewHolder {
//        TextView tv;
//    }
//}

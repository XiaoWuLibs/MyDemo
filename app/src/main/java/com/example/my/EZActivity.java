package com.example.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.adapter.GuideAdapter;
import com.example.my.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by  wsl
 * on 2019/6/18 12:52
 */
public class EZActivity extends BaseActivity implements View.OnClickListener {
    private MyHandler myHandler;
    private ViewPager viewpager;
    private Button btnSubmit;
    private ImageView ivPoint1, ivPoint2, ivPoint3, ivPoint4;
    private ImageView ivAnimain;
    private List<ImageView> guidePointList;
    private AutoCompleteTextView ac_text;
    private String[] hintArray = {"第一", "第一次", "第一次写代码", "第一次领工资", "第二", "第二个"};

    //这个一旦赋值就不会改变了，相当数据库的概念，是搜索的数据源，也就是说展示的list数据是根据定义
    //的过滤规则，从mSearchDataBaseList中取出符合过滤规则的数据。
    //这个mSearchDataBaseList一般是从后台获取。
    private List<String> mSearchDataBaseList = new ArrayList<>();

    //这个是搜索的结果集合，随搜索内容而改变
    private List<String> mSearchResultList = new ArrayList<>();//

    private MyAutoCompleteTvAdapter mAutoCompleteTvAdapter;
    private Animation animation01 = null;

    /**
     * 启动activity
     *
     * @param context 上下文
     */
    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, EZActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //设置是否显示标题栏
        setShowTitle(true);
        //设置是否显示状态栏
        setShowStatusBar(true);
        //是否允许屏幕旋转
        setAllowScreenRoate(true);
        //以上设置一定要在 super.onCreate(savedInstanceState) 方法之前设置
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int initLayout() {
        //初始化布局
        return R.layout.activity_ez_layout;
    }

    @Override
    protected void initView() {
        guidePointList = new ArrayList<>();
        viewpager = findViewById(R.id.viewpager);
        btnSubmit = findViewById(R.id.btnSubmit);
        ivPoint1 = findViewById(R.id.ivPoint1);
        ivPoint2 = findViewById(R.id.ivPoint2);
        ivPoint3 = findViewById(R.id.ivPoint3);
        ivPoint4 = findViewById(R.id.ivPoint4);
        ivAnimain = findViewById(R.id.ivAnimain);

        addGuidePointToList();
        animation01 = AnimationUtils.loadAnimation(context, R.anim.animation_guide_alpha_0_1);


    }

    /**
     * 添加引导点 到 list
     */
    private void addGuidePointToList() {
        guidePointList.add(ivPoint1);
        guidePointList.add(ivPoint2);
        guidePointList.add(ivPoint3);
        guidePointList.add(ivPoint4);
    }

    @Override
    protected void initData() {
        final List<Integer> list = new ArrayList<>();
        list.add(R.drawable.book_1);
        list.add(R.drawable.book_2);
        list.add(R.drawable.book_3);
        list.add(R.drawable.book_4);
        final GuideAdapter guideAdapter = new GuideAdapter(context, list);
        viewpager.setAdapter(guideAdapter);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == list.size() - 1) {
                    btnSubmit.setVisibility(View.VISIBLE);
                    if (animation01 != null) {
                        btnSubmit.setAnimation(animation01);
                        animation01.start();
                    }
                } else {
                    btnSubmit.setVisibility(View.GONE);
                }

                setGuidePoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//        viewpager.setPageTransformer(false, new DepthPageTransformer());

        ac_text = findViewById(R.id.ac_text);
        mSearchDataBaseList = Arrays.asList(hintArray);
//        for (String str : hintArray) {
//            mSearchResultList.add(str);
//        }
        mAutoCompleteTvAdapter = new MyAutoCompleteTvAdapter(getApplicationContext(), mSearchResultList);
        ac_text.setAdapter(mAutoCompleteTvAdapter);

    }

    /**
     * 设置引导点  的状态 （选中、未选中）
     *
     * @param position 当前滑动到的位置
     */
    private void setGuidePoint(int position) {
        for (int i = 0; i < guidePointList.size(); i++) {
            if (position == i) {
                guidePointList.get(i).setImageResource(R.drawable.shape_guide_point_big);
            } else {
                guidePointList.get(i).setImageResource(R.drawable.shape_guide_point_small);
            }
        }
    }

    /**
     * 点击事件
     */
    public OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            switch (view.getId()) {
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {

    }

    class MyAutoCompleteTvAdapter extends BaseAdapter implements Filterable {

        private Context context;
        //该list存放的是最终弹出列表的数据
        private List<String> list = new ArrayList<>();

        private MyAutoCompleteTvAdapter(Context context, List<String> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public Filter getFilter() {
            Filter filter = new Filter() {
                /**
                 * 在后台线程执行，定义过滤算法
                 * @param constraint ：就是你在输入框输入的字符串
                 * @return 符合条件的数据结果，会在下面的publishResults方法中将数据传给list
                 */
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();

                    if (constraint == null || constraint.length() == 0) {
                        //
                        results.values = mSearchDataBaseList;
                        results.count = mSearchDataBaseList.size();
                    } else {

                        //这个newList是实际搜索出的结果集合，实际上是将该newList的数据赋给了list
                        List<String> newList = new ArrayList<>();
                        for (String s : mSearchDataBaseList) {
                            // if (s.contains(String.valueOf(constraint.charAt(i))))  =true,则cyc+1
                            int cyc = 0;
                            //包含就添加到newList中
                            int strLength = constraint.length();
                            Log.i("StrLength", strLength + "");
                            for (int i = 0; i < strLength; i++) {
                                if (s.contains(String.valueOf(constraint.charAt(i)))) {
                                    cyc++;
                                }
                            }
//                            if (s.contains(constraint.toString().trim())) {
//                                newList.add(s);
//                            }
                            if (cyc == strLength) {
                                newList.add(s);
                            }
                        }

                        //将newList传给results
                        results.values = newList;
                        results.count = newList.size();
                        newList = null;
                    }

                    return results;
                }

                /**
                 * 本方法在UI线程执行，用于更新自动完成列表
                 * @param constraint
                 * @param results
                 */
                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {

                    if (results != null && results.count > 0) {//有符合过滤规则的数据
                        list.clear();
                        list.addAll((List<String>) results.values);
                        notifyDataSetChanged();
                    } else {//没有符合过滤规则的数据
                        notifyDataSetInvalidated();
                    }
                }

                /**
                 * 将符合条件的数据转换为你想要的方式，一般无需实现
                 * 控制用户点击提示时要填充至输入框的文本内容。
                 */
                @Override
                public CharSequence convertResultToString(Object resultValue) {

                    return super.convertResultToString(resultValue);
                    //假如这里写 return "啊哈哈";
                    //那么，无论你点击哪个条目，出现在输入框的永远是"啊哈哈"这几个字。
                }
            };

            return filter;
        }

        @Override
        public int getCount() {
            return list != null && list.size() > 0 ? list.size() : 0;
        }

        /**
         * 这里必须返回list.get(position)，否则点击条目后输入框显示的是position，而非该position的数据
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            TvViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(context, R.layout.item_autocompletetv_simple_dropdown, null);
                holder = new TvViewHolder();
                holder.tv = (TextView) convertView.findViewById(R.id.text1);
                convertView.setTag(holder);

            } else {
                holder = (TvViewHolder) convertView.getTag();
            }

            //注意这里不要为convertView添加点击事件，默认是点击后：①下拉窗收起；
            //②点击的条目数据会显示在搜索框中；③光标定位到字符串末位。
            //如果自己添加点击事件，就要首先实现上面的①、②、③。
            holder.tv.setText(list.get(position));
            return convertView;
        }

        class TvViewHolder {
            TextView tv;
        }
    }

    public static class MyHandler extends Handler {
        private WeakReference<EZActivity> activity;

        public MyHandler(EZActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EZActivity ezActivity = activity.get();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

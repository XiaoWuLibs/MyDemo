package com.example.my.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.ThirtyFourActivity;
import com.example.my.adapter.ExamPaperNoAnswerFragmentDetailAdapter;
import com.example.my.base.BaseFragment;
import com.example.my.bean.Page;
import com.example.my.interpolator.DecelerateAccelerateDecelerateInterpolator;

import java.lang.ref.WeakReference;
import java.util.List;


/**
 * Created by  wsl
 * on 2019/9/17 09:05
 * 调查问卷 考试界面
 */
public class ExamPaperNoAnswerDetailFragment extends BaseFragment {
    private static final String TAG_PARENT_POSITION = "ParentPosition";
    private TextView tvQueType;
    private TextView tvQue;
    private LinearLayout llPanDuanView;
    private LinearLayout llTitlePic;
    private ImageView ivTitlePic;
    private TextView tvSubmitLeft;
    private TextView tvSubmitRight;
    private RecyclerView recycleAnswer;
    private NestedScrollView scrollView;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ExamPaperNoAnswerFragmentDetailAdapter deyailsAdapter;
    private int position;
    private List<Page.Quesition> questionList = null;
    private MyHandler myHandler;
    private TranslateAnimation animation;
    private int repeat;
    int height;
    private MyReceiver myReceiver;

    public static ExamPaperNoAnswerDetailFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt(TAG_PARENT_POSITION, position);
        ExamPaperNoAnswerDetailFragment fragment = new ExamPaperNoAnswerDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int initLayout() {
        return R.layout.fragment_viewpager_exam_paper_no_answer_layout;
    }

    @Override
    protected void initView(View rootView) {
        myHandler = new MyHandler(this);
        tvQueType = rootView.findViewById(R.id.tvQueType);
        tvQue = rootView.findViewById(R.id.tvQue);
        llPanDuanView = rootView.findViewById(R.id.llPanDuanView);
        llTitlePic = rootView.findViewById(R.id.llTitlePic);
        ivTitlePic = rootView.findViewById(R.id.ivTitlePic);
        tvSubmitLeft = rootView.findViewById(R.id.tvSubmitLeft);
        tvSubmitRight = rootView.findViewById(R.id.tvSubmitRight);
        recycleAnswer = rootView.findViewById(R.id.recycleAnswer);
        scrollView = rootView.findViewById(R.id.scrollView);
        imageView1 = rootView.findViewById(R.id.imageView1);
        imageView2 = rootView.findViewById(R.id.imageView2);
        imageView3 = rootView.findViewById(R.id.imageView3);


        tvSubmitLeft.setOnClickListener(onDoubleClickListener);
        tvSubmitRight.setOnClickListener(onDoubleClickListener);


    }

    @Override
    protected void initData(Context mContext) {
        position = getArguments().getInt(TAG_PARENT_POSITION);
        questionList = ((ThirtyFourActivity) getActivity()).getQuestionList();
        if (null != questionList && !questionList.isEmpty()) {
            String quesCode = questionList.get(position).getQues_Code();
            String quesTitle = questionList.get(position).getQues_Name();
            String ifPicture = questionList.get(position).getIFPicture();
            String titlePic = questionList.get(position).getAddress();
            List<Page.Quesition.Answer> answerList = questionList.get(position).getChle();
            initQues(quesCode, quesTitle, ifPicture, titlePic);
            initRecycler(answerList);
        }


        height = getWindowXY();
        initAnimation(height);

        //滑动到最顶部
        scrollView.scrollTo(0, 0);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.my.fragment.ExamPaperNoAnswerDetailFragment.MyReceiver");
        myReceiver = new MyReceiver();
        context.registerReceiver(myReceiver, intentFilter);

//        postMercWithHeight();

    }

    public void postMercWithHeight() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                int parentMeasuredHeight = scrollView.getChildAt(0).getHeight() + 570;
                Log.i(TAG, "height:" + height + ";parentMeasuredHeight:" + parentMeasuredHeight);
                if (parentMeasuredHeight > height) {
//                    showToast("内容超过屏幕啦 ");
//                    imageView1.setVisibility(View.VISIBLE);
//                    imageView2.setVisibility(View.VISIBLE);
//                    imageView3.setVisibility(View.VISIBLE);
//                    playAnimation();
                    //内容超过屏幕后，发送广播，对用户进行提示
                    Intent intent1 = new Intent("com.example.my.ThirtyFourActivity.ThirtyFourReceiver");
                    context.sendBroadcast(intent1);
                }
            }
        });
    }

    /**
     * 初始化问题类型和题目
     *
     * @param quesCode  问题类型码
     * @param quesTitle 问题题目
     * @param ifPicture 判断是否有图片
     * @param titlePic  图片地址
     */
    private void initQues(String quesCode, String quesTitle, String ifPicture, String titlePic) {
        if (!TextUtils.isEmpty(ifPicture) && ifPicture.contains("1") && !TextUtils.isEmpty(titlePic)) {
            //加载图片
            llTitlePic.setVisibility(View.VISIBLE);
            ivTitlePic.setVisibility(View.VISIBLE);
//            Glide.with(context).load(titlePic).apply(new RequestOptions()).into(ivTitlePic);
        }
        if (!TextUtils.isEmpty(quesCode) && quesCode.contains("10")) {
            tvQueType.setText("判断");
            recycleAnswer.setVisibility(View.GONE);
            llPanDuanView.setVisibility(View.VISIBLE);
        } else if (!TextUtils.isEmpty(quesCode) && quesCode.contains("20")) {
            tvQueType.setText("单选");
            llPanDuanView.setVisibility(View.GONE);
            recycleAnswer.setVisibility(View.VISIBLE);
        } else if (!TextUtils.isEmpty(quesCode) && quesCode.contains("30")) {
            tvQueType.setText("多选");
            llPanDuanView.setVisibility(View.GONE);
            recycleAnswer.setVisibility(View.VISIBLE);
        } else {
            tvQueType.setText("单选");
            llPanDuanView.setVisibility(View.GONE);
            recycleAnswer.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(quesTitle)) {
            tvQue.setText(quesTitle);
        } else {
            tvQue.setText("--");
        }

    }

    /**
     * 初始化列表数据
     */
    private void initRecycler(final List<Page.Quesition.Answer> answerList) {
        recycleAnswer.setLayoutManager(new LinearLayoutManager(context));
        recycleAnswer.setNestedScrollingEnabled(false);
        deyailsAdapter = new ExamPaperNoAnswerFragmentDetailAdapter(context, position, answerList);
        recycleAnswer.setAdapter(deyailsAdapter);

        deyailsAdapter.setOnMyItemClickListener(new ExamPaperNoAnswerFragmentDetailAdapter.OnMyItemClickListener() {
            @Override
            public void onMyItemClick(int quesPosition, int answerPosition, Page.Quesition.Answer item) {
                /**
                 * 刷新答案列表
                 * 10 :判断
                 * 20 ：单选
                 * 30 :多选
                 */
                List<Page.Quesition> questionList2 = ((ThirtyFourActivity) getActivity()).getQuestionList();
                String quesCode = questionList2.get(quesPosition).getQues_Code();
                if (!TextUtils.isEmpty(quesCode) && quesCode.contains("30")) {
                    if (answerList.get(answerPosition).ans_state == 0) {
                        ((ThirtyFourActivity) getActivity()).getQuestionList().get(quesPosition)
                                .getChle().get(answerPosition).ans_state = 1;
                    } else {
                        ((ThirtyFourActivity) getActivity()).getQuestionList().get(quesPosition)
                                .getChle().get(answerPosition).ans_state = 0;
                    }
                } else if (!TextUtils.isEmpty(quesCode) && quesCode.contains("20")) {
                    for (int i = 0; i < answerList.size(); i++) {
                        if (i == answerPosition) {
                            if (answerList.get(answerPosition).ans_state == 0) {
                                ((ThirtyFourActivity) getActivity()).getQuestionList().get(quesPosition)
                                        .getChle().get(answerPosition).ans_state = 1;
                            }
                        } else {
                            ((ThirtyFourActivity) getActivity()).getQuestionList().get(quesPosition)
                                    .getChle().get(i).ans_state = 0;
                        }
                    }
                }


                //更新题目的状态，更新已经答题和未答题标志
                updateQuesState(quesPosition);
                //检查是否全部答题，全部答完，提交按钮变为绿色
                ((ThirtyFourActivity) getActivity()).checkAnswerCompleted();
                //更新页面数据
                deyailsAdapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 更新题目的状态，更新已经答题和未答题标志
     *
     * @param quesPosition 题目位置position
     */
    private void updateQuesState(int quesPosition) {
        List<Page.Quesition.Answer> answers = ((ThirtyFourActivity) getActivity())
                .getQuestionList().get(quesPosition).getChle();
        int quesState = 0;
        for (Page.Quesition.Answer answer : answers) {
            if (answer.ans_state == 1) {
                quesState++;
            }
        }
        if (quesState > 0) {
            ((ThirtyFourActivity) getActivity())
                    .getQuestionList().get(quesPosition).que_state = 1;
        } else {
            ((ThirtyFourActivity) getActivity())
                    .getQuestionList().get(quesPosition).que_state = 0;
        }
    }

    private OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
        }
    };
    private OnMultiClickListener onDoubleClickListener = new OnMultiClickListener() {
        @Override
        public void onMultiClick(View view) {
            switch (view.getId()) {
                //正确
                case R.id.tvSubmitLeft:
                    if (null != questionList && !questionList.isEmpty()) {
                        List<Page.Quesition.Answer> answers = questionList.get(position).getChle();
                        for (int i = 0; i < answers.size(); i++) {
                            if (!TextUtils.isEmpty(answers.get(i).getAnswer_Code())
                                    && answers.get(i).getAnswer_Code().contains("对")) {
                                if (answers.get(i).ans_state == 0) {
                                    ((ThirtyFourActivity) getActivity()).getQuestionList().get(position)
                                            .getChle().get(i).ans_state = 1;
                                }
                            } else {
                                ((ThirtyFourActivity) getActivity()).getQuestionList().get(position)
                                        .getChle().get(i).ans_state = 0;
                            }
                        }
                        tvSubmitLeft.setBackgroundResource(R.drawable.icon_exam_btn_bg_xuanzhong);
                        tvSubmitLeft.setTextColor(getResources().getColor(R.color.white));
                        tvSubmitRight.setBackgroundResource(R.drawable.icon_exam_btn_bg_weixuanzhong);
                        tvSubmitRight.setTextColor(Color.parseColor("#333333"));
                        //更新题目的状态，更新已经答题和未答题标志
                        updateQuesState(position);
                        //检查是否全部答题，全部答完，提交按钮变为绿色
                        ((ThirtyFourActivity) getActivity()).checkAnswerCompleted();
                    }
                    break;
                //错误
                case R.id.tvSubmitRight:
                    if (null != questionList && !questionList.isEmpty()) {
                        List<Page.Quesition.Answer> answers = questionList.get(position).getChle();
                        for (int i = 0; i < answers.size(); i++) {
                            if (!TextUtils.isEmpty(answers.get(i).getAnswer_Code())
                                    && answers.get(i).getAnswer_Code().contains("错")) {
                                if (answers.get(i).ans_state == 0) {
                                    ((ThirtyFourActivity) getActivity()).getQuestionList().get(position)
                                            .getChle().get(i).ans_state = 1;
                                }
                            } else {
                                ((ThirtyFourActivity) getActivity()).getQuestionList().get(position)
                                        .getChle().get(i).ans_state = 0;
                            }
                        }
                        tvSubmitRight.setBackgroundResource(R.drawable.icon_exam_btn_bg_xuanzhong);
                        tvSubmitRight.setTextColor(getResources().getColor(R.color.white));
                        tvSubmitLeft.setBackgroundResource(R.drawable.icon_exam_btn_bg_weixuanzhong);
                        tvSubmitLeft.setTextColor(Color.parseColor("#333333"));
                        //更新题目的状态，更新已经答题和未答题标志
                        updateQuesState(position);
                        //检查是否全部答题，全部答完，提交按钮变为绿色
                        ((ThirtyFourActivity) getActivity()).checkAnswerCompleted();
                    }
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * 获取window尺寸
     *
     * @return 获取屏幕高度
     */
    private int getWindowXY() {
        int height = 0;
        try {
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            height = point.y;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }

    /**
     * `初始化动画控件，并开始播放
     *
     * @param height 屏幕高度
     */
    private void initAnimation(int height) {
        animation = new TranslateAnimation(0, 0, height - 800, height + 10);
        animation.setDuration(2 * 1000);
        animation.setInterpolator(new DecelerateAccelerateDecelerateInterpolator());
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                myHandler.sendEmptyMessage(1000);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /**
     * 播放动画
     */
    private void playAnimation() {
        myHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(1000);
            }
        }, 500);
    }

    public static class MyHandler extends Handler {
        static WeakReference<ExamPaperNoAnswerDetailFragment> weakReference;

        public MyHandler(ExamPaperNoAnswerDetailFragment fragment) {
            weakReference = new WeakReference<>(fragment);
        }

        @Override
        public void handleMessage(Message msg) {
            ExamPaperNoAnswerDetailFragment fragment = weakReference.get();
            fragment.repeat++;
            if (fragment.repeat <= 5) {
                fragment.imageView1.startAnimation(fragment.animation);


                fragment.imageView2.startAnimation(fragment.animation);


                fragment.imageView3.startAnimation(fragment.animation);
            } else {
                fragment.animation.cancel();
                fragment.repeat = 0;
                fragment.imageView1.setVisibility(View.GONE);
                fragment.imageView2.setVisibility(View.GONE);
                fragment.imageView3.setVisibility(View.GONE);
            }
        }
    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int currentPage = intent.getIntExtra("CurrentPage", 0);
                if (currentPage == position) {
                    postMercWithHeight();

                }
            }
        }
    }

    @Override
    public void onDestroy() {
        if (myReceiver != null) {
            context.unregisterReceiver(myReceiver);
        }
        super.onDestroy();
    }
}

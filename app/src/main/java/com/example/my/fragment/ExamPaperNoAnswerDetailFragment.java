package com.example.my.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.ThirtyFourActivity;
import com.example.my.adapter.ExamPaperNoAnswerFragmentDetailAdapter;
import com.example.my.base.BaseFragment;
import com.example.my.bean.Page;

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
    private ExamPaperNoAnswerFragmentDetailAdapter deyailsAdapter;
    private int position;
    private List<Page.Quesition> questionList = null;

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

        tvQueType = rootView.findViewById(R.id.tvQueType);
        tvQue = rootView.findViewById(R.id.tvQue);
        llPanDuanView = rootView.findViewById(R.id.llPanDuanView);
        llTitlePic = rootView.findViewById(R.id.llTitlePic);
        ivTitlePic = rootView.findViewById(R.id.ivTitlePic);
        tvSubmitLeft = rootView.findViewById(R.id.tvSubmitLeft);
        tvSubmitRight = rootView.findViewById(R.id.tvSubmitRight);
        recycleAnswer = rootView.findViewById(R.id.recycleAnswer);
        scrollView = rootView.findViewById(R.id.scrollView);


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
        //滑动到最顶部
        scrollView.scrollTo(0, 0);

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


}

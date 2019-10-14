package com.example.my.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.my.R;


/**
 * 描述：调查问卷 提交后分数展示
 * 创建人：wsl
 * 创建时间：2019/8/2
 */
public class ExamResultDialogFragment extends AppCompatDialogFragment {
    public static final String SCORE = "score";
    public static final String ANSWER = "Answer";
    private View root;
    private TextView tvCancel, tvSubmit, tvScore;
    private int score;
    private String mAnswer;
    static OnCancelDialogListener myOnCancelDialogListener = null;

    public static ExamResultDialogFragment newInstance(int score, String answer) {
        Bundle args = new Bundle();
        args.putInt(SCORE, score);
        args.putString(ANSWER, answer);
        ExamResultDialogFragment fragment = new ExamResultDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.dialog_exam_result_layout, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initIntentData();
        initView();
        initListener();
        return root;
    }

    private void initIntentData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            score = arguments.getInt(SCORE, 0);
            mAnswer = arguments.getString(ANSWER, "");
        }
    }


    private void initView() {
        tvSubmit = root.findViewById(R.id.tvSubmit);
        tvCancel = root.findViewById(R.id.tvCancel);
        tvScore = root.findViewById(R.id.tvScore);
        String scoreStr = "<font> <big> <big> <big>" + score + "</big></big></big></font>分";
        tvScore.setText(Html.fromHtml(scoreStr));
    }

    private void initListener() {
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface OnCancelDialogListener {
        void cancelDialog();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(false);
        Window win = dialog.getWindow();
        win.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.CENTER;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = (int) (dm.widthPixels * 0.8);
//        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = (int) (dm.heightPixels * 0.25);
        win.setAttributes(params);
    }


}
package com.example.my.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.my.R;


/**
 * 描述：调查问卷新手指导页
 * 创建人：wsl
 * 创建时间：2019/8/2
 */
public class ExamUsedGuideDialogFragment extends AppCompatDialogFragment {
    private static final String APP_TAG_FIRST_INTO_PAPER = "AppTagFirstIntoPaper";
    private View root;
    private ImageView image;
    private TextView tvSubmit;

    public static ExamUsedGuideDialogFragment newInstance() {
        Bundle args = new Bundle();
        ExamUsedGuideDialogFragment fragment = new ExamUsedGuideDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.dialog_exam_used_guide_hint, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();
        initListener();
        return root;
    }

    private void initView() {
        image = root.findViewById(R.id.image);
        tvSubmit = root.findViewById(R.id.tvSubmit);
        final Animation animationInToOut = AnimationUtils.loadAnimation(getContext(), R.anim.exam_used_guide_scale_in_to_out);
        final Animation animationOutToIn = AnimationUtils.loadAnimation(getContext(), R.anim.exam_used_guide_scale_out_to_in);
        animationInToOut.setInterpolator(new LinearInterpolator());
        animationOutToIn.setInterpolator(new LinearInterpolator());
        image.startAnimation(animationInToOut);
        animationInToOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationOutToIn.setInterpolator(new LinearInterpolator());
                image.startAnimation(animationOutToIn);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        animationOutToIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationInToOut.setInterpolator(new LinearInterpolator());
                image.startAnimation(animationInToOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    private void initListener() {
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }


    @Override
    public void onStart() {
        super.onStart();
    }


}
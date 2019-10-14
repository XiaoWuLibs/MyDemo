package com.example.my.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.my.R;
import com.example.my.bean.Page;

import java.util.List;


/**
 * Describe
 * Created by hxc on 2017/9/11.
 */

public class ExamPaperNoAnswerFragmentDetailAdapter extends RecyclerView.Adapter<ExamPaperNoAnswerFragmentDetailAdapter.MyViewHolder> {
    private OnMyItemClickListener myItemClickListener;
    private Context context;
    private int parentPosition;
    private List<Page.Quesition.Answer> list;

    public ExamPaperNoAnswerFragmentDetailAdapter(Context context, int parentPosition
            , List<Page.Quesition.Answer> list) {
        this.context = context;
        this.parentPosition = parentPosition;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_exam_paper_no_answer_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Page.Quesition.Answer item = list.get(position);
        holder.tvAnswerContent.setText(item.getAnswer_Code() + ".  " + item.getAnswer_Content());
        if (item.ans_state == 0) {
            holder.ivCheckButton.setImageResource(R.drawable.icon_exam_duigou_weixuan);
            holder.tvAnswerContent.setTextColor(Color.parseColor("#333333"));
        } else {
            holder.ivCheckButton.setImageResource(R.drawable.jx_detail_intro_select);
            holder.tvAnswerContent.setTextColor(Color.parseColor("#35CFAD"));
        }
        holder.llCheckButton.setOnClickListener(null);
        holder.llCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myItemClickListener != null) {
                    myItemClickListener.onMyItemClick(parentPosition, position, item);
                }
            }
        });
        String ifPicture = item.getIFPicture();
        String picPath = item.getAddress();
        if (!TextUtils.isEmpty(ifPicture) && ifPicture.contains("1") && !TextUtils.isEmpty(picPath)) {
            holder.llImage.setVisibility(View.VISIBLE);
//           setImage(context, holder.image, picPath);
        } else {
            holder.llImage.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAnswerContent;
        private LinearLayout llCheckButton;
        private LinearLayout llImage;
        private ImageView ivCheckButton;
        private ImageView image;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvAnswerContent = itemView.findViewById(R.id.tvAnswerContent);
            llCheckButton = itemView.findViewById(R.id.llCheckButton);
            llImage = itemView.findViewById(R.id.llImage);
            ivCheckButton = itemView.findViewById(R.id.ivCheckButton);
            image = itemView.findViewById(R.id.image);
        }
    }
//    @Override
//    protected void onBindData(EasyRVHolder viewHolder, final int position, final Page.Quesition.Answer item) {
//        viewHolder.setText(R.id.tvAnswerContent, item.getAnswer_Code() + ".  " + item.getAnswer_Content());
//        LinearLayout llCheckButton = viewHolder.getView(R.id.llCheckButton);
//        ImageView ivCheckButton = viewHolder.getView(R.id.ivCheckButton);
//        TextView tvAnswerContent = viewHolder.getView(R.id.tvAnswerContent);
//        LinearLayout llImage = viewHolder.getView(R.id.llImage);
//        ImageView image = viewHolder.getView(R.id.image);
//        if (item.ans_state == 0) {
//            ivCheckButton.setImageResource(R.drawable.icon_exam_duigou_weixuan);
//            tvAnswerContent.setTextColor(Color.parseColor("#333333"));
//        } else {
//            ivCheckButton.setImageResource(R.drawable.icon_exam_duigou_zhengque);
//            tvAnswerContent.setTextColor(Color.parseColor("#35CFAD"));
//        }
//        llCheckButton.setOnClickListener(null);
//        llCheckButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (myItemClickListener != null) {
//                    myItemClickListener.onMyItemClick(parentPosition, position, item);
//                }
//            }
//        });
//        String ifPicture = item.getIFPicture();
//        String picPath = item.getAddress();
//        if (!TextUtils.isEmpty(ifPicture) && ifPicture.contains("1") && !TextUtils.isEmpty(picPath)) {
//            llImage.setVisibility(View.VISIBLE);
//            ImageHelper.setImage(context, image, picPath);
//        } else {
//            llImage.setVisibility(View.GONE);
//        }
//    }


    public interface OnMyItemClickListener {
        void onMyItemClick(int quesPosition, int answerPosition, Page.Quesition.Answer item);
    }

    public void setOnMyItemClickListener(OnMyItemClickListener onMyItemClickListener) {
        myItemClickListener = onMyItemClickListener;
    }
}

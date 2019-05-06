package com.example.my.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.my.R;

/**
 * Created by  wsl
 * on 2019/4/23 11:13
 */
public class FragmentAddressAdapter extends RecyclerView.Adapter<FragmentAddressAdapter.MyViewHolder> {
    private String[] dataStr;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public FragmentAddressAdapter(Context context, String[] str) {
        this.context = context;
        this.dataStr = str;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.dialog_fragment_address, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tv_address.setText(dataStr[position]);
        holder.tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataStr == null ? 0 : dataStr.length;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void addOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_address;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_address = itemView.findViewById(R.id.tv_address);
        }
    }
}

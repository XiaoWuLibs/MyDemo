package com.example.my.util;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.my.R;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2018/9/20.
 */

public class MyProgressDialog extends Dialog implements DialogInterface.OnCancelListener {
    private WeakReference<Context> reference = new WeakReference<>(null);
    private volatile static MyProgressDialog dialog;

    /**
     * 构造函数
     *
     * @param context  上下文
     * @param drawable 自定义图形，用于显示loading时的图像；如果使用默认值，请填写null
     */
    public MyProgressDialog(Context context, Drawable drawable) {
        super(context, R.style.MyProgressDialogTheme);
        initView(context, drawable);
    }

    /**
     * 初始化控件
     *
     * @param context  上下文
     * @param drawable 自定义图形，用于显示loading时的图像；如果使用默认值，请填写null
     */
    private void initView(Context context, Drawable drawable) {
        reference = new WeakReference<>(context);

        View view = LayoutInflater.from(context).inflate(R.layout.my_dialog_bg, null);
        if (drawable != null) {
            ProgressBar mProgressBar = view.findViewById(R.id.mProgressBar);
            mProgressBar.setIndeterminateDrawable(drawable);
        }

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(view, layoutParams);

        setOnCancelListener(this);
    }

    /**
     * loading
     *
     * @param context  上下文
     * @param drawable 自定义图形，用于显示loading时的图像；如果使用默认值，请填写null
     */
    public static synchronized void showLoading(Context context, Drawable drawable) {
        showLoading(context, true, drawable);
    }

    /**
     * loading
     *
     * @param context    上下文
     * @param cancelable 点击控件外面，是否会关闭
     * @param drawable   自定义图形，用于显示loading时的图像；如果使用默认值，请填写null
     */
    public static synchronized void showLoading(Context context, boolean cancelable, Drawable drawable) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (context == null || !(context instanceof Activity)) {
            return;
        }
        dialog = new MyProgressDialog(context, drawable);
        dialog.setCancelable(cancelable);
        if (dialog != null && !dialog.isShowing() && !((Activity) context).isFinishing()) {
            dialog.show();
        }
    }

    public static synchronized void stopLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        dialog = null;
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        Context context = reference.get();
        if (context != null) {
            Toast.makeText(context, "cancel", Toast.LENGTH_SHORT).show();
        }
    }
}

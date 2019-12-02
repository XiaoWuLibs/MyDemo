package com.example.my;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.my.base.BaseActivity;
import com.example.my.bean.GetModuleBean;
import com.example.my.bean.LoginBeanForUrl;
import com.example.my.gson.GsonUtils;
import com.example.my.http.HttpService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by  wsl
 * on 2019/11/27 14:27
 */
public class ThirtySixActivity extends BaseActivity {

    private Button btnRequestData, btnRequestDataGet;

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, ThirtySixActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_thirty_six_layout;
    }

    @Override
    protected void initView() {
        btnRequestData = findViewById(R.id.btnRequestData);
        btnRequestData.setOnClickListener(onSingleClickListener);
        btnRequestDataGet = findViewById(R.id.btnRequestDataGet);
        btnRequestDataGet.setOnClickListener(onSingleClickListener);
    }

    @Override
    protected void initData() {

    }

    private OnSingleClickListener onSingleClickListener = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            switch (view.getId()) {
                case R.id.btnRequestData:
                    boolean isAllGranted = checkPermissionAllGranted(new String[]{
                            Manifest.permission.INTERNET
                    });
                    if (isAllGranted) {
                        getData();
                    } else {
                        ActivityCompat.requestPermissions(ThirtySixActivity.this
                                , new String[]{Manifest.permission.INTERNET}, 0);
                    }
                    break;
                case R.id.btnRequestDataGet:
                    getDataForGet();
                    break;
                default:
                    break;
            }
        }
    };
    private OnMultiClickListener onMultiClickListener = new OnMultiClickListener() {
        @Override
        public void onMultiClick(View view) {

        }
    };

    private void getData() {
        showLoading("加载中...");
        try {
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(HttpService.BASE_URL_FOR_SERVICE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            retrofit
                    .create(HttpService.class)
                    .loginForService("15731137350", "31137350")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBeanForUrl>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(LoginBeanForUrl loginBeanForUrl) {
                            cancelLoading();

                            Log.i(TAG, GsonUtils.toJson(loginBeanForUrl));
                            showToast(GsonUtils.toJson(loginBeanForUrl));
                        }

                        @Override
                        public void onError(Throwable e) {
                            cancelLoading();
                            Log.i(TAG, "Error：（" + e.getMessage().toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();

            Log.e(TAG, e.getMessage());
        }
    }

    private void getDataForGet() {
        try {
            showLoading("加载中...");
            Retrofit retrofit = new Retrofit
                    .Builder()
                    .baseUrl(HttpService.GET_MODULE_DATA)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofit.create(HttpService.class)
                    .getModule()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GetModuleBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GetModuleBean getModuleBean) {
                            cancelLoading();
                            showToast(GsonUtils.toJson(getModuleBean));
                        }

                        @Override
                        public void onError(Throwable e) {
                            cancelLoading();
                            showToast(e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查是否有指定的权限
     *
     * @param permissions 权限列表
     * @return 是否有权限
     */
    private boolean checkPermissionAllGranted(String[] permissions) {
        for (String permission : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (getApplicationContext().checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            boolean isAllGrant = true;
            for (int i : grantResults) {
                if (i != PackageManager.PERMISSION_GRANTED) {
                    isAllGrant = false;
                    break;
                }
            }
            if (isAllGrant) {
                showToast("网络权限已开启");
                getData();
            } else {
                showToast("请开启网络权限");
            }
        }
    }
}

package com.example.my.util;

import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2018/7/18.
 */

public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
// 获取当前包名
//        String packageName = context.getPackageName();
// 获取当前进程名
//        String processName = getProcessName(android.os.Process.myPid());
// 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
// 初始化Bugly
//        CrashReport.initCrashReport(context, "4c64453166", true, strategy);
    }
}

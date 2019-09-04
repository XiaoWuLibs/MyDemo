package com.example.my.util;

import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

import org.litepal.LitePalApplication;

/**
 * Created by Administrator on 2018/7/18.
 */

public class MyApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        PrettyFormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(1)         // （可选）要显示的方法行数。 默认2
                .methodOffset(5)        // （可选）隐藏内部方法调用到偏移量。 默认5
                .tag("MyDemoTag")//（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));//根据上面的格式设置logger相应的适配器

        Logger.addLogAdapter(new DiskLogAdapter());

//        CrashHandler.getInstance().init(this);
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

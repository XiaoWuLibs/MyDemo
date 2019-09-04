package com.example.my.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 跟App相关的辅助类
 *
 * @author zhy
 */
public class AppUtils {

    private AppUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");

    }

//    public static void reStartApp() {
//        Intent i = BaseLibApp.context.getPackageManager()
//                .getLaunchIntentForPackage(BaseLibApp.context.getPackageName());
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        BaseLibApp.context.startActivity(i);
//    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;

        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否存在包名的应用
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstall(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        List<String> packageNames = new ArrayList<String>();
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        return packageNames.contains(packageName);
    }

    /**
     * 跳转到其他app
     *
     * @param context
     * @param packageName
     * @param activityname
     */
    public static void jump2OtherApp(Context context, String packageName, String activityname) {
        ComponentName componet = new ComponentName(packageName, activityname);
        Intent intent = new Intent();
        intent.setComponent(componet);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 获取软件版本号
     */
    public static int getLocalVersion(Context ctx) {
        int localVersion = 0;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    public static void installApk(Context context, String file) {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(new File(path)),
//                "application/vnd.android.package-archive");
//        context.startActivity(intent);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //Log.e("tag","包名--"+context.getPackageName());
            Uri contentUri = FileProvider.getUriForFile(context.getApplicationContext(), context.getPackageName() + ".fileProvider", new File(file));

//            Uri contentUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", file);
//            Uri contentUri = FileProvider.getUriForFile(context,"com.crlgc.firecontrol",file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(new File(file)), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    /**
     * 文件存储
     *
     * @param context
     * @param fileName
     * @param data
     */
    public static void saveFile(Context context, String fileName, String data) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            if (!TextUtils.isEmpty(fileName) && !TextUtils.isEmpty(data)) {
                context.deleteFile(fileName);
                //设置文件名称，以及存储方式
                out = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                //创建一个OutputStreamWriter对象，传入BufferedWriter的构造器中
                writer = new BufferedWriter(new OutputStreamWriter(out));
                //向文件中写入数据
                writer.write(data);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件读取
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readFile(Context context, String fileName) {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        if (TextUtils.isEmpty(fileName)) {
            return "";
        }
        try {
            //设置将要打开的存储文件名称
            in = context.openFileInput(fileName);
            //FileInputStream -> InputStreamReader ->BufferedReader
            reader = new BufferedReader(new InputStreamReader(in));
            String line = new String();
            //读取每一行数据，并追加到StringBuilder对象中，直到结束
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    /**
     * 高低位互换
     *
     * @param bytes
     * @return
     */
    public static byte[] exchangeHeight4Low4(byte[] bytes) {
        byte[] result_bytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            // 高四位
            int height = getHeight4(bytes[i]);
            // 低四位
            int low = getLow4(bytes[i]);
            result_bytes[i] = (byte) (height + low);
        }
        return result_bytes;
    }

    public static int getHeight4(byte data) {//获取高四位
        int height;
        height = ((data & 0xf0) >> 4);
        return height;
    }

    public static int getLow4(byte data) {//获取低四位
        int low;
        low = (data & 0x0f) << 4;
        return low;
    }

    /**
     * @param strPart 字符串
     * @return 16进制字符串
     * @throws
     * @Title:string2HexString
     * @Description:字符串转16进制字符串
     */
    public static String string2HexString(String strPart) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < strPart.length(); i++) {
            int ch = (int) strPart.charAt(i);
            String strHex = Integer.toHexString(ch);
            hexString.append(strHex);
        }
        return hexString.toString();
    }

    /**
     * @param src 16进制字符串
     * @return 字节数组
     * @throws
     * @Title:hexString2String
     * @Description:16进制字符串转字符串
     */
    public static String hexString2String(String src) {
        String temp = "";
        for (int i = 0; i < src.length() / 2; i++) {
            temp = temp
                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),
                    16).byteValue();
        }
        return temp;
    }

    /**
     * 字符串转换成为16进制(无需Unicode编码)
     *
     * @param str
     * @return
     */
    public static String str2HexStr(String str) {
        char[] chars = "0123456789abcdef".toCharArray();
        StringBuilder sb = new StringBuilder("");
        byte[] bs = str.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(chars[bit]);
            bit = bs[i] & 0x0f;
            sb.append(chars[bit]);
            // sb.append(' ');
        }
        return sb.toString().trim();
    }

    /**
     * 16进制直接转换成为字符串(无需Unicode解码)
     *
     * @param hexStr
     * @return
     */
    public static String hexStr2Str(String hexStr) {
        String str = "0123456789abcdef";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

//    /**
//     * 加密
//     *
//     * @param str
//     * @returnstr
//     */
//    public static String encryption(String str) {
//        String hexStr = AppUtils.str2HexStr(str);
//        byte[] bytes = ByteUtil.hexStrToBytes(hexStr);
//        byte[] bytes_exchange = exchangeHeight4Low4(bytes);
//        String hexString = ByteUtil.toHexString(bytes_exchange);
//        return hexString;
//    }

//    /**
//     * 解密
//     *
//     * @param hexStr
//     * @returnstr
//     */
//    public static String decode(String hexStr) {
//        byte[] bytes = ByteUtil.hexStrToBytes(hexStr);
//        byte[] bytes_exchange = exchangeHeight4Low4(bytes);
//        String hexString = ByteUtil.toHexString(bytes_exchange);
//        return hexStr2Str(hexString);
//    }


//    /**
//     * 安装apk
//     */
//    public static void installApk2(Context mContext, File file) {
//        // 获取当前sdcard存储路径
////        File apkfile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +"/updateApkFile/"+name);
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        //判断是否是AndroidN以及更高的版本
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//            Uri contentUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileProvider", file);
//            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//        } else {
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//        mContext.startActivity(intent);
//
//    }

    /**
     * @param type 0:年月日时分  1:年月日  2:时分
     * @return 时间字符串
     */
    public static String getTime(int type) {
        SimpleDateFormat formatter = null;
        if (type == 0) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (type == 1) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else if (type == 2) {
            formatter = new SimpleDateFormat("HH:mm");
        } else if (type == 3) {
            formatter = new SimpleDateFormat("MM月dd号");
        }
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String strTime = formatter.format(curDate);
        return strTime;
    }

    /**
     * @param type 0年月日时分秒  1年月日
     * @param year 获取距当前时间相差几年的时间  month date hour minute second同理  0为当前时间
     * @return 时间字符串
     */
    public static String getTime(int type, int year, int month, int date, int hour, int minute, int second) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // 设置为当前时间
        if (year != 0) {
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + year);
        }
        if (month != 0) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + month);
        }
        if (date != 0) {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + date);
        }
        if (hour != 0) {
            calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hour);
        }
        if (minute != 0) {
            calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        }
        if (second != 0) {
            calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        }
        Date d = calendar.getTime();
        String time = "";
        if (type == 0) {
            time = dateFormat.format(d);
        } else {
            time = dateFormat2.format(d);
        }
        return time;
    }


    public static String deleteTimeSecond(String time) {
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);

            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(startDate);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取今天是周几
     */
    public static String StringData() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(mWay)) {
            mWay = "周日";
        } else if ("2".equals(mWay)) {
            mWay = "周一";
        } else if ("3".equals(mWay)) {
            mWay = "周二";
        } else if ("4".equals(mWay)) {
            mWay = "周三";
        } else if ("5".equals(mWay)) {
            mWay = "周四";
        } else if ("6".equals(mWay)) {
            mWay = "周五";
        } else if ("7".equals(mWay)) {
            mWay = "周六";
        }
        return mWay;
    }

    public static void scaleTextSize(Activity activity, float size) {
        Configuration configuration = activity.getResources().getConfiguration();
        configuration.fontScale = size; //0.85 small size, 1 normal size, 1.15 big etc
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        metrics.scaledDensity = configuration.fontScale * metrics.density;
        activity.getBaseContext().getResources().updateConfiguration(configuration, metrics);//更新全局的配置
    }
}

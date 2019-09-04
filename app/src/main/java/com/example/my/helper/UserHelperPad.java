package com.example.my.helper;//package com.crlgc.basestation.helper;
//
//import android.content.Context;
//import android.content.pm.PackageManager;
//
//import com.crlgc.exercisemanageforphone.base.App;
//import com.crlgc.exercisemanageforphone.util.AppUtils;
//
///**
// * 重要 在小app里一定要用有context的方法，否则获取不到内容
// * Created by hxc on 2017/12/7.
// */
//
//public class UserHelperPad {
//    public static String MAIN_APP_PACKAGE_NAME = "com.crlgc.exercisemanageforphone";
//
//    public static String getToken() {
//        return AppUtils.decode(AppUtils.readFile(App.context, "token"));
//    }
//
//    public static String getToken(Context context) {
//
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "token"));
//    }
//
//    public static void setToken(String token) {
//        AppUtils.saveFile(App.context, "token", AppUtils.encryption(token));
//    }
//
//    public static void setToken(Context context, String token) {
//        AppUtils.saveFile(getMainAppContext(context), "token", AppUtils.encryption(token));
//    }
//
//    public static String getSid() {
//        return AppUtils.decode(AppUtils.readFile(App.context, "sid"));
//    }
//
//    public static String getSid(Context context) {
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "sid"));
//    }
//
//    public static void setSid(String sid) {
//        AppUtils.saveFile(App.context, "sid", AppUtils.encryption(sid));
//    }
//
//    public static void setSid(Context context, String sid) {
//        AppUtils.saveFile(getMainAppContext(context), "sid", AppUtils.encryption(sid));
//    }
//
//    public static String getImei() {
//        return AppUtils.decode(AppUtils.readFile(App.context, "imei"));
//    }
//
//    public static String getImei(Context context) {
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "imei"));
//    }
//
//    public static void setImei(String imei) {
//        AppUtils.saveFile(App.context, "imei", AppUtils.encryption(imei));
//    }
//
//    public static void setImei(Context context, String imei) {
//        AppUtils.saveFile(getMainAppContext(context), "imei", AppUtils.encryption(imei));
//    }
//
//    public static String getName() {
//        return AppUtils.decode(AppUtils.readFile(App.context, "name"));
//    }
//
//    public static String getName(Context context) {
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "name"));
//    }
//
//    public static void setName(String name) {
//        AppUtils.saveFile(App.context, "name", AppUtils.encryption(name));
//    }
//
//    public static void setEid(String eid) {
//        AppUtils.saveFile(App.context, "eid", AppUtils.encryption(eid));
//    }
//
//    public static String getEid() {
//        return AppUtils.decode(AppUtils.readFile(App.context, "eid"));
//    }
//
//    public static void setName(Context context, String name) {
//        AppUtils.saveFile(getMainAppContext(context), "name", AppUtils.encryption(name));
//    }
//
//    public static String getPwd() {
//        return AppUtils.decode(AppUtils.readFile(App.context, "pwd"));
//    }
//
//
//    public static String getPwd(Context context) {
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "pwd"));
//    }
//
//    public static void setPwd(String pwd) {
//        AppUtils.saveFile(App.context, "pwd", AppUtils.encryption(pwd));
//    }
//
//    public static void setPwd(Context context, String pwd) {
//        AppUtils.saveFile(getMainAppContext(context), "pwd", AppUtils.encryption(pwd));
//    }
//
//    public static void setDefaultLatitudeLocation(String latitudeLocation) {
//        AppUtils.saveFile(App.context, "latitudeLocation", AppUtils.encryption(latitudeLocation));
//    }
//
//    public static String getDefaultLatitudeLocation(Context context) {
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "latitudeLocation"));
//    }
//
//    public static void setDefaultLongitudeLocation(String longitudeLocation) {
//        AppUtils.saveFile(App.context, "longitudeLocation", AppUtils.encryption(longitudeLocation));
//    }
//
//    public static String getDefaultLongitudeLocation(Context context) {
//        return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "longitudeLocation"));
//    }
//
//
//    public static void setDataRoute(String dataRoute) {
//        AppUtils.saveFile(App.context, "dataRoute", AppUtils.encryption(dataRoute));
//    }
//
//    public static String getDataRoute(Context context) {
//        try {
//            return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "dataRoute"));
//        } catch (Exception e) {
//            return null;
//        }
//
//    }
//
//    public static void setFileRoute(String fileRoute) {
//        AppUtils.saveFile(App.context, "fileRoute", AppUtils.encryption(fileRoute));
//    }
//
//    public static String getFileRoute(Context context) {
//        try {
//            return AppUtils.decode(AppUtils.readFile(getMainAppContext(context), "fileRoute"));
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//
//    private static Context getMainAppContext(Context context) {
//        Context ct = null;
//        try {
//            ct = context.createPackageContext(MAIN_APP_PACKAGE_NAME, Context.CONTEXT_IGNORE_SECURITY);
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//        return ct;
//    }
//
//}

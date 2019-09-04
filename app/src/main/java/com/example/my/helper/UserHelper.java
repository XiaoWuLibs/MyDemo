package com.example.my.helper;//package com.crlgc.basestation.helper;
//
//import android.content.Intent;
//import android.os.Handler;
//import android.os.Looper;
//import android.widget.Toast;
//
//import com.crlgc.exercisemanageforphone.base.App;
//import com.crlgc.exercisemanageforphone.ui.activity.LoginActivity;
//
///**
// * 描述：ExerciseManage
// * 创建人：hxc
// * 创建时间：2018/8/6
// */
//public class UserHelper {
//    public static String getToken() {
//        return UserHelperPad.getToken();
//    }
//
//    public static String getSid() {
//        return UserHelperPad.getSid();
//    }
//
//    public static void setToken(String token) {
//        UserHelperPad.setToken(token);
//    }
//
//    public static void setSid(String sid) {
//        UserHelperPad.setSid(sid);
//    }
//
//
//    public static void setUserName(String userName) {
//        UserHelperPad.setName(userName);
//    }
//
//    public static String getUserName() {
//        return UserHelperPad.getName();
//    }
//
//    public static void setEid(String eid) {
//        UserHelperPad.setEid(eid);
//    }
//
//    public static String getEid() {
//        return UserHelperPad.getEid();
//    }
//
//
//    public static void setPwd(String pwd) {
//        UserHelperPad.setPwd(pwd);
//    }
//
//    public static String getPwd() {
//        return UserHelperPad.getPwd();
//    }
//
//
//    public static void setImei(String imei) {
//        UserHelperPad.setImei(imei);
//    }
//
//    public static String getImei() {
//        return UserHelperPad.getImei();
//    }
//
//
//    public static void setNameAndPwd(String name, String pwd) {
//        setUserName(name);
//        setPwd(pwd);
//    }
//
//    public static void clearNameAndPwd() {
//        setUserName("");
//        setPwd("");
//    }
//
//    public static void clearUserData() {
//        UserHelperPad.setName(App.context, "");
//        UserHelperPad.setPwd(App.context, "");
//        UserHelperPad.setImei(App.context, "");
//        UserHelperPad.setToken(App.context, "");
//        UserHelperPad.setSid(App.context, "");
//    }
//
//    public static void jump2LoginPage() {
//        Toast.makeText(App.context, "该账号已在其他设备登录!", Toast.LENGTH_SHORT).show();
//        ActivityCollector.finishAll();
//        Intent intent = new Intent(App.context, loginClass);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        App.context.startActivity(intent);
//    }
//
//    public static void jump2LoginPageAndClearUserData() {
//        new Handler(Looper.getMainLooper()).post(new Runnable() {
//            @Override
//            public void run() {
//                clearUserData();
//                jump2LoginPage();
//            }
//        });
//
//    }
//
//    private static Class loginClass = LoginActivity.class;
//}

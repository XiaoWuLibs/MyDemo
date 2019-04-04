package com.example.my.util;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2018/7/24.
 * 定义一个与JS对象映射关系的Android类：AndroidToJS
 */

public class AndroidToJS extends Object {
    @JavascriptInterface
    public void hello(String msg) {
        Log.i("----------", msg);
    }
}

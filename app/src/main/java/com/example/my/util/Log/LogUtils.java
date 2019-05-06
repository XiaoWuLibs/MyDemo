package com.example.my.util.Log;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.example.my.util.FileUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by  wsl
 * on 2019/4/30 13:38
 * 如果使用缓存文件，文件保存在主存贮路径下，以项目名称来命名的文件
 */
public class LogUtils {
    /**
     * isWrite:用于开关是否吧日志写入txt文件中</p>
     */
    private static boolean isWrite = false;
    /**
     * isDebug :是用来控制，是否打印日志
     */
    private static final boolean isDeBug = true;
    /**
     * 存放日志文件的所在路径
     */
//    private static final String DIRPATH = AppConfig.LOG_DIRPATH;
    private static final String DIRPATH = "log/";
    /**
     * 存放日志的文本名
     */
//    private static final String LOGNAME = AppConfig.LOG_FILENAME;
    private static final String LOGNAME = "log.log";
    /**
     * 设置时间的格式
     */
    private static final String INFORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * VERBOSE日志形式的标识符
     */
    public static final int VERBOSE = 5;
    /**
     * DEBUG日志形式的标识符
     */
    public static final int DEBUG = 4;
    /**
     * INFO日志形式的标识符
     */
    public static final int INFO = 3;
    /**
     * WARN日志形式的标识符
     */
    public static final int WARN = 2;
    /**
     * ERROR日志形式的标识符
     */
    public static final int ERROR = 1;

    /**
     * 把异常用来输出日志的综合方法
     *
     * @param @param tag 日志标识
     * @param @param throwable 抛出的异常
     * @param @param type 日志类型(VERBOSE,DEBUG,INFO,WARN,ERROR)
     * @param @param write 是否写入缓存文件(true:写入缓存文件；false：不写入缓存文件 )
     * @return void 返回类型
     * @throws
     */
    public static void log(Context context, String tag, Throwable throwable, int type, boolean write) {
        log(context, tag, exToString(throwable), type, write);
    }

    /**
     * 用来输出日志的综合方法（文本内容）
     *
     * @param @param tag 日志标识
     * @param @param msg 要输出的内容
     * @param @param type 日志类型(VERBOSE,DEBUG,INFO,WARN,ERROR)
     * @param @param write 是否写入缓存文件(true:写入缓存文件；false：不写入缓存文件 )
     * @return void 返回类型
     * @throws
     */
    public static void log(Context context, String tag, String msg, int type, boolean write) {
        isWrite = write;
        switch (type) {
            case VERBOSE:
                v(context, tag, msg);// verbose等级
                break;
            case DEBUG:
                d(context, tag, msg);// debug等级
                break;
            case INFO:
                i(context, tag, msg);// info等级
                break;
            case WARN:
                w(context, tag, msg);// warn等级
                break;
            case ERROR:
                e(context, tag, msg);// error等级
                break;
            default:
                break;
        }
    }

    /**
     * verbose等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    private static void v(Context context, String tag, String msg) {
        // 是否开启日志输出
        if (isDeBug) {
            Log.v(tag, msg);
        }
        // 是否将日志写入文件
        if (isWrite) {
            write(context, tag, msg);
        }
    }

    /**
     * debug等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    private static void d(Context context, String tag, String msg) {
        if (isDeBug) {
            Log.d(tag, msg);
        }
        if (isWrite) {
            write(context, tag, msg);
        }
    }

    /**
     * info等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    private static void i(Context context, String tag, String msg) {
        if (isDeBug) {
            Log.i(tag, msg);
        }
        if (isWrite) {
            write(context, tag, msg);
        }
    }

    /**
     * warn等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    private static void w(Context context, String tag, String msg) {
        if (isDeBug) {
            Log.w(tag, msg);
        }
        if (isWrite) {
            write(context, tag, msg);
        }
    }

    /**
     * error等级的日志输出
     *
     * @param tag 标识
     * @param msg 内容
     * @return void 返回类型
     */
    private static void e(Context context, String tag, String msg) {
        if (isDeBug) {
            Log.e(tag, msg);
        }
        if (isWrite) {
            write(context, tag, msg);
        }
    }

    /**
     * verbose等级的日志输出
     *
     * @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    public static void v(String msg) {
        Log.v("", msg);
    }

    /**
     * debug等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void d(String msg) {
        Log.d("", msg);
    }

    /**
     * info等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void i(String msg) {
        Log.i("", msg);
    }

    /**
     * warn等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     * @throws
     */
    public static void w(String msg) {
        Log.w("", msg);
    }

    /**
     * error等级的日志输出
     *
     * @param msg 内容
     * @return void 返回类型
     */
    public static void e(String msg) {
        Log.e("", msg);
    }

    /**
     * 用于把日志内容写入制定的文件
     *
     * @param @param tag 标识
     * @param @param msg 要输出的内容
     * @return void 返回类型
     * @throws
     */
    private static void write(Context context, String tag, String msg) {
        String path = FileUtils.getLocalSavePath_log(context, LOGNAME);
        if (TextUtils.isEmpty(path)) {
            return;
        }
        String log = DateFormat.format(INFORMAT, System.currentTimeMillis())
                + tag
                + "========>>"
                + msg
                + "\r\n=================================分割线=================================\r\n";
        FileUtils.write2File(path, log, true);
    }

    /**
     * 用于把崩溃日志内容写入制定的文件
     *
     * @param ex 异常
     */
    public static void write(Context context, Throwable ex) {
        write(context, "", exToString(ex));
    }

    /**
     * 用于把自定义日志内容写入制定的文件
     *
     * @param content 自定义日志内容
     */
    public static void write(Context context, String content) {
        write(context, "", content);
    }

    /**
     * 把异常信息转化为字符串
     *
     * @param ex 异常信息
     * @return 异常信息字符串
     */
    private static String exToString(Throwable ex) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        printWriter.close();
        String result = writer.toString();
        return result;
    }
}

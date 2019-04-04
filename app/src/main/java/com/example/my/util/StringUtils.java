package com.example.my.util;

public class StringUtils {

    /**
     * 去除字符串中指定的标记
     *
     * @param str 需要处理的字符串
     * @param sign 需要去除的符号
     * @return 去除符号后返回值
     */
    public static String removeAllSign(String str, String sign) {
        return str.replace(sign, "");
    }
}

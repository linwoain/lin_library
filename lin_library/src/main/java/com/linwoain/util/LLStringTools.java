package com.linwoain.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * 字符串相关操作
 *
 * @author linwoain
 * @version 2014年8月25日 下午6:35:07
 */
public class LLStringTools {

    /**
     * 判断给定字符串是否为空
     *
     * @param str 要检测的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }

        return true;
    }


    /**
     * 获取当前时间,以yyyy-MM-dd:hh:mm:ss格式
     *@return 当前时间
     */
    public static String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss", Locale.getDefault());
        return format.format(System.currentTimeMillis());
    }
}

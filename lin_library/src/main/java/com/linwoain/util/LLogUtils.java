package com.linwoain.util;

import android.util.Log;

/**
 * 日志输出工具，从Android中移植过来
 *
 * @author: linwoain
 * @version: 2015年3月25日09:55:06
 */
public class LLogUtils {

    private static final String PRE_STRING = "";
    // "~~\n------------------------------------------------------------------\n";
    private static final String AFTER_STRING = "";
    private static final String TAG = "linwoain";
    /**
     * 设置日志输出是否生效
     */
    private static boolean enable = true;
    /**
     * 设置Log的tag
     */
    private static String tag = null;

    /**
     * 设置是否输出日志,默认允许
     *
     * @param logEnable 是否允许
     */
    public static void setLogEnable(boolean logEnable) {
        LLogUtils.enable = logEnable;
    }

    private LLogUtils() {
    }

    /**
     * 只显示执行位置不显示内容
     */
    public static void log() {
        if (enable) {
            Log.i(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + AFTER_STRING);
        }
    }

    /**
     * 获取当前的打印日志
     *
     * @return 当前tag
     */
    public static String getTag() {
        return tag;
    }

    /**
     * 设置日志选择输出的tag名
     *
     * @param tag 要设置的tag 默认为linwoain
     */
    public static void setTag(String tag) {
        LLogUtils.tag = tag;
    }

    public static void i(String text) {
        if (enable) {
            Log.i(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + text + AFTER_STRING);
        }
    }

    public static void d(String text) {
        if (enable) {
            Log.d(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + text + AFTER_STRING);
        }
    }

    public static void v(String text) {
        if (enable) {
            Log.v(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + text + AFTER_STRING);
        }
    }

    public static void w(String text) {
        if (enable) {
            Log.w(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + text + AFTER_STRING);
        }
    }

    public static void e(String text) {
        if (enable) {
            Log.e(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + text + AFTER_STRING);
        }
    }

    public static void i(Object obj) {
        if (enable) {
            Log.i(null == tag ? TAG : tag, PRE_STRING + getTagfromCaller(getCall()) + "\n" + obj.toString() + AFTER_STRING);
        }
    }

    private static String getTagfromCaller(StackTraceElement call) {
        StringBuilder builder = new StringBuilder();
        builder.append(call.getClassName().substring(call.getClassName().lastIndexOf(".") + 1)).append(".").append(call.getMethodName()).append("中")
                .append(call.getLineNumber()).append("行");
        return builder.toString();
    }

    private static StackTraceElement getCall() {
        return Thread.currentThread().getStackTrace()[4];
    }

}

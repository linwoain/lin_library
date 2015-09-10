/**
 * 知我者为我心忧，不知我者谓我何求！
 * linwoain@outlook.com
 * 作者 linwoain
 * 日期 2014/10/21 14:49
 */
package com.linwoain.library;

import android.content.Context;

/**
 * 上下文的管理类，可以从此处获取一个长期存在的 mContext
 * 供一些工具类使用，调用时就无须使用上下文对象
 * 如：CacheUtil
 *
 * @author linwoain
 * @version 2014/10/21 14:49
 */
public class LApplication {
    private static Context mContext;

    private LApplication() {
    }

    /**
     * 初始化Lin_library服务
     * 需要在系统的入口类中注册或者继承LinActivity
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        if (mContext != null) {
            return;
        }
        if (context == null) {
            throw new RuntimeException("传入的context对象为null");
        }

        mContext = context.getApplicationContext();
    }

    /**
     * 获取当前应用的上下文对象
     *
     * @return 应用的上下文
     */
    public static Context getContext() {
        if (mContext == null) {
            throw new RuntimeException("Lin_library未注册！！！请在程序入口处使用LApplication.init(Context mContext)方法初始化框架！");
        }

        return mContext;
    }




    /**
     * 完全退出当前应用
     */
    public static void killMyself() {

        android.os.Process.killProcess(android.os.Process.myPid());
    }
}

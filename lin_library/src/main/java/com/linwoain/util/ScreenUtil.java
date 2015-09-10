package com.linwoain.util;

import java.lang.reflect.Field;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.linwoain.library.LApplication;

/**
 * 获取屏幕相关参数并设置相关样式，如全屏、沉浸式...
 */
public class ScreenUtil {

    private static int sbar = 0;

    /**
     * 获取屏幕宽度，px
     *
     * @return 屏幕宽度
     */
    public static int getWidth() {
        return LApplication.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕宽度，dx
     *
     * @return 宽度的DP值
     */
    public static int getDPWidth() {
        return px2dp(LApplication.getContext().getResources().getDisplayMetrics().widthPixels);
    }

    /**
     * 获取屏幕高度，px
     *
     * @return 屏幕高度
     */
    public static int getHeight() {
        return LApplication.getContext().getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕高度，dp
     *
     * @return 高度的DP值
     */
    public static int getDPHeight() {
        return px2dp(LApplication.getContext().getResources().getDisplayMetrics().heightPixels);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue dp值
     * @return px
     */
    public static int dp2px(float dpValue) {
        final float scale = LApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue px
     * @return db
     */
    public static int px2dp(float pxValue) {
        final float scale = LApplication.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 设置为4.4的沉浸式状态栏<br>
     * 在1.0.1中废弃，使用{@link ScreenUtil#setchenjin(Activity)} 替代<br>
     * 需在{@link Activity#setContentView(int)}后调用
     *
     * @param activity 当前Activity对象
     * @param root     当前布局文件中的根view，此view背景色应该与状态栏背景色相同
     */
    @Deprecated
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setchenjin(Activity activity, View root) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            // activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            if (root != null) {
                root.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);
            }

        }
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        if (sbar > 0) {
            return sbar;
        }
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            sbar = LApplication.getContext().getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            LLogUtils.e("get status bar height fail");
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 设置当前activity为沉浸式，状态栏颜色与布局文件中根节点的颜色相同 <br>
     * 需在{@link Activity#setContentView(int)}后调用
     *
     * @param act activity
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static void setchenjin(Activity act) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            act.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            View root = ((ViewGroup) act.findViewById(android.R.id.content)).getChildAt(0);
            root.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);

        }
    }

    public static void setChenjinColor(Activity act,int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            act.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            View view = act.findViewById(android.R.id.content);
            View root = ((ViewGroup) view).getChildAt(0);
            root.setPadding(0, ScreenUtil.getStatusBarHeight(), 0, 0);
            view.setBackgroundColor(color);
        }
    }

    /**
     * 设置全屏，需在{@link Activity#setContentView(int)}或
     * {@link Activity#setContentView(View)}前调用
     *
     * @param act 要设置的activity
     */
    public static void setFullScreen(Activity act) {
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);
        act.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    /**
     * 设置为无ActionBar样式
     *
     * @param act 要设置的activity
     */
    public static void setNoActionBar(Activity act) {
        act.requestWindowFeature(Window.FEATURE_NO_TITLE);

    }

    /**
     * 设置全屏，需在{@link Activity#setContentView(int)}或
     * {@link Activity#setContentView(View)}前调用<br>
     *
     * @param context 要设置的activity
     */
    public static void setChenjinOrFullScreen(Activity context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setchenjin(context, null);
        } else {
            setFullScreen(context);
        }
    }

    /**
     * 设置全屏，需在{@link Activity#setContentView(int)}或
     * {@link Activity#setContentView(View)}前调用<br>
     * 在1.0.2中废弃,使用{@link ScreenUtil#setChenjinOrFullScreen(Activity)}代替
     *
     * @param context 要设置的activity
     */
    @Deprecated
    public static void setChenjinOrFullScreen(Activity context, View v) {

        setChenjinOrFullScreen(context);
    }

    private ScreenUtil() {

    }

}
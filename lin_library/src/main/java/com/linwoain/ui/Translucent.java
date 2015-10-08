package com.linwoain.ui;

import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;


/**
 * Created by wuxuejian on 2015/5/8.
 * 实现4.4以上机型的沉浸式
 * use {@link com.linwoain.util.ScreenUtil}
 */
@Deprecated
public class Translucent {


    private final Activity activity;

    public Translucent(Activity activity) {

        this.activity = activity;
    }


    private View viewStatus = null;


    private Translucent setTranslucent(View root) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            LinearLayout layout = new LinearLayout(activity);
            layout.setOrientation(LinearLayout.VERTICAL);
            viewStatus = new View(activity);
            int paddingHeight = getStatusBarHeight();
            if (activity.getActionBar() != null) {
                //此判断用于增加状态栏高度
                TypedValue tv = new TypedValue();
                if (activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
                    paddingHeight += TypedValue.complexToDimensionPixelSize(tv.data, activity.getResources().getDisplayMetrics());
                }
            }

            layout.addView(viewStatus, ViewGroup.LayoutParams.MATCH_PARENT, paddingHeight);

            layout.addView(root, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            activity.setContentView(layout);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//暂不考虑导航栏问题

        }
        return this;
    }
    private boolean isInject=false;

    /**
     * 使用侵入式方式在{@link Activity#setContentView(int)}或{@link Activity#setContentView(View)}方法调用之后
     *
     * @return 当前对象
     */
    public Translucent inject() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT&&!isInject) {
			ViewGroup content = (ViewGroup) activity.findViewById(android.R.id.content);
			View root = content.getChildAt(0);
			content.removeView(root);
			setTranslucent(root);
            isInject = true;
        }
		return this;
    }

    /**
     * use {@link com.linwoain.util.ScreenUtil#setChenjinColor(Activity, int)}
     * @param color  color
     * @return
     */
    @Deprecated
    public Translucent setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			if (viewStatus == null) {
				throw new RuntimeException("use inject() ??");
			}
			viewStatus.setBackgroundColor(color);
		}
		return this;
    }


    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public int getStatusBarHeight() {

        int sbar = -1;
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            sbar = activity.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            Log.e("Translucent", "get status bar height fail");
            e1.printStackTrace();
        }
        return sbar;
    }


}

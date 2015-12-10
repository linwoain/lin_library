package com.linwoain.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.linwoain.library.LApplication;

/**
 * 执行一个只需执行一次的操作
 * Created by linwoain on 2015/12/10.
 */
public class Once {

    private static SharedPreferences sp = LApplication.getContext().getSharedPreferences("once", Context.MODE_PRIVATE);

    public static void execute(int resId, @NonNull OnceCallback callback) {
        execute(LApplication.getContext().getString(resId), callback);
    }

    public static void execute(@NonNull String tag, @NonNull OnceCallback callback) {
        boolean nonOnce = sp.getBoolean(tag, false);
        if (!nonOnce) {
            callback.onOnce();
            sp.edit().putBoolean(tag, true).apply();
        }
    }

    public interface OnceCallback {
        /**
         * 仅在第一次的时候执行
         */
        void onOnce();
    }
}

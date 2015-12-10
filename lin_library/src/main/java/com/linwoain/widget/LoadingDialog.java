package com.linwoain.widget;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.linwoain.library.R;
import com.linwoain.util.LLogUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 正在加载中对话框
 *
 * @author: linwoain
 * @version: 2014年8月26日 下午3:46:05
 */
public class LoadingDialog extends AlertDialog {

    private String text = null;
    private TextView tv;

    public LoadingDialog(Context context) {
        super(context);
    }

    public LoadingDialog(Context context, String text) {
        super(context);
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tips_loading);
        tv = (TextView) findViewById(R.id.tv);
        if (text != null && tv != null) {
            tv.setText(text);
        }

    }

    @Override
    public void show() {
        super.show();
        timer.schedule(task, overtime * 1000);

    }

    private static TimerTask task = new TimerTask() {
        @Override
        public void run() {
            dismissDialog();
            LLogUtils.e("dialog显示超过了" + overtime + "秒,自动关闭，通过setOverTime可设置超时时间");

        }
    };
    private static LoadingDialog dialog;

    public static void showDialog(Activity act, String text) {
        dialog = new LoadingDialog(act, text);
        dialog.show();
    }

    private static Timer timer = new Timer();

    private static int overtime = 10;

    public static void setOverTime(int timeOut) {
        overtime = timeOut;
    }

    public static void dismissDialog() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        timer.cancel();
    }
}

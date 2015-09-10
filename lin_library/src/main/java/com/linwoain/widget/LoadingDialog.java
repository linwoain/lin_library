package com.linwoain.widget;


import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.linwoain.library.R;

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
        setCancelable(false);
    }

    public LoadingDialog(Context context, String text) {
        super(context);
        this.text = text;
        setCancelable(false);
    }

    public void setText(String text) {
        this.text = text;
        if (text != null) {
            tv.setText(text);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tips_loading);
        tv = (TextView) findViewById(R.id.tv);
        if (text != null && tv != null) {
            tv.setText(text);
        }
//        FrameLayout view = (FrameLayout) findViewById(android.R.id.content);
//        FrameLayout.LayoutParams parame= (FrameLayout.LayoutParams) view.getLayoutParams();
//        parame.height= ViewGroup.LayoutParams.WRAP_CONTENT;
//        parame.width= ViewGroup.LayoutParams.WRAP_CONTENT;
//        view.setLayoutParams(parame);

    }

    /**
     * 如果已显示则关闭，否则显示
     *
     * @author: linwoain
     * @version: 2014年8月26日 下午3:32:30
     */
    public void toggle() {
        if (this.isShowing()) {
            this.dismiss();
        } else {
            this.show();
        }
    }
}

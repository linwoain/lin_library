package com.linwoan.library;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.linwoain.library.LApplication;
import com.linwoain.util.Once;
import com.linwoain.util.ScreenUtil;
import com.linwoain.util.ToastUtil;
import com.linwoain.widget.LoadingDialog;
import com.linwoin.library.demo.R;

/**
 * 沉浸式状态栏demo<br>
 * 1.继承LinActivity<br>
 * 3.设置状态栏颜色<br>
 */
public class ChenjinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LApplication.init(this);
        ScreenUtil.setChenjinColor(this, getResources().getColor(R.color.red));
        Once.execute("lalala", new Once.OnceCallback() {
            @Override
            public void onOnce() {
                ToastUtil.show("这个只会展示一次");
            }
        });
    }


    public void click(View v) {
        ScreenUtil.setChenjinColor(this, Color.BLUE);
    }

    public void showDialog(View view) {
        LoadingDialog.showDialog(this,"Haha");

    }
}

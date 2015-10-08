package com.linwoan.library;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.linwoain.library.LApplication;
import com.linwoain.util.ScreenUtil;
import com.linwoin.library.demo.R;

/**
 *沉浸式状态栏demo<br>
 * 1.继承LinActivity<br>
 * 3.设置状态栏颜色<br>
 */
public class ChenjinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        LApplication.init(this);
//        Translucent translucent = new Translucent(this).inject();
//        translucent.setStatusBarColor(getResources().getColor(R.color.red));
        ScreenUtil.setChenjinColor(this, getResources().getColor(R.color.red));
//        ScreenUtil.setchenjin(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void click(View v) {
        ScreenUtil.setChenjinColor(this, Color.BLUE);
    }
}

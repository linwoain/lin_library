package com.linwoan.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.linwoain.ui.LinActivity;
import com.linwoin.library.demo.R;

public class MainActivity extends LinActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void LinListView(View view) {
        startActivity(new Intent(this, PagingActivity.class));
    }
}
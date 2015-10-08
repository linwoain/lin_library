package com.linwoan.library;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linwoain.library.LViewHelper;
import com.linwoain.library.LinAdapter;
import com.linwoain.ui.LinActivity;
import com.linwoain.widget.LinListView;
import com.linwoin.library.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PagingActivity extends LinActivity implements LinListView.OnRefreshListener {

    LinListView listview;
    StringAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paging);
        listview = (LinListView) findViewById(R.id.listview);
        listview.setEveryPageItemCount(20);

        adapter = new StringAdapter(this, getData());
        listview.setAdapter(adapter);
        listview.setOnRefreshListener(this);
    }

    int count=0;
    List<String> getData() {
        if (count>=4) {
            return null;
        }
        count++;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(new Random().nextInt(1000) + "");
        }
        return list;
    }

    Handler h = new Handler();
    @Override
    public void onLoadMore() {

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                listview.addMore(getData());
                listview.stopFreshing();
            }
        }, 2000);
    }

    @Override
    public void onRefresh() {
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter = new StringAdapter(PagingActivity.this, getData());
                listview.setAdapter(adapter);
                listview.stopFreshing();
                count=0;
            }
        }, 500);

    }


    class StringAdapter extends LinAdapter<String> {
        /**
         * LinAdapter通用的构造方法
         *
         * @param context 传入的上下文
         * @param beans   要显示的数据源封装好的列表
         */
        public StringAdapter(Activity context, List<String> beans) {
            super(context, beans);
        }

        @Override
        protected View LgetView(int position, View convertView, ViewGroup parent) {
            TextView view = (TextView) LViewHelper.getView(android.R.layout.simple_list_item_1);
            view.setText(beans.get(position));
            return view;
        }
    }
}

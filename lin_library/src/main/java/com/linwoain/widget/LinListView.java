package com.linwoain.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.linwoain.library.LViewHelper;
import com.linwoain.library.LinAdapter;
import com.linwoain.library.R;
import com.linwoain.util.ToastUtil;

/**
 * Created by wuxuejian on 2015/8/12.
 * 需要v4包支持
 */
public class LinListView extends LinearLayout implements AbsListView.OnScrollListener {

    private int lastVisiableIndex;
    /**
     * 上次增加数据的时间
     */
    private long lastAddTime;


    public LinListView(Context context) {
        super(context);
        init();
    }

    public LinListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LinListView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
    }

    SwipeRefreshLayout refresh;
    ListView listView;
    View footView;

    private void init() {
        if (isInEditMode()) {
            return;
        }
        View.inflate(getContext(), R.layout.custom_listview_fresh, this);

        refresh = (SwipeRefreshLayout) findViewById(R.id.fresh_layout);
        listView = (ListView) findViewById(R.id.paging_list_view);
        footView = LViewHelper.getView(R.layout.loading_view, getContext());
        listView.addFooterView(footView);
        listView.setOnScrollListener(this);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (listener != null) {
                    listener.onRefresh();
                }
            }
        });
    }

    /**
     * 显示刷新动画
     */
    public void setRefreshing() {
        refresh.setRefreshing(true);
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    /**
     * 需要在界面更新代码结束后调用,如：{@link #setAdapter(LinAdapter)}之后
     */
    public void stopFreshing() {
        if (refresh.isRefreshing()) {
            refresh.setRefreshing(false);
        } else {
            if (listView.getAdapter() != null && System.currentTimeMillis() - lastAddTime > 1000) {//如果是正常加载数据无需调用下方的代码，仅加载更多失败时调用
                lastTime = System.currentTimeMillis();
                listView.smoothScrollBy(-footView.getHeight(), 0);
            }
        }
    }

    //上拉刷新失败的时间
    long lastTime = 0;

    private OnRefreshListener listener;

    public void setOnItemClickListener(AbsListView.OnItemClickListener onItemClickListener) {
        listView.setOnItemClickListener(onItemClickListener);

    }

    public void setOnItemClickListener(AdapterView.OnItemLongClickListener longClickListener) {
        listView.setOnItemLongClickListener(longClickListener);
    }

    /**
     * 需要在 {@link #setEveryPageItemCount(int)  }后调用
     *
     * @param adapter
     * @see #setEveryPageItemCount(int)
     */
    public void setAdapter(LinAdapter adapter) {
        if (everyPageItemCount <= 0) {
            throw new RuntimeException("设置adapter之前请使用setEveryPageItemCount方法，指定分页中每页的bean个数");
        }
        if (adapter.getCount() >= everyPageItemCount) {
            setHasMore();
        } else {
            setNotMore();
        }
        listView.setAdapter(adapter);
    }

    private int everyPageItemCount = -1;

    public void setEveryPageItemCount(int everyPageItemCount) {
        this.everyPageItemCount = everyPageItemCount;
    }

    public int getEveryPageItemCount() {
        return everyPageItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {


        if (scrollState == SCROLL_STATE_IDLE && lastVisiableIndex == listView.getAdapter().getCount()) {
            if (!isShowMore) {
                ToastUtil.show("没有更多了");
            } else {
                if (listener != null && System.currentTimeMillis() - lastTime > 1000) {//这里加入一个lastTime做对比是为了防止上拉时断网后listview上移导致重复触发加载更多
                    listener.onLoadMore();
                }
            }

        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastVisiableIndex = firstVisibleItem + visibleItemCount;
    }



    /**
     * 刷新或加载更多时的回调
     */
    public interface OnRefreshListener {
        void onLoadMore();

        void onRefresh();
    }

    //是否还可以显示更多
    boolean isShowMore = true;
    //是否已经增加了listview的底部view
    boolean isAddFooterView = true;

    /**
     * 标志没有更多
     */
    public void setNotMore() {
        if (isAddFooterView) {
            listView.removeFooterView(footView);
        }
        isShowMore = false;
        isAddFooterView = false;
    }


    private void setHasMore() {
        isShowMore = true;
        if (!isAddFooterView) {
            listView.addFooterView(footView);
            isAddFooterView = true;
        }
    }
}

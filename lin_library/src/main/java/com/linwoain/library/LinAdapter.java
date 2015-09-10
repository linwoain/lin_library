/**
 * 知我者为我心忧，不知我者谓我何求！
 * linwoain@outlook.com
 * 作者 linwoain
 * 日期 2014/10/23 9:50
 */
package com.linwoain.library;

import java.util.List;

import android.app.Activity;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 提取的Adapter的公共方法，简化操作
 *
 * @author linwoain
 * @version 2014/10/23 9:50
 */
public abstract class LinAdapter<T> extends BaseAdapter {

    protected List<T> beans;
    protected Activity context;


    /**
     * LinAdapter通用的构造方法
     *
     * @param context 传入的上下文
     * @param beans   要显示的数据源封装好的列表
     */
    public LinAdapter(Activity context, List<T> beans) {
        this.context = context;
        this.beans = beans;

    }


    @Override
    public int getCount() {
        return beans.size();
    }

    @Override
    public Object getItem(int position) {
        return beans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return LgetView(position, convertView, parent);
    }

    /**
     * Adapter中唯一需要实现的方法
     *
     * @param convertView 循环使用的view
     * @param parent      附加的view
     * @param position    调用的条目的位置
     * @return 返回要显示在position位置的View
     */
    protected abstract View LgetView(int position, View convertView, ViewGroup parent);

    /**
     * 添加一些条目到列表中
     *
     * @param lists 新增的条目
     */
    public void addLists(List<T> lists) {
        beans.addAll(lists);
        notifyDataSetChanged();
    }

    /**
     * 清空所有条目
     */
    public void clearAll() {
        beans.clear();
        notifyDataSetChanged();
    }

    /**
     * 清空并重新加载所有新传入的数据
     *
     * @param lists 重新加载lists
     */
    public void refresh(List<T> lists) {
        beans.clear();
        beans.addAll(lists);
        notifyDataSetChanged();

    }

    /**
     * 添加一条新的条目
     *
     * @param item 新增的条目
     */
    public void add(T item) {
        beans.add(item);
        notifyDataSetChanged();
    }

    public static class ViewHolders {

        private ViewHolders() {

        }

        @SuppressWarnings("unchecked")
        public static <K extends View> K get(View view, int id) {
            SparseArray<View> viewholder = (SparseArray<View>) view.getTag();

            if (viewholder == null) {
                viewholder = new SparseArray<View>();
                view.setTag(viewholder);
            }
            View childView = viewholder.get(id);
            if (childView == null) {
                childView = view.findViewById(id);
                viewholder.put(id, childView);
            }

            return (K) childView;

        }
    }
}

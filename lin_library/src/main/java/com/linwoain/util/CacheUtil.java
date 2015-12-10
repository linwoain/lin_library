package com.linwoain.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.linwoain.bean.BaseBean;
import com.linwoain.library.LApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据缓存管理
 *
 * @author linwoain
 * @version 2014年9月24日 上午11:44:52
 */

public class CacheUtil {

    private static boolean showLog = false;


    private static SharedPreferences sp = LApplication.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
    /**
     * 数值类型的默认返回值，int、float、long<br>
     * 默认为-5927
     */
    public static final int DEFAULT_INT = -5927;


    /**
     * 获取一个值,若未找到，则返回空字符串
     *
     * @param key 要获取的值对应的键
     * @return 要获取的值
     */

    public static String getString(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为null");
        }

        return sp.getString(key, "");
    }

    /**
     * 保存一个键值
     *
     * @param key   要保存的键
     * @param value 要保存的值
     */

    public static void save(String key, Object value) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        if (value instanceof String) {
            sp.edit().putString(key, (String) value).apply();
        } else if (value instanceof Boolean) {
            sp.edit().putBoolean(key, (Boolean) value).apply();
        } else if (value instanceof Integer) {
            sp.edit().putInt(key, (Integer) value).apply();
        } else if (value instanceof Float) {
            sp.edit().putFloat(key, (Float) value).apply();
        } else if (value instanceof Long) {
            sp.edit().putLong(key, (Long) value).apply();
        } else if (value instanceof BaseBean) {
            sp.edit().putString(key, ((BaseBean) value).toBase64String()).apply();
        } else if (value instanceof ArrayList) {
            saveListString(key, (ArrayList<String>) value);
        }
        if (showLog) {
            LLogUtils.d("保存了一个键值对" + key + "=" + value + "--- 类型是：" + value.getClass().getSimpleName());
        }
    }


    /**
     * 是否每次保存sp时显示日志
     *
     * @param show 是否显示
     */
    public static void setEnableLog(boolean show) {
        CacheUtil.showLog = show;
    }

    /**
     * 删除列表中的一个值,若此list不存在，返回true
     *
     * @param key         key
     * @param tobeRemoved 要删除的值
     * @return 是否移除成功
     */
    public static boolean removeItemFromListString(String key, String tobeRemoved) {
        List<String> lists = getStringList(key);
        if (lists == null) {
            //若没有这个这个list，返回true
            return true;
        }
        if (lists.contains(tobeRemoved)) {
            //若此list中有这个值
            boolean isRemoved = lists.remove(tobeRemoved);
            saveListString(key, lists);
            return isRemoved;
        } else {
            return true;
        }

    }


    /**
     * 向ListString中添加新项
     *
     * @param key       键
     * @param tobeAdded 值
     */
    public static void addItemToListString(String key, String tobeAdded) {

        List<String> list = getStringList(key);
        if (list == null) {
            list = new ArrayList<String>();
        }
        list.add(tobeAdded);
        saveListString(key, list);
    }


    /**
     * 无此键值对则返回defInt， defInt可修改，默认为-9527
     *
     * @param key 要获取的值得键
     * @return 值 若不存在则返回-9527
     */
    public static int getInt(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getInt(key, DEFAULT_INT);
    }


    public static List<String> getStringList(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        Set<String> set = sp.getStringSet(key, null);
        List<String> lists = new ArrayList<String>();
        if (set != null) {
            lists.addAll(set);
        }
        return lists;
    }

    public static void saveListString(String key, List<String> lists) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }

        Set<String> set = new HashSet<String>();
        set.addAll(lists);
        sp.edit().putStringSet(key, set).apply();
    }

    /**
     * 返回一个Long，无此键值对则返回defInt， defInt可修改，默认为-9527
     *
     * @param key 要获取的值得键
     * @return 值 若不存在则返回-9527
     */
    public static long getLong(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getLong(key, DEFAULT_INT);
    }

    /**
     * 返回一个float，无此键值对则返回 DEFAULT_INT
     *
     * @param key 要获取的值得键
     * @return 值 若不存在则返回-9527
     */
    public static float getFloat(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getFloat(key, DEFAULT_INT);
    }

    /**
     * 若找不到此键值对将直接返回false
     *
     * @param key 要获取的值得键
     * @return 值 若不存在则返回false
     */
    public static boolean getBoolean(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        return sp.getBoolean(key, false);
    }

    /**
     * 清除所有缓存
     */
    public static void clearAll() {
        sp.edit().clear().apply();
    }

    public static BaseBean getBaseBean(String key) {
        if (key == null) {
            throw new RuntimeException("key 不能为null");
        }
        String string = sp.getString(key, null);
        if (LLStringTools.isEmpty(string)) {
            return null;
        }
        return BaseBean.fromBase64(string);
    }
}

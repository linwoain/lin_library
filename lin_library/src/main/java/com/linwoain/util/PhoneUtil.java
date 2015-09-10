/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/22 16:01
 */
package com.linwoain.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.linwoain.library.LApplication;

/**
 * 设备相关的工具类
 *
 * @author linwoain
 * @version 2014/10/22 16:01
 */
public class PhoneUtil {
    public static TelephonyManager manager;
    static {
        manager = (TelephonyManager) LApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取手机号码
     * <br>需要权限android.permission.READ_PHONE_STATE
     * @return 手机号码
     */
    public static  String getPhoneNumber() {
        return manager.getLine1Number();
    }

    /**
     * 获取设备id号码
     * <br>需要权限android.permission.READ_PHONE_STATE
     * @return deviceId
     */
    public static  String getDeviceId() {
        return manager.getDeviceId();
    }

    /**
     * 获取SIM卡串号
     * <br>需要权限android.permission.READ_PHONE_STATE
     * @return SIM卡串号
     */
    public static  String getSimInfo() {

        return manager.getSimSerialNumber();
    }

    /**
     * 获取当前手机卡运营商名
     * <br>需要权限android.permission.READ_PHONE_STATE
     * @return 运营商名称 中国移动、中国移动、中国电信
     */
    public String getProvidersName() {
        String ProvidersName = null;
        // 返回唯一的用户ID;就是这张卡的编号神马的
        String IMSI = manager.getSubscriberId();
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        System.out.println(IMSI);
        if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
            ProvidersName = "中国移动";
        } else if (IMSI.startsWith("46001")) {
            ProvidersName = "中国联通";
        } else if (IMSI.startsWith("46003")) {
            ProvidersName = "中国电信";
        }
        return ProvidersName;
    }

    /**
     * 获取IMSI号码
     * <br>需要权限android.permission.READ_PHONE_STATE
     * @return IMSI号码
     */
    public static  String getImsi() {

        return manager.getSubscriberId();
    }

    /**
     * 获取手机UA信息
     * @return UA字符串
     */
    public static String getUserAgent() {
        WebView w = new WebView(LApplication.getContext());
        WebSettings st = w.getSettings();
        return st.getUserAgentString();
    }

    /**
     * 根据号码从联系人数据库中查询姓名，适用于android设备
     *
     * @param number 电话号码
     * @return 姓名
     */

    public static String getNameByNumber(String number) {

        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);

        ContentResolver resolver = LApplication.getContext().getContentResolver();

        Cursor cursor = resolver.query(uri, new String[]{"display_name"}, null, null, null);

        if (cursor.moveToFirst()) {

            String name = cursor.getString(0);

            cursor.close();

            return name;

        } else {

            cursor.close();

            return null;

        }

    }
}

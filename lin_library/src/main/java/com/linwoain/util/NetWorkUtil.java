/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/22 16:24
 */
package com.linwoain.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.linwoain.library.LApplication;
import com.linwoain.library.NetWorkStatus;

/**
 * 网络状态相关
 *
 * @author linwoain
 * @version 2014/10/22 16:24
 */
public class NetWorkUtil {

	private static ConnectivityManager manager;

	static {
		manager = (ConnectivityManager) LApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	/**
	 * 获取当前网络状态 <br>
	 *
	 * @return 各种状态值
	 */
	public static NetWorkStatus getWorkStatus() {

		NetworkInfo info = manager.getActiveNetworkInfo();
		if (null == info) {
			return NetWorkStatus.ALL_CLOSED;
		}
		switch (info.getType()) {

		case ConnectivityManager.TYPE_WIFI:
			return NetWorkStatus.WIFI_CONNECTED;

		case ConnectivityManager.TYPE_MOBILE:
			int subType = info.getSubtype();
			if (subType == TelephonyManager.NETWORK_TYPE_CDMA || subType == TelephonyManager.NETWORK_TYPE_GPRS || subType == TelephonyManager.NETWORK_TYPE_EDGE) {
				return NetWorkStatus.G2_CONNECTED;
			} else if (subType==TelephonyManager.NETWORK_TYPE_HSPAP||subType == TelephonyManager.NETWORK_TYPE_UMTS || subType == TelephonyManager.NETWORK_TYPE_HSDPA || subType == TelephonyManager.NETWORK_TYPE_EVDO_A || subType == TelephonyManager.NETWORK_TYPE_EVDO_0 || subType == TelephonyManager.NETWORK_TYPE_EVDO_B) {
				return NetWorkStatus.G3_CONNECTED;
			} else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {// LTE是3g到4g的过渡，是3.9G的全球标准
				return NetWorkStatus.G4_CONNECTED;
			}

			break;
			
			
			case ConnectivityManager.TYPE_BLUETOOTH:
				return NetWorkStatus.BLUETOOTH_CONNECTED;
		}
		return NetWorkStatus.ALL_CLOSED;
	}

	/**
	 * 获取wifi状态下ip地址 <br>
	 * 需要权限 android.permission.ACCESS_NETWORK_STATE<br>
	 * 与android.permission.ACCESS_WIFI_STATE <br>
	 *
	 * @return 若当前工作在非wifi环境下则返回null
	 */
	public static String getIP() {

		if (getWorkStatus() == NetWorkStatus.WIFI_CONNECTED) {
			WifiManager manager = (WifiManager) LApplication.getContext().getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = manager.getConnectionInfo();
			int ipAddr = info.getIpAddress();
			StringBuffer ipBuf = new StringBuffer();
			ipBuf.append(ipAddr & 0xff).append('.').append((ipAddr >>>= 8) & 0xff).append('.').append((ipAddr >>>= 8) & 0xff).append('.').append((ipAddr >>>= 8) & 0xff);
			return new String(ipBuf);
		} else {
			return null;
		}

	}

	/**
	 * 网络是否可用 权限勿忘
	 * @return 是否可用
	 */
	public static boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) LApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (NetworkInfo anInfo : info) {
					if (anInfo.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
}

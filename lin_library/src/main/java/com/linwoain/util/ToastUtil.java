/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/22 9:38
 */
package com.linwoain.util;

import android.widget.Toast;

import com.linwoain.library.LApplication;

/**
 * 展示Toast
 * 
 * @author linwoain
 * @version 2014/10/22 9:38
 */
public class ToastUtil {

	/**
	 * 展示Toast，默认时间为短时间
	 * 
	 * @param msg
	 *            要展示的内容
	 */
	public static void show(String msg) {
		Toast.makeText(LApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 展示Toast，默认时间为短时间
	 * 
	 * @param strId
	 *            要展示的内容的资源id
	 */
	public static void show(int strId) {
		Toast.makeText(LApplication.getContext(), strId, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 展示Toast，时间为长
	 * 
	 * @param msg
	 *            要展示的内容
	 */
	public static void showLong(String msg) {
		Toast.makeText(LApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * 展示Toast，时间为长
	 * 
	 * @param strId
	 *            要展示的内容的资源id
	 */
	public static void showLong(int strId) {
		Toast.makeText(LApplication.getContext(), strId, Toast.LENGTH_SHORT).show();
	}

}

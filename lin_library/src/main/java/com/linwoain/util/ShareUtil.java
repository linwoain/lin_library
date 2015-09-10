/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/31 18:45
 */
package com.linwoain.util;

import android.app.Activity;
import android.content.Intent;

/**
 * 调用系统内置的分享功能
 * 
 * @author linwoain
 * @version 2014/10/31 18:45
 */
public class ShareUtil {
	private ShareUtil() {
	}

	/**
	 * 调用系统内置的分享功能分享文字
	 * 
	 * @author linwoain
	 * @version 2014年11月15日 上午9:00:27
	 * @param text
	 *            要分享的内容
	 * @param context
	 *            Activity对象
	 */
	public static void shareText(String text, Activity context) {

		Intent share = new Intent();
		share.setAction(Intent.ACTION_SEND);
		share.setType("text/*");
		share.putExtra(Intent.EXTRA_SUBJECT, "分享");
		share.putExtra(Intent.EXTRA_TEXT, text);
		share.addCategory("android.intent.category.DEFAULT");
		share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(Intent.createChooser(share, "分享"));
	}
}

/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/29 11:43
 */
package com.linwoain.util;

/**
 * 操作数字的工具类
 * 
 * @author linwoain
 * @version 2014/10/29 11:43
 */
public class NumberUtil {


	private NumberUtil() {
	}



	/**
	 * 将字节数转换为MB,KB,GB格式显示
	 * @param bytes 字节数
	 * @return 转换后的值
	 */
	public static String byte2Simple(float bytes) {
		String string;
		if (bytes < 1024) {
			string = bytes + "B";
		} else if (bytes < 1024 * 1024) {

			string = (int) bytes / 1024 + "KB";
		} else if (bytes < 1024 * 1024 * 1024) {
			string = String.format("%.2f", bytes / 1024 / 1024) + "MB";
		} else {
			string = String.format("%.2f", bytes / 1024 / 1024 / 1024) + "GB";
		}
		return string;
	}
}

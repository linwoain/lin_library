/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2015/1/23 18:34
 */
package com.linwoain.util;

import android.animation.ValueAnimator;

/**
 * 获取一个不断变化的数
 * 
 * @author linwoain
 * @version 2015/1/23 18:34
 */
public class ValueUtil {
	/**
	 * 获取一个不断变化的数
	 * 
	 * @param duration
	 *            在多长的时间内变化
	 * @param callback
	 *            回调
	 * @param values
	 *            变化范围
	 */
	public static void ofInt(int duration, final ValueChangeCallback callback,
			int... values) {

		ValueAnimator animator = ValueAnimator.ofInt(values);
		animator.setDuration(duration);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				if (callback != null) {
					callback.onChange(Integer.valueOf(animation
							.getAnimatedValue().toString()));
				}
			}
		});
		animator.start();
	}

	public interface ValueChangeCallback {
		public void onChange(int value);
	}
}

package com.shaw.randomwallpaper.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.shaw.randomwallpaper.base.App;

/**
 * Created by shaw on 2017/8/31.
 */

public class DimenUtil {

	public static int getScreenWidth() {
		return getDisplayMetrics().widthPixels;
	}

	public static int getScreenHeight() {
		return getDisplayMetrics().heightPixels;
	}

	/**
	 * dp转px
	 *
	 * @param dp
	 * @return
	 */
	public static int dip2px(int dp) {
		float density = getDisplayMetrics().density;
		return (int) (dp * density + 0.5);
	}

	/**
	 * px转换dip
	 */
	public static int px2dip(int px) {
		final float scale = getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	/**
	 * px转换sp
	 */
	public static int px2sp(int pxValue) {
		final float fontScale = getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * sp转换px
	 */
	public static int sp2px(int spValue) {
		final float fontScale = getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	public static DisplayMetrics getDisplayMetrics() {
		final Resources resources = App.getApplication().getResources();
		return resources.getDisplayMetrics();
	}
}

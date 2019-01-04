package com.shaw.randomwallpaper.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.shaw.randomwallpaper.R;

/**
 * Created on 2018/12/19.
 *
 * @author XCZ
 */
public class PopupUtil {
	public static void showPopup(Context context, View parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.popup_pic, null, false);

		final int popupWidth = DimenUtil.getScreenWidth() - 100;
		final int popupHeight = DimenUtil.dip2px(82);
		final PopupWindow window = new PopupWindow(view, popupWidth, popupHeight, true);
		window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		window.setOutsideTouchable(true);
		window.setTouchable(true);
		window.showAtLocation(parent, Gravity.CENTER, 0, 0);
	}
}

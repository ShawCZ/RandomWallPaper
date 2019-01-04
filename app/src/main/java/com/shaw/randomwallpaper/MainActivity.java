package com.shaw.randomwallpaper;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Set up the toolbar.
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		ActionBar ab = getSupportActionBar();
//		ab.setHomeAsUpIndicator(R.drawable.ic_menu);
		ab.setDisplayShowTitleEnabled(false);
		initToolbar();
	}

	private void initToolbar() {
		//设置 paddingTop
		ViewGroup rootView = getWindow().getDecorView().findViewById(android.R.id.content);
		rootView.setPadding(0, getStatusBarHeight(), 0, 0);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			//5.0 以上直接设置状态栏颜色
			getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimary));
		} else {
			//根布局添加占位状态栏
			ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
			View statusBarView = new View(this);
			ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight());
			statusBarView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
			decorView.addView(statusBarView, lp);
		}
	}

	/**
	 * 利用反射获取状态栏高度
	 *
	 * @return
	 */
	public int getStatusBarHeight() {
		int result = 0;
		//获取状态栏高度的资源id
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}
}

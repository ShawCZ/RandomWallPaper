package com.shaw.randomwallpaper.details;

import android.os.Bundle;

import com.shaw.randomwallpaper.R;
import com.shaw.randomwallpaper.base.PresenterActivity;

public class DetailActivity extends PresenterActivity<DetailContract.Presenter> implements DetailContract.View {

	@Override
	protected DetailContract.Presenter initPresenter() {
		return new DetailPresenter(this);
	}

	@Override
	protected int setLayout() {
		return R.layout.activity_detail;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
	}

	private void initView() {
	}
}

package com.shaw.randomwallpaper.picture;


import android.util.Log;

import com.shaw.randomwallpaper.base.BasePresenter;
import com.shaw.randomwallpaper.model.DataRepository;
import com.shaw.randomwallpaper.model.converter.PublicDataConverter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2018/12/17.
 *
 * @author XCZ
 */
public class PicturePresenter extends BasePresenter<PictureContract.View> implements PictureContract.Presenter {
	public PicturePresenter(PictureContract.View view) {
		super(view);
	}

	@Override
	public void getWallPapers() {
		DISPOSABLES.add(DataRepository.getWallPapers()
				.map(s -> PublicDataConverter.with(s).convert())
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(s -> {
					Log.d(TAG, "accept: result = " + s.size());
					getView().updateUI(s);
				}, throwable -> Log.d(TAG, "accept: throwable = " + throwable.toString())));
	}
}

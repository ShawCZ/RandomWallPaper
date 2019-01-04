package com.shaw.randomwallpaper.details;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.bumptech.glide.request.FutureTarget;
import com.shaw.randomwallpaper.base.App;
import com.shaw.randomwallpaper.base.BasePresenter;
import com.shaw.randomwallpaper.model.DataRepository;
import com.shaw.randomwallpaper.model.converter.PublicDataConverter;
import com.shaw.randomwallpaper.picture.PictureActivity;
import com.shaw.randomwallpaper.picture.PictureContract;
import com.shaw.randomwallpaper.util.FilesUtil;
import com.shaw.randomwallpaper.util.WallpaperUtil;
import com.shaw.randomwallpaper.util.glide.GlideApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2018/12/17.
 *
 * @author XCZ
 */
public class DetailPresenter extends BasePresenter<DetailContract.View> implements DetailContract.Presenter {
	public DetailPresenter(DetailContract.View view) {
		super(view);
	}

	@Override
	public void setWallPaper(String url, Context context) {
		DISPOSABLES.add(downloadPic(url, context)
				.doOnNext(file -> WallpaperUtil.setWallPaper(new FileInputStream(file)))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						result -> Log.d(TAG, "accept: result = " + result),
						throwable -> Log.d(TAG, "accept: throwable = " + throwable.toString())
				));
	}

	@Override
	public void downloadWallPaper(String url, Context context) {
		DISPOSABLES.add(downloadPic(url, context)
				.doOnNext(this::savePic)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe(
						result -> Log.d(TAG, "accept: result = " + result),
						throwable -> Log.d(TAG, "accept: throwable = " + throwable.toString())
				));

	}

	private Flowable<File> downloadPic(String url, Context context) {
		return Flowable.just(url)
				.map(urlPic -> {
					FutureTarget<File> target = GlideApp.with(context).asFile().load(urlPic).submit();
					return target.get();
				});
	}

	private void savePic(File resource) {
		//首先保存图片
		File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
		String fileName = System.currentTimeMillis() + ".jpg";
		File destFile = new File(pictureFolder, fileName);

		try {
			FilesUtil.writeToDisk(new FileInputStream(resource), pictureFolder.getName(), fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// 最后通知图库更新
		App.getApplication().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
				Uri.fromFile(new File(destFile.getPath()))));
	}
}

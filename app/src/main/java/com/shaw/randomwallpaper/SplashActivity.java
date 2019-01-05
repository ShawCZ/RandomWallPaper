package com.shaw.randomwallpaper;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.shaw.randomwallpaper.util.DimenUtil;
import com.shaw.randomwallpaper.util.PaletteUtil;
import com.shaw.randomwallpaper.util.glide.GlideApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
	private static final String TAG = "SplashActivity";

	//设置图片加载策略
	public static final RequestOptions OPTIONS =
			new RequestOptions()
					.centerCrop()
					.skipMemoryCache(true)
					.diskCacheStrategy(DiskCacheStrategy.NONE)
					.dontAnimate();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_fullscreen);
		AppCompatImageView imageView = findViewById(R.id.img);


		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

		int width = DimenUtil.getScreenWidth()
				- ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).leftMargin
				- ((ViewGroup.MarginLayoutParams) imageView.getLayoutParams()).rightMargin;
		int height = width * 3 / 2;
		ViewGroup.LayoutParams para = imageView.getLayoutParams();
		if (para != null) {
			para.height = height;
			imageView.setLayoutParams(para);
		}

		GlideApp.with(this)
				.load(R.drawable.banner1)
				.apply(OPTIONS)
				.into(imageView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		Disposable disposable = Observable.just(0)
				.observeOn(Schedulers.io())
				.map(integer -> {
					FutureTarget<Bitmap> target = GlideApp.with(SplashActivity.this)
							.asBitmap()
							.load(R.drawable.banner1)
							.submit();
					return target.get();
				})
				.flatMap((Function<Bitmap, ObservableSource<Integer>>) PaletteUtil::getRGB)
				.subscribe(this::updateBg,
						throwable -> Log.d(TAG, "accept: throwable = " + throwable.toString()));
	}

	private void updateBg(int rgb) {
		findViewById(R.id.parent).setBackgroundColor(rgb);
		if (Build.VERSION.SDK_INT > 21) {
			Window window = getWindow();
			//状态栏改变颜色。
			int color = PaletteUtil.convertRGB(rgb);
			window.setStatusBarColor(color);
		}
	}
}

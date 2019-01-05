package com.shaw.randomwallpaper.util;

import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.palette.graphics.Palette;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created on 2019/1/5.
 *
 * @author XCZ
 */
public class PaletteUtil {
	public static Observable<Integer> getRGB(Bitmap source) {
		return Observable.just(source)
				.map(new Function<Bitmap, Integer>() {
					@Override
					public Integer apply(Bitmap bitmap) throws Exception {
						Palette palette = Palette.from(source).generate();
						Palette.Swatch vibrant = palette.getVibrantSwatch();
						if (vibrant == null) {
							for (Palette.Swatch swatch : palette.getSwatches()) {
								vibrant = swatch;
								break;
							}
						}
						// 这样获取的颜色可以进行改变。
						return vibrant.getRgb();
					}
				})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public static int convertRGB(int rgb) {
		int red = rgb >> 16 & 0xFF;
		int green = rgb >> 8 & 0xFF;
		int blue = rgb & 0xFF;
		red = (int) Math.floor(red * (1 - 0.2));
		green = (int) Math.floor(green * (1 - 0.2));
		blue = (int) Math.floor(blue * (1 - 0.2));
		return Color.rgb(red, green, blue);
	}
}

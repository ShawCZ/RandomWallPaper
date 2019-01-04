package com.shaw.randomwallpaper.util;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.shaw.randomwallpaper.base.App;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created on 2018/12/19.
 *
 * @author XCZ
 */
public class WallpaperUtil {
	public static boolean setWallPaper(InputStream wallPaperInputStream) {
		WallpaperManager manager = WallpaperManager.getInstance(App.getApplication());
		try {
			manager.setStream(wallPaperInputStream);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean setWallPaperBitmap(Bitmap wallPaperBitmap) {
		WallpaperManager manager = WallpaperManager.getInstance(App.getApplication());
		try {
			manager.setBitmap(wallPaperBitmap);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}

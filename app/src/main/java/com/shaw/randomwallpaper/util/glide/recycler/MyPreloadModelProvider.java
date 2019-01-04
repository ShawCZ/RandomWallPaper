package com.shaw.randomwallpaper.util.glide.recycler;

import android.app.Activity;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.RequestBuilder;
import com.shaw.randomwallpaper.model.bean.PublicBean;
import com.shaw.randomwallpaper.util.glide.GlideApp;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created on 2018/12/26.
 *
 * @author XCZ
 */
public class MyPreloadModelProvider implements ListPreloader.PreloadModelProvider<String> {
	private final Activity mActivity;
	private final List<PublicBean> mUrls;

	public MyPreloadModelProvider(Activity activity, List<PublicBean> urls) {
		mActivity = activity;
		mUrls = urls;
	}

	@NonNull
	@Override
	public List<String> getPreloadItems(int position) {
		String url = mUrls.get(position).getUrl();
		if (TextUtils.isEmpty(url)) {
			return Collections.emptyList();
		}
		return Collections.singletonList(url);
	}

	@Nullable
	@Override
	public RequestBuilder getPreloadRequestBuilder(@NonNull String url) {
		return GlideApp.with(mActivity)
				.load(url);
	}
}

package com.shaw.randomwallpaper.model;

import com.shaw.randomwallpaper.util.net.ApiHost;
import com.shaw.randomwallpaper.util.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Flowable;

/**
 * Created on 2018/12/25.
 *
 * @author XCZ
 */
public class DataRepository {

	public static Flowable<String> getWallPapers() {
		return get(ApiHost.WALLPAPERS);
	}

	public static Flowable<String> get(String url) {
		return get(url, Params.builder().build());
	}

	public static Flowable<String> get(String url, WeakHashMap<String, Object> params) {
		return RxRestClient.builder()
				.url(url)
				.params("client_id", ApiHost.ACCESS_KEY)
				.params(params)
				.build()
				.get();
	}
}

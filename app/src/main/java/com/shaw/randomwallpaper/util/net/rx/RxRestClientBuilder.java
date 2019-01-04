package com.shaw.randomwallpaper.util.net.rx;


import com.shaw.randomwallpaper.util.net.RestCreator;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;

/**
 *
 * @author xcz
 * @date 2017/8/31
 */

public class RxRestClientBuilder {
	private String mUrl = null;
	private static final Map<String, Object> PARAMS = new WeakHashMap<>();
	private RequestBody mBody = null;

	RxRestClientBuilder() {
	}

	public final RxRestClientBuilder url(String url) {
		this.mUrl = url;
		return this;
	}

	public final RxRestClientBuilder body(RequestBody body) {
		this.mBody = body;
		return this;
	}


	public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
		PARAMS.putAll(params);
		return this;
	}

	public final RxRestClientBuilder params(String key, Object value) {
		PARAMS.put(key, value);
		return this;
	}


	public final RxRestClient build() {
		return new RxRestClient(mUrl, PARAMS, mBody);
	}

}

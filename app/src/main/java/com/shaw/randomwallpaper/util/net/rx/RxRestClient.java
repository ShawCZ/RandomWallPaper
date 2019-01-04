package com.shaw.randomwallpaper.util.net.rx;


import com.shaw.randomwallpaper.model.bean.PublicBean;
import com.shaw.randomwallpaper.util.net.HttpMethod;
import com.shaw.randomwallpaper.util.net.RestCreator;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * @author shaw
 * @date 2017/8/31
 * 使用建造者模式
 */

public class RxRestClient {
	private final String URL;
	private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
	private final RequestBody BODY;


	public RxRestClient(String url, Map<String, Object> params, RequestBody body) {
		this.URL = url;
		PARAMS.putAll(params);
		this.BODY = body;
	}

	public static RxRestClientBuilder builder() {
		return new RxRestClientBuilder();
	}

	private Flowable<String> request(HttpMethod method) {
		final RxRestService service = RestCreator.getRxRestService();
		Flowable<String> observable = null;

		switch (method) {
			case GET:
				observable = service.get(URL, PARAMS);
				break;
			case POST:
				observable = service.post(URL, PARAMS);
				break;
			case POST_RAW:
				observable = service.postRaw(URL, BODY);
				break;
			default:
				break;
		}

		return observable;
	}

	public final Flowable<String> get() {
		return request(HttpMethod.GET);
	}

	public final Flowable<ResponseBody> getPic() {
		final RxRestService service = RestCreator.getRxRestService();
		return service.getPic();

	}

	public final Flowable<String> post() {
		if (BODY == null) {
			return request(HttpMethod.POST);
		} else {
			if (!PARAMS.isEmpty()) {
//				throw new RuntimeException("params must be null");
			}
			return request(HttpMethod.POST_RAW);
		}
	}
}
